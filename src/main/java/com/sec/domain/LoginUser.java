package com.sec.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-14 15:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private SysUser sysUser;

    // 存储权限信息
    private List<String> permissions;

    // 授权信息
    @JSONField(serialize = false) // 不需要序列化
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(SysUser sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    // 获取用户被授予的权限，用于实现访问控制
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        } else {
            authorities = new ArrayList<>();
        }

        for (String permission : permissions) {
            if (StringUtils.isNotBlank(permission)) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
                authorities.add(authority);
            }
        }

//        authorities = permissions.stream()
//                .filter(item -> null != item && "".equals(item)).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    // 获取用户的密码，一般用于进行密码验证
    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    // 获取用户的用户名，用户身份验证
    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    // 用于判断用户的账户是否未过期，可以用于实现账户有效期控制
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 用户判断用户的账户是否未锁定，可以用于实现账户锁定功能
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 用于判断用户的凭证是否未过期，
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 用于判断用户是否已激活
    @Override
    public boolean isEnabled() {
        return true;
    }
}
