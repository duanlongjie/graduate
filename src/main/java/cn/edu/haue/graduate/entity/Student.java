package cn.edu.haue.graduate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生实体类
 * Created by 杨晋升 on 2018/5/29.
 */
@Entity
public class Student {

    @Id
    private String studentId;

    private String studentName;

    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    private Major major;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Grade> gradeList = new ArrayList<>();

    protected Student() {
    }

    public Student(String studentId, String studentName, String password) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.password = password;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", password='" + password + '\'' +
                ", major=" + major +
                ", gradeList=" + gradeList +
                '}';
    }
}
