package cn.edu.haue.graduate.service.Impl;

import cn.edu.haue.graduate.constant.CourseType;
import cn.edu.haue.graduate.constant.ResultCode;
import cn.edu.haue.graduate.constant.StudentResultMessage;
import cn.edu.haue.graduate.constant.StudentStatus;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Resource
    private  ServletContext application;
    @Resource
    private StudentDao studentDao;
    private  Map<String,Float> courseMap =new HashMap<>();


    @Override
    public ResultInfo<Student> addStudent(Student student) {
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
//            if (student.getAcquireCredit()==null){
//                float ac=0;
//                student.setAcquireCredit(ac);
//            }
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
        return resultInfo;
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


    @Override
    public ResultInfo<Float> getObtainPublicElectiveCourse(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.Public_Elective_Course)){
                float score = g.getScore();
                if(score>=60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getLosePublicElectiveCourse(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }
        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.Public_Elective_Course)){
                float score = g.getScore();
                if(score<60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getObtainPECourse(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.PE)){
                float score = g.getScore();
                if(score>=60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getLosePECourse(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.PE)){
                float score = g.getScore();
                if(score<60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getObtainPublicBasicCourses(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.Public_Basic_Course)){
                float score = g.getScore();
                if(score>=60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getLosePublicBasicCourses(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.Public_Basic_Course)){
                float score = g.getScore();
                if(score<60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getObtainPracticeCourse(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.Practice_Course)){
                float score = g.getScore();
                if(score>=60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getLosePracticeCourse(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.Practice_Course)){
                float score = g.getScore();
                if(score<60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getObtainMajorCompulsoryCourses(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.Required_Course)){
                float score = g.getScore();
                if(score>=60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getLoseMajorCompulsoryCourses(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.Required_Course)){
                float score = g.getScore();
                if(score<60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getObtainMajorElectiveCourse(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.Elective_Course)){
                float score = g.getScore();
                if(score>=60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Float> getLoseMajorElectiveCourse(String id) {
        Object object = application.getAttribute("courseMap");
        if(object instanceof Map){
            courseMap=(Map<String,Float>)object;
        }

        ResultInfo<Float> resultInfo=new ResultInfo<>();
        Optional<Student> optional = studentDao.findById(id);
        Float sum=0f;
        Student student = optional.get();
        List<Grade> gradeList = student.getGradeList();
        for(Grade g:gradeList){
            if(g.getCourseType().equals(CourseType.Elective_Course)){
                float score = g.getScore();
                if(score<60){
                    Float c = courseMap.get(g.getCourseName());
                    sum+=c;
                }
            }
        }
        resultInfo.setResultObj(sum);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<List<Student>> getLoseCourseCreditOver(float score) {
        ResultInfo<List<Student>> resultInfo =new ResultInfo<>();
        //用于存放 挂科的同学
        List<Student> students =new ArrayList<>();
        List<Student> studentList = studentDao.findAll();

        for(Student s:studentList){
            ResultInfo<Float> r1 = getLoseMajorCompulsoryCourses(s.getStudentId());//获取 挂掉专业必修课学分和
            ResultInfo<Float> r2 = getLoseMajorElectiveCourse(s.getStudentId());//获取 挂掉专业选修课学分和
            ResultInfo<Float> r3=getLosePracticeCourse(s.getStudentId());//获取 挂掉实践课学分和
            float sum=0l;
            sum=r1.getResultObj()+r2.getResultObj()+r3.getResultObj();
            if(sum>=score){
            students.add(s);
            }
        }
        if(students.size()==0){
            System.out.println("没有符合以上要求的学生!");
        }
        resultInfo.setResultObj(students);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<List<String>> getAllId() {
        ResultInfo<List<String>> resultInfo =new ResultInfo<>();
        List<String>  ids =new ArrayList<>();
         List<Student> students = studentDao.findAll();
         for(Student s:students){
             ids.add(s.getStudentId());
         }
         resultInfo.setResultObj(ids);
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
