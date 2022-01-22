package com.example.demo.service;

import com.example.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.IRoleRepo;

import java.util.List;
@Service
public class RoleService implements IRoleService{
    @Autowired
    IRoleRepo iRoleRepo;
    @Override
    public List<Role> findAll() {
        return (List<Role>) iRoleRepo.findAll();
    }
}
