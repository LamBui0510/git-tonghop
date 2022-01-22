package com.example.demo.validate;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.example.demo.service.IUserService;

import java.util.List;

@Component
public class ValidateUserName implements Validator {
    @Autowired
    IUserService iUserService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target; //ép kiểu về đtg object trong vld
        List<User> userList = iUserService.findAll();
        for (User u: userList) {
            if (user.getUserName().equals(u.getUserName())){
                errors.rejectValue("userName","","userName already exist!");
                return;
            }
        }
    }
}
