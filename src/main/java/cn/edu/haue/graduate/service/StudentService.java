package cn.edu.haue.graduate.service;


import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * Author:dlj
 */
public interface StudentService {
    /**
     * 添加一位学生
     * @param student 学生信息
     * @return  返回添加后的学生信息
     */
    ResultInfo<Student> addStudent(Student student);

    /**
     * 删除一位学生
     * @param id 要删除学生的id
     * @return 返回删除学生的信息
     */
    ResultInfo<Student> deleteStudentById(String id);

    /**
     * 根据id 获取一位学生
     * @param id 学生id
     * @return 返回学生信息
     */
    ResultInfo<Student> getStudentById(String id);

    /**
     * 修改 学生信息
     * @param student 学生信息
     * @return 返回修改后的学生信息
     */
    ResultInfo<Student> updateStudent(Student student);

    /**
     *  获取所有学生列表 (没有删除的)
     * @return 返回所有学生列表信息
     */
    ResultInfo<List<Student>> getAllStudent();


    ResultInfo<Page<Student>> findAllByPage(Integer pageNo,Integer pageSize);

    ResultInfo<Page<Student>> findAllByIsDelete(Integer pageNo,Integer pageSize);

}
