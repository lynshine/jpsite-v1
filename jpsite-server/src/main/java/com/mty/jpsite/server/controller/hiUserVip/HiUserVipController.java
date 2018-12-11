package com.mty.jpsite.server.controller.hiUserVip;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.hiUserVip.HiUserVip;
import com.mty.jpsite.server.service.hiUserVip.HiUserVipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 我的会员卡 前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Api(description = "我的会员卡")
@RestController
@RequestMapping("/hiUserVip")
public class HiUserVipController {
    @Autowired
    private HiUserVipService hiUserVipService;

    @ApiOperation(value = "", notes = "保存会员卡")
    @PostMapping()
    public void save(@ApiParam(name = "hiUserVip", value = "会员卡实体", required = true)
                     @RequestBody HiUserVip hiUserVip) {
        hiUserVipService.save(hiUserVip);
    }

    @ApiOperation(value = "/{id}", notes = "根据会员卡id删除")
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true)
                       @PathVariable("id") int id) {
        hiUserVipService.removeById(id);
    }

    @ApiOperation(value = "", notes = "编辑会员卡信息")
    @PutMapping()
    public void update(@ApiParam(name = "hiUserVip", value = "会员卡实体", required = true)
                       @RequestBody HiUserVip hiUserVip) {
        hiUserVipService.updateById(hiUserVip);
    }

    @ApiOperation(value = "", notes = "查询所有会员卡")
    @GetMapping()
    public List<HiUserVip> findAll(@ApiParam(name = "userId", value = "用户id")
                                   @RequestParam(defaultValue = "0", value = "userId") Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (userId > 0) {
            queryWrapper.eq("user_id", userId);
        }
        return hiUserVipService.list(queryWrapper);
    }

    @ApiOperation(value = "/my", notes = "查询我的会员卡")
    @GetMapping("/my")
    public List<HiUserVip> findMe() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userDetails.getUsername());
        return hiUserVipService.list(queryWrapper);
    }
}