package cn.edu.haue.graduate.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

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
    //专业必修课
    private List<Course> requiredCourses = new ArrayList<>();

    //需要公共课选修课学分
    private float needPublicCredit;

    //需要体育课学分
    private float needPECredit;

    //最多重修学分
    private float maxFailCredit;

    protected Major() {
    }

    public Major(Integer majorID, String majorName, List<Course> requiedCourses, float needPublicCredit, float needPECredit, float maxFailCredit) {
        MajorID = majorID;
        MajorName = majorName;
        this.requiredCourses = requiedCourses;
        this.needPublicCredit = needPublicCredit;
        this.needPECredit = needPECredit;
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

    public List<Course> getRequiedCourses() {
        return requiredCourses;
    }

    public void setRequiedCourses(List<Course> requiedCourses) {
        this.requiredCourses = requiedCourses;
    }

    public float getNeedPublicCredit() {
        return needPublicCredit;
    }

    public void setNeedPublicCredit(float needPublicCredit) {
        this.needPublicCredit = needPublicCredit;
    }

    public float getNeedPECredit() {
        return needPECredit;
    }

    public void setNeedPECredit(float needPECredit) {
        this.needPECredit = needPECredit;
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
                ", needPECredit=" + needPECredit +
                ", maxFailCredit=" + maxFailCredit +
                '}';
    }
}
