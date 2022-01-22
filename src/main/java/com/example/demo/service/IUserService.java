package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    public List<User> findAll();
    public Page<User> findAll(Pageable pageable);
    public void save(User user);
    public void delete(long id);
    public User findById(long id);
    public List<User> searchByName(String searchName);
    public List<User> sortByUserName();
}
