package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 杨晋升 on 2018/5/30.
 */
public interface AdminDao extends JpaRepository<Admin, Integer> {
    Admin findAdminByUsername(String username);
}
