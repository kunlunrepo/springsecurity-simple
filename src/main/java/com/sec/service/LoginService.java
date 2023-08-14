package com.sec.service;

import com.sec.domain.ResponseResult;
import com.sec.domain.SysUser;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-14 16:05
 */
public interface LoginService {

    ResponseResult login(SysUser sysUser);

    ResponseResult logout();
}
