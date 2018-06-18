package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by 杨晋升 on 2018/5/29.
 */
public interface StudentDao extends JpaRepository<Student, String> {


}
