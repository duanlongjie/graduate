package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 杨晋升 on 2018/5/29.
 */
public interface StudentDao extends JpaRepository<Student, String> {
    /**
     *  通过 isDelete 参数查询 学生信息
     * @param isDelete  0: 可用 1:是删除
     * @return  满足条件的 学生列表
     */
    List<Student> getAllByIsDelete(Integer isDelete);

}
