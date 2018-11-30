package com.mty.jpsite.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;


@Controller
public class MyErrorController extends AbstractErrorController {
    @Autowired
    ObjectMapper objectMapper;

    public MyErrorController() {
        super(new DefaultErrorAttributes());
    }

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping("/testError")
    public void testError() {
        int error = 1 / 0;
    }

    @RequestMapping("/error")
    public String getErrorPath(HttpServletRequest request) {
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request, false));

        Throwable cause = getCause(request);
        int status = (Integer) model.get("status");
        String message = (String) model.get("message");
        String errorMessage = getErrorMessage(cause);
        boolean jsonRequest = isJsonRequest(request);
        System.out.println(status + ", " + message + ", " + errorMessage);
        return status + ", " + message + ", " + errorMessage;
    }

    protected Throwable getCause(HttpServletRequest request) {
        Throwable error = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
        }
        return error;
    }

    protected String getErrorMessage(Throwable ex) {
        return "服务器错误，请联系jls";
    }

    protected boolean isJsonRequest(HttpServletRequest request) {
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri != null && requestUri.endsWith(".json")) {
            return true;
        } else {
            //也可以通过获取HTTP头，根据Accept字段是否包含JSON来进一步判断，比如
            request.getHeader("Accept").contains("application/json");
            return false;
        }
    }
}
