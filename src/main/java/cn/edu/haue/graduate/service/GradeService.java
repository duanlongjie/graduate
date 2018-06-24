package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;

import java.util.List;

/**
 * 成绩类业务层
 * Created by 李东奎
 */
public interface GradeService {

    //根据学生id 查询学生的成绩表
    ResultInfo<List<Grade>> getGradeList(Student student);

    //根据学生id 修改学生成绩
    ResultInfo<Grade> updateGrade(Grade grade);

    //添加学生成绩
    ResultInfo<Grade> addGrade(Grade grade, Student student);

    //删除学生成绩
    ResultInfo<Grade> delGrade(String id, Student student);




}
