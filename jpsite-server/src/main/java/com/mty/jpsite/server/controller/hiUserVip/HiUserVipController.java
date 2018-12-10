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

    @ApiOperation(value = "/save", notes = "保存会员卡")
    @PostMapping("/save")
    public void save(@RequestBody HiUserVip hiUserVip) {
        hiUserVipService.save(hiUserVip);
    }

    @ApiOperation(value = "/delete", notes = "根据会员卡id删除")
    @GetMapping("/delete")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true) int id) {
        hiUserVipService.removeById(id);
    }

    @ApiOperation(value = "/findAll", notes = "查询所有会员卡")
    @GetMapping("/findAll")
    public List<HiUserVip> findAll() {
        return hiUserVipService.list();
    }

    @ApiOperation(value = "/my", notes = "查询我的会员卡")
    @GetMapping("/my")
    public List<HiUserVip> findMe() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String userId = userDetails.getUsername();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        return hiUserVipService.list(queryWrapper);
    }
}