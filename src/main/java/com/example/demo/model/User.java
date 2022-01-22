package com.example.demo.model;

import com.example.demo.model.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
public class User {

//    id,user,pass,fullname,phone,email,avatar
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String userName;
    @Size(max = 125, min = 6, message = "from 6 to 125 character at least")
    private String passWord;
    @NotEmpty
    private String fullName;
    @NotEmpty(message = "Not Null!")
    private String phone;
    @NotEmpty(message = "Not Null!")
    private String email;
    private String avatar;
    @ManyToOne
    private Role role;
}
