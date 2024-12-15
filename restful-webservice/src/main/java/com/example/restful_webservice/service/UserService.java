package com.example.restful_webservice.service;

import com.example.restful_webservice.mapper.UserMapper;
import com.example.restful_webservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.findAll();
    }
}
