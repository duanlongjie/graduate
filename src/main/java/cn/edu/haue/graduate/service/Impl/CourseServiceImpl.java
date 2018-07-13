package cn.edu.haue.graduate.service.Impl;

import cn.edu.haue.graduate.constant.ResultCode;
import cn.edu.haue.graduate.dao.CourseDao;
import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

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
}
