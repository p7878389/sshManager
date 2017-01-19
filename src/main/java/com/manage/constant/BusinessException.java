package com.manage.constant;

/**
 * 类名：BusinessException <br/>
 * 功能说明： 业务异常处理<br/>
 * 修改历史： <br/>
 * 1.[2016/12/22  11:00]创建类 by pxh
 */
public class BusinessException extends RuntimeException {

	private int errCode;

	public BusinessException(String msg, int errCode) {
		super( msg );
		this.errCode = errCode;
	}


	public BusinessException(String msg) {
		super( msg );
	}

	public BusinessException(ErrorsDiscriptor errorsDiscriptor) {
		super( errorsDiscriptor.getMsg() );
		this.errCode = errorsDiscriptor.getCode();
	}


	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
}
