package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.Admin;
import cn.edu.haue.graduate.entity.MainMenu;
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
    @Resource
    private MainMenuDao mainMenuDao;

    @Test
    public void add() {
        Admin admin = new Admin("admin", PasswordEncrypter.getPasswordEncoder().encode("123"));
        adminDao.save(admin);
        List<MainMenu> mainMenuList = mainMenuDao.findAll();
        admin.getMainMenuList().addAll(mainMenuList);
        adminDao.save(admin);
    }

}
