package cn.edu.haue.graduate.utils;

import cn.edu.haue.graduate.constant.StudentStatus;
import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.exceptions.ExcelException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * create by dlj
 * Excel 工具类
 */
public class ExcelUtil {


    /**
     * 获取将成绩设置到相应学生的学生集合
     * @param t  学生集合
     * @param address 文件路径
     * @param xuehao 文件表头对应学号的列名称
     * @return 学生集合
     */
    public static   List<Student> getEntityListIncludeGrade(List<Student> t,String address,String xuehao){
        List<Student> students =new ArrayList<>();
        Workbook book = getWorkBookByFileAddress(address);
        Sheet sheet = book.getSheet(0);
        //标志 映射的 名称和 excel表中表头是否一致
        int flag=0;
        //表头所在行数
        int row=0;
        //记录表头所在行
        int k=0;
        // 获取表头行
        int cloums=sheet.getColumns();
        for(int i=0; i<sheet.getRows();i++){
            flag=0;
            Cell[] c = sheet.getRow(i);
            if(c.length == cloums){
                for(int j=0;j<cloums;j++){
                    if(c[j].getContents()!=""){
                        flag++;
                    }
                }
            }
            if(flag==cloums){
                row=i;
                break;
            }

        }


        Cell[] cells = sheet.getRow(row);
        //存放 表头信息的数组
        String [] fns = new String[cells.length];
        List<String> ke=new ArrayList<>();
        for(int i=0; i<cells.length; i++){
            fns[i]=cells[i].getContents();
            if(fns[i].contains("/")){
                ke.add(fns[i]);
            }
        }
        //存放学生信息的集合
        //遍历学生成绩行
        for(int i=row+1; i<sheet.getRows(); i++){
            Cell[] cs = sheet.getRow(i);
            for(int j= 0;j<sheet.getColumns();j++){
                if(fns[j].equals(xuehao)){
                    for(Student student: t){
                        if(student.getStudentId().equals(cs[j].getContents())){
                            for(int l=0; l<sheet.getColumns();l++){
                                if(ke.contains(fns[l]) && ""!=cs[l].getContents()){
                                    String goldstr = cs[l].getContents();
                                    float gold;
                                    //成绩非空
                                    if(!"".equals(goldstr)){
                                        if("优".equals(goldstr)){
                                            gold=90;
                                        }
                                        else  if("良".equals(goldstr)){
                                            gold=80;
                                        }
                                        else  if("中".equals(goldstr)){
                                            gold=70;
                                        }
                                        else  if("及格".equals(goldstr)){
                                            gold=60;
                                        }
                                        else {
                                            gold = Float.parseFloat(goldstr);
                                        }
                                        Grade grade =new Grade(fns[l].split("/")[0],fns[l].split("/")[1],student.getStudentId(),gold);
                                        student.getGradeList().add(grade);
                                        students.add(student);
                                    }
                                }

                            }

                    }

                }
            }
            }

        }
        return students;

    }


    /**
     * 获取将成绩设置到相应学生的学生集合
     * @param t  学生集合
     * @param inputStream 文件路径
     * @param xuehao 文件表头对应学号的列名称
     * @return 学生集合
     */
    public static   List<Student> getEntityListIncludeGrade(List<Student> t,
                                                            InputStream inputStream,String xuehao){
        List<Student> students =new ArrayList<>();
        Workbook book = getWorkBookByFileAddress(inputStream);
        Sheet sheet = book.getSheet(0);
        //标志 映射的 名称和 excel表中表头是否一致
        int flag=0;
        //表头所在行数
        int row=0;
        //记录表头所在行
        int k=0;
        // 获取表头行
        int cloums=sheet.getColumns();
        for(int i=0; i<sheet.getRows();i++){
            flag=0;
            Cell[] c = sheet.getRow(i);
            if(c.length == cloums){
                for(int j=0;j<cloums;j++){
                    if(c[j].getContents()!=""){
                        flag++;
                    }
                }
            }
            if(flag==cloums){
                row=i;
                break;
            }

        }


        Cell[] cells = sheet.getRow(row);
        //存放 表头信息的数组
        String [] fns = new String[cells.length];
        List<String> ke=new ArrayList<>();
        for(int i=0; i<cells.length; i++){
            fns[i]=cells[i].getContents();
            if(fns[i].contains("/")){
                ke.add(fns[i]);
            }
        }
        //存放学生信息的集合
        //遍历学生成绩行
        for(int i=row+1; i<sheet.getRows(); i++){
            Cell[] cs = sheet.getRow(i);
            for(int j= 0;j<sheet.getColumns();j++){
                if(fns[j].equals(xuehao)){
                    for(Student student: t){
                        if(student.getStudentId().equals(cs[j].getContents())){
                            for(int l=0; l<sheet.getColumns();l++){
                                if(ke.contains(fns[l]) && ""!=cs[l].getContents()){
                                    String goldstr = cs[l].getContents();
                                    float gold;
                                    //成绩非空
                                    if(!"".equals(goldstr)){
                                        if("优".equals(goldstr)){
                                            gold=90;
                                        }
                                        else  if("良".equals(goldstr)){
                                            gold=80;
                                        }
                                        else  if("中".equals(goldstr)){
                                            gold=70;
                                        }
                                        else  if("及格".equals(goldstr)){
                                            gold=60;
                                        }
                                        else {
                                            gold = Float.parseFloat(goldstr);
                                        }
                                        Grade grade =new Grade(fns[l].split("/")[0],fns[l].split("/")[1],student.getStudentId(),gold);
                                        student.getGradeList().add(grade);
                                        students.add(student);
                                    }
                                }

                            }

                        }

                    }
                }
            }

        }
        return students;

    }
    /**
     * 不带成绩导入的 （获取学生信息列表）
     *  指定泛型，将指定Excel表头和实体属性的映射 关系  导入到list集合中
     * @param t  指定的泛型
     * @param address 文件所在地址
     * @param mapping 表头和属性名的映射关系
     * @return  数据集合
     * @throws Exception
     */
    public static <T> List<T> getEntityList (T t,String address,
                                                     Map<String,String> mapping) throws  Exception{
        //标志 映射的 名称和 excel表中表头是否一致
        int flag=0;
        Workbook book = getWorkBookByFileAddress(address);
        Sheet sheet = book.getSheet(0);
        //表头所在行数
        int row=0;
        //记录表头所在行
        int k=0;
        for(int i=0; i<sheet.getRows();i++){
            if(flag==mapping.size()) break;
            k=i;
            Cell[] c = sheet.getRow(i);
            for (String s:mapping.keySet()){
            for(int j=0;j<c.length;j++){
                    if(c[j].getContents().equals(s)){
                        flag++;
                        break;
                    }
                }

                if(flag==mapping.size()) break;
            }
        }

        if(flag==mapping.size()){
            row=k;
        }
        // 获取表头行
        Cell[] cells = sheet.getRow(row);
        //存放 表头信息的数组
        String [] fns = new String[cells.length];

        for(int i=0; i<cells.length; i++){
            fns[i]=cells[i].getContents();
        }
        //存放学生信息的集合
        List<T> lists =new ArrayList<>();
        //遍历学生成绩行
        for(int i=row+1; i<sheet.getRows(); i++){

            Cell[] cs = sheet.getRow(i);
            T o = (T)t.getClass().newInstance();
            if(o instanceof Student){
                ((Student) o).setIsDelete(StudentStatus.USEFUL);
            }
            for(int j= 0;j<sheet.getColumns();j++){
                for(String ss:mapping.keySet()){
                    //如果 表头信息
                    if(fns[j].equals(ss)){
                        String value = mapping.get(fns[j]);
                        //将 属性和 excel表头 映射的信息值 set进去
                        BeanUtils.setProperty(o,value,cs[j].getContents());
                    }
                }
            }
            lists.add(o);

        }
        return lists;
    }

    /**
     * 不带成绩导入的 （获取学生信息列表）
     *  指定泛型，将指定Excel表头和实体属性的映射 关系  导入到list集合中
     * @param t  指定的泛型
     * @param inputStream 文件所在地址
     * @param mapping 表头和属性名的映射关系
     * @return  数据集合
     * @throws Exception
     */
    public static <T> List<T> getEntityList (T t,InputStream inputStream,
                                             Map<String,String> mapping) throws  Exception{
        //标志 映射的 名称和 excel表中表头是否一致
        int flag=0;
        Workbook book = getWorkBookByFileAddress(inputStream);
        Sheet sheet = book.getSheet(0);
        //表头所在行数
        int row=0;
        //记录表头所在行
        int k=0;
        for(int i=0; i<sheet.getRows();i++){
            if(flag==mapping.size()) break;
            k=i;
            Cell[] c = sheet.getRow(i);
            for (String s:mapping.keySet()){
                for(int j=0;j<c.length;j++){
                    if(c[j].getContents().equals(s)){
                        flag++;
                        break;
                    }
                }

                if(flag==mapping.size()) break;
            }
        }

        if(flag==mapping.size()){
            row=k;
        }
        // 获取表头行
        Cell[] cells = sheet.getRow(row);
        //存放 表头信息的数组
        String [] fns = new String[cells.length];

        for(int i=0; i<cells.length; i++){
            fns[i]=cells[i].getContents();
        }
        //存放学生信息的集合
        List<T> lists =new ArrayList<>();
        //遍历学生成绩行
        for(int i=row+1; i<sheet.getRows(); i++){

            Cell[] cs = sheet.getRow(i);
            T o = (T)t.getClass().newInstance();
            if(o instanceof Student){
                ((Student) o).setIsDelete(StudentStatus.USEFUL);
            }
            for(int j= 0;j<sheet.getColumns();j++){
                for(String ss:mapping.keySet()){
                    //如果 表头信息
                    if(fns[j].equals(ss)){
                        String value = mapping.get(fns[j]);
                        //将 属性和 excel表头 映射的信息值 set进去
                        BeanUtils.setProperty(o,value,cs[j].getContents());
                    }
                }
            }
            lists.add(o);

        }
        return lists;
    }

    /**
     *  根据文件地址 获取 工作簿对象
     * @param address  文件地址
     * @return  工作簿对象
     */
    public static Workbook getWorkBookByFileAddress(String address){
        Workbook workbook = null;
        try{
            InputStream inputStream = new FileInputStream(address);
            workbook= Workbook.getWorkbook(inputStream);
        }catch (Exception e){
            System.out.println("不存在对应的文件!");
            e.printStackTrace();
        }

        return workbook;

    }


    /**
     *  根据文件地址 获取 工作簿对象
     * @param inputStream  文件地址
     * @return  工作簿对象
     */
    public static Workbook getWorkBookByFileAddress(InputStream inputStream){
        Workbook workbook = null;
        try{
            workbook= Workbook.getWorkbook(inputStream);
        }catch (Exception e){
            System.out.println("不存在对应的文件!");
            e.printStackTrace();
        }

        return workbook;

    }
    /**
     * 带成绩导入的
     *  获取 学生信息集合 以及 学生对应的 分数集合
     * @param address  excel 表所对应物理地址
     * @param row  表头所在行数
     * @param startCloum 学生信息开始列数
     * @param endCloum  学生信息结束列
     * @param mapping  excel 表头和 学生 属性的映射关系
     * @param <T>
     * @return  返回学生集合
     * @throws Exception
     */
    public static <T> List<Student> getStudentsInfo (String address,
                            Integer row,Integer startCloum,Integer endCloum,
                            Map<String,String> mapping) throws  Exception{

        Workbook book = getWorkBookByFileAddress(address);
        Sheet sheet = book.getSheet(0);

        // 获取表头行
        Cell[] cells = sheet.getRow(row);
        //存放 表头信息的数组
        String [] fns = new String[cells.length];

        for(int i=0; i<cells.length; i++){
          fns[i]=cells[i].getContents();
        }
        //存放学生信息的集合
        List<Student> students =new ArrayList<>();
        //遍历学生成绩行
        for(int i=row+1; i<sheet.getRows(); i++){

            Cell[] cs = sheet.getRow(i);
            Student s = new Student();
            s.setIsDelete(StudentStatus.USEFUL);
            for(int j= 0;j<endCloum;j++){
                for(String k:mapping.keySet()){
                    //如果 表头信息
                    if(fns[j].equals(k)){
                        String value = mapping.get(fns[j]);
                        //将 属性和 excel表头 映射的信息值 set进去
                        BeanUtils.setProperty(s,value,cs[j].getContents());
                    }
                }
            }
            //遍历成绩
            for(int j=endCloum; j<sheet.getColumns();j++){
                String goldstr = cs[j].getContents();
                float gold;
                //成绩非空
                if(!"".equals(goldstr)){
                    if("优".equals(goldstr)){
                        gold=90;
                    }
                  else  if("良".equals(goldstr)){
                        gold=80;
                    }
                    else  if("中".equals(goldstr)){
                        gold=70;
                    }
                    else  if("及格".equals(goldstr)){
                        gold=60;
                    }
                    else {
                    gold = Float.parseFloat(goldstr);
                    }
                    Grade grade =new Grade(fns[j].split("/")[0],fns[j].split("/")[1],s.getStudentId(),gold);
                    s.getGradeList().add(grade);
                }

            }
            students.add(s);

        }
        return students;
    }

    /**
     * 获取 excel表 的列数
     * @param address
     * @return
     */
    public static  Integer getCloums(String address){
        Workbook book= getWorkBookByFileAddress(address);
       return book.getSheet(0).getColumns();
    }
    /**
     * 获取 表头内容集合
     * @param address  文件所在物理路径
     * @param row  表头所在行
     * @param startCloum 起始列
     * @param endCloum 终止列
     * @return 表头内容的集合
     */
    public static  List<String> getFiledNames(String address, Integer row,Integer startCloum,Integer endCloum){

        List<String> fileds =new ArrayList<>();
        Workbook book= getWorkBookByFileAddress(address);

        Sheet sheet = book.getSheet(0);
        Cell[] cells = sheet.getRow(row);
        for(int i =startCloum; i<endCloum; i++){
            String contents = cells[i].getContents();
            fileds.add(contents);
        }

        return fileds;
    }

    /**
     * 获取 课程表头
     * @param address  文件所在物理路径
     * @return 表头内容的集合
     */
    public static  List<String> getFiledNames(String address){
        List<String> keNames=new ArrayList<>();
        Workbook book = getWorkBookByFileAddress(address);
        Sheet sheet = book.getSheet(0);
        int flag=0;
        //表头所在行数
        int row=0;
        //记录表头所在行
        int k=0;
        // 获取表头行
        int cloums=sheet.getColumns();
        for(int i=0; i<sheet.getRows();i++){
            flag=0;
            Cell[] c = sheet.getRow(i);
            if(c.length == cloums){
                for(int j=0;j<cloums;j++){
                    if(c[j].getContents()!=""){
                        flag++;
                    }
                }
            }
            if(flag==cloums){
                row=i;
                break;
            }

        }
        Cell[] cells = sheet.getRow(row);
        //存放 表头信息的数组
        String [] fns = new String[cells.length];

        for(int i=0; i<cells.length; i++){
            fns[i]=cells[i].getContents();
            if(fns[i].contains("/")){
                keNames.add(fns[i]);
            }
        }
        return keNames;
    }





    /**
     * 获取 课程表头
     * @param inputStream  文件所在物理路径
     * @return 表头内容的集合
     */
    public static  List<String> getFiledNames(InputStream inputStream){
        List<String> keNames=new ArrayList<>();
        Workbook book = getWorkBookByFileAddress(inputStream);
        Sheet sheet = book.getSheet(0);
        int flag=0;
        //表头所在行数
        int row=0;
        //记录表头所在行
        int k=0;
        // 获取表头行
        int cloums=sheet.getColumns();
        for(int i=0; i<sheet.getRows();i++){
            flag=0;
            Cell[] c = sheet.getRow(i);
            if(c.length == cloums){
                for(int j=0;j<cloums;j++){
                    if(c[j].getContents()!=""){
                        flag++;
                    }
                }
            }
            if(flag==cloums){
                row=i;
                break;
            }

        }
        Cell[] cells = sheet.getRow(row);
        //存放 表头信息的数组
        String [] fns = new String[cells.length];

        for(int i=0; i<cells.length; i++){
            fns[i]=cells[i].getContents();
            if(fns[i].contains("/")){
                keNames.add(fns[i]);
            }
        }
        return keNames;
    }
    /**
     * 获取表头名称集合
     * @param address excel 文件输入流
     * @param row 表头所在行
     * @return 表头集合
     */
    public static  List<String> getFiledNames(String address, Integer row){
        List<String> fileds =new ArrayList<>();
        Workbook book = getWorkBookByFileAddress(address);
        Sheet sheet = book.getSheet(0);
        Cell[] cells = sheet.getRow(row);
        for(int i =0; i<cells.length; i++){
            String contents = cells[i].getContents();
            fileds.add(contents);
        }
        return fileds;
    }


    /**
     *  将excel 文件 内容 转换成 list 集合
     *  指定 起始列终止列
     * @param address 含有 excel 文件信息的 输入流
     * @param columStart  起始列数
     * @param cloumEnd 终止列数
     * @param t  要封装到 哪个实体类里面
     * @param <T> 实体类的类型
     * @return  返回 实体类List集合
     */
    public static <T> List<T> excelToList(String address,T t,Integer columStart,Integer cloumEnd) {
        //将 excel 表中 数据 存入List
        List<T> list =new ArrayList<>();
        Workbook book = getWorkBookByFileAddress(address);
        Sheet sheet=book.getSheet(0);//取得第一个工作表，也可用sheet名字获得。
        //取得行数
        int len=sheet.getRows();
        System.out.println("---------------------len :"+len);
        //列数
        int cloum= sheet.getRow(1).length; //获取列数
        System.out.println("-----------------cloum :"+cloum);
        // 几列 对应 几个 set方法
        Method[] methods=new Method[cloum];

        //存储所有的 set方法的集合
//        List<Method> methods=new ArrayList<>();
        Method[] methods1 = t.getClass().getMethods();
        int index=0;
        //
        for(Method method:methods1){
            if(method.getName().contains("set")){
                //列数比 set方法 个数 少的情况
                if(index == cloum){
                    break;
                }
                else{
                    methods[index]=method;
                    index++;
                }
            }
        }

        System.out.println("--------------------- index : "+index);

        //遍历行数
        for(int i=1;i<len;i++){//从1开始，避免插入标题  列名
            Cell[] cells = sheet.getRow(i); //获取该行 元素内容
            T o=null;
            try {
                o = (T)t.getClass().newInstance();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            for(int j=columStart;j<cloumEnd;j++){ // 遍历每列
                String contents = cells[j].getContents();
                System.out.print(contents+"  ");
//                for(){
//
//                }
                try {
                    methods[j].invoke(o,contents);   //调用目标方法
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
            System.out.println();
            list.add(o);
        }
        book.close();
        return list;
    }


    /**
     *  将excel 文件 内容 转换成 list 集合
     * @param address 含有 excel 文件信息的 输入流
     * @param t  要封装到 哪个实体类里面
     * @param <T> 实体类的类型
     * @return  返回 实体类List集合
     */
//    public static <T> List<T> excelToList(String address,T t) {
//        //将 excel 表中 数据 存入List
//        List<T> list =new ArrayList<>();
//        Workbook book = getWorkBookByFileAddress(address);
//        Sheet sheet=book.getSheet(0);//取得第一个工作表，也可用sheet名字获得。
//        int len=sheet.getRows();//取得行数
//        //列数
//        int cloum= sheet.getRow(1).length; //获取列数
//        // 几列 对应 几个 set方法
//        Method[] methods=new Method[cloum];
//
//        //存储所有的 set方法的集合
////        List<Method> methods=new ArrayList<>();
//        Method[] methods1 = t.getClass().getMethods();
//        int index=0;
//        for(Method method:methods1){
//            if(method.getName().contains("set")){
//                //列数比 set方法 个数 少的情况
//                if(index == cloum){
//                    break;
//                }
//                else{
//                methods[index]=method;
//                index++;
//                }
//            }
//        }
//        for(int i=1;i<len;i++){//从1开始，避免插入标题  列名
//            //System.out.println(i);
//            Cell[] cells = sheet.getRow(i); //获取列数
//            T o=null;
//            try {
//                o = (T)t.getClass().newInstance();
//            } catch (Exception e){
//                System.out.println(e.getMessage());
//            }
//            for(int j=0;j<cells.length;j++){//打印每行信息
//                String contents = cells[j].getContents();
//                try {
//                    methods[j].invoke(o,contents);   //调用目标方法
//                } catch (Exception e){
//                    System.out.println(e.getMessage());
//                }
//
//            }
//            list.add(o);
//        }
//        book.close();
//        return list;
//    }



    /**
     *
     * @param pathName excel 文件所在路径
     * @param t  实体类型
     * @param <T> 实体类
     * @return  将 excel数据封装后 返回
     */
    public static <T> List<T> excelToList(String pathName,T t) {
        //将 excel 表中 数据 存入List
        List<T> list =new ArrayList<>();
        InputStream inputStream=null;
        Workbook book =null;
        try {
             inputStream=new FileInputStream(pathName);
             //获取工作簿
             book=Workbook.getWorkbook(inputStream);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Sheet sheet=book.getSheet(0);//取得第一个工作表，也可用sheet名字获得。
        int len=sheet.getRows();//取得行数
        //列数
        int cloum= sheet.getRow(1).length; //获取列数
        // 几列 对应 几个 set方法
            Method[] methods=new Method[cloum];
        //存储所有的 set方法的集合
//        List<Method> methods=new ArrayList<>();
        Method[] methods1 = t.getClass().getMethods();
        int index=0;
        for(Method method:methods1){
            if(method.getName().contains("set")){
                methods[index]=method;
                index++;
            }
        }
        for(int i=1;i<len;i++){//从1开始，避免插入标题  列名
            //System.out.println(i);
            Cell[] cells = sheet.getRow(i); //获取列数
            T o=null;
            try {
                o = (T)t.getClass().newInstance();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            for(int j=0;j<cells.length;j++){//打印每行信息
                String contents = cells[j].getContents();
                try {
                    methods[j].invoke(o,contents);   //调用目标方法
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
            list.add(o);
        }
        book.close();
        return list;
    }






    /**
     * @MethodName  : listToExcel
     * @Description : 导出Excel（可以导出到本地文件系统，也可以导出到浏览器，可自定义工作表大小）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * 如果需要的是引用对象的属性，则英文属性使用类似于EL表达式的格式
     * 如：list中存放的都是student，student中又有college属性，而我们需要学院名称，则可以这样写
     * fieldMap.put("college.collegeName","学院名称")
     * @param sheetName 工作表的名称
     * @param sheetSize 每个工作表中记录的最大个数
     * @param out       导出流
     * @throws ExcelException
     */
    public static <T>  void   listToExcel (
            List<T> list ,
            LinkedHashMap<String,String> fieldMap,
            String sheetName,
            int sheetSize,
            OutputStream out
    ) throws ExcelException {


        if(list.size()==0 || list==null){
            throw new ExcelException("数据源中没有任何数据");
        }

        if(sheetSize>65535 || sheetSize<1){
            sheetSize=65535;
        }

        //创建工作簿并发送到OutputStream指定的地方
        WritableWorkbook wwb;
        try {
            wwb = Workbook.createWorkbook(out);

            //因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
            //所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
            //1.计算一共有多少个工作表
            double sheetNum=Math.ceil(list.size()/new Integer(sheetSize).doubleValue());

            //2.创建相应的工作表，并向其中填充数据
            for(int i=0; i<sheetNum; i++){
                //如果只有一个工作表的情况
                if(1==sheetNum){
                    WritableSheet sheet=wwb.createSheet(sheetName, i);
                    fillSheet(sheet, list, fieldMap, 0, list.size()-1);

                    //有多个工作表的情况
                }else{
                    WritableSheet sheet=wwb.createSheet(sheetName+(i+1), i);

                    //获取开始索引和结束索引
                    int firstIndex=i*sheetSize;
                    int lastIndex=(i+1)*sheetSize-1>list.size()-1 ? list.size()-1 : (i+1)*sheetSize-1;
                    //填充工作表
                    fillSheet(sheet, list, fieldMap, firstIndex, lastIndex);
                }
            }

            wwb.write();
            wwb.close();

        }catch (Exception e) {
            e.printStackTrace();
            //如果是ExcelException，则直接抛出
            if(e instanceof ExcelException){
                throw (ExcelException)e;

                //否则将其它异常包装成ExcelException再抛出
            }else{
                throw new ExcelException("导出Excel失败");
            }
        }

    }

    /**
     * @MethodName  : listToExcel
     * @Description : 导出Excel（可以导出到本地文件系统，也可以导出到浏览器，工作表大小为2003支持的最大值）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * @param out       导出流
     * @throws ExcelException
     */
    public static  <T>  void   listToExcel (
            List<T> list ,
            LinkedHashMap<String,String> fieldMap,
            String sheetName,
            OutputStream out
    ) throws ExcelException{

        listToExcel(list, fieldMap, sheetName, 65535, out);

    }


    /**
     * @MethodName  : listToExcel
     * @Description : 导出Excel（导出到浏览器，可以自定义工作表的大小）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * @param sheetSize    每个工作表中记录的最大个数
     * @param response  使用response可以导出到浏览器
     * @throws ExcelException
     */
    public static  <T>  void   listToExcel (
            List<T> list ,
            LinkedHashMap<String,String> fieldMap,
            String sheetName,
            int sheetSize,
            HttpServletResponse response
    ) throws ExcelException{

        //设置默认文件名为当前时间：年月日时分秒
        String fileName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();

        //设置response头信息
        response.reset();
        response.setContentType("application/vnd.ms-excel");        //改成输出excel文件
        response.setHeader("Content-disposition","attachment; filename="+fileName+".xls" );

        //创建工作簿并发送到浏览器
        try {

            OutputStream out=response.getOutputStream();
            listToExcel(list, fieldMap, sheetName, sheetSize,out );

        } catch (Exception e) {
            e.printStackTrace();

            //如果是ExcelException，则直接抛出
            if(e instanceof ExcelException){
                throw (ExcelException)e;

                //否则将其它异常包装成ExcelException再抛出
            }else{
                throw new ExcelException("导出Excel失败");
            }
        }
    }


    /**
     * @MethodName  : listToExcel
     * @Description : 导出Excel（导出到浏览器，工作表的大小是2003支持的最大值）
     * @param list      数据源
     * @param fieldMap      类的英文属性和Excel中的中文列名的对应关系
     * @param response  使用response可以导出到浏览器
     * @throws ExcelException
     */
    public static <T>  void   listToExcel (
            List<T> list ,
            LinkedHashMap<String,String> fieldMap,
            String sheetName,
            HttpServletResponse response
    ) throws ExcelException{

        listToExcel(list, fieldMap, sheetName, 65535, response);
    }

    /**
     * @MethodName          : excelToList
     * @Description             : 将Excel转化为List
     * @param in                    ：承载着Excel的输入流
     * @param entityClass       ：List中对象的类型（Excel中的每一行都要转化为该类型的对象）
     * @param fieldMap          ：Excel中的中文列头和类的英文属性的对应关系Map
     * @param uniqueFields  ：指定业务主键组合（即复合主键），这些列的组合不能重复
     * @return                      ：List
     * @throws ExcelException
     */
    public static <T>  List<T>  f(
            InputStream in,
            String sheetName,
            Class<T> entityClass,
            LinkedHashMap<String, String> fieldMap,
            String[] uniqueFields
    ) throws ExcelException{

        //定义要返回的list
        List<T> resultList=new ArrayList<T>();

        try {

            //根据Excel数据源创建WorkBook
            Workbook wb=Workbook.getWorkbook(in);
            //获取工作表
            Sheet sheet=wb.getSheet(sheetName);
            int rows = sheet.getRows();
            int realRows =rows;
            //获取工作表的有效行数
//            int realRows=0;
//            for(int i=0;i<sheet.getRows();i++){
//
//                int nullCols=0;
//                for(int j=0;j<sheet.getColumns();j++){
//                    Cell currentCell = sheet.getCell(j, i);
//                    if(currentCell.getContents()==null || "".equals(currentCell.getContents().toString())){
//                        nullCols++;
//                    }
//                    if(currentCell.getContents()!=""){
//                        realRows++;
//                        break;
//                    }
//                    }
//
//                if(nullCols==sheet.getColumns()){
//                    break;
//                }else{
//                    realRows++;
//                }
//            }
//
//            System.out.println(realRows+"==============");


            //如果Excel中没有数据则提示错误
            if(realRows<=1){
                throw new ExcelException("Excel文件中没有任何数据");
            }


            Cell[] firstRow=sheet.getRow(2);
            for (int i =0; i<firstRow.length; i++){
                System.out.print(firstRow[i].getContents()+" " );

            }
            System.out.println();

            String[] excelFieldNames=new String[firstRow.length];

            //获取Excel中的列名
            for(int i=0;i<firstRow.length;i++){
                excelFieldNames[i]=firstRow[i].getContents().toString().trim();
            }

            //判断需要的字段在Excel中是否都存在
            boolean isExist=true;
            List<String> excelFieldList=Arrays.asList(excelFieldNames);
            for(String cnName : fieldMap.keySet()){
                if(!excelFieldList.contains(cnName)){
                    isExist=false;
                    break;
                }
            }

            //如果有列名不存在，则抛出异常，提示错误
            if(!isExist){
                throw new ExcelException("Excel中缺少必要的字段，或字段名称有误");
            }


            //将列名和列号放入Map中,这样通过列名就可以拿到列号
            LinkedHashMap<String, Integer> colMap=new LinkedHashMap<String, Integer>();
            for(int i=0;i<excelFieldNames.length;i++){
                colMap.put(excelFieldNames[i], firstRow[i].getColumn());
            }



            //判断是否有重复行
            //1.获取uniqueFields指定的列
            Cell[][] uniqueCells=new Cell[uniqueFields.length][];
            for(int i=0;i<uniqueFields.length;i++){
                int col=colMap.get(uniqueFields[i]);
                uniqueCells[i]=sheet.getColumn(col);
            }

            //2.从指定列中寻找重复行
            for(int i=1;i<realRows;i++){
                int nullCols=0;
                for(int j=0;j<uniqueFields.length;j++){
                    String currentContent=uniqueCells[j][i].getContents();
                    Cell sameCell=sheet.findCell(currentContent,
                            uniqueCells[j][i].getColumn(),
                            uniqueCells[j][i].getRow()+1,
                            uniqueCells[j][i].getColumn(),
                            uniqueCells[j][realRows-1].getRow(),
                            true);
                    if(sameCell!=null){
                        nullCols++;
                    }
                }

                if(nullCols==uniqueFields.length){
                    throw new ExcelException("Excel中有重复行，请检查");
                }
            }

            //将sheet转换为list
            for(int i=1;i<realRows;i++){
                //新建要转换的对象
                T entity=entityClass.newInstance();

                //给对象中的字段赋值
                for(Map.Entry<String, String> entry : fieldMap.entrySet()){
                    //获取中文字段名
                    String cnNormalName=entry.getKey();
                    //获取英文字段名
                    String enNormalName=entry.getValue();
                    //根据中文字段名获取列号
                    int col=colMap.get(cnNormalName);

                    //获取当前单元格中的内容
                    String content=sheet.getCell(col, i).getContents().toString().trim();

                    //给对象赋值
                    setFieldValueByName(enNormalName, content, entity);
                }

                resultList.add(entity);
            }
        } catch(Exception e){
            e.printStackTrace();
            //如果是ExcelException，则直接抛出
            if(e instanceof ExcelException){
                throw (ExcelException)e;

                //否则将其它异常包装成ExcelException再抛出
            }else{
                e.printStackTrace();
                throw new ExcelException("导入Excel失败");
            }
        }
        return resultList;
    }





    /*<-------------------------辅助的私有方法----------------------------------------------->*/
    /**
     * @MethodName  : getFieldValueByName
     * @Description : 根据字段名获取字段值
     * @param fieldName 字段名
     * @param o 对象
     * @return  字段值
     */
    private static  Object getFieldValueByName(String fieldName, Object o) throws Exception{

        Object value=null;
        Field field = getFieldByName(fieldName, o.getClass());

        if(field !=null){
            field.setAccessible(true);
            value=field.get(o);
        }else{
            throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 "+fieldName);
        }

        return value;
    }

    /**
     * @MethodName  : getFieldByName
     * @Description : 根据字段名获取字段
     * @param fieldName 字段名
     * @param clazz 包含该字段的类
     * @return 字段
     */
    private static Field getFieldByName(String fieldName, Class<?>  clazz){
        //拿到本类的所有字段
        Field[] selfFields=clazz.getDeclaredFields();

        //如果本类中存在该字段，则返回
        for(Field field : selfFields){
            if(field.getName().equals(fieldName)){
                return field;
            }
        }

        //否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz=clazz.getSuperclass();
        if(superClazz!=null  &&  superClazz !=Object.class){
            return getFieldByName(fieldName, superClazz);
        }

        //如果本类和父类都没有，则返回空
        return null;
    }



    /**
     * @MethodName  : getFieldValueByNameSequence
     * @Description :
     * 根据带路径或不带路径的属性名获取属性值
     * 即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
     *
     * @param fieldNameSequence  带路径的属性名或简单属性名
     * @param o 对象
     * @return  属性值
     * @throws Exception
     */
    private static  Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception{

        Object value=null;

        //将fieldNameSequence进行拆分
        String[] attributes=fieldNameSequence.split("\\.");
        if(attributes.length==1){
            value=getFieldValueByName(fieldNameSequence, o);
        }else{
            //根据属性名获取属性对象
            Object fieldObj=getFieldValueByName(attributes[0], o);
            String subFieldNameSequence=fieldNameSequence.substring(fieldNameSequence.indexOf(".")+1);
            value=getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
        }
        return value;

    }


    /**
     * @MethodName  : setFieldValueByName
     * @Description : 根据字段名给对象的字段赋值
     * @param fieldName  字段名
     * @param fieldValue    字段值
     * @param o 对象
     */
    private static void setFieldValueByName(String fieldName,Object fieldValue,Object o) throws Exception{

        Field field=getFieldByName(fieldName, o.getClass());
        if(field!=null){
            field.setAccessible(true);
            //获取字段类型
            Class<?> fieldType = field.getType();

            //根据字段类型给字段赋值
            if (String.class == fieldType) {
                field.set(o, String.valueOf(fieldValue));
            } else if ((Integer.TYPE == fieldType)
                    || (Integer.class == fieldType)) {
                field.set(o, Integer.parseInt(fieldValue.toString()));
            } else if ((Long.TYPE == fieldType)
                    || (Long.class == fieldType)) {
                field.set(o, Long.valueOf(fieldValue.toString()));
            } else if ((Float.TYPE == fieldType)
                    || (Float.class == fieldType)) {
                field.set(o, Float.valueOf(fieldValue.toString()));
            } else if ((Short.TYPE == fieldType)
                    || (Short.class == fieldType)) {
                field.set(o, Short.valueOf(fieldValue.toString()));
            } else if ((Double.TYPE == fieldType)
                    || (Double.class == fieldType)) {
                field.set(o, Double.valueOf(fieldValue.toString()));
            } else if (Character.TYPE == fieldType) {
                if ((fieldValue!= null) && (fieldValue.toString().length() > 0)) {
                    field.set(o, Character
                            .valueOf(fieldValue.toString().charAt(0)));
                }
            }else if(Date.class==fieldType){
                field.set(o, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue.toString()));
            }else{
                field.set(o, fieldValue);
            }
        }else{
            throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 "+fieldName);
        }
    }


    /**
     * @MethodName  : setColumnAutoSize
     * @Description : 设置工作表自动列宽和首行加粗
     * @param ws
     */
    private static void setColumnAutoSize(WritableSheet ws,int extraWith){
        //获取本列的最宽单元格的宽度
        for(int i=0;i<ws.getColumns();i++){
            int colWith=0;
            for(int j=0;j<ws.getRows();j++){
                String content=ws.getCell(i,j).getContents().toString();
                int cellWith=content.length();
                if(colWith<cellWith){
                    colWith=cellWith;
                }
            }
            //设置单元格的宽度为最宽宽度+额外宽度
            ws.setColumnView(i, colWith+extraWith);
        }

    }

    /**
     * @MethodName  : fillSheet
     * @Description : 向工作表中填充数据
     * @param sheet     工作表
     * @param list  数据源
     * @param fieldMap 中英文字段对应关系的Map
     * @param firstIndex    开始索引
     * @param lastIndex 结束索引
     */
    private static <T> void fillSheet(
            WritableSheet sheet,
            List<T> list,
            LinkedHashMap<String,String> fieldMap,
            int firstIndex,
            int lastIndex
    )throws Exception{

        //定义存放英文字段名和中文字段名的数组
        String[] enFields=new String[fieldMap.size()];
        String[] cnFields=new String[fieldMap.size()];

        //填充数组
        int count=0;
        for(Map.Entry<String,String> entry:fieldMap.entrySet()){
            enFields[count]=entry.getKey();
            cnFields[count]=entry.getValue();
            count++;
        }
        //填充表头
        for(int i=0;i<cnFields.length;i++){
            Label label=new Label(i,0,cnFields[i]);
            sheet.addCell(label);
        }

        //填充内容
        int rowNo=1;
        for(int index=firstIndex;index<=lastIndex;index++){
            //获取单个对象
            T item=list.get(index);
            for(int i=0;i<enFields.length;i++){
                Object objValue=getFieldValueByNameSequence(enFields[i], item);
                String fieldValue=objValue==null ? "" : objValue.toString();
                Label label =new Label(i,rowNo,fieldValue);
                sheet.addCell(label);
            }

            rowNo++;
        }

        //设置自动列宽
        setColumnAutoSize(sheet, 5);
    }
}
