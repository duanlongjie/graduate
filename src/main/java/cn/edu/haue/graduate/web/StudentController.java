package cn.edu.haue.graduate.web;

import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.entity.StudentCreditResult;
import cn.edu.haue.graduate.service.GraduateOrNotService;
import cn.edu.haue.graduate.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: lnp
 * Date: 2018/6/15
 **/
@RequestMapping("/student")
@Controller
public class StudentController {

    @Resource
    GraduateOrNotService graduateOrNotService;

    @Resource
    StudentService studentService;

    @RequestMapping("/student/graduationCon")
    public String goToGraduationConditionsPage(HttpSession session, Model model) {
        Student student= (Student) session.getAttribute("student");
        StudentCreditResult studentCreditResult = graduateOrNotService.GraduateOrNot(student.getStudentId());
        model.addAttribute("studentCreditResult", studentCreditResult);
        System.out.println(studentCreditResult);
        return "student/graduationConditions";
    }

    @RequestMapping("/student/grade")
    public String goToGradePage(HttpSession session, Model model){
        Student student= (Student) session.getAttribute("student");
        List<Grade> gradeList = student.getGradeList();
        model.addAttribute("gradeList",gradeList);
        model.addAttribute("student",student);
        System.out.println(gradeList);
        return "/student/grade";


    }
}
