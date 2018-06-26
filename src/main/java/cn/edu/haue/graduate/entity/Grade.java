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
    @Column(length = 32)
    private String courseName;

    //所属学生
    @Id
    @Column(length = 12)
    private String studentId;

    //分数
    private float score;

    public Grade() {
    }

    public Grade(String courseName, String studentId, float score) {
        this.courseName = courseName;
        this.studentId = studentId;
        this.score = score;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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
                "courseName='" + courseName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", score=" + score +
                '}';
    }
}
