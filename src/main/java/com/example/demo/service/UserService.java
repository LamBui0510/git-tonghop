package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.repository.IUserRepo;

import java.util.Comparator;
import java.util.List;
@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepo iUserRepo;

    @Override
    public List<User> findAll() {
        return (List<User>) iUserRepo.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return iUserRepo.findAll(pageable);
    }

    @Override
    public void save(User user) {
    iUserRepo.save(user);
    }

    @Override
    public void delete(long id) {
    iUserRepo.deleteById(id);
    }

    @Override
    public User findById(long id) {
        return iUserRepo.findById(id).get();
    }

    @Override
    public List<User> searchByName(String searchName) {
        return iUserRepo.findAllByName(searchName);
    }

    @Override
    public List<User> sortByUserName() {
        List<User> userList = findAll();
        userList.sort(Comparator.comparing(User::getUserName));
        return userList;
    }
}
