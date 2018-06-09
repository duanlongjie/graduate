package cn.edu.haue.graduate.utils;

import cn.edu.haue.graduate.entity.Student;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExcelUtilTest {
    //测试将  数据写入excel表格
    @Test
    public void test1() throws Exception{
        List<Student> list=new ArrayList<>();
        for(int i=0 ;i<10; i++){
            list.add(new Student(""+(i+1),"tom"+i,"123"+i));
        }
        System.out.println("===========");
        for(Student s:list){
            System.out.println(s);
        }
        System.out.println("________________");
        LinkedHashMap<String,String> filedMap=new LinkedHashMap<>();
        filedMap.put("studentId","id");  filedMap.put("studentName","姓名");
        filedMap.put("password","密码"); filedMap.put("gradeList","成绩");
        File file=new File("C:/各种软件/学生表.xls");
        OutputStream out=new FileOutputStream(file);
        ExcelUtil.<Student> listToExcel(list,filedMap,"学生表",1000,out);
    }

}
