package com.ymjtt.test.web;

import com.ymjtt.common.util.JSONConvertUtil;
import com.ymjtt.test.service.StudentDoService;
import com.ymjtt.test.xdo.StudentDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/6 15:43
 **/
@Controller
public class StudentDoController {

    @Autowired
    private StudentDoService studentDoService;

    @RequestMapping("/listStudentDo")
    public void listStudentDo(HttpServletResponse response) throws IOException {
        System.out.println("------Start-------");
        List<StudentDo> list = studentDoService.listStudentDo();
        response.getWriter().write(JSONConvertUtil.list2Json(list));
    }
}
