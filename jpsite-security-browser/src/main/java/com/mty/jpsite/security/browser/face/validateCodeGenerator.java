package com.mty.jpsite.security.browser.face;

import com.mty.jpsite.security.core.domain.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器接口
 * Created by jiangpeng on 2018/11/9.
 */
public interface validateCodeGenerator {
    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    ValidateCode generator(ServletWebRequest request) throws Exception;
}
