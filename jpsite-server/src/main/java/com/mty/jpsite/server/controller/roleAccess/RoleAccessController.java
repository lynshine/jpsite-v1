package com.mty.jpsite.server.controller.roleAccess;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.role.Role;
import com.mty.jpsite.server.entity.roleAccess.RoleAccess;
import com.mty.jpsite.server.service.role.RoleService;
import com.mty.jpsite.server.service.roleAccess.RoleAccessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色权限表 
 前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Api(description = "角色权限")
@RestController
@RequestMapping("/roleAccess")
public class RoleAccessController {
    @Autowired
    private RoleAccessService roleAccessService;

    @ApiOperation(value = "", notes = "保存角色权限")
    @PostMapping()
    public void save(@ApiParam(name = "roleAccess", value = "角色权限实体", required = true)
                     @RequestBody RoleAccess roleAccess) {
        roleAccessService.save(roleAccess);
    }

    @ApiOperation(value = "/{id}", notes = "根据角色权限id删除")
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true)
                       @PathVariable("id") int id) {
        roleAccessService.removeById(id);
    }

    @ApiOperation(value = "", notes = "编辑角色权限")
    @PutMapping()
    public void update(@ApiParam(name = "roleAccess", value = "角色信息", required = true)
                       @RequestBody RoleAccess roleAccess) {
        roleAccessService.updateById(roleAccess);
    }

    @ApiOperation(value = "", notes = "查询所有角色权限")
    @GetMapping()
    public List<Role> findAll(@ApiParam(name = "roleId", value = "角色id")
                              @RequestParam(defaultValue = "0", value = "roleId") Integer roleId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (roleId > 0) {
            queryWrapper.eq("role_id", roleId);
        }
        return roleAccessService.list(queryWrapper);
    }
}

