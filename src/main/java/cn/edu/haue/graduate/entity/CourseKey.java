package cn.edu.haue.graduate.entity;

import java.io.Serializable;

public class CourseKey implements Serializable {
    private String courseName;
    private String majorName;
    private String courseType;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseKey)) return false;

        CourseKey courseKey = (CourseKey) o;

        if (getCourseName() != null ? !getCourseName().equals(courseKey.getCourseName()) : courseKey.getCourseName() != null)
            return false;
        if (getMajorName() != null ? !getMajorName().equals(courseKey.getMajorName()) : courseKey.getMajorName() != null)
            return false;
        return getCourseType() != null ? getCourseType().equals(courseKey.getCourseType()) : courseKey.getCourseType() == null;
    }

    @Override
    public int hashCode() {
        int result = getCourseName() != null ? getCourseName().hashCode() : 0;
        result = 31 * result + (getMajorName() != null ? getMajorName().hashCode() : 0);
        result = 31 * result + (getCourseType() != null ? getCourseType().hashCode() : 0);
        return result;
    }
}