package cn.edu.haue.graduate.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 专业实体类
 * Created by 杨晋升 on 2018/6/9.
 */
@Entity
public class Major {

    @Id
    //学号前9位，就是专业ID
    private Integer MajorID;

    private String MajorName;

    //毕业标准
    //专业必修课
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> requiredCourses = new ArrayList<>();

    //需要公共课选修课学分
    private float needPublicCredit;

    //需要体育课学分
    private float needPeCredit;

    //最多重修学分
    private float maxFailCredit;

    protected Major() {
    }

    public Major(Integer majorID, String majorName, float needPublicCredit, float needPeCredit, float maxFailCredit) {
        MajorID = majorID;
        MajorName = majorName;
        this.needPublicCredit = needPublicCredit;
        this.needPeCredit = needPeCredit;
        this.maxFailCredit = maxFailCredit;
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

    public List<Course> getRequiredCourses() {
        return requiredCourses;
    }

    public void setRequiredCourses(List<Course> requiredCourses) {
        this.requiredCourses = requiredCourses;
    }

    public float getNeedPublicCredit() {
        return needPublicCredit;
    }

    public void setNeedPublicCredit(float needPublicCredit) {
        this.needPublicCredit = needPublicCredit;
    }

    public float getneedPeCredit() {
        return needPeCredit;
    }

    public void setneedPeCredit(float needPeCredit) {
        this.needPeCredit = needPeCredit;
    }

    public float getMaxFailCredit() {
        return maxFailCredit;
    }

    public void setMaxFailCredit(float maxFailCredit) {
        this.maxFailCredit = maxFailCredit;
    }

    @Override
    public String toString() {
        return "Major{" +
                "MajorID=" + MajorID +
                ", MajorName='" + MajorName + '\'' +
                ", needPublicCredit=" + needPublicCredit +
                ", needPeCredit=" + needPeCredit +
                ", maxFailCredit=" + maxFailCredit +
                '}';
    }
}
