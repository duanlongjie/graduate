package cn.edu.haue.graduate.service.Impl;


import cn.edu.haue.graduate.dao.MajorDao;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.*;
import cn.edu.haue.graduate.service.GraduateOrNotService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static cn.edu.haue.graduate.constant.StudentCreditMessage.GraduateOrNot_No;
import static cn.edu.haue.graduate.constant.StudentCreditMessage.GraduateOrNot_Yes;
import static cn.edu.haue.graduate.constant.CourseType.Elective_Course;
import static cn.edu.haue.graduate.constant.CourseType.PE;
import static cn.edu.haue.graduate.constant.CourseType.Required_Course;


/**
 * Author: lnp
 * Date: 2018/6/10
 **/
@Service
@Transactional
public class GraduateOrNotServiceImpl implements GraduateOrNotService {

    @Resource
    private StudentDao studentDao;
    @Resource
    private MajorDao majorDao;

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
        float requiredCoursesCredit=0;                      //必修课学分标准
        for(Course course: courseList){
            requiredCoursesCredit+=course.getCourseCredit();
        }
        float max_fail_credit=major.getMaxFailCredit();     //最多补考学分标准
        float needPeCredit=major.getneedPeCredit();         //体育课学分标准
        float needPublicCredit=major.getNeedPublicCredit(); //公共选修课学分标准

        List<Grade> gradeList=student.getGradeList();
        float studentPeCredit=0;       //该学生体育课学分
        float studentElectiveCredit=0; //该学生选修课学分
        float studentRequiredCredit=0; //该学生必修课学分
        float studentFailCredit=0;     //该学生补考学分
        for(Grade grade:gradeList){
            String courseType=grade.getCourse().getCourseType();
            if(grade.getScore()>=60) {
                if (PE.equals(courseType)) {             //体育课
                    studentPeCredit += grade.getCourse().getCourseCredit();
                }
                if (Elective_Course.equals(courseType)) { //选修课
                    studentElectiveCredit += grade.getCourse().getCourseCredit();

                }else if (Required_Course.equals(courseType)) { //必修课
                    studentRequiredCredit += grade.getCourse().getCourseCredit();
                }
            }else {
                if (Required_Course.equals(courseType)) { //必修课
                    studentFailCredit++;
                }
            }
        }
        ResultInfo<Student> resultInfo=new ResultInfo<>();
        resultInfo.setResultObj(student);
        float[] array={studentRequiredCredit,studentElectiveCredit,studentPeCredit,studentFailCredit}; //该学生学分信息
        resultInfo.setCreditResultMessage(array);
        if(requiredCoursesCredit<=studentRequiredCredit&&needPeCredit<=studentPeCredit&&needPublicCredit<=studentElectiveCredit){
            resultInfo.setResultMessage(GraduateOrNot_Yes);
        }else{
            resultInfo.setResultMessage(GraduateOrNot_No);
        }
        return resultInfo;
    }
}
