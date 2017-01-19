package com.manage.service;

import com.manage.entity.User;
import com.manage.vo.Page;

public interface UserService {

	/**
	 * 根据user对象查询用户信息
	 *
	 * @param user
	 * @return
	 */
	User findByUser(User user);


	/**
	 * 根据用户id查询用户信息
	 *
	 * @param id
	 * @return
	 */
	User findById(Integer id);

	void deleteUser(Integer id);

	void saveOrUpdate(User user);

	Page<User> pageQuery(Page<User> page, User user);
}
