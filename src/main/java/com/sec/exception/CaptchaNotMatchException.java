package com.sec.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-22 14:58
 */
public class CaptchaNotMatchException extends AuthenticationException {
    public CaptchaNotMatchException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CaptchaNotMatchException(String msg) {
        super(msg);
    }
}
