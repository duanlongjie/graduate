package cn.edu.haue.graduate.utils;

import cn.edu.haue.graduate.dao.*;
import cn.edu.haue.graduate.entity.*;
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
        List<Student> students = ExcelUtil.excelToList("C:/sorftwares/软工1641.xls", new Student(), 0, 4);
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
//        Integer cloums = ExcelUtil.getCloums(in);


        List<String> filedNames = ExcelUtil.getFiledNames("C:/sorftwares/软工1641.xls", 2,4,ExcelUtil.getCloums( "C:/sorftwares/软工1641.xls"));
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
    public void testgetFiledName() throws  Exception {
//        List<String> filedNames = ExcelUtil.getFiledNames("C:/sorftwares/2016本科新生数据.xls", 0);
//        for(String s: filedNames){
//            System.out.print(s+" ");
//        }
//        System.out.println();
//        Workbook workbook = ExcelUtil.getWorkBookByFileAddress("C:/sorftwares/2016本科新生数据.xls");
//        Sheet sheet = workbook.getSheet(0);
//        for (int i = 0; i < sheet.getRows(); i++) {
//            Cell[] cell = sheet.getRow(i);
//            for (int j = 0; j < cell.length; j++) {
//                System.out.print(cell[j].getContents() + " ");
//            }
//            System.out.println();

            Map<String, String> map = new HashMap<>();
            map.put("stu_name", "name");
            map.put("stu_card_no", "identityCard");
            map.put("stu_home_in", "address");
            map.put("stu_sex", "gender");
            map.put("haue_major_name", "major");

//            List<Students> students = ExcelUtil.getStudentsInfo(new Students(), "C:/sorftwares/2016本科新生数据.xls", 0, 0, ExcelUtil.getCloums("C:/sorftwares/2016本科新生数据.xls"), map);
//            for (Students s : students) {
//                studentsDao.save(s);
//            }

    }

    //导入学生
    @Test
    public void  save() throws Exception{

        Map<String,String> map =new HashMap<>();
        map.put("学号","studentId"); map.put("姓名","studentName");
        map.put("获得学分","acquireCredit");
        List<Student> students = ExcelUtil.getStudentsInfo("C:/sorftwares/软工1641.xls", 2, 0, 4, map);
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
    @Test
    public void importTest() throws Exception{
        String path="C:/sorftwares/软工1641.xls";
        Map<String,String> map =new HashMap<>();
        map.put("学号","studentId"); map.put("姓名","studentName");
        map.put("获得学分","acquireCredit");
//        map.put("stu_name", "name");
//        map.put("stu_card_no", "identityCard");
//        map.put("stu_home_in", "address");
//        map.put("stu_sex", "gender");
//        map.put("haue_major_name", "major");
        List<Student> students = ExcelUtil.getEntityList(new Student(), path, map);
        List<Student> list = ExcelUtil.getEntityListIncludeGrade(students, path, "学号");
        for(Student s:students){
//            System.out.println(s.getStudentName()+"_________");
//            System.out.println(s.getGradeList());
            studentDao.save(s);
        }
    }
    @Test
    public void getCourseName(){
        List<String> filedNames = ExcelUtil.getFiledNames("C:/sorftwares/软工1641.xls");
        for(String s: filedNames){
             Course c =new Course(s.split("/")[0],"软件工程",s.split("/")[1],
                     Float.parseFloat(s.split("/")[2]));
             courseDao.save(c);
        }
    }

}
