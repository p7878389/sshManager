package com.manage.dao.Imp;

import com.manage.dao.ResourceDao;
import com.manage.entity.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource,Integer> implements ResourceDao {

    /*@Override
    public List<Resource> findAllResource() {
        return this.getSession().createCriteria(Resource.class).list();
    }*/
}
