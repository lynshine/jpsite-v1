package com.mty.jpsite.server.controller.hiReservationLog;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.hiReservationLog.HiReservationLog;
import com.mty.jpsite.server.service.hiReservationLog.HiReservationLogService;
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
 * 我的预约记录表 前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Api(description = "我的预约记录表")
@RestController
@RequestMapping("/hiReservationLog")
public class HiReservationLogController {
    @Autowired
    private HiReservationLogService hiReservationLogService;

    @ApiOperation(value = "", notes = "保存预约记录")
    @PostMapping()
    public void save(@RequestBody HiReservationLog hiReservationLog) {
        hiReservationLogService.save(hiReservationLog);
    }

    @ApiOperation(value = "/{id}", notes = "根据预约记录id删除")
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable("id") int id) {
        hiReservationLogService.removeById(id);
    }

    @ApiOperation(value = "", notes = "查询所有预约记录")

    @GetMapping()
    public List<HiReservationLog> findAll(@ApiParam(name = "userId", value = "用户id")
                                          @RequestParam(defaultValue = "0", value = "userId") Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (userId > 0) {
            queryWrapper.eq("user_id", userId);
        }
        return hiReservationLogService.list(queryWrapper);
    }

    @ApiOperation(value = "/my", notes = "查询我的预约记录")
    @GetMapping("/my")
    public List<HiReservationLog> findMe() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userDetails.getUsername());
        return hiReservationLogService.list(queryWrapper);
    }
}