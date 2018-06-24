package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Resource
    private StudentService studentService;
    @Test
    public void getStudentByIdTest(){
        ResultInfo<Student> re = studentService.getStudentById("201612211315");
        Student student = re.getResultObj();
        System.out.println(student);
    }

    @Test
    public void getStudentGradeByIdTest(){
        ResultInfo<Student> re = studentService.getStudentById("201612211415");
        Student student = re.getResultObj();
//        System.out.println(student.getGradeList());
        for(Grade x:student.getGradeList()){
            System.out.println(x.getCourse());
        }
    }

    @Test
    public void addStudentTest(){
        Student student=new Student("201612211122","段龙杰","123");
        ResultInfo<Student> resultInfo = studentService.addStudent(student);

    }

    @Test
    public void updateStudentTest(){
        Student student=new Student("201612211122","段龙杰","1234");
        ResultInfo<Student> resultInfo = studentService.updateStudent(student);

    }

    @Test
    public void deleteStudentByIdTest(){
        ResultInfo<Student> resultInfo = studentService.deleteStudentById("201612211122");
    }
}
