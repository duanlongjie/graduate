package cn.edu.haue.graduate.entity;


import javax.persistence.*;

@Entity
@IdClass(CourseKey.class)
public class Course {
    @Id
    @Column(length = 32)
    private String courseName;
    @Id
    @Column(length = 32)
    private String majorName;

    //课程类型
    private String courseType;

    //课程学分
    private float courseCredit;

    public Course() {
    }

    public Course(String courseName, String majorName, String courseType, float courseCredit) {
        this.courseName = courseName;
        this.majorName = majorName;
        this.courseType = courseType;
        this.courseCredit = courseCredit;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
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

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", majorName='" + majorName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseCredit=" + courseCredit +
                '}';
    }
}