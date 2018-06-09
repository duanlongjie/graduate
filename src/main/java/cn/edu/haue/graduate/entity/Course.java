package cn.edu.haue.graduate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 课程实体类
 * Created by 杨晋升 on 2018/5/29.
 */
@Entity
public class Course {

    @Id
    private Integer courseId;

    private String courseName;

    private String courseType;

    //课程学分
    private Integer courseCredit;

    protected Course() {
    }

    public Course(Integer courseId, String courseName, String courseType, Integer courseCredit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseCredit = courseCredit;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
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

    public Integer getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Integer courseCredit) {
        this.courseCredit = courseCredit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseCredit=" + courseCredit +
                '}';
    }
}
