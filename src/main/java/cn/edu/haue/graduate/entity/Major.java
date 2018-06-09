package cn.edu.haue.graduate.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 专业实体类
 * Created by 杨晋升 on 2018/6/9.
 */
@Entity
public class Major {

    @Id
    private Integer MajorID;

    private String MajorName;

    //毕业标准


    protected Major() {
    }

    public Major(Integer majorID, String majorName) {
        MajorID = majorID;
        MajorName = majorName;
    }

    public Integer getMajorID() {
        return MajorID;
    }

    public void setMajorID(Integer majorID) {
        MajorID = majorID;
    }

    public String getMajorName() {
        return MajorName;
    }

    public void setMajorName(String majorName) {
        MajorName = majorName;
    }

    @Override
    public String toString() {
        return "Major{" +
                "MajorID=" + MajorID +
                ", MajorName='" + MajorName + '\'' +
                '}';
    }
}
