package com.lxf.test;

import com.lxf.School;
import com.lxf.Student;
import com.lxf.User;
import com.lxf.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaOneToManyTest {

    @Test
    public void testSave(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Student student = new Student();
            student.setName("石昊");
            School school = new School();
            school.setName("天神书院");

            //创建关系
            student.setSchool(school);
//            school.getStudents().add(student);//两句代码都可以创建关系

            entityManager.persist(school);//先保存主表，如果先保存从表的话最好需要多一个update操作设置从表外键字段值
            entityManager.persist(student);

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
    public void testDelete(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            School school = entityManager.find(School.class, 6);
            entityManager.remove(school);

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
    public void testCascadeSave(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Student student = new Student();
            student.setName("叶凡");
            School school = new School();
            school.setName("荒古禁地");

            //创建关系
            student.setSchool(school);

            //这里只保存从表，主表会被级联保存
            entityManager.persist(student);

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
    public void testCascadeDelete(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            School school = entityManager.find(School.class, 10);
            entityManager.remove(school);

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
