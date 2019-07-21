package com.mty.jpsite.security.core.enums;

import com.mty.jpsite.security.core.properties.SecurityConstants;

/**
 * 验证码类型
 * @author haha
 */
public enum ValidateCodeType {
    /**
     * 短信验证码 smsCode
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码 imageCode
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从httpRequest请求中获取参数的名字
     */
    public abstract String getParamNameOnValidate();

}
