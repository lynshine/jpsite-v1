package com.mty.jpsite.security.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于获取数据库用户
 * @author haha
 */
@Data
public class MyUser implements Serializable {
    private Long id;
    private String name;
    private String password;
    private Integer roleId;
    private Date createDate;
}
