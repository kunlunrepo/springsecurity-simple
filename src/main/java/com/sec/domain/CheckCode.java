package com.sec.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-22 12:33
 */
public class CheckCode implements Serializable {

    private String code; // 验证字符
    private LocalDateTime expireTime; // 过期时间

    public CheckCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime); // 指定过期时间
    }

    public CheckCode(String code) {
        // 默认验证码 60秒后过期
        this(code, 60);
    }

    // 是否过期
    public boolean isExpired() {
        return this.expireTime.isBefore(LocalDateTime.now());
    }

    public String getCode() {
        return code;
    }
}
