package com.mty.jpsite.security.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码
 * @author haha
 */
@Data
@AllArgsConstructor
public class ValidateCode implements Serializable {
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 判断是否过期
     *
     * @return Boolean true过期
     */
    public Boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
