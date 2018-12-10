package com.mty.jpsite.server.entity.hiReservationLog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 我的预约记录表
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hi_reservation_log")
@ApiModel(value="HiReservationLog对象", description="我的预约记录表")
public class HiReservationLog extends Model<HiReservationLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "课程表id")
    private Integer hiCourseScheduleId;

    private LocalDateTime createAt;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String HI_COURSE_SCHEDULE_ID = "hi_course_schedule_id";

    public static final String CREATE_AT = "create_at";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
