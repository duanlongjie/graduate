package cn.edu.haue.graduate.web;

import cn.edu.haue.graduate.constant.ResultCode;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.entity.StudentCreditResult;
import cn.edu.haue.graduate.service.GraduateOrNotService;
import cn.edu.haue.graduate.service.StudentLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentLoginController {

    @Resource
    private StudentLoginService studentLoginService;
    @Resource
    GraduateOrNotService graduateOrNotService;

    //主页
    @RequestMapping("/")
    public String studentToLogin(){
        return "student/login";
    }

    //登陆
    @RequestMapping("login")
    public String studentLogin(@PathVariable("username") String studentId, @PathVariable("passwords")String passwords, HttpSession httpSession, Model model){
        System.out.println(studentId +"--" +passwords);
        ResultInfo<Student> studentResultInfo = studentLoginService.studentLogin(studentId, passwords);
        httpSession.setAttribute("student",studentResultInfo.getResultObj());
        //增加毕业条件数据
        StudentCreditResult studentCreditResult = graduateOrNotService.GraduateOrNot(studentId);
        model.addAttribute("studentCreditResult", studentCreditResult);
        System.out.println(studentCreditResult);
        return "student/graduationConditions";  //进入首页

    }

    //修改密码页面
    @RequestMapping("updatePage")
    public String updateStudent(){
        return "student/updatepwd";
    }

    //修改密码
    @ResponseBody
    @RequestMapping("update")
    public ResultInfo<Student> studentUpdate(@RequestParam("username")String studentId,@RequestParam("oldpasswords")String oldpasswords,@RequestParam("passwords")String passwords){
        ResultInfo<Student> studentResultInfo = studentLoginService.studentUpdate(studentId, oldpasswords, passwords);
        return studentResultInfo;
    }
}
