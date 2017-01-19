package com.manage.resultBean;

import com.manage.constant.BusinessException;
import com.manage.constant.ErrorsDiscriptor;

/**
 * Created by Administrator on 2016/6/26.
 */
public class BaseResult {

	private int errorCode;

	private String msg;

	private Object data;

	private boolean success;

	public BaseResult() {
		this.success = true;
	}

	public BaseResult(ErrorsDiscriptor errorsDiscriptor, boolean success) {
		this.errorCode = errorsDiscriptor.getCode();
		this.msg = errorsDiscriptor.getMsg();
		this.success = success;
	}

	public BaseResult(int errorCode, String msg) {
		this.errorCode = errorCode;
		this.msg = msg;
		this.success = true;
	}

	public BaseResult(String msg, int errorCode, boolean success) {
		this.msg = msg;
		this.errorCode = errorCode;
		this.success = success;
	}

	public BaseResult(BusinessException ex) {
		this.msg = ex.getMessage();
		this.errorCode = ex.getErrCode();
		this.success = true;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
