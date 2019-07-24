package com.mty.jpsite.security.browser.handler;

import com.mty.jpsite.security.core.enums.ValidateCodeType;
import com.mty.jpsite.security.core.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 校验码管理处理器
 *
 * @author haha
 */
@Component
public class ValidateCodeProcessorHandler {
    @Autowired
    private Map<String, ValidateCodeProcess> validateCodeProcessors;

    /**
     * @param type 验证码类型
     * @return
     */
    public ValidateCodeProcess findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString());
    }

    /**
     * 根据验证码类型不同，从validateCodeProcessors Map调用不同的ValidateCodeProcessor 接口实现
     *
     * @param type 验证码类型
     * @return
     */
    public ValidateCodeProcess findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcess.class.getSimpleName();
        ValidateCodeProcess processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
