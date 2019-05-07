package com.ymjtt.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @auther ywx
 * @date 2019/4/25 9:11
 **/
@SpringBootApplication
@ImportResource("classpath*:spring/applicationContext_*.xml")
public class SSO_SpringBootApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SSO_SpringBootApp.class, args);
    }

}
