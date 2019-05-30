package com.mty.jpsite.server.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.user.User;
import com.mty.jpsite.server.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-06
 */
@Api(description = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "", notes = "保存用户")
    @PostMapping()
    public void save(@ApiParam(name = "user", value = "用户实体", required = true)
                     @RequestBody User user) {
        userService.save(user);
    }

    @ApiOperation(value = "/{id}", notes = "根据id删除")
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true)
                       @PathVariable("id") int id) {
        userService.removeById(id);
    }

    @ApiOperation(value = "", notes = "编辑用户信息")
    @PutMapping()
    public void update(@ApiParam(name = "user", value = "角色信息", required = true)
                       @RequestBody User user) {
        userService.updateById(user);
    }

    @ApiOperation(value = "", notes = "查询所有用户")
    @GetMapping()
    public List<User> findAll(@ApiParam(name = "name", value = "name")
                              @RequestParam(defaultValue = "", value = "id") String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isBlank(name)) {
            queryWrapper.eq("name", name);
        }
        return userService.list(queryWrapper);
    }
}