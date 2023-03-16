package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("select * from USERS where USERNAME = #{username}")
    Optional<UserEntity> findUserByUsername(String username);

    @Insert("Insert into USERS (USERNAME, SALT, PASSWORD, FIRSTNAME, LASTNAME) " +
            "values (#{username},#{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(UserEntity user);
}
