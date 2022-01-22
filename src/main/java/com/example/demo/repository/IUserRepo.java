package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface IUserRepo extends PagingAndSortingRepository<User,Long> {
    @Query
    (value = "select u from User u where u.userName like concat('%' ,:userName, '%')")
    ArrayList<User> findAllByName(@Param("userName") String userName);
}
