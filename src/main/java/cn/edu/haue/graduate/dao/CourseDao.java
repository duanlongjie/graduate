package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 杨晋升 on 2018/5/29.
 */
public interface CourseDao extends JpaRepository<Course, Integer> {
}
