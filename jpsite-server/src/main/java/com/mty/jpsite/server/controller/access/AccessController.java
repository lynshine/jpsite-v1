package com.mty.jpsite.server.controller.access;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.access.Access;
import com.mty.jpsite.server.entity.role.Role;
import com.mty.jpsite.server.service.access.AccessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限表  前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Api(description = "权限")
@RestController
@RequestMapping("/access")
public class AccessController {
    @Autowired
    private AccessService accessService;

    @ApiOperation(value = "", notes = "保存权限")
    @PostMapping()
    public void save(@ApiParam(name = "access", value = "权限实体", required = true)
                     @RequestBody Access access) {
        accessService.save(access);
    }

    @ApiOperation(value = "/{id}", notes = "根据权限id删除")
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true)
                       @PathVariable("id") int id) {
        accessService.removeById(id);
    }

    @ApiOperation(value = "", notes = "编辑权限信息")
    @PutMapping()
    public void update(@ApiParam(name = "access", value = "权限信息", required = true)
                       @RequestBody Access access) {
        accessService.updateById(access);
    }

    @ApiOperation(value = "", notes = "查询所有权限")
    @GetMapping()
    public List<Role> findAll(@ApiParam(name = "id", value = "权限id")
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
        return accessService.list(queryWrapper);
    }
}

