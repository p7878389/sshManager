package com.manage.service;

import com.manage.entity.Resource;

import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */

public interface ResourceService {

    public static final Integer MENU_TYPE=1;

    public Resource getResource(Integer id);

    public void updateResource(Resource resource);

    public List<Resource> initMenu(int id);

    public List<Resource> findAllResource();


}
