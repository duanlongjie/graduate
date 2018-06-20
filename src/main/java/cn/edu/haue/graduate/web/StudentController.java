package cn.edu.haue.graduate.web;

import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.entity.StudentCreditResult;
import cn.edu.haue.graduate.service.GraduateOrNotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Author: lnp
 * Date: 2018/6/15
 **/
@Controller
public class StudentController {

    @Resource
    GraduateOrNotService graduateOrNotService;

    @RequestMapping("/student/graduationCon/{id}")
    public String goToGraduationConditionsPage(@PathVariable String id,HttpSession session, Model model) {
        Student student= (Student) session.getAttribute("student");
        StudentCreditResult studentCreditResult = graduateOrNotService.GraduateOrNot(id);
        model.addAttribute("studentCreditResult", studentCreditResult);
        System.out.println(studentCreditResult);
        return "student/graduationConditions";
    }
}
