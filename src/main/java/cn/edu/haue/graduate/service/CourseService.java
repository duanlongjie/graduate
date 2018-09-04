package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.ResultInfo;

import java.util.List;

/**
 * create on 2018.7.13
 */
public interface CourseService {
    ResultInfo<Course> addCourse(Course course);
    ResultInfo<Course> findCouseByNameAndType(String name,String type);
    /**
     * 查询课程所有类型
     * @return 返回类型集合
     */
    ResultInfo<List<String>> findAllType();

    /**
     * 获取所有课程
     * @return
     */
    ResultInfo<List<Course>> getAll();
}
