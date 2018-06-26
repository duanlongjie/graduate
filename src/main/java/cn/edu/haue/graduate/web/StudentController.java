package cn.edu.haue.graduate.web;

import cn.edu.haue.graduate.entity.*;
import cn.edu.haue.graduate.service.GraduateOrNotService;
import cn.edu.haue.graduate.service.StudentService;
import cn.edu.haue.graduate.utils.ExcelUtil;
import org.springframework.data.domain.Page;
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
        ResultInfo<StudentCreditResult> resultResultInfo = graduateOrNotService.GraduateOrNot(id);
        modelAndView.addObject("studentCreditResult", resultResultInfo.getResultObj());
        System.out.println(resultResultInfo.getResultObj());
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

    // 跳转 学生列表页   分页显示
    @RequestMapping("admin/student_list")
    public String getStudentList(Model model,HttpSession session, Integer pager){
        if (pager == null) {
            pager = 0;
        }
        ResultInfo<Page<Student>> resultInfo = studentService.findAllByPage(pager, 5);
        Page<Student> page = resultInfo.getResultObj();
        List<Student> students = page.getContent();
        int totalPages = page.getTotalPages();
        model.addAttribute("students",students);
        model.addAttribute("currentPage", pager + 1);
        model.addAttribute("totalPage", totalPages);
//        model.addAttribute("currentAdmin", administratorService.findAdminByUid((Integer) session.getAttribute("currentUid")).getResultObj());
        return "/admin/student";
    }
}
