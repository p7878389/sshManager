package com.manage.dao;

import java.util.List;

public interface BaseDao<T,PK> {

    public void save(T entity);

    public void update(T entity);

    public void delete(PK id);

    public T findById(PK id);

    public List<T> findByHQL(String hql, Object... params);

    public void saveOrUpdate(T t);

}