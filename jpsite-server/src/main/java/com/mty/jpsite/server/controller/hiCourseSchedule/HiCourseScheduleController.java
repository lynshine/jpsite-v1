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

    @ApiOperation(value = "", notes = "保存课程表")
    @PostMapping()
    public void save(@ApiParam(name = "hiCourseSchedule", value = "课程表实体", required = true)
                     @RequestBody HiCourseSchedule hiCourseSchedule) {
        hiCourseScheduleService.save(hiCourseSchedule);
    }

    @ApiOperation(value = "/{id}", notes = "根据课程表id删除")
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable("id") int id) {
        hiCourseScheduleService.removeById(id);
    }

    @ApiOperation(value = "", notes = "修改课程表")
    @PutMapping()
    public void update(@ApiParam(name = "hiCourseSchedule", value = "课程表实体", required = true)
                       @RequestBody HiCourseSchedule hiCourseSchedule) {
        hiCourseScheduleService.updateById(hiCourseSchedule);
    }

    @ApiOperation(value = "", notes = "查询所有课程表")
    @GetMapping()
    public List<HiCourseSchedule> findAll(@ApiParam(name = "startDate", value = "上课时间", required = false)
                                          @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                                          @ApiParam(name = "endDate", value = "下课时间", required = false)
                                          @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (startDate != null) {
            queryWrapper.between("class_hour_start", startDate, endDate);
        }
        if (endDate != null) {
            queryWrapper.between("class_hour_down", startDate, endDate);

        }
        return hiCourseScheduleService.list(queryWrapper);
    }
}