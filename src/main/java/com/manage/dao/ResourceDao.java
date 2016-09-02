package com.manage.dao;

import com.manage.entity.Resource;

import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public interface ResourceDao extends  BaseDao<Resource,Integer>{

    public List<Resource> findAllResource();
}
