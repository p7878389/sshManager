package com.manage.controller;

import com.manage.constant.BusinessException;
import com.manage.constant.ErrorConstant;
import com.manage.resultBean.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;


/**
 * 类名：GlobalController <br/>
 * 功能说明：全局异常处理 <br/>
 * 修改历史： <br/>
 * 1.[2016/12/22  10:58]创建类 by pxh
 */
@ControllerAdvice
public class GlobalExcepController {

	private static final Logger LOG = LoggerFactory.getLogger( GlobalExcepController.class );

	/***
	 * 自定义业务异常 返回200
	 *
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<BaseResult> handleBusinessExp(BusinessException ex) throws Exception {
		BaseResult result = new BaseResult( ex );
		return ResponseEntity.status( HttpStatus.OK ).body( result );
	}


	/***
	 * 处理Servlet异常，返回4XX错误
	 *
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = ServletException.class)
	public ResponseEntity<BaseResult> servletErrorHandler(HttpServletRequest req, ServletException e) throws Exception {
		LOG.error( "Controller called error!Catch in servletErrorHandler", e );
		LOG.error( "Url:{},Method:{},Content:{}", req.getRequestURL(), req.getMethod(), getContentFromReq( req ) );
		BaseResult result = new BaseResult( ErrorConstant.INTERNAL_SERVICE_ERROR, false );
		ResponseEntity<BaseResult> responseEntity = ResponseEntity.badRequest().body( result );
		return responseEntity;
	}

	/**
	 * 处理一般异常，返回5XX错误
	 *
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<BaseResult> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		LOG.error( "Controller called error!Catch in defaultErrorHandler", e );
		LOG.error( "Url:{},Method:{},Content:{}", req.getRequestURL(), req.getMethod(), getContentFromReq( req ) );
		BaseResult result = new BaseResult( ErrorConstant.INTERNAL_SERVICE_ERROR, false );
		ResponseEntity<BaseResult> responseEntity = ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( result );
		return responseEntity;
	}

	private static String getContentFromReq(HttpServletRequest req) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append( line );
		} catch (Exception e) {
			LOG.error( "Controller called error!Catch in getContentFromReader", e );
		}
		return jb.toString();
	}
}
