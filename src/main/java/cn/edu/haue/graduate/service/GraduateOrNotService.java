package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.entity.StudentCreditResult;

/**
 * 是否满足毕业条件service
 */
public interface GraduateOrNotService {

    public StudentCreditResult GraduateOrNot(String id);

}
