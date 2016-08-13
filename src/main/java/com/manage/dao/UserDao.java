package com.manage.dao;

import com.manage.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public interface UserDao extends BaseDao<User,Integer>{

    public List<User> getUser(User user);
}
