package com.mty.jpsite.security.core.face;

import com.mty.jpsite.security.core.enums.ValidateCodeType;
import com.mty.jpsite.security.core.domain.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码存取器
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     *
     * @param request
     * @param code
     * @param validateCodeType 校验码类型
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     *
     * @param request
     * @param validateCodeType 校验码类型
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     *
     * @param request
     * @param validateCodeType 校验码类型
     */
    void remove(ServletWebRequest request, ValidateCodeType validateCodeType);

}
