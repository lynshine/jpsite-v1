package com.mty.jpsite.server.controller.userRole;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.userRole.UserRole;
import com.mty.jpsite.server.service.userRole.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Api(description = "用户角色")
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "", notes = "保存角色")
    @PostMapping()
    public void save(@ApiParam(name = "userRole", value = "用户角色实体", required = true)
                     @RequestBody UserRole userRole) {
        userRoleService.save(userRole);
    }

    @ApiOperation(value = "/{id}", notes = "根据id删除")
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true)
                       @PathVariable("id") int id) {
        userRoleService.removeById(id);
    }

    @ApiOperation(value = "", notes = "查询所有用户角色")
    @GetMapping()
    public List<UserRole> findAll(@ApiParam(name = "userId", value = "角色id")
                                  @RequestParam(defaultValue = "0", value = "userId") Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (userId > 0) {
            queryWrapper.eq("uid", userId);
        }
        return userRoleService.list(queryWrapper);
    }
}

