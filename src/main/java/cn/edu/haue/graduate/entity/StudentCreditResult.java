package cn.edu.haue.graduate.entity;

/**
 * Author: lnp
 * Date: 2018/6/19
 * 学生毕业条件返回结果信息
 **/
public class StudentCreditResult {
    //学生姓名
    private String name;
    //能否毕业
    private String graduateOrNot;
    //需要公共课选修课学分
    private float needPublicCredit;
    //需要体育课学分
    private float needPeCredit;
    //最多补考学分
    private float maxFailCredit;
    //必修课学分标准
    private float requiredCoursesCredit;
    //该学生体育课学分
    private float studentPeCredit;
    //该学生选修课学分
    private float studentElectiveCredit;
    //该学生必修课学分
    private float studentRequiredCredit;
    //该学生补考学分
    private float studentFailCredit;

    @Override
    public String toString() {
        return "StudentCreditResult{" +
                "name='" + name + '\'' +
                ", graduateOrNot='" + graduateOrNot + '\'' +
                ", needPublicCredit=" + needPublicCredit +
                ", needPeCredit=" + needPeCredit +
                ", maxFailCredit=" + maxFailCredit +
                ", requiredCoursesCredit=" + requiredCoursesCredit +
                ", studentPeCredit=" + studentPeCredit +
                ", studentElectiveCredit=" + studentElectiveCredit +
                ", studentRequiredCredit=" + studentRequiredCredit +
                ", studentFailCredit=" + studentFailCredit +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGraduateOrNot() {
        return graduateOrNot;
    }

    public void setGraduateOrNot(String graduateOrNot) {
        this.graduateOrNot = graduateOrNot;
    }

    public float getNeedPublicCredit() {
        return needPublicCredit;
    }

    public void setNeedPublicCredit(float needPublicCredit) {
        this.needPublicCredit = needPublicCredit;
    }

    public float getNeedPeCredit() {
        return needPeCredit;
    }

    public void setNeedPeCredit(float needPeCredit) {
        this.needPeCredit = needPeCredit;
    }

    public float getMaxFailCredit() {
        return maxFailCredit;
    }

    public void setMaxFailCredit(float maxFailCredit) {
        this.maxFailCredit = maxFailCredit;
    }

    public float getRequiredCoursesCredit() {
        return requiredCoursesCredit;
    }

    public void setRequiredCoursesCredit(float requiredCoursesCredit) {
        this.requiredCoursesCredit = requiredCoursesCredit;
    }

    public float getStudentPeCredit() {
        return studentPeCredit;
    }

    public void setStudentPeCredit(float studentPeCredit) {
        this.studentPeCredit = studentPeCredit;
    }

    public float getStudentElectiveCredit() {
        return studentElectiveCredit;
    }

    public void setStudentElectiveCredit(float studentElectiveCredit) {
        this.studentElectiveCredit = studentElectiveCredit;
    }

    public float getStudentRequiredCredit() {
        return studentRequiredCredit;
    }

    public void setStudentRequiredCredit(float studentRequiredCredit) {
        this.studentRequiredCredit = studentRequiredCredit;
    }

    public float getStudentFailCredit() {
        return studentFailCredit;
    }

    public void setStudentFailCredit(float studentFailCredit) {
        this.studentFailCredit = studentFailCredit;
    }
}
