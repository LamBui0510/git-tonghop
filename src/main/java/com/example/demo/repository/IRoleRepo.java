package com.example.demo.repository;

import com.example.demo.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IRoleRepo extends PagingAndSortingRepository<Role, Long> {

}
