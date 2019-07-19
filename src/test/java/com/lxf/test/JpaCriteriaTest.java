package com.lxf.test;

import com.lxf.User;
import com.lxf.util.JPAUtil;
import org.junit.Test;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Consumer;

public class JpaCriteriaTest {
    @Test
    public void testCriteria(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            //拿到CriteriaBuilder、CriteriaQuery、Root三个对象
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);

            //构建过滤条件
            Predicate idCondition = criteriaBuilder.greaterThan(root.get("id"), 3);
            Predicate ageCondition = criteriaBuilder.greaterThan(root.get("age"), 18);
            Predicate predicate = criteriaBuilder.and(idCondition, ageCondition);

            //组合查询
            criteriaQuery.where(predicate);

            //查询并获取结果
            TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
            List<User> resultList = typedQuery.getResultList();//得到id>3的所有数据
            resultList.forEach(new Consumer<User>() {
                @Override
                public void accept(User user) {
                    System.out.println(user);
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
    public void testCriteria2(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            //拿到CriteriaBuilder、CriteriaQuery、Root三个对象
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tuple> tupleQuery = criteriaBuilder.createTupleQuery();
            Root<User> root = tupleQuery.from(User.class);
            tupleQuery.multiselect(root.get("name"),root.get("age"));

            //构建过滤条件
            Predicate idCondition = criteriaBuilder.greaterThan(root.get("id"), 3);
            Predicate ageCondition = criteriaBuilder.greaterThan(root.get("age"), 18);
            Predicate predicate = criteriaBuilder.and(idCondition, ageCondition);

            //组合查询
            tupleQuery.where(predicate);

            //查询并获取结果
            TypedQuery<Tuple> typedQuery = entityManager.createQuery(tupleQuery);
            List<Tuple> resultList = typedQuery.getResultList();//得到id>3的所有数据
            resultList.forEach(new Consumer<Tuple>() {
                @Override
                public void accept(Tuple tuple) {
                    System.out.println(tuple.get(0,String.class));
                    System.out.println(tuple.get(1,Integer.class));
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
