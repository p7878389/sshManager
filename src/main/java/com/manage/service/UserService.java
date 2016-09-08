package com.manage.service;

import com.manage.entity.User;
import com.manage.util.BaseResult;

public interface UserService {

    /**
     * 根据user对象查询用户信息
     * @param user
     * @return
     */
    public User findByUser(User user);


    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    public User findById(Integer id);


    public BaseResult saveOrUpdate(User user);
}
