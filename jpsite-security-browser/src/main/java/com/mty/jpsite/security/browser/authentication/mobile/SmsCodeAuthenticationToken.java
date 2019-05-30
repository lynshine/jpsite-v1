package com.mty.jpsite.security.browser.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 短信登录验证信息封装类 extends {@link AbstractAuthenticationToken}
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    /**
     * 手机号
     */
    private final Object principal;

    /**
     * SmsCodeAuthenticationFilter中构建的未认证的Authentication
     * @param mobile
     */
    public SmsCodeAuthenticationToken(String mobile) {
        super(null);
        this.principal = mobile;
        this.setAuthenticated(false);
    }

    /**
     * SmsCodeAuthenticationProvider中构建已认证的Authentication
     *
     * @param principal
     * @param authorities
     */
    public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        //必须使用super，因为我们重写
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
