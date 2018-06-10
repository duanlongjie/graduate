package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.Student;

/**
 * 是否满足毕业条件service
 */
public interface GraduateOrNotService {

    public boolean GraduateOrNot(Student student);

}
