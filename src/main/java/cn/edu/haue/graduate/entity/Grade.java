package cn.edu.haue.graduate.entity;

import javax.persistence.*;

/**
 * 成绩实体类
 * Created by 杨晋升 on 2018/5/29.
 */
@Entity
@IdClass(GradeKey.class)
public class Grade {

    //所属课程
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    //所属学生
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    //分数
    private float score;

    public Grade() {
    }

    public Grade(Course course, Student student, float score) {
        this.course = course;
        this.student = student;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "course=" + course +
                ", student=" + student +
                ", score=" + score +
                '}';
    }
}
