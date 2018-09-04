package cn.edu.haue.graduate.entity;

/**
 * Author: lnp
 * Date: 2018/9/4
 * 成绩详情页 值对象
 **/
public class GradeInfo {
    private String courseName;
    private String courseType;
    private float courseCredit;
    private float score;

    public GradeInfo() {
    }

    public GradeInfo(String courseName, String courseType, float courseCredit, float score) {
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseCredit = courseCredit;
        this.score = score;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public float getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(float courseCredit) {
        this.courseCredit = courseCredit;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "GradeInfo{" +
                "courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseCredit=" + courseCredit +
                ", score=" + score +
                '}';
    }
}
