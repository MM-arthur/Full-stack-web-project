package com.example.restful_webservice.mapper;

import com.example.restful_webservice.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> findAll();
}

