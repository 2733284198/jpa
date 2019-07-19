package com.lxf;

import com.lxf.dao.UserDao;
import com.lxf.entity.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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
    @Transactional(rollbackFor = Exception.class)
//    @Rollback(value = false)//如果设置为fasle，即使发生异常也不会回滚
    public void testJpql(){
        User user = userDao.findUserByName("lili",18);
        System.out.println(user);

        userDao.updateNameById(user.getId(),"lili_2");

        int a = 1/0;
        userDao.updateNameById(user.getId(),"lili_3");
    }

    @org.junit.Test
    public void testName(){
        User user1 = userDao.findByName("tom");
        System.out.println(user1);

        User user2 = userDao.findByNameLike("t%");
        System.out.println(user2);

        User user3 = userDao.findByNameLikeAndAge("tom",18);
        System.out.println(user3);

        List<User> users = userDao.findByIdBetween(1, 3);
        users.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });
    }
}
