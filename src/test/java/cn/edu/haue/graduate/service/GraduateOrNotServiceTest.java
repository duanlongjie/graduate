package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * Author: lnp
 * Date: 2018/6/14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class GraduateOrNotServiceTest {

    @Resource
    private GraduateOrNotService graduateOrNotService;

    @Test
    public void graduateOrNot() {
        ResultInfo<Student> resultInfo=graduateOrNotService.GraduateOrNot("201612211415");
        System.out.println("---------------"+resultInfo);
    }
}