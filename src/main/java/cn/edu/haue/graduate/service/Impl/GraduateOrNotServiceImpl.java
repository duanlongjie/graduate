package cn.edu.haue.graduate.service.Impl;

import cn.edu.haue.graduate.dao.CourseDao;
import cn.edu.haue.graduate.dao.MajorDao;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.*;
import cn.edu.haue.graduate.service.GraduateOrNotService;
import org.hibernate.validator.constraints.EAN;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.ArrayList;
import java.util.List;

import static cn.edu.haue.graduate.constant.AuditResultMessage.*;
import static cn.edu.haue.graduate.constant.CourseType.Elective_Course;
import static cn.edu.haue.graduate.constant.CourseType.PE;
import static cn.edu.haue.graduate.constant.CourseType.Required_Course;


/**
 * Author: lnp
 * Date: 2018/6/10
 **/
public class GraduateOrNotServiceImpl implements GraduateOrNotService {

    @Resource
    private StudentDao studentDao;
    @Resource
    private MajorDao majorDao;
    @Resource
    private CourseDao courseDao;


    /**
     * 毕业条件审核
     * @param id
     * @return
     */
    @Override
    public ResultInfo<Student> GraduateOrNot(String id) {
        Student student=studentDao.getOne(id);
        //获取学生的专业，根据专业的标准来审核毕业条件
        Integer majorId=student.getMajor().getMajorID();
        //根据专业id去查找出该专业毕业的标准
        Major major=majorDao.getOne(majorId);
        List<Course> courseList=major.getRequiredCourses();
        float requiredCoursesCredit=0;                      //必修课标准学分
        for(Course course: courseList){
            requiredCoursesCredit+=course.getCourseCredit();
        }
        float needPeCredit=major.getneedPeCredit();         //体育课标准学分
        float needPublicCredit=major.getNeedPublicCredit(); //公共选修课标准学分

        List<Grade> gradeList=student.getGradeList();
        float studentPeCredit=0;       //该学生体育课学分
        float studentElectiveCredit=0; //该学生选修课学分
        float studentRequiredCredit=0; //该学生必修课学分
        for(Grade grade:gradeList){
            String courseType=grade.getCourse().getCourseType();
            if(grade.getScore()>=60) {
                if (PE.equals(courseType)) {             //体育课
                    studentPeCredit += grade.getCourse().getCourseCredit();
                }
                if (Elective_Course.equals(courseType)) { //选修课
                    studentElectiveCredit += grade.getCourse().getCourseCredit();

                } else if (Required_Course.equals(courseType)) { //必修课
                    studentRequiredCredit += grade.getCourse().getCourseCredit();
                }
            }
        }
        ResultInfo<Student> resultInfo=new ResultInfo<>();
        List<String> list=new ArrayList<>();
        if(requiredCoursesCredit<=studentRequiredCredit){
            list.add(requiredCoursesCredit_Yes);
        }else{
            list.add(requiredCoursesCredit_No);
        }
        if(needPeCredit<=studentPeCredit){
            list.add(needPeCredit_Yes);
        }else{
            list.add(needPeCredit_No);
        }
        if(needPublicCredit<=studentElectiveCredit){
            list.add(needPublicCredit_Yes);
        }else{
            list.add(needPublicCredit_No);
        }
        System.out.println(resultInfo.getAuditResultMessage());
        return resultInfo;
    }
}
