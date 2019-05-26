package com.ymjtt.sso.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @auther ywx
 * @date 2019/4/25 9:11
 **/
@SpringBootApplication
@PropertySource("classpath:config/other.properties")
@ImportResource({"classpath*:spring/applicationContext_*.xml", "classpath:config/application*.*"})
public class SSO_Web_SpringBootApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SSO_Web_SpringBootApp.class, args);
    }

}
