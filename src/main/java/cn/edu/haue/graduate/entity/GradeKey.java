package cn.edu.haue.graduate.entity;

import java.io.Serializable;

/**
 * 成绩联合主键实体类
 * Created by 杨晋升 on 2018/6/25.
 */
public class GradeKey implements Serializable {
    //所属课程
    private Course course;

    //所属学生
    private Student student;

    public GradeKey() {
    }

    public GradeKey(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradeKey)) return false;

        GradeKey gradeKey = (GradeKey) o;

        if (getCourse() != null ? !getCourse().equals(gradeKey.getCourse()) : gradeKey.getCourse() != null)
            return false;
        return getStudent() != null ? getStudent().equals(gradeKey.getStudent()) : gradeKey.getStudent() == null;
    }

    @Override
    public int hashCode() {
        int result = getCourse() != null ? getCourse().hashCode() : 0;
        result = 31 * result + (getStudent() != null ? getStudent().hashCode() : 0);
        return result;
    }
}
