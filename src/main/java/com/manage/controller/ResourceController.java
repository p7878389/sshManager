package com.manage.controller;

import com.manage.entity.Resource;
import com.manage.entity.User;
import com.manage.service.ResourceService;
import com.manage.resultBean.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	/**
	 * 功能说明：
	 *
	 * @param request <br/>
	 * @return org.springframework.http.ResponseEntity<com.manage.resultBean.BaseResult> <br/>
	 * 修改历史：<br/>
	 * 1.[2016/9/2 16:42 ] 创建方法 by pxh
	 */
	@RequestMapping(path = "/initMenu/{id}", method = RequestMethod.GET)
	public ResponseEntity<BaseResult> initMenu(HttpServletRequest request, @PathVariable Integer id) {
		User user = (User) request.getSession().getAttribute( "admin" );
		List<Resource> resources = resourceService.initMenu( user.getUserId() );
		BaseResult baseResult = new BaseResult();
		baseResult.setData( resources );
		return ResponseEntity.ok().body( baseResult );
	}

	/**
	 * 功能说明：获取单个资源信息
	 *
	 * @param id <br/>
	 * @return org.springframework.http.ResponseEntity<com.manage.resultBean.BaseResult> <br/>
	 * 修改历史：<br/> 
	 * 1.[2016/9/2 16:42 ] 创建方法 by pxh
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BaseResult> getResource(@PathVariable Integer id) {
		BaseResult result = new BaseResult();
		Resource resource = resourceService.getResource( id );
		result.setData( resource );
		return ResponseEntity.ok().body( result );
	}

	/**
	 * 功能说明：查询所有资源信息
	 *
	 * @return org.springframework.http.ResponseEntity<com.manage.resultBean.BaseResult> <br/>
	 * 修改历史：<br/>
	 * 1.[2016/9/2 17:08 ] 创建方法 by pxh
	 */
	@RequestMapping(value = "/findAll")
	@ResponseBody
	public ResponseEntity<BaseResult> findAllResource() {
		BaseResult result = new BaseResult();
		List<Resource> resources = resourceService.findAllResource();
		result.setData( resources );
		return ResponseEntity.ok().body( result );
	}

}
