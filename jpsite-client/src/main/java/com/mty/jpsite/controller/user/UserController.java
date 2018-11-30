package com.mty.jpsite.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mty.jpsite.server.entity.user.User;
import com.mty.jpsite.server.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-11-30
 */
@RestController
@RequestMapping("/staff")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/add")
    public void testInsert() {
        User user = new User();
        user.setLoginName("001");
        user.setLoginPassword("okong-insert");
        userService.save(user);
        log.info("新增结束");
    }

    @GetMapping("/get")
    public void get(String name) {
        Page<User> page = new Page<User>(0, 20);
        IPage<User> iPage = userService.selectPageVo(page, name);
        log.info("get结束{}", iPage);
    }

    @GetMapping("/get2")
    public void get2(String name) {
        Page<User> page = new Page<User>(0, 20);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        Map map = new HashMap();
        map.put("name", name);
        queryWrapper.allEq(map);
        IPage<User> page1 = userService.page(page, queryWrapper);
        log.info("get结束{}", page1);
    }

}

