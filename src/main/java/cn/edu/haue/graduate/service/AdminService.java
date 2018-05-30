package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.dao.AdminDao;
import cn.edu.haue.graduate.entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by 杨晋升 on 2018/5/30.
 */
@Transactional
@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Admin getAdminByUsername(String username) {
        return adminDao.findAdminByUsername(username);
    }
}
