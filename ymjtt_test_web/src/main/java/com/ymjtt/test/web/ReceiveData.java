package com.ymjtt.test.web;

import com.ymjtt.test.xdo.StudentDo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/11/6 8:34
 * RequestMapping属性
 **/
@Controller
public class ReceiveData {

    //consume使用的是默认的方式                                                      //TODO: 简单数据类型用默认方式, 复数数据结构, 用application/json
    //简单数据类型
    @RequestMapping({"/requestMapper01", "/item/requestMapper01"})
    public void requestMapper01(HttpServletRequest request, HttpServletResponse response, Integer id, String name, Integer age) throws IOException {
        System.out.println("/requestMapper01 success......");
        response.getWriter().write("/requestMapper01 success, id=" + id + ", name=" + name + ", age=" + age);
    }
    //Restful类型
    @RequestMapping("/requestMapper02/{rid}/{rname}/{rage}")
    public void requestMapper02(HttpServletResponse response, @PathVariable Integer id, @PathVariable String name, @PathVariable Integer age) throws IOException {
        System.out.println("/requestMapper02 success......");
        response.getWriter().write("/requestMapper02 success 成功");
        response.getWriter().write("/requestMapper02 success, id=" + id + ", name=" + name + ", age=" + age);
    }
    //POJO类型
    @RequestMapping("/requestMapper030")
    public void requestMapper030(HttpServletResponse response, Integer id, String name, Integer age) throws IOException {
        System.out.println("/requestMapper030 success......");
        response.getWriter().write("/requestMapper030 success 成功");
        response.getWriter().write("/requestMapper030 success, id=" + id + ", name=" + name + ", age=" + age);
    }
    @RequestMapping("/requestMapper031")
    public void requestMapper031(HttpServletResponse response, StudentDo studentDo) throws IOException {
        System.out.println("/requestMapper031 success......");
        response.getWriter().write("/requestMapper031 success 成功");
        response.getWriter().write("/requestMapper031 success, studentDo=" + studentDo);
    }

    //数组类型
    @RequestMapping("/requestMapper040")
    public void requestMapper040(HttpServletResponse response, @RequestBody String[] array) throws IOException {
        System.out.println("/requestMapper040 success......");
        response.getWriter().write("/requestMapper040 success 成功");

        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s).append(",");
        }
        response.getWriter().write("/requestMapper040 success, array=" + sb.toString());
    }
    @RequestMapping("/requestMapper041")
    public void requestMapper041(HttpServletResponse response, StudentDo[] array) throws IOException {
        System.out.println("/requestMapper041 success......");
        response.getWriter().write("/requestMapper041 success 成功");

        StringBuilder sb = new StringBuilder();
        for (StudentDo s : array) {
            sb.append(s).append(",");
        }
        response.getWriter().write("/requestMapper040 success, array=" + sb.toString());
    }
    //List类型
    @RequestMapping("/requestMapper050")
    public void requestMapper050(HttpServletResponse response, List<String> list) throws IOException {
        System.out.println("/requestMapper050 success......");
        response.getWriter().write("/requestMapper050 success 成功");

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(",");
        }
        response.getWriter().write("/requestMapper050 success, list=" + sb);
    }
    @RequestMapping("/requestMapper051")
    public void requestMapper051(HttpServletResponse response, List<StudentDo> list) throws IOException {
        System.out.println("/requestMapper051 success......");
        response.getWriter().write("/requestMapper051 success 成功");

        StringBuilder sb = new StringBuilder();
        for (StudentDo s : list) {
            sb.append(s).append(",");
        }
        response.getWriter().write("/requestMapper051 success, list=" + sb);
    }
    //Map类型
    @RequestMapping("/requestMapper060")
    public void requestMapper060(HttpServletResponse response, Map<String, String> map) throws IOException {
        System.out.println("/requestMapper060 success......");
        response.getWriter().write("/requestMapper060 success 成功");

        StringBuilder sb = new StringBuilder();
        for (String s : map.keySet()) {
            sb.append(s).append("--").append(map.get(s)).append("\n");
        }
        response.getWriter().write("/requestMapper060 success, map=" + sb);
    }
    @RequestMapping("/requestMapper061")
    public void requestMapper061(HttpServletResponse response, Map<String, StudentDo> map) throws IOException {
        System.out.println("/requestMapper06 success......");
        response.getWriter().write("/requestMapper061 success 成功");

        StringBuilder sb = new StringBuilder();
        for (String s : map.keySet()) {
            sb.append(s).append("--").append(map.get(s)).append("\n");
        }
        response.getWriter().write("/requestMapper060 success, map=" + sb);
    }

}
