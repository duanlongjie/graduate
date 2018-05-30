package cn.edu.haue.graduate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 课程实体类
 * Created by 杨晋升 on 2018/5/29.
 */
@Entity
@Table
public class Course {
    @Id
    private Integer courseId;

    private String courseName;

    private String courseType;

    protected Course() {
    }

    public Course(Integer courseId, String courseName, String courseType) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseType = courseType;
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

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                '}';
    }
}
