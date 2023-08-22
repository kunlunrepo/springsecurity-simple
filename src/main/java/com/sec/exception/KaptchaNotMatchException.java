package com.sec.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * description : 自定义验证码错误异常
 *
 * @author kunlunrepo
 * date :  2023-08-22 14:03
 */
public class KaptchaNotMatchException extends AuthenticationException {
    public KaptchaNotMatchException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public KaptchaNotMatchException(String msg) {
        super(msg);
    }
}
