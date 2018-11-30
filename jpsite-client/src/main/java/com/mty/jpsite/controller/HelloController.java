package com.mty.jpsite.controller;

import com.mty.jpsite.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jiangpeng on 2018/10/24.
 */
@RestController
@CrossOrigin  // cros跨域处理
@Api(tags = "hello", description = "你好controller")
public class HelloController {

    @ApiOperation(value = "你好", notes = "测试你好输出")
    @GetMapping("/hello.html")
    public String say() {
        return "hello world!!!";
    }

    @GetMapping("/login.html")
    public void addSession(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        request.getSession().setAttribute("user", user);
    }
}
