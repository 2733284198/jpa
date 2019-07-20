package com.lxf;

import javax.persistence.*;

@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer studentId;
    @Column
    private String name;
    @Column
    private Double score;
    @Column
    private Integer sex;
    @Column(name = "school_id",insertable = false,updatable = false)//外键字段
    private Integer schoolId;

    @ManyToOne(targetEntity = School.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)//配置多对一关系
    @JoinColumn(name = "school_id",referencedColumnName = "schoolId")//配置外键
    private School school;

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", sex=" + sex +
                ", schoolId=" + schoolId +
                ", school=" + school +
                '}';
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
