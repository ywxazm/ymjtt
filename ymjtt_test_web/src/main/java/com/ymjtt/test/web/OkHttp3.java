package com.ymjtt.test.web;

import com.ymjtt.common.okhttp3.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用@RequestMapping
 */
@Controller
public class OkHttp3 {

    //供OkHttp3测试用
    //for get 请求
    @RequestMapping(value = "/forOKHttp3Test00")
    public void forOKHttp3Test00(HttpServletResponse response, HttpServletRequest request, String name, Integer age) throws IOException {
        System.out.println("forOKHttp3Test00 is Success!");
        response.getWriter().write("访问成功, name = " + name + ",  age = " + age);
    }
    //提交Content_Type = "application/x-www-form-urlencoded;charset=utf-8"     //默认Content_Type,接收简单数据类型
    @RequestMapping(value = "/forOKHttp3Test01", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    public void forOKHttp3Test01(HttpServletResponse response, HttpServletRequest request, String name, Integer age) throws IOException {
        System.out.println("forOKHttp3Test01 is Success!");
        response.getWriter().write("访问成功, name = " + name + ",  age = " + age);
    }
    //提交Content_Type = "application/x-www-form-urlencoded;charset=utf-8"     //默认Content_Type,接收POJO
    @RequestMapping(value = "/forOKHttp3Test02", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    public void forOKHttp3Test02(HttpServletResponse response, HttpServletRequest request, UserDTO userDTO) throws IOException {
        System.out.println("forOKHttp3Test02 is Success!");
        response.getWriter().write("访问成功, " + userDTO);
    }
    //提交Content_Type = "application/json;charset=utf-8"                      //JSON Content_Type,接收简单数据类型, 不用@RequestBody    Fail 接收不到数据
    @RequestMapping(value = "/forOKHttp3Test03", consumes = "application/json;charset=utf-8")
    public void forOKHttp3Test03(HttpServletResponse response, HttpServletRequest request, String name, Integer age) throws IOException {
        System.out.println("forOKHttp3Test03 is Success!");
        response.getWriter().write("访问成功, name = " + name + ",  age = " + age);
    }
    //提交Content_Type = "application/json;charset=utf-8"                      //JSON Content_Type, 接收简单数据类型, 用@RequestBody,  Fail 报错, Required request body is missing
    @RequestMapping(value = "/forOKHttp3Test04", consumes = "application/json;charset=utf-8")
    public void forOKHttp3Test04(HttpServletResponse response, HttpServletRequest request, @RequestBody String name, @RequestBody Integer age) throws IOException {
        System.out.println("forOKHttp3Test04 is Success!");
        response.getWriter().write("访问成功, name = " + name + ",  age = " + age);
    }
    //提交Content_Type = "application/json;charset=utf-8"                      //JSON Content_Type,接收POJO, 不用@RequestBody            Fail 接收不到数据
    @RequestMapping(value = "/forOKHttp3Test05", consumes = "application/json;charset=utf-8")
    public void forOKHttp3Test05(HttpServletResponse response, HttpServletRequest request, UserDTO userDTO) throws IOException {
        System.out.println("forOKHttp3Test05 is Success!");
        response.getWriter().write("访问成功, " + userDTO);
    }
    //提交Content_Type = "application/json;charset=utf-8"                      //JSON Content_Type,接收POJO, 用@RequestBody              OK
    @RequestMapping(value = "/forOKHttp3Test06", consumes = "application/json;charset=utf-8")
    public void forOKHttp3Test06(HttpServletResponse response, HttpServletRequest request, @RequestBody UserDTO userDTO) throws IOException {
        System.out.println("forOKHttp3Test06 is Success!");
        response.getWriter().write("访问成功, " + userDTO);
    }
}
