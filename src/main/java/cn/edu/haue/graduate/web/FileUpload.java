package cn.edu.haue.graduate.web;

import cn.edu.haue.graduate.dao.CourseDao;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.*;
import cn.edu.haue.graduate.service.AdminService;
import cn.edu.haue.graduate.service.CourseService;
import cn.edu.haue.graduate.service.StudentService;
import cn.edu.haue.graduate.utils.ExcelUtil;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理文件上传
 */

@Controller
@RequestMapping("admin")
public class FileUpload {
//    @Resource
//    private StudentDao studentDao;
//    @Resource
//    private CourseDao courseDao;
    @Resource
    private StudentService studentService;
    @Resource
    private CourseService courseService;

    @Resource
    private AdminService adminService;
    @RequestMapping("import")
    public String test(){
        return "admin/excelImport";
    }

    //处理文件上传
    @RequestMapping(value="studentDeal", method = RequestMethod.POST)
    public
    String uploadDeal(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request,HttpSession session,Model model) {
        InputStream inputStream =null;
        Map<String,String> map =new HashMap<>();
        map.put("学号","studentId"); map.put("姓名","studentName");
        map.put("获得学分","acquireCredit");
        List<Student> students=null;
        try{
            inputStream = file.getInputStream();
            students= ExcelUtil.getEntityList(new Student(), inputStream, map);
          // 获取 excel 文件 的 输入流对象

        }catch (Exception e){
            e.printStackTrace();
        }
        for(Student s:students){
            studentService.addStudent(s);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
    return "admin/home";
    }

    //处理文件上传
    @RequestMapping(value="gradeDeal", method = RequestMethod.POST)
    public
    String uploadDeal2(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request,HttpSession session,Model model) {
        InputStream inputStream =null;
        List<Student> students =null;

        try{
            inputStream = file.getInputStream();
            ResultInfo<List<Student>> resultInfo = studentService.getAllStudent();
            List<Student> all = resultInfo.getResultObj();
            students= ExcelUtil.getEntityListIncludeGrade(all, inputStream, "学号");
            // 获取 excel 文件 的 输入流对象

        }catch (Exception e){
            e.printStackTrace();
        }
        for(Student s:students){
           studentService.addStudent(s);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        return "admin/home";

    }



    @RequestMapping(value="courseDeal", method = RequestMethod.POST)
    public
    String uploadDeal3(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request,HttpSession session,Model model) {
        InputStream inputStream =null;
        List<String> filedNames =null;

        try{
            inputStream = file.getInputStream();
             filedNames = ExcelUtil.getFiledNames(inputStream);
            // 获取 excel 文件 的 输入流对象

        }catch (Exception e){
            e.printStackTrace();
        }
        for(String s:filedNames){
            Course c =new Course(s.split("/")[0],"软件工程",s.split("/")[1],
                    Float.parseFloat(s.split("/")[2]));
            courseService.addCourse(c);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        return "admin/home";

    }

    @RequestMapping("course")
    public String course(Model model, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        return "admin/courseImport";
    }

    @RequestMapping("student")
    public String student(Model model, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        return "admin/studentImport";
    }

    @RequestMapping("grade")
    public String grade(Model model, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminService.getAdminByUsername(authentication.getName());
        model.addAttribute("currentAdmin", admin);
        return "admin/gradeImport";
    }

}
