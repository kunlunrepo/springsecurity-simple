package com.sec.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.sec.domain.LoginBody;
import com.sec.domain.ResponseResult;
import com.sec.domain.SysUser;
import com.sec.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    // 带验证码登录方法
    @PostMapping("/user/captcha-login")
    public ResponseResult captchaLogin(@RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = loginService.login(loginBody.getUserName(),
                loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return new ResponseResult(200,"登录成功", map);
    }
}
