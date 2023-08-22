package com.sec.service.impl;

import com.sec.domain.LoginUser;
import com.sec.domain.ResponseResult;
import com.sec.domain.SysUser;
import com.sec.exception.CaptchaNotMatchException;
import com.sec.service.LoginService;
import com.sec.utils.JwtUtil;
import com.sec.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-14 16:07
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final String CAPTCHA_CODE_KEY = "sec:captcha:";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(SysUser sysUser) {
        // 1.通过authenticate方法，进行用户认证
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(sysUser.getUserName(),
                sysUser.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);

        // 2.如果认证没有通过，给出错误提示
        if (Objects.isNull(auth)) {
            throw new RuntimeException("登录失败");
        }

        // 3. 如果认证通过，使用userId生成一个JWT,并将其保存到结果中返回
        LoginUser loginUser = (LoginUser) auth.getPrincipal();

        String userId = loginUser.getSysUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userId);

        // 4.将用户信息存在在redis中，在一下次请求时能够识别出用户，userid作为key
        redisCache.setCacheObject("login:" + userId, loginUser);

        // 5.封装返回结果
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult logout() {

        // 获取当前用户的认证信息
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authToken)) {
            throw new RuntimeException("获取用户认证信息失败，请重新登录！");
        }

        LoginUser loginUser = (LoginUser) authToken.getPrincipal();
        Long userId = loginUser.getSysUser().getUserId();

        // 删除redis的用户信息
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult(200, "注销成功");
    }

    // 带验证码登录
    @Override
    public String login(String username, String password, String code, String uuid) {
        // 从redis中获取验证码
        String verifyKey = CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(captcha);

        if (captcha == null || !code.equalsIgnoreCase(captcha)) {
            throw new CaptchaNotMatchException("验证码错误！");
        }

        // 该方法会去调用 loadUserByUsername 方法
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // 如果认证通过，使用userId生成一个JWT，并将其返回
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        String userId = loginUser.getSysUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userId);

        redisCache.setCacheObject("login:"+userId, loginUser);

        return jwt;
    }
}
