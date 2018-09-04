package cn.edu.haue.graduate.config;

import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.service.CourseService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by dlj on 2018/9/3 21:36
 */
@Component
@Order(value = 1)
public class DataInitialize implements ApplicationRunner {
    @Resource
    private CourseService courseService;
    @Resource
    private ServletContext application;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //项目启动 完成 将 课程数据放入应用缓存
        Map<String,Float> courseMap=new HashMap<>();
        ResultInfo<List<Course>> resultInfo = courseService.getAll();
        List<Course> courses = resultInfo.getResultObj();
        for(Course c:courses){
            courseMap.put(c.getCourseName(),c.getCourseCredit());
        }
        application.setAttribute("courses",courses);
        application.setAttribute("courseMap",courseMap);
    }
}
