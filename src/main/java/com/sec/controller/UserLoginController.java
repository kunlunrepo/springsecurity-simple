package com.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-22 11:56
 */
@Controller
public class UserLoginController {

    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }

}
