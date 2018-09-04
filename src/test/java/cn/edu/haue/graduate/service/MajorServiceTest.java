package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.dao.MajorDao;
import cn.edu.haue.graduate.entity.Major;
import cn.edu.haue.graduate.entity.MajorKey;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * created by dlj on 2018/9/3 17:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MajorServiceTest {
    @Resource
    private MajorService majorService;
    @Resource
    private MajorDao majorDao;
    @Resource
    private StudentService studentService;
    @Test
    public void getAllMajors() {
        ResultInfo<List<Major>> resultInfo = majorService.getAllMajors();
        List<Major> majors = resultInfo.getResultObj();
        System.out.println("--------------------");
        for(Major m:majors){
            System.out.println(m.getMajorName());
        }
    }

    @Test
    public void getByMajorkey() {
        String name="物联网";String years="4";
        MajorKey m=new MajorKey();
        m.setMajorName(name); m.setMajorYears(years);
        Optional<Major> op = majorDao.findById(m);
        Major major = op.get();
        System.out.println(major);
        Student s=new Student("111","张三");
        s.setMajor(major);
        studentService.addStudent(s);

    }

    @Test
    public void getByMajorByname() {
        Major ma = majorDao.getMajorByMajorName("物联网");
        System.out.println(ma);

    }
}