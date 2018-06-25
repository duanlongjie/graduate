package cn.edu.haue.graduate.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 课程联合主键实体类
 * Created by 杨晋升 on 2018/6/25.
 */
@Embeddable
public class CourseKey implements Serializable {

    private String courseName;

    private Major major;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "CourseKey{" +
                "courseName='" + courseName + '\'' +
                ", major=" + major +
                '}';
    }
}
