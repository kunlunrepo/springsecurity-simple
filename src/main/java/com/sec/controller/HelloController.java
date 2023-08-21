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
    @PreAuthorize("hasAuthority('system:role:list')")
    public String ok() {
        return "ok";
    }

}
