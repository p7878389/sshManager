package com.manage.controller;

import com.manage.check.UserCheck;
import com.manage.constant.BusinessException;
import com.manage.entity.User;
import com.manage.service.UserService;
import com.manage.resultBean.BaseResult;
import com.manage.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户信息
 */

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 查询用户信息
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<BaseResult> findUser(@PathVariable int id) {
		User user = userService.findById( id );
		BaseResult result = new BaseResult();
		result.setData( user );
		return ResponseEntity.ok().body( result );
	}


	/**
	 * 功能说明：用户新增
	 *
	 * @param user <br/>
	 * @return org.springframework.http.ResponseEntity<com.manage.resultBean.BaseResult> <br/>
	 * 修改历史：<br/>
	 * 1.[2016/10/9 15:13 ] 创建方法 by pxh
	 */
	@RequestMapping(path = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> saveUser(@RequestBody User user) throws BusinessException {
		userService.saveOrUpdate( user );
		return ResponseEntity.ok().body( new BaseResult() );
	}


	/**
	 * 功能说明：用户删除
	 *
	 * @param user <br/>
	 * @return org.springframework.http.ResponseEntity<com.manage.resultBean.BaseResult> <br/>
	 * 修改历史：<br/>
	 * 1.[2016/10/9 15:13 ] 创建方法 by pxh
	 */
	@RequestMapping(path = "/deletet", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> deleteUser(User user) {
		UserCheck.INSTANCE.checkUserId( user );
		userService.deleteUser( user.getUserId() );
		return ResponseEntity.ok().body( new BaseResult() );
	}

	/**
	 * 更新用户信息
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<BaseResult> updateUser(@RequestBody User user) {
		userService.saveOrUpdate( user );
		return ResponseEntity.ok().body( new BaseResult() );
	}

	/**
	 * 用户分页查询
	 *
	 * @param user
	 * @param page
	 * @return
	 */
	@RequestMapping(path = "/pageUser", method = RequestMethod.GET)
	public ResponseEntity<BaseResult> pageUser(User user, Page page) {
		page = userService.pageQuery( page, user );
		BaseResult baseResult = new BaseResult();
		baseResult.setData( page );
		return ResponseEntity.ok().body( baseResult );
	}
}
