package cn.edu.haue.graduate.service.Impl;


import cn.edu.haue.graduate.constant.ResultCode;
import cn.edu.haue.graduate.dao.CourseDao;
import cn.edu.haue.graduate.dao.MajorDao;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.*;
import cn.edu.haue.graduate.service.GraduateOrNotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static cn.edu.haue.graduate.constant.CourseType.*;
import static cn.edu.haue.graduate.constant.StudentCreditMessage.GraduateOrNot_No;
import static cn.edu.haue.graduate.constant.StudentCreditMessage.GraduateOrNot_Yes;


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
    @Resource
    private CourseDao courseDao;

    /**
     * 毕业条件审核
     *
     * @param id
     * @return
     */
    @Override
    public ResultInfo<StudentCreditResult> GraduateOrNot(String id) {
        ResultInfo<StudentCreditResult> resultInfo = new ResultInfo<>();

        Student student = studentDao.getOne(id);
        //获取学生的专业，根据专业的标准来审核毕业条件
        String majorName = student.getMajor().getMajorName();
        String majorYear = student.getMajor().getMajorYears();
        //根据专业id去查找出该专业毕业的标准
        Major major = majorDao.findMajorByMajorNameAndMajorYears(majorName, majorYear);

        float requiredCoursesCredit = 0;                      //专业课学分标准
        float max_fail_credit = major.getMaxFailCredit();     //最多补考学分标准
        float needPeCredit = major.getNeedPeCredit();       //体育课学分标准
        float needPublicCredit = major.getNeedPublicCredit(); //选修课学分标准

        List<Grade> gradeList = student.getGradeList();
        float studentPeCredit = 0;       //该学生体育课学分
        float studentElectiveCredit = 0; //该学生选修课学分
        float studentRequiredCredit = 0; //该学生专业课学分
        float studentFailCredit = 0;     //该学生补考学分
        for (Grade grade : gradeList) {
            String courseType = grade.getCourseType();
            String courseName=grade.getCourseName();
            Course course=courseDao.findByCourseNameAndCourseType(courseName, courseType);
            if (Required_Course.equals(courseType)|| Elective_Course.equals(courseType)|| Practice_Course.equals(courseType)) {
                requiredCoursesCredit += course.getCourseCredit(); //获取专业课学分标准
            }
            if (grade.getScore() >= 60) {
                if (PE.equals(courseType)) {             //体育课
                    studentPeCredit += course.getCourseCredit();
                }
                if (Public_Basic_Course.equals(courseType)||Public_Elective_Course.equals(courseType)) { //选修课
                    studentElectiveCredit += course.getCourseCredit();
                } else if (Required_Course.equals(courseType)|| Elective_Course.equals(courseType)|| Practice_Course.equals(courseType)) { //专业课课
                    studentRequiredCredit += course.getCourseCredit();
                }
            } else {
                if (Required_Course.equals(courseType)|| Elective_Course.equals(courseType)|| Practice_Course.equals(courseType)) { //专业课
                    studentFailCredit += course.getCourseCredit();
                }
            }
        }
        StudentCreditResult studentCreditResult = new StudentCreditResult();
        if (requiredCoursesCredit <= studentRequiredCredit && needPeCredit <= studentPeCredit && needPublicCredit <= studentElectiveCredit) {
            studentCreditResult.setGraduateOrNot(GraduateOrNot_Yes);
        } else {
            studentCreditResult.setGraduateOrNot(GraduateOrNot_No);
        }
        studentCreditResult.setName(student.getStudentName());
        studentCreditResult.setMaxFailCredit(max_fail_credit);
        studentCreditResult.setRequiredCoursesCredit(requiredCoursesCredit);
        studentCreditResult.setNeedPublicCredit(needPublicCredit);
        studentCreditResult.setNeedPeCredit(needPeCredit);
        studentCreditResult.setStudentFailCredit(studentFailCredit);
        studentCreditResult.setStudentRequiredCredit(studentRequiredCredit);
        studentCreditResult.setStudentElectiveCredit(studentElectiveCredit);
        studentCreditResult.setStudentPeCredit(studentPeCredit);
        resultInfo.setResultObj(studentCreditResult);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }
}