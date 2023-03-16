package com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface;

import com.udacity.jwdnd.course1.cloudstorage.entity.UserEntity;

public interface IUserService {
    int insert(UserEntity user);
}
