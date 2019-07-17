package com.lxf.test;

import com.lxf.User;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    @Test
    public void testSave(){
        //1.加载配置文件，获取工厂对象
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
        //2.创建实体管理器
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //3.获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //4.开启事务
        transaction.begin();
        //5.相关CRUD操作
        User user = new User();
        user.setName("tom");
        user.setAge(18);
        user.setSex(1);
        //保存
        entityManager.persist(user);
        //6.提交事务
        transaction.commit();
        //7.释放资源
        entityManager.close();
        entityManagerFactory.close();
    }
}
