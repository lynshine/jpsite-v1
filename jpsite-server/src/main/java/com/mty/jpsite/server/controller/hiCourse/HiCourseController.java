package com.mty.jpsite.server.controller.hiCourse;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.hiCourse.HiCourse;
import com.mty.jpsite.server.service.hiCourse.HiCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Api(description = "课程")
@RestController
@RequestMapping("/hiCourse")
public class HiCourseController {
    @Autowired
    private HiCourseService hiCourseService;

    @ApiOperation(value = "/save", notes = "保存课程")
    @PostMapping("/save")
    public void save(@RequestBody HiCourse hiCourse) {
        hiCourseService.save(hiCourse);
    }

    @ApiOperation(value = "/delete", notes = "根据课程id删除")
    @GetMapping("/delete")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true) int id) {
        hiCourseService.removeById(id);
    }

    @ApiOperation(value = "/update", notes = "修改课程")
    @PostMapping("/update")
    public void update(@RequestBody HiCourse hiCourse) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", hiCourse.getId());
        hiCourseService.update(hiCourse, queryWrapper);
    }

    @ApiOperation(value = "/findAll", notes = "查询所有课程")
    @GetMapping("/findAll")
    public List<HiCourse> findAll() {
        return hiCourseService.list();
    }
}

