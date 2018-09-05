package cn.edu.haue.graduate.service.Impl;

import cn.edu.haue.graduate.constant.ResultCode;
import cn.edu.haue.graduate.dao.CourseDao;
import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.CourseKey;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;
    @Override
    public ResultInfo<Course> addCourse(Course course) {
        ResultInfo<Course> resultInfo =new ResultInfo<>();
        Course save = courseDao.save(course);

        resultInfo.setResultObj(save);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Course> findCouseByNameAndType(String name, String type) {
        ResultInfo<Course> resultInfo =new ResultInfo<>();
        Course course = courseDao.findByCourseNameAndCourseType(name, type);
        resultInfo.setResultObj(course);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<List<String>> findAllType() {
        ResultInfo<List<String>> resultInfo =new ResultInfo<>();
        List<String> types=new ArrayList<>();
        List<Course> courses = courseDao.findAll();
        for(Course c:courses){
            if(!types.contains(c.getCourseType())){
                types.add(c.getCourseType());
            }
        }
        resultInfo.setResultObj(types);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<List<Course>> getAll() {
        ResultInfo<List<Course>> resultInfo=new ResultInfo<>();
        List<Course> courses = courseDao.findAll();
        resultInfo.setResultObj(courses);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Course> findByKey(String name,String type,String majorName) {
        ResultInfo<Course> resultInfo =new ResultInfo<>();
        CourseKey key =new CourseKey();
        key.setCourseName(name);
        key.setCourseType(type);
        key.setMajorName(majorName);
        Optional<Course> op = courseDao.findById(key);
        if(op==null){
            resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
            return resultInfo;
        }
        Course course = op.get();
        resultInfo.setResultObj(course);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Boolean> judgeCouseExist(String name, String type, String majorName) {
        ResultInfo<Boolean> resultInfo =new ResultInfo<>();
        //默认课程不存在
        boolean flag=false;
        List<Course> courses = courseDao.findAll();
        for(Course c:courses){
            if(c.getCourseName().equals(name) && c.getCourseType().equals(type) && c.getMajorName().equals(majorName)){
                flag =true;
                break;
            }
        }
        resultInfo.setResultObj(flag);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }
}
