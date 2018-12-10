package com.mty.jpsite.server.entity.hiUserVip;

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
 * 我的会员卡
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hi_user_vip")
@ApiModel(value="HiUserVip对象", description="我的会员卡")
public class HiUserVip extends Model<HiUserVip> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "vip卡类型： 1: 次卡 2：月卡 3：年卡 4：终身")
    private Integer cardType;

    @ApiModelProperty(value = "总次数")
    private Integer courseNumberMax;

    @ApiModelProperty(value = "剩余次数")
    private Integer courseNumberMin;

    @ApiModelProperty(value = "申请日期")
    private LocalDateTime applicationDate;

    @ApiModelProperty(value = "到期日")
    private LocalDateTime maturityDate;

    @ApiModelProperty(value = "课程类型(多种)")
    private String hiCourseIds;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String CARD_TYPE = "card_type";

    public static final String COURSE_NUMBER_MAX = "course_number_max";

    public static final String COURSE_NUMBER_MIN = "course_number_min";

    public static final String APPLICATION_DATE = "application_date";

    public static final String MATURITY_DATE = "maturity_date";

    public static final String HI_COURSE_IDS = "hi_course_ids";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
