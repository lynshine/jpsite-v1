package com.mty.jpsite.security.core.domain;

/**
 * Created by jiangpeng on 2018/11/7.
 */
public class SecurityResponse {

    public SecurityResponse(Object content) {
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
