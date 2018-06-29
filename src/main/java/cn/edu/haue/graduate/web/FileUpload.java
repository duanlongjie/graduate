package cn.edu.haue.graduate.web;

import cn.edu.haue.graduate.dao.CourseDao;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.utils.ExcelUtil;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理文件上传
 */

@Controller
public class FileUpload {
    @Resource
    private StudentDao studentDao;
    @Resource
    private CourseDao courseDao;
    @RequestMapping("test")
    public String test(){
        return "admin/excelImport";
    }

    //处理文件上传
    @RequestMapping(value="uploadDeal", method = RequestMethod.POST)
    public @ResponseBody
    void uploadDeal(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) {
        InputStream inputStream =null;
        Map<String,String> map =new HashMap<>();
        map.put("学号","studentId"); map.put("姓名","studentName");
        map.put("获得学分","acquireCredit");
        List<Student> students=null;

        try{
            inputStream = file.getInputStream();
            students= ExcelUtil.getEntityList(new Student(), inputStream, map);
          // 获取 excel 文件 的 输入流对象

        }catch (Exception e){
            e.printStackTrace();
        }
        for(Student s:students){
            studentDao.save(s);
        }

    }

    //处理文件上传
    @RequestMapping(value="uploadDeal2", method = RequestMethod.POST)
    public @ResponseBody
    void uploadDeal2(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) {
        InputStream inputStream =null;
        List<Student> students =null;

        try{
            inputStream = file.getInputStream();
            List<Student> all = studentDao.findAll();
            students= ExcelUtil.getEntityListIncludeGrade(all, inputStream, "学号");
            // 获取 excel 文件 的 输入流对象

        }catch (Exception e){
            e.printStackTrace();
        }
        for(Student s:students){
            studentDao.save(s);
        }

    }



    @RequestMapping(value="uploadDeal3", method = RequestMethod.POST)
    public @ResponseBody
    void uploadDeal3(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) {
        InputStream inputStream =null;
        List<String> filedNames =null;

        try{
            inputStream = file.getInputStream();
             filedNames = ExcelUtil.getFiledNames(inputStream);
            // 获取 excel 文件 的 输入流对象

        }catch (Exception e){
            e.printStackTrace();
        }
        for(String s:filedNames){
            Course c =new Course(s.split("/")[0],"软件工程",s.split("/")[1],
                    Float.parseFloat(s.split("/")[2]));
            courseDao.save(c);
        }

    }

}
