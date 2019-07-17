package com.lxf.test;

import com.lxf.User;
import com.lxf.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaCRUDTest {
    @Test
    public void testSave(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            User user = new User();
            user.setName("tom");
            user.setAge(18);
            user.setSex(1);
            entityManager.persist(user);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw new RuntimeException("出异常啦");
        }finally {
            entityManager.close();
        }
    }

    @Test
    public void testFind(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            /**
             * 参数：
             *  1. class对象：查询数据结果需要包装的实体类类型的字节码
             *  2. 主键  这里是id
             */
            User user = entityManager.find(User.class, 1);
            System.out.println(user);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw new RuntimeException("出异常啦");
        }finally {
            entityManager.close();
        }
    }

    @Test
    public void testGetReference(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            /**
             * 参数：
             *  1. class对象：查询数据结果需要包装的实体类类型的字节码
             *  2. 主键  这里是id
             */
            User user = entityManager.getReference(User.class, 1);
            System.out.println(user);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw new RuntimeException("出异常啦");
        }finally {
            entityManager.close();
        }
    }

    @Test
    public void testRemove(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            User user = entityManager.getReference(User.class, 1);
            //接收要删除的对象
            entityManager.remove(user);
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
    public void testUpdate(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            User user = entityManager.getReference(User.class, 2);
            user.setName("lili");
            //接收要修改的对象
            entityManager.merge(user);
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
