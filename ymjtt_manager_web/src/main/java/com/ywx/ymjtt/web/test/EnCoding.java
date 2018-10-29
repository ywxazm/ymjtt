package com.ywx.ymjtt.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class EnCoding {

    /**
     * 不开启任何编码设置，全部使用默认设定
     */
    @RequestMapping("/test10")
    public void test10(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("name:  " + name);
        response.getWriter().write(name + "返回成功");
    }
}
