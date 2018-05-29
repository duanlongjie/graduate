package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 杨晋升 on 2018/5/29.
 */
public interface GradeDao extends JpaRepository<Grade, String> {

}
