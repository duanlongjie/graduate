package cn.edu.haue.graduate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员实体类
 * Created by 杨晋升 on 2018/5/30.
 */
@Entity
public class Admin {
    @Id
    private String username;

    private String password;

    //管理员所属具有的角色
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<MainMenu> mainMenuList = new ArrayList<>();

    //管理员偏好
    @ManyToMany
    private List<SubMenu> preferences = new ArrayList<>();

    protected Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public List<SubMenu> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<SubMenu> preferences) {
        this.preferences = preferences;
    }

    public List<MainMenu> getMainMenuList() {

        return mainMenuList;
    }

    public void setMainMenuList(List<MainMenu> mainMenuList) {
        this.mainMenuList = mainMenuList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
