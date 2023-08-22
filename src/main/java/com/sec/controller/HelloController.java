package com.sec.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-14 10:47
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('test')") // 方法执行前进行权限校验
    @PreAuthorize("hasAuthority('system:user:list')") // 方法执行前进行权限校验
    public String hello() {
        return "hello";
    }

    // 拥有system:role:list才能访问
    @GetMapping("/ok")
//    @PreAuthorize("hasAuthority('system:role:list')")
    @PreAuthorize("@my_ex.hasAuthority('system:role:list')")
    public String ok() {
        return "ok";
    }

    @RequestMapping("/yes")
    public String yes() {
        return "yes";
    }

    @GetMapping("/level1")
    // 当前用户是common角色，并且具有 或 权限
    @PreAuthorize("hasRole('common') AND hasAnyAuthority('system:role:list', 'system:user:list')")
    public String level1() {
        return "level1 page";
    }

    @GetMapping("/level2")
    // 当前用户拥有admin或common角色，或者具有system:role:list权限
    @PreAuthorize("hasAnyRole('admin','common') OR hasAuthority('system:role:list')")
    public String level2() {
        return "level2 page";
    }

}
