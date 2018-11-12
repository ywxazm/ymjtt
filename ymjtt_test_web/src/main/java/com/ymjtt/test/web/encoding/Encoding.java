package com.ymjtt.test.web.encoding;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @auther ywx
 * @date 2018/10/31 10:24
 **/
@Controller
public class Encoding {

    /**
     * Get请求
     * 浏览器不做编码设置
     * 代码不做编码处理
     * 入参: name = "小明"
     */
    @RequestMapping("/encoding01")
    public void encoding01(String name, HttpServletResponse response) throws IOException {
        System.out.println("name:  " + name);
        response.getWriter().write(name + ", 返回成功");
    }
    /**
     * Get请求
     * 浏览器 get请求没有Content-type字段, 返回的数据中也没有Content-type字段, 服务器不会自动添加此字段; 浏览器指定用UrlEncoding("utf-8")进行编码. 服务器指定解码方式进行解码
     * 代码 添加返回头信息
     * 入参: name = "小明"
     */
    @RequestMapping("/encoding02")
    public void encoding02(String name, HttpServletResponse response) throws IOException {      //中文参数封装至Request对象时, tomcat默认使用了ISO-8859-1的解码方式
        String encodeName = URLEncoder.encode(name, "ISO-8859-1");      //tomcat对url进行了一次url解码, 使用字符集为"ISO-8859-1", 现在反操作一次
        String decode = URLDecoder.decode(encodeName, "UTF-8");         //"UTF-8"就是匹配于浏览器的编码方式
                                                                                    //以上为其中一种处理方式, 还可以用new String(name.getByte("iso-8859-1"), "utf-8");;  还可以设置tomcat的解码方式为utf-8(彻底解决问题)

        System.out.println("tomcat封装数据name:  " + name);
        System.out.println("自行编码解码处理的name:  " + decode);

        response.getWriter().write(decode + ", 返回成功");
    }
    /**
     * Get请求
     * 浏览器 浏览器 在服务器代码中添加Content-Type字段
     * 代码 添加返回头信息
     * 入参: name = "小明"
     */
    @RequestMapping("/encoding03")
    public void encoding03(String name, HttpServletResponse response) throws IOException {
        String encodeName = URLEncoder.encode(name, "ISO-8859-1");
        String decode = URLDecoder.decode(encodeName, "UTF-8");

        System.out.println("tomcat封装数据name:  " + name);
        System.out.println("自行编码解码处理的name:  " + decode);
                                                                //tomcat默认是用iso-8859-1的方式进行编码
        response.setContentType("text/html;charset=utf-8");     //它的作用在于告诉tomcat, 要用utf8的方式进行编码, 也告诉浏览器,要用utf8的方式进行解码
        response.getWriter().write(decode + ", 返回成功");      //因此,此处的字符集, 无论你写怎样的字符集, 都不会出错
    }




    /**
     * Post请求
     * 浏览器不做编码设置
     * web.xml不做编码设置
     * 代码不做编码处理
     * 入参: name = "小明"
     */
    @RequestMapping("/encoding04")
    public void encoding04(String name, HttpServletResponse response) throws IOException {
        System.out.println("name:  " + name);
        response.getWriter().write(name + ", 返回成功");        /* 默认情况下, httpClient编码解码用:iso-8859-1, tomcat编码解码用:UTF-8 */
    }
    /**
     * Post请求
     * 浏览器 指定用utf8来进行编码
     * web.xml不做编码设置
     * 代码不做编码处理
     * 入参: name = "小明"
     */
    @RequestMapping("/encoding05")
    public void encoding05(String name, HttpServletResponse response) throws IOException {
        System.out.println("name:  " + name);
        response.getWriter().write(name + ", 返回成功");
    }
    /**
     * Post请求
     * 浏览器 指定用utf8来进行编码
     * web.xml指定Tomcat编码字符集
     * 代码不做编码处理
     * 入参: name = "小明"
     */
    @RequestMapping("/encoding06")
    public void encoding06(String name, HttpServletResponse response) throws IOException {
        System.out.println("name:  " + name);
        response.getWriter().write(name + ", 返回成功");
    }
    /**
     * Post请求
     * 浏览器 指定用utf8来进行编码, 解码
     * web.xml 指定Tomcat编码字符集
     * 代码不做编码处理
     * 入参: name = "小明"
     */
    @RequestMapping("/encoding07")
    public void encoding07(String name, HttpServletResponse response) throws IOException {
        System.out.println("name:  " + name);
        response.getWriter().write(name + ", 返回成功");
    }

}
