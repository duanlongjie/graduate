package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.CourseKey;
import cn.edu.haue.graduate.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 杨晋升 on 2018/5/29.
 */
public interface CourseDao extends JpaRepository<Course, CourseKey> {
    Course findCourseByCourseName(String courseName);
    Course findByCourseNameAndCourseType(String name,String type);

}
