package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.entity.Student;

/**
 * 是否满足毕业条件service
 */
public interface GraduateOrNotService {

    public ResultInfo<Student> GraduateOrNot(String id);

}
