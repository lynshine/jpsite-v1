package com.mty.jpsite.security.browser.face.impl;

import com.mty.jpsite.security.core.enums.ValidateCodeType;
import com.mty.jpsite.security.core.exception.ValidateCodeException;
import com.mty.jpsite.security.browser.domain.ValidateCode;
import com.mty.jpsite.security.browser.face.ValidateCodeRepository;
import com.mty.jpsite.security.browser.face.validateCodeGenerator;
import com.mty.jpsite.security.browser.handler.ValidateCodeProcess;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * 抽象的图片验证码处理器
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcess {
    /**
     * 收集系统中所有的 {@link validateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, validateCodeGenerator> validateCodeGeneratorMap;
    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 保存验证码到session
     *
     * @param request
     * @param validateCode
     */
    protected void save(ServletWebRequest request, C validateCode) {
        validateCodeRepository.save(request, validateCode, getValidateCodeType(request));
    }

    /**
     * 获取对应validateCodeGenerator实现类生成验证码
     *
     * @param request
     * @return
     * @throws Exception
     */
    private C generate(ServletWebRequest request) throws Exception {
        String type = getProcessorType(request);
        validateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(type + "CodeGenerator");
        return (C) validateCodeGenerator.generator(request);
    }

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }

    /**
     * 发送验证码抽象方法，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        // 截取当前具体类的前缀SimpleName， 抽象类的实现类
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcess");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @Override
    public void validate(ServletWebRequest request) {

        ValidateCodeType codeType = getValidateCodeType(request);
        //获取session中的验证码
        C codeInSession = (C) validateCodeRepository.get(request, codeType);

        String codeInRequest;
        try {
            // 获取request携带的验证码
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(codeType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException(codeType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码不匹配");
        }
        // 成功则删除session validateCode
        validateCodeRepository.remove(request, codeType);
    }
}
