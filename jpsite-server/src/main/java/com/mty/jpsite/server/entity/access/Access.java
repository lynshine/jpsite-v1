package com.mty.jpsite.server.entity.access;

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
 * 权限表 
 * </p>
 *
 * @author jiangpeng
 * @since 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("access")
@ApiModel(value="Access对象", description="权限表 ")
public class Access extends Model<Access> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限标题")
    private String title;

    @ApiModelProperty(value = "权限路径")
    private String uris;

    @ApiModelProperty(value = "该记录是否有效1：有效、0：无效")
    private Boolean status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String URIS = "uris";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
