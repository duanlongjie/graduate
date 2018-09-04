package cn.edu.haue.graduate.web;

import cn.edu.haue.graduate.entity.*;
import cn.edu.haue.graduate.service.*;
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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    @Resource
    private MajorService majorService;

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
        List<GradeInfo> gradeInfos=new ArrayList<>();//传到前端的结果
        for(Grade grade:gradeList){
            String courseName=grade.getCourseName();
            String courseType=grade.getCourseType();
            Course course=courseService.findCouseByNameAndType(courseName, courseType).getResultObj();
            float courseCredit=course.getCourseCredit();
            GradeInfo gradeInfo=new GradeInfo(courseName,courseType,courseCredit,grade.getScore());
            gradeInfos.add(gradeInfo);
        }
        model.addAttribute("gradeInfos", gradeInfos);
        model.addAttribute("student", student);
        System.out.println(gradeInfos);
        return "/student/grade";
    }

    // 跳转 学生列表页   分页显示
    @RequestMapping("admin/student_list")
    public String getStudentList(Model model,HttpSession session, Integer pager){
        if (pager == null) {
            pager = 0;
        }
        ResultInfo<Page<Student>> resultInfo = studentService.findAllByIsDelete(pager, 10);
        Page<Student> page = resultInfo.getResultObj();
        List<Student> students = page.getContent();
        int totalPages = page.getTotalPages();
        ResultInfo<List<Major>> resultInfo1 = majorService.getAllMajors();
        List<Major> majors = resultInfo1.getResultObj();
        model.addAttribute("majors",majors);
        model.addAttribute("students",students);
        model.addAttribute("currentPage", pager + 1);
        model.addAttribute("totalPage", totalPages);
        ResultInfo<List<String>> resultInfo2 = majorService.getMajorNames();
        ResultInfo<List<String>> resultInfo3 = majorService.getMajorYears();
        List<String> names = resultInfo2.getResultObj();
        List<String> years = resultInfo3.getResultObj();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        model.addAttribute("names", names);
        model.addAttribute("years", years);
        return "/admin/student";
    }

    @RequestMapping("admin/studentGrade")
    public String s(Model model,String id,HttpSession session){
        ResultInfo<Student> resultInfo = studentService.getStudentById(id);
        Student student = resultInfo.getResultObj();
        List<Grade> grades = student.getGradeList();
        model.addAttribute("student",student);
        model.addAttribute("grades",grades);
        ResultInfo<List<String>> resultInfo1 = courseService.findAllType();
        List<String> types = resultInfo1.getResultObj();
        model.addAttribute("types",types);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        return "/admin/studentGrade";
    }

    @RequestMapping("admin/addStu")
    public String addStu(String studentId,String studentName,String majorName,String majorYear){
        ResultInfo<Major> resultInfo1= majorService.getByMajorkey(majorName,majorYear);
        Major major = resultInfo1.getResultObj();
        Student student =new Student();
        student.setStudentId(studentId);
        student.setStudentName(studentName);
        student.setMajor(major);
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

        String[] split = courseType.split(",");
        System.out.println(split[1]);
        /**记录学生当前获得学分*/
        float sum=0;
        Course course=null;
        ResultInfo<Student> resultInfo = studentService.getStudentById(id);
        Student student = resultInfo.getResultObj();
        Grade grade =new Grade(courseName,split[1],id,score);
        if(student.getMajor()==null){
              course =new Course(courseName,"软件工程",split[1],courseCredit);
        }
        else{
              course =new Course(courseName,student.getMajor().getMajorName(),split[1],courseCredit);
        }

        ResultInfo<Course> resultInfo1 = courseService.findCouseByNameAndType(courseName, split[1]);
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

    /**
     * 筛选挂科分数 大于该分的同学 并显示到页面 点击详情 查看信息
     * @param score
     */
    @RequestMapping("admin/select_score")
    public String select_score(float score,Model model,HttpSession session){
        ResultInfo<List<Student>> resultInfo = studentService.getLoseCourseCreditOver(score);
        List<Student> students = resultInfo.getResultObj();
        int len = students.size();
        model.addAttribute("students",students);
        model.addAttribute("len",len);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        return "/admin/dangerStudent";

    }
    @RequestMapping("admin/studentDetails")
    public String studentDetails(String id,Model model,HttpSession session){
        ResultInfo<Student> resultInfo = studentService.getStudentById(id);
        Student student = resultInfo.getResultObj();
        Major major = student.getMajor();
        ResultInfo<Float> r1 = studentService.getObtainPublicElectiveCourse(id);
        ResultInfo<Float> r2 = studentService.getLosePublicElectiveCourse(id);
        ResultInfo<Float> r3 = studentService.getObtainPECourse(id);
        ResultInfo<Float> r4 = studentService.getLosePECourse(id);
        ResultInfo<Float> r5 = studentService.getObtainPublicBasicCourses(id);
        ResultInfo<Float> r6 = studentService.getLosePublicBasicCourses(id);
        ResultInfo<Float> r7 = studentService.getObtainPracticeCourse(id);
        ResultInfo<Float> r8 = studentService.getLosePracticeCourse(id);
        ResultInfo<Float> r9 = studentService.getObtainMajorCompulsoryCourses(id);
        ResultInfo<Float> r10 = studentService.getLoseMajorCompulsoryCourses(id);
        ResultInfo<Float> r11 = studentService.getObtainMajorElectiveCourse(id);
        ResultInfo<Float> r12 = studentService.getLoseMajorElectiveCourse(id);
        //挂掉的学分总和  专业必修+专业选修+实践
        float loseMajorCredit= r10.getResultObj()+r12.getResultObj()+r8.getResultObj();
       //公共课学分
        float publicCredit=r1.getResultObj()+r5.getResultObj();
        //获得体育课学分
        float PECredit=r3.getResultObj();
        //专业课学分
        float majorCredit= r9.getResultObj()+r11.getResultObj()+r7.getResultObj();
        model.addAttribute("major",major);
        model.addAttribute("student",student);
        model.addAttribute("loseMajorCredit",loseMajorCredit);
        model.addAttribute("publicCredit",publicCredit);
        model.addAttribute("PECredit",PECredit);
        model.addAttribute("majorCredit",majorCredit);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        return "/admin/studentDetail";

    }

}
