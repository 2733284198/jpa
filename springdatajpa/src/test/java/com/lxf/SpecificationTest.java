package com.lxf;

import com.lxf.dao.UserDao;
import com.lxf.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring_data_jpa.xml")
public class SpecificationTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //分别构造各个单属性的过滤条件
                Predicate namePredicate = criteriaBuilder.like(root.get("address"), "河南%");
                Predicate agePredicate = criteriaBuilder.ge(root.get("age"), 18);//大于等于

                //组合成最终的过滤条件
                Predicate predicate = criteriaBuilder.and(namePredicate, agePredicate);
                return predicate;
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        Pageable pageable = PageRequest.of(0,10);
        Pageable pageable = PageRequest.of(0,10,sort);
        Page<User> users = userDao.findAll(specification, pageable);
        users.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });
    }
}
