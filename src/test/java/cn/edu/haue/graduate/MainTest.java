package cn.edu.haue.graduate;

import cn.edu.haue.graduate.dao.CourseDao;
import cn.edu.haue.graduate.dao.GradeDao;
import cn.edu.haue.graduate.dao.MajorDao;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.Major;
import cn.edu.haue.graduate.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

    @Resource
    private StudentDao studentDao;

    @Resource
    private CourseDao courseDao;

    @Resource
    private GradeDao gradeDao;

    @Resource
    private MajorDao majorDao;
    @Test
    public void load() {
        //1、读取excel文件名，如16软件工程
        Major major = new Major(201612211, "16软件工程", 16, 4, 30);
        majorDao.save(major);
        //2、读取表首行，课程信息，如Java EE 开发技术/专业选修课/4、线性代数A/专业必修课/3
        courseDao.save(new Course("Java EE 开发技术", "专业选修课",4, major));
        courseDao.save(new Course("线性代数A", "专业选修课",3, major));
        //3、读下一行
        //3.1、读该行前三列，如：1	201612211101	来亮亮,舍弃序号
        Student student1 = new Student("201612211101", "来亮亮");
        //3.2、继续读该行后面的列，有数据就停下来，获取该数据的所属列和所属行，如数据73属于列Java EE 开发技术/专业选修课/4
        Grade grade1 = new Grade();
        grade1.setStudent(student1);
        grade1.setScore(77);
        grade1.setCourse(courseDao.findCourseByCourseNameAndAndMajor("Java EE 开发技术", major));
        //3.3、继续读该行后面的列，有数据就停下来，获取该数据的所属列，如数据60属于列线性代数A/专业必修课/3
        Grade grade2 = new Grade();
        grade2.setStudent(student1);
        grade2.setScore(60);
        grade2.setCourse(courseDao.findCourseByCourseNameAndAndMajor("线性代数A", major));
        //3.4、把该行数据读完，得到grade1、grade2、grade3.........,把他们设置为该行学生的分数,然后存学生
        student1.getGradeList().add(grade1);
        student1.getGradeList().add(grade2);
        studentDao.save(student1);
        //4、继续读取下一行.........
    }
}
