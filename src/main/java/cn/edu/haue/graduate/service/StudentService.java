package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 杨晋升 on 2018/5/30.
 */
@Service
@Transactional
public interface StudentService {
    /**
     * 添加以为学生
     * @param student 要添加的学生
     * @return 返回 添加学生的信息
     */
    ResultInfo<Student> addStudent(Student student);

    /**
     * 根据id 删除 学生信息
     * @param id   学生Id
     * @return  返回删除的学生信息
     */
    ResultInfo<Student> deleteStudentById(String id);

    /**
     * 根据id 获取学生信息
     * @param id 学生id
     * @return  返回学生信息
     */
    ResultInfo<Student> getStudentById(String id);

    /**
     * 根据学生姓名 查找 学生列表
     * @param name 学生姓名
     * @return  学生列表
     */
    ResultInfo<List<Student>> getStudentByName(String name);

    /**
     * 更新学生信息
     * @param student 学生信息
     * @return 返回更新后的学生信息
     */
    ResultInfo<Student> updateStudent(Student student);

}
