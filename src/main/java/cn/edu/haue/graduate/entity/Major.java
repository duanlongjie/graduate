package cn.edu.haue.graduate.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * 专业实体类
 * Created by 杨晋升 on 2018/6/9.
 */
@Entity
@IdClass(MajorKey.class)
public class Major {

    //专业名称
    @Id
    @Column(length = 32)
    private String majorName;
    //专业年级
    @Id
    @Column(length = 32)
    private String majorYears;
    //毕业标准

    //需要公共课选修课学分
    private float needPublicCredit;

    //需要体育课学分
    private float needPeCredit;

    //最多重修学分
    private float maxFailCredit;

    protected Major() {
    }

    public Major(String majorName, String majorYears) {
        this.majorName = majorName;
        this.majorYears = majorYears;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
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

    public String getMajorYears() {
        return majorYears;
    }

    public void setMajorYears(String majorYears) {
        this.majorYears = majorYears;
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorName='" + majorName + '\'' +
                ", majorYears='" + majorYears + '\'' +
                ", needPublicCredit=" + needPublicCredit +
                ", needPeCredit=" + needPeCredit +
                ", maxFailCredit=" + maxFailCredit +
                '}';
    }
}
