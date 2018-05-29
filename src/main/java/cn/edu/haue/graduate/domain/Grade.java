package cn.edu.haue.graduate.domain;

import javax.persistence.*;

/**
 * 成绩实体类
 * Created by 杨晋升 on 2018/5/29.
 */
@Entity
@Table
public class Grade {

    @Id
    private String gradeId;

    private Integer score;

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
