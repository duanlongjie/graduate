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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: lnp
 * Date: 2018/6/15
 **/
@Controller
public class StudentController {

    @Resource
    GraduateOrNotService graduateOrNotService;

    @Resource
    StudentService studentService;

    @RequestMapping("/student/graduationCon")
    public ModelAndView goToGraduationConditionsPage(@RequestParam(value = "studentId") String id,HttpSession session) {
        ResultInfo resultInfo= studentService.getStudentById(id);
        Student student = (Student) resultInfo.getResultObj();
        session.setAttribute("student",student);
        ModelAndView modelAndView = new ModelAndView("/student/graduationConditions");
        StudentCreditResult studentCreditResult = graduateOrNotService.GraduateOrNot(id);
        modelAndView.addObject("studentCreditResult", studentCreditResult);
        System.out.println(studentCreditResult);
        return modelAndView;
    }


    @RequestMapping("/grade")
    public String goToGradePage(HttpSession session, Model model) {
//        ResultInfo resultInfo= studentService.getStudentById(id);
//        Student student = (Student) resultInfo.getResultObj();
        Student student = (Student)session.getAttribute("student");
        List<Grade> gradeList = student.getGradeList();
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("student", student);
        System.out.println(gradeList);
        return "/student/grade";
    }
}
