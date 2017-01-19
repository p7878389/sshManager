package com.manage.service;

import com.manage.entity.Resource;

import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */

public interface ResourceService {

	Integer MENU_TYPE = 1;

	Resource getResource(Integer id);

	void updateResource(Resource resource);

	List<Resource> initMenu(int id);

	List<Resource> findAllResource();


}
