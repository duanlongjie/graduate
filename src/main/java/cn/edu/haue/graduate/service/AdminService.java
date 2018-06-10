package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.Admin;

/**
 * Created by 杨晋升 on 2018/5/30.
 */
public interface AdminService {
    Admin getAdminByUsername(String username);
}
