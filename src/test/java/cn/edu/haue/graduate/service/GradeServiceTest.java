package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.constant.CourseType;
import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.utils.PasswordEncrypter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeServiceTest {

    @Autowired GradeService gradeService;

    @Test
    public void getGradeList(){
       // Student student = new Student("201612211415","杨晋升","$10$BFdncAFbsht2PMZMls3yNulzGpcK.gG.IVPNR1oABvPYnEsfR0DV2");
        Student student = new Student("201612211302","李东奎","123");

        ResultInfo<List<Grade>> resultInfo = gradeService.getGradeList(student);
        List<Grade> gradeList = resultInfo.getResultObj();
        System.out.println(gradeList);


    }

    @Test
    public void updateGrade() {
        Grade grade = new Grade();
        grade.setGradeId("b883ca01-fe3f-4455-9ac3-d1f6dce83ead");
        grade.setScore(92f);
        ResultInfo<Grade> gradeResultInfo = gradeService.updateGrade(grade);
        Grade grade1 = gradeResultInfo.getResultObj();
        System.out.println(grade1);
    }

    @Test
    public void delGrade() {
        //Student student = new Student("201612211415","杨晋升","$10$BFdncAFbsht2PMZMls3yNulzGpcK.gG.IVPNR1oABvPYnEsfR0DV2");
        Student student = new Student("201612211302","李东奎","123");


        gradeService.delGrade("b883ca01-fe3f-4455-9ac3-d1f6dce83ead",student);

    }

    @Test
    public void addGrade() {

       // Student student = new Student("201612211415","杨晋升","$10$BFdncAFbsht2PMZMls3yNulzGpcK.gG.IVPNR1oABvPYnEsfR0DV2");
        Student student = new Student("201612211302","李东奎","123");
        Grade grade = new Grade();
        Course course = new Course(112221003,"数据结构",CourseType.Required_Course,3f);
        grade.setScore(89f);
        grade.setCourse(course);

        gradeService.addGrade(grade,student);

    }
}