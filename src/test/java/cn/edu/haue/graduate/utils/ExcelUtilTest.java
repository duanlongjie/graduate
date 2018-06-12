package cn.edu.haue.graduate.utils;

import cn.edu.haue.graduate.entity.Student;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.junit.Test;

import java.io.*;
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

    @Test
    public void excelToListTest() throws Exception{
        InputStream inputStream=new FileInputStream("C:/各种软件/学生表.xls");
        Workbook book=Workbook.getWorkbook(inputStream);
        System.out.println(book);
        Sheet sheet=book.getSheet(0);//取得第一个工作表，也可用sheet名字获得。
        int len=sheet.getRows();//取得行数
        System.out.println("len:"+len);
        for(int i=1;i<len;i++){//从1开始，避免插入标题
            //System.out.println(i);
            Cell[] cells = sheet.getRow(i); //获取列数

            for(int j=0;j<cells.length;j++){//打印每行信息
                String contents = cells[j].getContents();
                System.out.println("con:"+contents);
            }
            System.out.println();
        }
    }
//    @Test
//    public void testexcelToList() throws Exception{
//        List<User> users = ExcelUtil.<User>excelToList("C:/各种软件/学生表.xls", new User());
//        for (User user:users){
//            System.out.println(user);
//        }
//    }
}
