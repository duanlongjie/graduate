package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.Grade;
import cn.edu.haue.graduate.entity.GradeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 杨晋升 on 2018/5/29.
 */
public interface GradeDao extends JpaRepository<Grade, GradeKey> {

}
