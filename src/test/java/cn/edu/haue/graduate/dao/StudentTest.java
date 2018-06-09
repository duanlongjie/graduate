package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.Major;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.utils.PasswordEncrypter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by 杨晋升 on 2018/5/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

    @Resource
    private StudentDao studentDao;

    @Resource
    private CourseDao courseDao;

    @Resource
    private GradeDao gradeDao;

    @Test
    public void add() {

        Major major = new Major(201612211, "软件工程");

        Course course1 = new Course(112221003, "数据结构", "专业必修课", 5);
        Course course2 = new Course(112233035, "Java EE 开发技术", "专业选修课", 4);
        Grade grade1 = new Grade();
        grade1.setCourse(course1);
        grade1.setScore(77);
        grade1.setGradeId(UUID.randomUUID().toString());
        Grade grade2 = new Grade();
        grade2.setCourse(course2);
        grade2.setScore(96);
        grade2.setGradeId(UUID.randomUUID().toString());
        Student student = new Student("201612211415", "杨晋升", PasswordEncrypter.getPasswordEncoder().encode("123"));
        student.getGradeList().add(grade1);
        student.getGradeList().add(grade2);
        student.setMajor(major);
        studentDao.save(student);
    }

    @Test
    public void get() {
        List<Student> students = studentDao.findAll();
        for (Student student : students) {
            List<Grade> gradeList = student.getGradeList();
            for (Grade grade : gradeList) {
                System.out.println(grade);
            }
        }
    }
}
