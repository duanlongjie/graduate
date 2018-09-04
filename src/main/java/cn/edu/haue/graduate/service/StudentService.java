package cn.edu.haue.graduate.service;


import cn.edu.haue.graduate.entity.Course;
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

    /**
     * 获取公共选修课获得 学分和
     * @return
     */
    ResultInfo<Float> getObtainPublicElectiveCourse(String id);
    /**
     * 获取公共选修课挂科 学分和
     * @return
     */
    ResultInfo<Float> getLosePublicElectiveCourse(String id);

    /**
     * 获取体育课获得 学分和
     * @return
     */
    ResultInfo<Float> getObtainPECourse(String id);
    /**
     * 获取体育课挂科 学分和
     * @return
     */
    ResultInfo<Float> getLosePECourse(String id);
    /**
     * 获取公共基础课获得 学分和
     * @return
     */
    ResultInfo<Float> getObtainPublicBasicCourses(String id);
    /**
     * 获取公共基础课挂科 学分和
     * @return
     */
    ResultInfo<Float> getLosePublicBasicCourses(String id);
    /**
     * 获取实践课获得 学分和
     * @return
     */
    ResultInfo<Float> getObtainPracticeCourse(String id);
    /**
     * 获取实践课挂科 学分和
     * @return
     */
    ResultInfo<Float> getLosePracticeCourse(String id);
    /**
     * 获取专业必修课获得 学分和
     * @return
     */
    ResultInfo<Float> getObtainMajorCompulsoryCourses(String id);
    /**
     * 获取专业必修课挂科 学分和
     * @return
     */
    ResultInfo<Float> getLoseMajorCompulsoryCourses(String id);
    /**
     * 获取专业选修课获得 学分和
     * @return
     */
    ResultInfo<Float> getObtainMajorElectiveCourse(String id);
    /**
     * 获取专业选修课挂科 学分和
     * @return
     */
    ResultInfo<Float> getLoseMajorElectiveCourse(String id);

    /**
     * 获取挂掉分数超过 一定分数的同学
     * @param score
     * @return
     */
    ResultInfo<List<Student>> getLoseCourseCreditOver(float score);


}
