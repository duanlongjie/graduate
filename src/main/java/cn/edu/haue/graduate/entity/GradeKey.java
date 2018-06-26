package cn.edu.haue.graduate.entity;

import java.io.Serializable;

/**
 * 成绩联合主键实体类
 * Created by 杨晋升 on 2018/6/25.
 */
public class GradeKey implements Serializable {
    //所属课程
    private String courseName;

    //所属课程类型
    private String courseType;

    //所属学生
    private String studentId;

    public GradeKey() {
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradeKey)) return false;

        GradeKey gradeKey = (GradeKey) o;

        if (getCourseName() != null ? !getCourseName().equals(gradeKey.getCourseName()) : gradeKey.getCourseName() != null)
            return false;
        if (getCourseType() != null ? !getCourseType().equals(gradeKey.getCourseType()) : gradeKey.getCourseType() != null)
            return false;
        return getStudentId() != null ? getStudentId().equals(gradeKey.getStudentId()) : gradeKey.getStudentId() == null;
    }

    @Override
    public int hashCode() {
        int result = getCourseName() != null ? getCourseName().hashCode() : 0;
        result = 31 * result + (getCourseType() != null ? getCourseType().hashCode() : 0);
        result = 31 * result + (getStudentId() != null ? getStudentId().hashCode() : 0);
        return result;
    }
}
