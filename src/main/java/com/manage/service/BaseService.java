package com.manage.service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public interface BaseService<T,PK> {
    public void save(T entity);

    public void update(T entity);

    public void delete(PK id);

    public T getById(PK id);

    public List<T> getByHQL(String hql, Object... params);
}
