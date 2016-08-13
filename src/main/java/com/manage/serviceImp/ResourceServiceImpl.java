package com.manage.serviceImp;

import com.manage.daoImp.ResourceDaoImpl;
import com.manage.entity.Resource;
import com.manage.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/6/26.
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDaoImpl resourceDao;

    @Override
    @Cacheable(value = "cacheManager" ,key = "#id.toString()")
    public Resource getResource(Integer id) {
        return resourceDao.findById(id);
    }

    @Override
    public void updateResource(Resource resource) {
        resourceDao.update(resource);
    }

    public ResourceDaoImpl getResourceDao() {
        return resourceDao;
    }

    public void setResourceDao(ResourceDaoImpl resourceDao) {
        this.resourceDao = resourceDao;
    }
}
