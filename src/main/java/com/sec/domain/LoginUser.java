package com.sec.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

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

    // 获取用户被授予的权限，用于实现访问控制
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
