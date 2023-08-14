package com.sec;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sec.dao")
@Slf4j
public class SecApp {
    public static void main(String[] args) {
        log.info("【SecApp】启动中...");
        SpringApplication.run(SecApp.class);
        log.info("【SecApp】启动成功！");
    }
}