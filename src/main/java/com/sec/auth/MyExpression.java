package com.sec.auth;

import com.sec.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description : 自定义校验
 * @author kunlunrepo
 * date :  2023-08-22 10:19
 */
@Component("my_ex")
public class MyExpression {

    // 自定义hasAuthority
    public boolean hasAuthority(String authority) {
        // 获取当前用户的权限
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        // 获取登录用户
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 获取登录用户权限
        List<String> permissions = loginUser.getPermissions();
        // 判断集合中是否有authority
        return permissions.contains(authority);
    }

}
