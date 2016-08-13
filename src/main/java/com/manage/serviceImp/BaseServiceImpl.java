package com.manage.serviceImp;

import com.manage.daoImp.BaseDaoImpl;
import com.manage.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

    @Autowired
    private BaseDaoImpl<T, PK> baseDao;

    @Override
    public void save(T entity) {
        baseDao.save(entity);
    }

    @Override
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    public void delete(PK id) {
        baseDao.delete(id);
    }

    @Override
    public T getById(PK id) {
        return baseDao.findById(id);
    }

    @Override
    public List<T> getByHQL(String hql, Object... params) {
        return baseDao.findByHQL(hql,params);
    }

    public BaseDaoImpl<T, PK> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDaoImpl<T, PK> baseDao) {
        this.baseDao = baseDao;
    }
}
