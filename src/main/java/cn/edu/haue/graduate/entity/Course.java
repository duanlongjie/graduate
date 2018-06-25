package cn.edu.haue.graduate.entity;

import javax.persistence.*;

/**
 * 课程实体类
 * Created by 杨晋升 on 2018/5/29.
 */
@Entity
public class Course {

    @EmbeddedId
    private CourseKey courseId;

    //课程名称
    private String courseName;

    //课程类型
    private String courseType;

    //所属专业
    @ManyToOne
    private Major major;

    //课程学分
    private float courseCredit;

    protected Course() {
    }

    public Course(String courseName, String courseType, float courseCredit, Major major) {
        this.courseName = courseName;
        this.courseType = courseType;
        this.major = major;
        this.courseCredit = courseCredit;
    }

    public CourseKey getCourseId() {
        return courseId;
    }

    public void setCourseId(CourseKey courseId) {
        this.courseId = courseId;
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

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Course{" +
                ", courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseCredit=" + courseCredit +
                '}';
    }
}
