package com.mty.jpsite.server.entity.hiCourseSchedule;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hi_course_schedule")
@ApiModel(value="HiCourseSchedule对象", description="课程表")
public class HiCourseSchedule extends Model<HiCourseSchedule> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "上课日期")
    private LocalDate date;

    @ApiModelProperty(value = "上课时间")
    private LocalDateTime classHourStart;

    @ApiModelProperty(value = "下课时间")
    private LocalDateTime classHourDown;

    @ApiModelProperty(value = "最大上课人数")
    private Integer studentMax;

    @ApiModelProperty(value = "实际上课人数")
    private Integer studentMin;

    @ApiModelProperty(value = "教室")
    private String classroom;

    @ApiModelProperty(value = "课程表id")
    private Integer hiCourseId;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


    public static final String ID = "id";

    public static final String DATE = "date";

    public static final String CLASS_HOUR_START = "class_hour_start";

    public static final String CLASS_HOUR_DOWN = "class_hour_down";

    public static final String STUDENT_MAX = "student_max";

    public static final String STUDENT_MIN = "student_min";

    public static final String CLASSROOM = "classroom";

    public static final String HI_COURSE_ID = "hi_course_id";

    public static final String CREATE_AT = "create_at";

    public static final String UPDATE_AT = "update_at";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
