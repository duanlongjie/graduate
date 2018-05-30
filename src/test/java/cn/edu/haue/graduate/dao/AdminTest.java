package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.Admin;
import cn.edu.haue.graduate.entity.Role;
import cn.edu.haue.graduate.utils.PasswordEncrypter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 杨晋升 on 2018/5/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {

    @Resource
    private AdminDao adminDao;

    @Test
    public void add() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(PasswordEncrypter.getPasswordEncoder().encode("123"));
        List<Role> roleList = admin.getRoleList();
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        roleList.add(role);
        adminDao.save(admin);
    }

    @Test
    public void checkPassword() {
        System.out.println(PasswordEncrypter.getPasswordEncoder().matches("123", "$2a$10$S48nCS2NZwVPPU3vJZsJY.IHh0kJRHW/.MrdJLf1M2tlOg30BKvte"));
        System.out.println(PasswordEncrypter.getPasswordEncoder().matches("123", "$2a$10$UlcOMHSxKjsubEeCZR2AaOjbDBWQFFfT5L9zryato4gBkAZaXJLYi"));
        System.out.println(PasswordEncrypter.getPasswordEncoder().matches("123", "$2a$10$UlcOMHSxKjsubEeCZR2AaOjbDBWQFFfT5L9zryato4gBkAZaXJpYi"));
    }

}
