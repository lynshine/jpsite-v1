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

    @ApiOperation(value = "", notes = "保存课程")
    @PostMapping()
    public void save(@ApiParam(name = "hiCourse", value = "课程实体", required = true)
                     @RequestBody HiCourse hiCourse) {
        hiCourseService.save(hiCourse);
    }

    @ApiOperation(value = "/{id}", notes = "根据课程id删除")
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable("id") int id) {
        hiCourseService.removeById(id);
    }

    @ApiOperation(value = "", notes = "修改课程")
    @PutMapping()
    public void update(@ApiParam(name = "hiCourse", value = "课程实体", required = true)
                       @RequestBody HiCourse hiCourse) {
        hiCourseService.updateById(hiCourse);
    }

    @ApiOperation(value = "", notes = "查询所有课程")
    @GetMapping()
    public List<HiCourse> findAll() {
        return hiCourseService.list();
    }
}

