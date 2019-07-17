package com.lxf.test;

import com.lxf.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

public class JpaQueryTest {
    @Test
    public void testFindAll(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            //查询全部
            String jpql = "from User";
            Query query = entityManager.createQuery(jpql);
            List resultList = query.getResultList();
            resultList.forEach(new Consumer() {
                @Override
                public void accept(Object o) {
                    System.out.println(o);
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
}
