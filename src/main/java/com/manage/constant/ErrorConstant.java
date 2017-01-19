package com.manage.constant;

import com.manage.resultBean.BaseResult;

/**
 * 类名：ErrorConstant <br/>
 * 功能说明： <br/>
 * 修改历史： <br/>
 * 1.[2016/10/9  14:34]创建类 by pxh
 */
public enum ErrorConstant {

	/***
	 * 系统相关错误
	 */
	SHIRO_ERROR( 000001, "shiro未知错误" ),
	INTERNAL_SERVICE_ERROR( 000002, "内部服务错误" ),

	/***
	 * 用户相关
	 */
	USER_NULLPOINTER( 100001, "用户不存在" ),
	USER_PASSWORD_ERROR( 100002, "用户名或密码错误" ),
	USER_LOGOUT_ERROR( 100003, "用户注销失败" ),
	USER_SAVE_ERROR( 100004, "用户新增失败" ),
	USER_UPDATE_ERROR( 100005, "用户保存失败" ),
	USER_DELETE_ERROR( 100006, "用户删除失败" ),
	USER_SESSION_INVALID( 100007, "用户登录已过期，请重新登录！" ),
	USER_LOCK( 100008, "该账户已经被锁定，请联系管理员" ),


	/***
	 * 菜单相关
	 */
	RESOURCE_NULLPOINTER( 200001, "找不到该资源" ),

	/***
	 * 参数翔相关
	 */
	PARAM_NULLPOINTER( 300001, "参数不能为空" );

	// 异常描述
	private int code;
	private String msg;

	ErrorConstant(int code, String msg) {
		this.code = code;
		this.msg = msg;

	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/***
	 * 染回自定义异常信息
	 *
	 * @return
	 */
	public BusinessException e() {
		return new BusinessException( this );
	}


	public BaseResult getResult() {
		return new BaseResult( this, true );
	}
}
