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
    /**标志 0:可用 1: 删除*/
    private Integer isDelete;
    /**学生已经获得的学分*/
    private Integer acquireCredit;

    //所属专业
    @ManyToOne(cascade = CascadeType.ALL)
    private Major major;

    //学生成绩单
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Grade> gradeList = new ArrayList<>();

    public Student() {
    }

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getAcquireCredit() {
        return acquireCredit;
    }

    public void setAcquireCredit(Integer acquireCredit) {
        this.acquireCredit = acquireCredit;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", password='" + password + '\'' +
                ", isDelete=" + isDelete +
                ", acquireCredit=" + acquireCredit +
                ", major=" + major +
                ", gradeList=" + gradeList +
                '}';
    }
}
