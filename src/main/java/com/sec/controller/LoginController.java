package com.sec.controller;

import com.sec.domain.ResponseResult;
import com.sec.domain.SysUser;
import com.sec.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-14 16:03
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody SysUser user) {

        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

}
