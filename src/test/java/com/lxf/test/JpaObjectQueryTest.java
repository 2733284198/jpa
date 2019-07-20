package com.lxf.test;

import com.lxf.School;
import com.lxf.Student;
import com.lxf.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;

public class JpaObjectQueryTest {
    @Test
    public void test(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
//            School school = entityManager.find(School.class, 5);
            School school = entityManager.getReference(School.class, 5);
            List<Student> students = school.getStudents();
            System.out.println(students.size());
            students.forEach(new Consumer<Student>() {
                @Override
                public void accept(Student student) {
                    System.out.println(student);
                }
            });

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException("出异常啦");
        }finally {
            entityManager.close();
        }
    }

    @Test
    public void test2(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Student student = entityManager.find(Student.class, 5);
//            Student student = entityManager.getReference(Student.class, 5);
            School school = student.getSchool();
            System.out.println(school);

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException("出异常啦");
        }finally {
            entityManager.close();
        }
    }
}
