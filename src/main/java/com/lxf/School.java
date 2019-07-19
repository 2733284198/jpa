package com.lxf;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer schoolId;
    @Column
    private String name;
    @Column
    private String address;

//    @OneToMany(targetEntity = Student.class)//声明关系，一对多
//    @JoinColumn(name = "school_id",referencedColumnName = "schoolId")//配置外键
    @OneToMany(mappedBy = "school",cascade = CascadeType.ALL)//Student里面已经配置了映射关系，表示参照Student里面的school
    private List<Student> students = new ArrayList<>();

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", students=" + students +
                '}';
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
