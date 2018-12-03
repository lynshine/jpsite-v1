package com.mty.jpsite.security.app.common;

import java.io.Serializable;

/**
 */
public class AppSecretException extends RuntimeException implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1629364510827838114L;

    public AppSecretException(String msg) {
        super(msg);
    }

}
