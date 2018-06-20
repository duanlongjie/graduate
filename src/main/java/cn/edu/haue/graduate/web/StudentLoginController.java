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
import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/student")
public class StudentLoginController {

    @Resource
    private StudentLoginService studentLoginService;

    //主页
    @RequestMapping("/")
    public String studentToLogin(){
        return "student/login";
    }

    //登陆
    @ResponseBody
    @RequestMapping("login")
    public  ResultInfo<Student> studentLogin(@RequestParam("username") String studentId, @RequestParam("passwords")String passwords){
        System.out.println(studentId +"--" +passwords);
        ResultInfo<Student> studentResultInfo = studentLoginService.studentLogin(studentId, passwords);
        return studentResultInfo;
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
