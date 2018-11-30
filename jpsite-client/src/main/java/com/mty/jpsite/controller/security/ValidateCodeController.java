package com.mty.jpsite.controller.security;

import com.mty.jpsite.security.browser.face.validateCodeGenerator;
import com.mty.jpsite.security.browser.handler.ValidateCodeProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by jiangpeng on 2018/11/9.
 */
@RestController
public class ValidateCodeController {
    @Autowired
    private Map<String, ValidateCodeProcess> validateCodeProcessors;

    /**
     * 创建验证码，根据验证码类型不同，调用不同的{@link validateCodeGenerator}接口实现
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/code/{type}")
    public void ImageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        String name = type.toLowerCase() + ValidateCodeProcess.class.getSimpleName();
        validateCodeProcessors.get(name).create(new ServletWebRequest(request, response));
    }

    /**
     * 创建验证码，根据验证码类型不同，调用不同的{@link validateCodeGenerator}接口实现
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/code/{type}")
    @ResponseBody
    public String smsCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        String mobile = request.getParameter("mobile");
        String name = type.toLowerCase() + ValidateCodeProcess.class.getSimpleName();
        validateCodeProcessors.get(name).create(new ServletWebRequest(request, response));
        return "{\"message\":\"发送成功\"}";
    }
}