package cn.edu.haue.graduate.service.Impl;

import cn.edu.haue.graduate.constant.ResultCode;
import cn.edu.haue.graduate.constant.StudentResultMessage;
import cn.edu.haue.graduate.constant.StudentStatus;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;
    @Override
    public ResultInfo<Student> addStudent(Student student) {
        System.out.println("______________________________________________________");
        ResultInfo<Student> resultInfo=new ResultInfo<>();
        String message = checkStudentInfo(student);
        //信息不合法
        if(message!= null){
            resultInfo.setResultMessage(message);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
            return resultInfo;
        }
        else{
            //默认 添加的学生是可用的
            student.setIsDelete(StudentStatus.USEFUL);
            if (student.getAcquireCredit()==null){
                System.out.println("???????????????????????????????????????????????????????");
                float ac=0;
                student.setAcquireCredit(ac);
            }
            studentDao.save(student);
            resultInfo.setResultObj(student);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
            return resultInfo;
        }
    }

    @Override
    public ResultInfo<Student> deleteStudentById(String id) {
        ResultInfo<Student> resultInfo=new ResultInfo<>();
        if(id!=null){
        Optional<Student> optional = studentDao.findById(id);
        if(optional.get()!=null){
            Student student = optional.get();
            //逻辑删除
            student.setIsDelete(StudentStatus.DELETE);
            studentDao.save(student);
            resultInfo.setResultObj(optional.get());
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
            return resultInfo;
        }
        }
        return null;
    }

    @Override
    public ResultInfo<Student> getStudentById(String id) {
        ResultInfo<Student> resultInfo=new ResultInfo<>();
        if(id!= null){
            Optional<Student> optional = studentDao.findById(id);
            Student student = optional.get();
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
            resultInfo.setResultObj(student);
            return resultInfo;
        }
        return null;
    }

    @Override
    public ResultInfo<Student> updateStudent(Student student) {
        ResultInfo<Student> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(student.getStudentId());
        if(optional.get() ==null){
            System.out.println("不存在该学生信息！");
            resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
            resultInfo.setResultObj(student);
            return resultInfo;
        }
        String message = checkStudentInfo(student);
        if(message!= null){
        resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
        resultInfo.setResultObj(student);
        return resultInfo;
        }
        else{
            studentDao.save(student);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
            resultInfo.setResultObj(student);
            return resultInfo;
        }
    }

    @Override
    public ResultInfo<List<Student>> getAllStudent() {
        ResultInfo<List<Student>> resultInfo = new ResultInfo<>();
        List<Student> students = studentDao.getAllByIsDelete(StudentStatus.USEFUL);
        resultInfo.setResultObj(students);
        return resultInfo;
    }

    @Override
    public ResultInfo<Page<Student>> findAllByPage(Integer pageNo, Integer pageSize) {
        ResultInfo<Page<Student>> resultInfo =new ResultInfo<>();
        Pageable pageable =new PageRequest(pageNo,pageSize);
        Page<Student> page = studentDao.findAll(pageable);
        resultInfo.setResultObj(page);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Page<Student>> findAllByIsDelete(Integer pageNo, Integer pageSize) {
        ResultInfo<Page<Student>> resultInfo =new ResultInfo<>();
        Pageable pageable =new PageRequest(pageNo,pageSize);
        Page<Student> page = studentDao.findAllByIsDelete(pageable,StudentStatus.USEFUL);
        resultInfo.setResultObj(page);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    //检测学生信息 合法性
    public String checkStudentInfo(Student student){
        String name = student.getStudentName();
        if("".equals(name) || name==null){
            return StudentResultMessage.RESULT_MESSAGE_STU_NAME;
        }
        return null;
    }
}
