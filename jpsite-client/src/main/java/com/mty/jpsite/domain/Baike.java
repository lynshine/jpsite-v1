package com.mty.jpsite.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangpeng on 2018/11/1.
 */
@ApiModel(value = "baike", description = "baike模型")
public class Baike {
    private String id;
    @ApiModelProperty(value = "描述", dataType = "com.mty.jpsite.enums.LoginResponseType")  // 字段api说明, 用枚举测试一下
    private String desc;
    private List<String> tag = new ArrayList<String>();
    private Date createDate = null;
    private Date updateDate = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
