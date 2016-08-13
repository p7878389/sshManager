package com.manage.service;

import com.manage.entity.User;

public interface UserService {

    public User findByUser(User user);

    public User findById(Integer id);

}
