package com.lxf.dao;

import com.lxf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

//    @Query(value = "from User where name = :name and age = :age")
//    public User findUserByName(@Param("name") String userName,@Param("age") int age);

    @Query(value = "update User set name = :name where id = :id")
    @Modifying
    public Integer updateNameById(@Param("id") int id, @Param("name") String userName);

    @Query(value = "select * from user where name = :name and age = :age", nativeQuery = true)
    public User findUserByName(@Param("name") String userName, @Param("age") int age);

    public User findByName(String name);

    public User findByNameLike(String name);

    public User findByNameLikeAndAge(String name, int age);

    public List<User> findByIdBetween(int idMin, int idMax);
}
