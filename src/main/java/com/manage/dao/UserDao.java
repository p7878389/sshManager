package com.manage.dao;

import com.manage.dao.BaseDao;
import com.manage.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public interface UserDao extends BaseDao<User,Integer> {

    public static final String USERNAME="userName";
    public static final String SALT="salt";
    public static final String STATE="state";

    public List<User> getUser(User user);

}
