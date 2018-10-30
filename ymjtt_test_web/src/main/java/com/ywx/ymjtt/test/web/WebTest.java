package com.ywx.ymjtt.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WebTest {

    @RequestMapping("/test01")
    public void test01(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("------------ test01 -----------");
        response.getWriter().write("test success");
    }
}
