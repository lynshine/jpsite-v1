package com.mty.jpsite.server.controller.user;


import com.mty.jpsite.server.entity.user.User;
import com.mty.jpsite.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @ResponseBody
    public User getUserInfo(String id) {
        User user = userService.getById(id);
        return user;
    }

}

