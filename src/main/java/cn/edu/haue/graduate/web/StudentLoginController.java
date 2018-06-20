package cn.edu.haue.graduate.web;

import cn.edu.haue.graduate.constant.ResultCode;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.service.StudentLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class StudentLoginController {

    @Resource
    private StudentLoginService studentLoginService;

    //主页
    @RequestMapping("/student/index")
    public String studentToLogin(){
        return "student/login";
    }

    //登陆
    @RequestMapping("/student/login")
    public String studentLogin(@RequestParam("username")String studentId,@RequestParam("passwords")String passwords,HttpSession httpSession){
        System.out.println(studentId +"--" +passwords);
        ResultInfo<Student> studentResultInfo = studentLoginService.studentLogin(studentId, passwords);
        httpSession.setAttribute("student",studentResultInfo.getResultObj());
        return "graduationCon";
    }

    //修改密码页面
    @RequestMapping("/student/updatePage")
    public String updateStudent(){
        return "student/updatepwd";
    }

    //修改密码
    @ResponseBody
    @RequestMapping("/student/update")
    public ResultInfo<Student> studentUpdate(@RequestParam("username")String studentId,@RequestParam("oldpasswords")String oldpasswords,@RequestParam("passwords")String passwords){
        ResultInfo<Student> studentResultInfo = studentLoginService.studentUpdate(studentId, oldpasswords, passwords);
        return studentResultInfo;
    }
}
