package cn.edu.haue.graduate.service.Impl;

import cn.edu.haue.graduate.constant.ResultCode;
import cn.edu.haue.graduate.dao.GradeDao;
import cn.edu.haue.graduate.dao.StudentDao;
import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public ResultInfo<List<Grade>> getGradeList(Student student) {
        ResultInfo<List<Grade>> resultInfo = new ResultInfo<>();
        try {
            //根据学生id查询成绩
            List<Grade> gradeList = gradeDao.getGradeList(student.getStudentId());
            resultInfo.setResultObj(gradeList);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
        }

        return resultInfo;
    }

    @Override
    public ResultInfo<Grade> updateGrade(Grade grade) {
        ResultInfo<Grade> resultInfo = new ResultInfo<>();
        try {

            //根据id获取成绩
            //Grade one = gradeDao.getOne(grade.getGradeId());
           // grade.setCourse(one.getCourse());
            //将成绩保存
            Grade save = gradeDao.save(grade);
            resultInfo.setResultObj(save);
            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
        }

        return resultInfo;
    }


    @Override
    public ResultInfo<Grade> addGrade(Grade grade, Student student) {
        ResultInfo<Grade> resultInfo = new ResultInfo<>();
        try {
            //
            Student one = studentDao.getOne(student.getStudentId());
            //查询学生成绩列表
            List<Grade> gradeList = one.getGradeList();
            //grade.setGradeId(UUID.randomUUID().toString());
            //添加学生的成绩
            gradeList.add(grade);

            one.setGradeList(gradeList);
            //保存
            studentDao.save(one);

        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
        }
        return resultInfo;
    }

    @Override
    public ResultInfo<Grade> delGrade(String id, Student student) {
        ResultInfo<Grade> resultInfo = new ResultInfo<>();
        try {
            //删除学生和成绩的关联关系
            Student one = studentDao.getOne(student.getStudentId());
            List<Grade> gradeList = one.getGradeList();

            Grade grade = gradeDao.getOne(id);
            //删除学生成绩
            gradeList.remove(grade);
            //保存
            one.setGradeList(gradeList);
            studentDao.save(one);

            //根据id删除成绩
            gradeDao.deleteById(id);

            resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);


        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
        }

        return resultInfo;
    }
}
