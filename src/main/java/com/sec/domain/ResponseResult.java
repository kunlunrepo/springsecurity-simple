package com.sec.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-14 16:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {

    private long code;

    private String msg;

    private Object data;

    public ResponseResult(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
