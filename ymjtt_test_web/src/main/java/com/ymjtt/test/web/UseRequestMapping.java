package com.ymjtt.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用@RequestMapping
 */
@Controller
public class UseRequestMapping {

    /**
     * @RequestMapping 路径的测试
     * @param response
     * @throws IOException
     */
    @RequestMapping("/useRequestMapping01")
    public void useRequestMapping01(HttpServletResponse response) throws IOException {
        System.out.println("useRequestMapping01 is Success!");
        response.getWriter().write("访问成功");
    }
    @RequestMapping(value = "/useRequestMapping02")
    public void useRequestMapping02(HttpServletResponse response) throws IOException {
        System.out.println("useRequestMapping02 is Success!");
        response.getWriter().write("访问成功");
    }
    @RequestMapping(value = {"/useRequestMapping031", "/useRequestMapping032"})
    public void useRequestMapping03(HttpServletResponse response) throws IOException {
        System.out.println("useRequestMapping03 is Success!");
        response.getWriter().write("访问成功");
    }
    @RequestMapping({"/useRequestMapping041", "/useRequestMapping042"})
    public void useRequestMapping04(HttpServletResponse response) throws IOException {
        System.out.println("useRequestMapping04 is Success!");
        response.getWriter().write("访问成功");
    }
    @RequestMapping("/useRequestMapping05/abc")
    public void useRequestMapping05(HttpServletResponse response) throws IOException {
        System.out.println("useRequestMapping05 is Success!");
        response.getWriter().write("访问成功");
    }
    @RequestMapping("/useRequestMapping06/{abc}")
    public void useRequestMapping06(HttpServletResponse response, String abc) throws IOException {
        System.out.println("useRequestMapping06 is Success! the param = " + abc);       //useRequestMapping06 is Success! the param = null
        response.getWriter().write("访问成功");                                      //需要用@PathVariable来接收url上的数据
    }
    @RequestMapping("/useRequestMapping07/{abc}")
    public void useRequestMapping07(HttpServletResponse response, @PathVariable int abc) throws IOException {
        System.out.println("useRequestMapping07 is Success! the param = " + abc);       //传入值为123aa，报错NumberFormatException，原因在于：取到url上的值后，进行转换时出错
        response.getWriter().write("访问成功");                                      //此处注意，前台传入什么数据，后台用什么类型接
    }
    @RequestMapping("/useRequestMapping08/{abc}")
    public void useRequestMapping08(HttpServletResponse response, @PathVariable String abc) throws IOException {
        System.out.println("useRequestMapping08 is Success! the param = " + abc);
        response.getWriter().write("访问成功");
    }




    /**
     * @RequestMapping 指定方法的测试
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/useRequestMapping10", method = RequestMethod.GET)
    public void useRequestMapping10(HttpServletResponse response) throws IOException {
        System.out.println("useRequestMapping10 is Success!");
        response.getWriter().write("访问成功");
    }
    @RequestMapping(value = "/useRequestMapping11", method = RequestMethod.POST)
    public void useRequestMapping11(HttpServletResponse response) throws IOException {
        System.out.println("useRequestMapping11 is Success!");
        response.getWriter().write("访问成功");
    }





    /**
     * @RequestMapping 指定consume的测试
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/useRequestMapping12", consumes = "text/html")
    public void useRequestMapping12(HttpServletResponse response, String name) throws IOException {
        System.out.println("useRequestMapping12 is Success! name = " + name);
        response.getWriter().write("访问成功");
    }
}
