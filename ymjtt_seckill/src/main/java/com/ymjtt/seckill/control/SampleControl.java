package com.ymjtt.seckill.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ymjtt.common.redis.StringOper;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.seckill.domain.StudentDo;
import com.ymjtt.seckill.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther ywx
 * @date 2019/4/25 9:14
 **/
@Controller
@RequestMapping("/sampleControl")
public class SampleControl {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StringOper stringOper;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "wangxiaojian");
        return "wxj";
    }

    @RequestMapping("/getById")
    public String getById(Model model) {
        model.addAttribute("student", studentService.getById(1));
        return "wxj";
    }

    @RequestMapping("/save")
    public String save(Model model) {
        StudentDo stu = new StudentDo();
        stu.setAge(90);
        stu.setName("wxj");
        model.addAttribute("result", studentService.save(stu));
        return "wxj";
    }

    @RequestMapping("/redis")
    public String redis(Model model) throws JsonProcessingException {
        String stuStr = stringOper.get("stu");
        if (StringUtils.isEmpty(stuStr)) {
            StudentDo studentDo = studentService.getById(1);
            stuStr = JSONConvertUtil.obj2Json(studentDo);
            stringOper.set("stu", stuStr);
        }
        model.addAttribute("result", stuStr);
        return "wxj";
    }

    @RequestMapping("/list")
    public String list(Model model) throws JsonProcessingException {
        model.addAttribute("result", JSONConvertUtil.obj2Json(studentService.list()));
        return "wxj";
    }





}
