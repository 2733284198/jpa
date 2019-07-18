package com.lxf;

import com.lxf.dao.UserDao;
import com.lxf.entity.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring_data_jpa.xml")
public class Test {
    @Autowired
    private UserDao userDao;

    @org.junit.Test
    public void testSave(){
        User user = new User();
        user.setName("小红");
        user.setAge(22);
        User userResult = userDao.save(user);
        System.out.println(userResult);
    }

    @org.junit.Test
    public void testDelete(){
        userDao.deleteById(2);
    }

    @org.junit.Test
    @Transactional
    public void testFindOne(){
//        Optional<User> user = userDao.findById(2);
//        System.out.println(user.get());
        User user = userDao.getOne(2);
        System.out.println(user);
    }

    @org.junit.Test
    public void testApi(){
        long count = userDao.count();
        boolean b = userDao.existsById(2);
    }

    @org.junit.Test
    @Transactional
    @Rollback(value = false)
    public void testJpql(){
        User user = userDao.findUserByName("lili",18);
        System.out.println(user);

        userDao.updateNameById(user.getId(),"lili_2");
    }
}
