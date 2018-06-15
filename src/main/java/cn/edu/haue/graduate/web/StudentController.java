package cn.edu.haue.graduate.web;

import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.service.GraduateOrNotService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Author: lnp
 * Date: 2018/6/15
 **/
@RequestMapping("/student")
public class StudentController {

    @Resource
    GraduateOrNotService graduateOrNotService;

    @RequestMapping("/graduationConditions")
    public String goToGraduationConditionsPage(Student student, Model model){
        ResultInfo<Student> resultInfo=graduateOrNotService.GraduateOrNot(student.getStudentId());
        model.addAttribute("studentCreditResult",resultInfo);
        return "redirect:graduationConditions";
    }


}
