package com.manage.service;

import com.manage.entity.User;
import com.manage.util.BaseResult;
import com.manage.vo.Page;

public interface UserService {

    /**
     * 根据user对象查询用户信息
     *
     * @param user
     * @return
     */
    public User findByUser(User user);


    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    public User findById(Integer id);

    /**
     * 用户登录   redis记录用户数量
     *
     * @param user
     * @return
     */
    public User userLogin(User user);

    public BaseResult deleteUser(Integer id);

    public BaseResult saveOrUpdate(User user);

    public Page<User> pageQuery(Page<User> page,User user);


}
