package cn.edu.haue.graduate.service.Impl;

import cn.edu.haue.graduate.constant.ResultCode;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.service.StudentLoginService;
import cn.edu.haue.graduate.utils.PasswordEncrypter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * 登陆实体类
 */
@Transactional
@Service
public class StudentLoginServiceImpl implements StudentLoginService {

    @Resource
    private StudentDao studentDao;

    //登陆
    @Override
    public ResultInfo<Student> studentLogin(String studentId, String passwords) {
        ResultInfo<Student> studentResultInfo = new ResultInfo<>();
        try {
            boolean flag = studentDao.existsById(studentId);
            if (flag == false) {
                studentResultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
                studentResultInfo.setResultMessage("学号或密码错误");
            } else {
                Student student = studentDao.findById(studentId).get();
                String username = student.getStudentId();
                String password = student.getPassword();
                //passwords为用户输入密码 password为数据库中查询密码
                boolean isLogin = PasswordEncrypter.getPasswordEncoder().matches(passwords, password);
                if (username.equals(studentId) && isLogin) {
                    studentResultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
                    studentResultInfo.setResultMessage("登陆成功");
                    studentResultInfo.setResultObj(student);
                } else {
                    studentResultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
                    studentResultInfo.setResultMessage("学号或密码错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            studentResultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
            studentResultInfo.setResultMessage("服务器出现错误,请刷新页面");
        }
        return studentResultInfo;
    }

    //修改

    @Override
    public ResultInfo<Student> studentUpdate(String studentId, String oldpwd, String pwd) {
        ResultInfo<Student> studentResultInfo = new ResultInfo<>();
        try {
            boolean stu = studentDao.existsById(studentId);
            if (stu == false) {
                studentResultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
                studentResultInfo.setResultMessage("学号不存在");
            } else {
                Student student = studentDao.findById(studentId).get();
                String pwd_flag = student.getPassword();
                boolean matches = PasswordEncrypter.getPasswordEncoder().matches(oldpwd, pwd_flag);
                if (matches == true && student.getStudentId().equals(studentId)) {
                    String encode = PasswordEncrypter.getPasswordEncoder().encode(pwd);
                    student.setPassword(encode);
                    studentDao.save(student);
                    studentResultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
                    studentResultInfo.setResultMessage("修改成功");
                }else{
                    studentResultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
                    studentResultInfo.setResultMessage("密码错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            studentResultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
            studentResultInfo.setResultMessage("系统错误,无法修改密码！");
        }
        return studentResultInfo;
    }
}
