package com.manage.controller;

import com.manage.constant.ErrorConstant;
import com.manage.entity.User;
import com.manage.service.imp.UserServiceImpl;
import com.manage.resultBean.BaseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserServiceImpl userService;

	private static final Logger logger = LoggerFactory.getLogger( LoginController.class );

	/***
	 * 用户登录
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/loginIn", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> login(@RequestBody User user, HttpServletRequest request) {
		BaseResult result = new BaseResult();
		user = userService.findByUser( user );
		if (user == null) {
			throw ErrorConstant.USER_PASSWORD_ERROR.e();
		} else {
			if (!user.getState().equals( 0 )) {
				throw ErrorConstant.USER_PASSWORD_ERROR.e();
			}
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken( user.getUserName(), user.getPassWord() );
			//是否记住密码
			//token.setRememberMe(true);
			subject.login( token );
			request.getSession().setAttribute( "admin", user );
			result.setData( user );
		}
		return ResponseEntity.ok().body( result );
	}


	/***
	 * 退出登录
	 *
	 * @return
	 */
	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> logout() {
		try {
			Subject subject = SecurityUtils.getSubject();
			if (subject.isAuthenticated()) {
				// session 会销毁，在SessionListener监听session销毁，清理权限缓存
				subject.logout();
			}
		} catch (Exception e) {
			logger.error( "用户注销失败", e );
			throw ErrorConstant.USER_LOGOUT_ERROR.e();
		}
		return ResponseEntity.ok().body( new BaseResult() );
	}

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
}
