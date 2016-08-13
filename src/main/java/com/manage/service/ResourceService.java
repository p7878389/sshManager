package com.manage.service;

import com.manage.entity.Resource;

/**
 * Created by Administrator on 2016/6/26.
 */

public interface ResourceService {

    public Resource getResource(Integer id);

    public void updateResource(Resource resource);

}
