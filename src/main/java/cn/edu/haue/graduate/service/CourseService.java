package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.ResultInfo;

/**
 * create on 2018.7.13
 */
public interface CourseService {
    ResultInfo<Course> addCourse(Course course);
    ResultInfo<Course> findCouseByNameAndType(String name,String type);
}
