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

/**
 * Author: lnp
 * Date: 2018/6/15
 **/
@RequestMapping("/student")
@Controller
public class StudentController {

    @Resource
    GraduateOrNotService graduateOrNotService;

//    @RequestMapping("/graduationConditions")
//    public String goToGraduationConditionsPage(Student student, Model model) {
//        StudentCreditResult studentCreditResult = graduateOrNotService.GraduateOrNot(student.getStudentId());
//        model.addAttribute("studentCreditResult", studentCreditResult);
//        System.out.println(studentCreditResult);
//        return "/student/graduationConditions";
//    }

    @RequestMapping("/graduationConditions/{id}")
    public String goToGraduationConditionsPage2(@PathVariable(value = "id") String id, Model model) {
        StudentCreditResult studentCreditResult = graduateOrNotService.GraduateOrNot(id);
        model.addAttribute("studentCreditResult", studentCreditResult);
        System.out.println(studentCreditResult);
        return "/student/graduationConditions";
    }

}
