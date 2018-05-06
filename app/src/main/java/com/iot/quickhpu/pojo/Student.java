package com.iot.quickhpu.pojo;

import java.io.Serializable;

/**
 * @Author m1563
 * @Date 2018/3/4
 * @Description 学生实体类
 */

public class Student implements Serializable {
    private String studentId;//[0] 学生号
    private String name;//[1] 姓名
    private String idCard;//2 身份证号
    private String sex;//3  性别
    private String education;// 学历 4
    private String national;//5 民族
    private String address;//6 住址
    private String birthday;//7 出生日期
    private String admissionTime;// 入学时间 11
    private String institute;// 学院  12
    private String major;// 专业  13
    private String grade;//14  年级
    private String studentClass;//15 班级

    public Student() {
    }

    public Student(String studentId, String name, String idCard, String sex, String education, String national, String address, String birthday, String admissionTime, String institute, String major, String grade, String studentClass) {
        this.studentId = studentId;
        this.name = name;
        this.idCard = idCard;
        this.sex = sex;
        this.education = education;
        this.national = national;
        this.address = address;
        this.birthday = birthday;
        this.admissionTime = admissionTime;
        this.institute = institute;
        this.major = major;
        this.grade = grade;
        this.studentClass = studentClass;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(String admissionTime) {
        this.admissionTime = admissionTime;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", sex='" + sex + '\'' +
                ", education='" + education + '\'' +
                ", national='" + national + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", admissionTime='" + admissionTime + '\'' +
                ", institute='" + institute + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", studentClass='" + studentClass + '\'' +
                '}';
    }
}
