package com.mty.jpsite.server.controller.hiCourseSchedule;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mty.jpsite.server.entity.hiCourse.HiCourse;
import com.mty.jpsite.server.entity.hiCourseSchedule.HiCourseSchedule;
import com.mty.jpsite.server.service.hiCourse.HiCourseService;
import com.mty.jpsite.server.service.hiCourseSchedule.HiCourseScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程表 前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Api(description = "课程表")
@RestController
@RequestMapping("/hiCourseSchedule")
public class HiCourseScheduleController {
    @Autowired
    private HiCourseScheduleService hiCourseScheduleService;

    @ApiOperation(value = "/save", notes = "保存课程表")
    @PostMapping("/save")
    public void save(@RequestBody HiCourseSchedule hiCourseSchedule) {
        hiCourseScheduleService.save(hiCourseSchedule);
    }

    @ApiOperation(value = "/delete", notes = "根据课程表id删除")
    @GetMapping("/delete")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true) int id) {
        hiCourseScheduleService.removeById(id);
    }

    @ApiOperation(value = "/update", notes = "修改课程表")
    @PostMapping("/update")
    public void update(@RequestBody HiCourseSchedule hiCourseSchedule) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", hiCourseSchedule.getId());
        hiCourseScheduleService.update(hiCourseSchedule, queryWrapper);
    }

    @ApiOperation(value = "/findAll", notes = "查询所有课程表")
    @GetMapping("/findAll")
    public List<HiCourseSchedule> findAll() {
        return hiCourseScheduleService.list();
    }

    @ApiOperation(value = "/queryDate", notes = "根据上课时间查询课程表")
    @GetMapping("/queryDate")
    public List<HiCourseSchedule> queryDate(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.between("class_hour_start", startDate, endDate);
        queryWrapper.between("class_hour_down", startDate, endDate);

        return hiCourseScheduleService.list(queryWrapper);
    }
}

