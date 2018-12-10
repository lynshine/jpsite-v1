package com.mty.jpsite.server.entity.hiCourse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hi_course")
@ApiModel(value = "HiCourse对象", description = "课程")
public class HiCourse extends Model<HiCourse> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "课程")
    private String course;

    @ApiModelProperty(value = "教师")
    private String teacher;

    @ApiModelProperty(value = "上课时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime classHourStart;

    @ApiModelProperty(value = "下课时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime classHourDown;

    @ApiModelProperty(value = "教室")
    private String classroom;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;


    public static final String ID = "id";

    public static final String COURSE = "course";

    public static final String TEACHER = "teacher";

    public static final String CLASS_HOUR_START = "class_hour_start";

    public static final String CLASS_HOUR_DOWN = "class_hour_down";

    public static final String CLASSROOM = "classroom";

    public static final String CREATE_AT = "create_at";

    public static final String UPDATE_AT = "update_at";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
