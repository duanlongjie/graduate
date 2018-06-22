package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.MainMenu;
import cn.edu.haue.graduate.entity.SubMenu;
import cn.edu.haue.graduate.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by 杨晋升 on 2018/6/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataTest {
    @Resource
    private MainMenuDao mainMenuDao;

    @Resource
    private SubMenuDao subMenuDao;

    @Test
    public void Init() throws IOException {
        mainMenuDao.deleteAll();
        Gson gson = new Gson();
        String json = JsonUtils.loadJsonFile("AdminMenu.json");
        List<MainMenu> mainMenuList = gson.fromJson(json, new TypeToken<List<MainMenu>>() {
        }.getType());
        for (MainMenu mainMenu : mainMenuList) {
            System.out.println(mainMenu);
            for (SubMenu subMenu : mainMenu.getSubMenuList()) {
                System.out.println(subMenu);
            }
        }
        mainMenuDao.saveAll(mainMenuList);
        for (MainMenu mainMenu : mainMenuDao.findAll()) {
            for (SubMenu subMenu : mainMenu.getSubMenuList()) {
                subMenu.setMainMenu(mainMenu);
            }
        }
    }
}
