package cn.edu.haue.graduate.service;


import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;

/**
 * 学生登陆
 */
public interface StudentLoginService {

    //登陆
    public abstract ResultInfo<Student> studentLogin(String studentId,String passwords);


    //修改密码
    public abstract ResultInfo<Student> studentUpdate(String studentId,String oldpwd,String pwd);
}
