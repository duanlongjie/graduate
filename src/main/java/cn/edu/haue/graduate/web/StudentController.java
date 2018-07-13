package cn.edu.haue.graduate.web;

import cn.edu.haue.graduate.entity.*;
import cn.edu.haue.graduate.service.AdminService;
import cn.edu.haue.graduate.service.CourseService;
import cn.edu.haue.graduate.service.GraduateOrNotService;
import cn.edu.haue.graduate.service.StudentService;
import cn.edu.haue.graduate.utils.ExcelUtil;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Resource
    private AdminService adminService;
    @Resource
    private CourseService courseService;

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
        ResultInfo<Page<Student>> resultInfo = studentService.findAllByIsDelete(pager, 10);
//        ResultInfo<Page<Student>> resultInfo = studentService.findAllByPage(pager, 10);
        Page<Student> page = resultInfo.getResultObj();
        List<Student> students = page.getContent();
        int totalPages = page.getTotalPages();
        model.addAttribute("students",students);
        model.addAttribute("currentPage", pager + 1);
        model.addAttribute("totalPage", totalPages);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);

        return "/admin/student";
    }

    @RequestMapping("admin/studentGrade")
    public String s(Model model,String id,HttpSession session){
        ResultInfo<Student> resultInfo = studentService.getStudentById(id);
        Student student = resultInfo.getResultObj();
        List<Grade> grades = student.getGradeList();
        model.addAttribute("student",student);
        model.addAttribute("grades",grades);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        return "/admin/studentGrade";
    }

    @RequestMapping("admin/addStu")
    public String addStu(String studentId,String studentName,String major){
        System.out.println(studentName+""+major);
        Major major1=new Major(major,"4");
        Student student =new Student();
        student.setStudentId(studentId);
        student.setStudentName(studentName);
        student.setMajor(major1);
        ResultInfo<Student> resultInfo = studentService.addStudent(student);
        return "redirect:student_list";
    }

    @RequestMapping("admin/updateStu")
    public String updateStu(String id,Model model,HttpSession session){
        ResultInfo<Student> resultInfo = studentService.getStudentById(id);
        Student student = resultInfo.getResultObj();
        List<Grade> grades = student.getGradeList();
        model.addAttribute("student",student);
        model.addAttribute("grades",grades);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        return "/admin/updateStudent";
    }

    @ResponseBody
    @RequestMapping("updateStudent")
    public void updateStudent(final String [] names,String id){
        ResultInfo<Student> resultInfo = studentService.getStudentById(id);
        Student student = resultInfo.getResultObj();
        List<Grade> grades = student.getGradeList();
        for(Grade g:grades){
            for(String s:names){
                if(g.getCourseName().equals(s.split(":")[0])){
                    g.setScore(Float.parseFloat(s.split(":")[1]));
                }
            }

        }

        student.setGradeList(grades);
        studentService.updateStudent(student);

    }

    @ResponseBody
    @RequestMapping("admin/deleteStu")
    public void deleteStu(String id){
        ResultInfo<Student> resultInfo = studentService.deleteStudentById(id);

    }


    @RequestMapping("admin/addCourse")
    public String addCourse(String id,String courseName,
                          String courseType,float courseCredit,float score){
        /**记录学生当前获得学分*/
        float sum=0;
        Course course=null;
        ResultInfo<Student> resultInfo = studentService.getStudentById(id);
        Student student = resultInfo.getResultObj();
        Grade grade =new Grade(courseName,courseType,id,score);
        if(student.getMajor()==null){
              course =new Course(courseName,"软件工程",courseType,courseCredit);
        }
        else{
              course =new Course(courseName,student.getMajor().getMajorName(),courseType,courseCredit);
        }

        ResultInfo<Course> resultInfo1 = courseService.findCouseByNameAndType(courseName, courseType);
        Course resultObj = resultInfo1.getResultObj();
        System.out.println(resultObj);
        if(resultObj==null){
            courseService.addCourse(course);
        }
        student.getGradeList().add(grade);
        for(Grade g:student.getGradeList()){
            ResultInfo<Course> resultInfo2 = courseService.findCouseByNameAndType(g.getCourseName(), g.getCourseType());
            Course c = resultInfo2.getResultObj();
            float cs = c.getCourseCredit();
            sum+=cs;
        }
        student.setAcquireCredit(sum);
        studentService.updateStudent(student);
        return "studentGrade";
    }
}
