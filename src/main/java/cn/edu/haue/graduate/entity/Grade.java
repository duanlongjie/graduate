package cn.edu.haue.graduate.entity;

import javax.persistence.*;

/**
 * 成绩实体类
 * Created by 杨晋升 on 2018/5/29.
 */
@Entity
public class Grade {

    @EmbeddedId
    private GradeKey gradeId;

    //分数
    private float score;

    //所属课程
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Course course;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Student student;

    public GradeKey getGradeId() {
        return gradeId;
    }

    public void setGradeId(GradeKey gradeId) {
        this.gradeId = gradeId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
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
    public String toString() {
        return "Grade{" +
                "gradeId='" + gradeId + '\'' +
                ", score=" + score +
                ", course=" + course +
                '}';
    }
}
