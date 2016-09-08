package com.manage.dao;

import com.manage.vo.Page;
import org.hibernate.criterion.Criterion;

import java.util.List;
import java.util.Map;

public interface BaseDao<T,PK> {

    public void save(T entity);

    public void update(T entity);

    public void delete(PK id);

    public T findById(PK id);

    public List<T> findByHQL(String hql, Object... params);

    public void saveOrUpdate(T t);

    public List<T> findAll(T t);

    public Page<T> pageQuery(Page<T> page, List<Criterion> criterionList, T t);

}