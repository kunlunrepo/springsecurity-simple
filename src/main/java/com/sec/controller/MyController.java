package com.sec.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-22 10:00
 */
@RestController
public class MyController {

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/my-endpoint")
    public String myEndpoint() {
        return "测试";
    }
}
