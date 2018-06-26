package cn.edu.haue.graduate.entity;

import java.io.Serializable;

public class MajorKey implements Serializable {
    private String majorName;
    private String majorYears;

    public MajorKey() {
    }

    public MajorKey(String majorName, String majorYears) {
        this.majorName = majorName;
        this.majorYears = majorYears;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorYears() {
        return majorYears;
    }

    public void setMajorYears(String majorYears) {
        this.majorYears = majorYears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MajorKey)) return false;

        MajorKey majorKey = (MajorKey) o;

        if (getMajorName() != null ? !getMajorName().equals(majorKey.getMajorName()) : majorKey.getMajorName() != null)
            return false;
        return getMajorYears() != null ? getMajorYears().equals(majorKey.getMajorYears()) : majorKey.getMajorYears() == null;
    }

    @Override
    public int hashCode() {
        int result = getMajorName() != null ? getMajorName().hashCode() : 0;
        result = 31 * result + (getMajorYears() != null ? getMajorYears().hashCode() : 0);
        return result;
    }
}
