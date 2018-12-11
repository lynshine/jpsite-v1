package com.mty.jpsite.server.controller.role;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.role.Role;
import com.mty.jpsite.server.service.role.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表  前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-06
 */
@Api(description = "角色")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "", notes = "保存角色")
    @PostMapping()
    public void save(@ApiParam(name = "role", value = "角色实体", required = true)
                     @RequestBody Role role) {
        roleService.save(role);
    }

    @ApiOperation(value = "/{id}", notes = "根据角色id删除")
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true)
                       @PathVariable("id") int id) {
        roleService.removeById(id);
    }

    @ApiOperation(value = "", notes = "编辑角色信息")
    @PutMapping()
    public void update(@ApiParam(name = "role", value = "角色信息", required = true)
                       @RequestBody Role role) {
        roleService.updateById(role);
    }

    @ApiOperation(value = "", notes = "查询所有角色")
    @GetMapping()
    public List<Role> findAll(@ApiParam(name = "id", value = "角色id")
                              @RequestParam(defaultValue = "0", value = "id") Integer id,
                              @ApiParam(name = "status", value = "该记录是否有效1：有效、0：无效")
                              @RequestParam(defaultValue = "-1", value = "status") Integer status) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (id > 0) {
            queryWrapper.eq(" ", id);
        }
        if (status > -1) {
            queryWrapper.eq("status", status);
        }
        return roleService.list(queryWrapper);
    }
}