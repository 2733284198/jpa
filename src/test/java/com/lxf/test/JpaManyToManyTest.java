package com.lxf.test;

import com.lxf.Developer;
import com.lxf.Language;
import com.lxf.School;
import com.lxf.Student;
import com.lxf.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaManyToManyTest {

    @Test
    public void testSave(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Developer developer = new Developer();
            developer.setName("lxf");
            Language language = new Language();
            language.setDes("java");

            //创建关系
            developer.getLanguages().add(language);

            entityManager.persist(developer);
            entityManager.persist(language);

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
            Developer developer = new Developer();
            developer.setName("lxf");
            Language language = new Language();
            language.setDes("java");

            developer.getLanguages().add(language);
            language.getDevelopers().add(developer);

            entityManager.persist(developer);

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
            Developer developer = entityManager.find(Developer.class, 4);
            entityManager.remove(developer);

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
