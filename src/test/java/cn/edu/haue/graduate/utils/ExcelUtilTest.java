package cn.edu.haue.graduate.utils;

import cn.edu.haue.graduate.dao.CourseDao;
import cn.edu.haue.graduate.dao.GradeDao;
import cn.edu.haue.graduate.dao.MajorDao;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.Course;
import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.Major;
import cn.edu.haue.graduate.entity.Student;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelUtilTest {

    @Resource
    private StudentDao studentDao;

    @Resource
    private CourseDao courseDao;

    @Resource
    private GradeDao gradeDao;

    @Resource
    private MajorDao majorDao;
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

    @Test
    public void testexcelToList() throws Exception{
//        List<User> users = ExcelUtil.<User>excelToList("C:/各种软件/学生表.xls", new User());
//        for (User user:users){
//            System.out.println(user);
//        }
    }

    @Test
    public void testexcelToList02() throws Exception{
        //InputStream inputStream,T t,Integer columStart,Integer cloumEnd
        InputStream in = new FileInputStream("C:/sorftwares/软工1641.xls");
        List<Student> students = ExcelUtil.excelToList(in, new Student(), 0, 4);
//        for (Student s:students){
//            System.out.println(s);
//        }

    }

    @Test
    public void test01() throws Exception{
        //1、读取excel文件名，如16软件工程

        List<Course> list = new ArrayList<>();

        //2、读取表首行，课程信息，如Java EE 开发技术/专业选修课/4和线性代数A/专业必修课/3
       //InputStream inputStream,T t,Integer columStart,Integer cloumEnd
        InputStream in =new FileInputStream("C:/sorftwares/软工1641.xls");
//        Integer cloums = ExcelUtil.getCloums(in);


        List<String> filedNames = ExcelUtil.getFiledNames(in, 2,4,63);
        for (String s:filedNames){
            System.out.println(s);
            String[] split = s.split("/");


            System.out.println(split.length);
            Course course = new Course(split[0],"16软件工程",split[1],Float.parseFloat(split[2]));
            courseDao.save(course);
            list.add(course);
        }

        for(Course c: list){
            System.out.println(c);
        }
//
//        3、读下一行
//        3.1、读该行前三列，如：1	201612211101	来亮亮,舍弃序号
        Student student1 = new Student("201612211101", "来亮亮");
        //3.2、继续读该行后面的列，有数据就停下来，获取该数据的所属列和所属行，如数据73属于列Java EE 开发技术/专业选修课/4
//        Grade grade1 = new Grade(courseDao.findCourseByCourseName("Java EE 开发技术"), student1, 73);
        //3.3、继续读该行后面的列，有数据就停下来，获取该数据的所属列，如数据60属于列线性代数A/专业必修课/3
//        Grade grade2 = new Grade(courseDao.findCourseByCourseName("线性代数A"), student1, 60);
        //3.4、把该行数据读完，得到grade1、grade2、grade3.........,把他们设置为该行学生的分数,然后存学生
//        student1.getGradeList().add(grade1);
//        student1.getGradeList().add(grade2);
//        studentDao.save(student1);
//        4、继续读取下一行.........
    }

    @Test
    public void test03() throws  Exception{
        InputStream in =new FileInputStream("C:/sorftwares/软工1641.xls");

//        ExcelUtil
    }

    //导入学生
    @Test
    public void  save() throws Exception{
//        Student s =new Student();
//        s.setIsDelete(0);s.setPassword("123");s.setStudentId("201612211122");
//        Grade g= new Grade("高数","201612211122",89);
//        s.getGradeList().add(g);
//        studentDao.save(s);

        InputStream in =new FileInputStream("C:/sorftwares/软工1641.xls");
        Map<String,String> map =new HashMap<>();
        map.put("学号","studentId"); map.put("姓名","studentName");
        map.put("获得学分","acquireCredit");
        List<Student> students = ExcelUtil.getStudentsInfo(in, 2, 0, 4, map);
        for(Student s:students){
            studentDao.save(s);
            if(s.getStudentName().equals("段龙杰")){
                System.out.println(s);

                List<Grade> gradeList =
                        s.getGradeList();
                for(Grade g:gradeList){
                    System.out.println(g);
                }
            }

            System.out.println("--------------------------------------------------------");
            System.out.println("--------------------------------------------------------");
        }

    }

}
