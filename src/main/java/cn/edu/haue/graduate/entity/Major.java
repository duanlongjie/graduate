package cn.edu.haue.graduate.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 专业实体类
 * Created by 杨晋升 on 2018/6/9.
 */
@Entity
public class Major {

    //学号前9位，就是专业ID
    @Id
    @Column(length = 9)
    private String MajorID;

    private String MajorName;

    //毕业标准

    //需要公共课选修课学分
    private float needPublicCredit;

    //需要体育课学分
    private float needPeCredit;

    //最多重修学分
    private float maxFailCredit;

    protected Major() {
    }

    public Major(String majorID, String majorName, float needPublicCredit, float needPeCredit, float maxFailCredit) {
        MajorID = majorID;
        MajorName = majorName;
        this.needPublicCredit = needPublicCredit;
        this.needPeCredit = needPeCredit;
        this.maxFailCredit = maxFailCredit;
    }

    public String getMajorID() {
        return MajorID;
    }

    public void setMajorID(String majorID) {
        MajorID = majorID;
    }

    public String getMajorName() {
        return MajorName;
    }

    public void setMajorName(String majorName) {
        MajorName = majorName;
    }

    public float getNeedPublicCredit() {
        return needPublicCredit;
    }

    public void setNeedPublicCredit(float needPublicCredit) {
        this.needPublicCredit = needPublicCredit;
    }

    public float getNeedPeCredit() {
        return needPeCredit;
    }

    public void setNeedPeCredit(float needPeCredit) {
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
                "MajorID='" + MajorID + '\'' +
                ", MajorName='" + MajorName + '\'' +
                ", needPublicCredit=" + needPublicCredit +
                ", needPeCredit=" + needPeCredit +
                ", maxFailCredit=" + maxFailCredit +
                '}';
    }
}
