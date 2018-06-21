package cn.edu.haue.graduate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 成绩实体类
 * Created by 杨晋升 on 2018/5/29.
 */
@Entity
public class Grade {

    @Id
    private String gradeId;

    //分数
    private float score;

    //所属课程
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Course course;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
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

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId='" + gradeId + '\'' +
                ", score=" + score +
                ", course=" + course +
                '}';
    }
}
