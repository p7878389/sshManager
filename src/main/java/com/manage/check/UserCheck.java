package com.manage.check;

import com.manage.constant.ErrorConstant;
import com.manage.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名：UserCheck <br/>
 * 功能说明： <br/>
 * 修改历史： <br/>
 * 1.[2017/1/19  11:55]创建类 by pxh
 */
public enum UserCheck {
	INSTANCE;

	private static final Logger LOG = LoggerFactory.getLogger( UserCheck.class );

	/***
	 * 用户id校验
	 *
	 * @param user
	 */
	public void checkUserId(User user) {
		if (user == null || user.getUserId() == null) {
			LOG.error( "user param is null" );
			throw ErrorConstant.PARAM_NULLPOINTER.e();
		}
	}
}
