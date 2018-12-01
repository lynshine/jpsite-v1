package com.mty.jpsite.security.core.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码
 * Created by jiangpeng on 2018/11/9.
 */
public class ValidateCode implements Serializable {
    private static final long serialVersionUID = 1588203828504660915L;

    private String code;  // 验证码
    private LocalDateTime expireTime;   //过期时间

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
