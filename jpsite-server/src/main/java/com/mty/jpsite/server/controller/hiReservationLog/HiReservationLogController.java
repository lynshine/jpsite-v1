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

import javax.servlet.http.HttpServletRequest;
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

    @ApiOperation(value = "/save", notes = "保存预约记录")
    @PostMapping("/save")
    public void save(@RequestBody HiReservationLog hiReservationLog) {
        hiReservationLogService.save(hiReservationLog);
    }

    @ApiOperation(value = "/delete", notes = "根据预约记录id删除")
    @DeleteMapping("/delete")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true) int id) {
        hiReservationLogService.removeById(id);
    }

    @ApiOperation(value = "/findAll", notes = "查询所有预约记录")
    @GetMapping("/findAll")
    public List<HiReservationLog> findAll() {
        return hiReservationLogService.list();
    }

    @ApiOperation(value = "/my", notes = "查询我的预约记录")
    @GetMapping("/my")
    public List<HiReservationLog> findMe(HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userDetails.getUsername());
        return hiReservationLogService.list(queryWrapper);
    }
}