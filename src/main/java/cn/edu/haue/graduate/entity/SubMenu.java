package cn.edu.haue.graduate.entity;

import javax.persistence.*;

/**
 * Created by 杨晋升 on 2018/6/21.
 */
@Entity
public class SubMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String href;

    @ManyToOne(cascade = CascadeType.ALL)
    private MainMenu mainMenu;

    protected SubMenu() {
    }

    public SubMenu(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public String toString() {
        return "SubMenu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
