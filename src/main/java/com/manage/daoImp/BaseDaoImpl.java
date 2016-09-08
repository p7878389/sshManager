package com.manage.daoImp;

import com.manage.dao.BaseDao;
import com.manage.vo.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


@Repository
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

    /**
     * 向DAO层注入SessionFactory
     */
    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    /**
     * 通过构造方法指定DAO的具体实现类
     */
    public BaseDaoImpl() {
        this.clazz = null;
        Class c = getClass();
        Type t = c.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.clazz = (Class<T>) p[0];
        }
    }

    /**
     * 获取当前工作的Session
     */
    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {
        this.getSession().save( entity );
    }

    @Override
    public void update(T entity) {
        this.getSession().update( entity );
    }

    @Override
    public void delete(PK id) {
        this.getSession().delete( this.findById( id ) );
    }

    @Override
    public T findById(PK id) {
        return (T) this.getSession().get( this.clazz, id );
    }


    @Override
    public List<T> findByHQL(String hql, Object... params) {
        Query query = this.getSession().createQuery( hql );
        for (int i = 0; params != null && i < params.length; i++) {
            query.setParameter( i, params );
        }
        return query.list();
    }

    @Override
    public void saveOrUpdate(T t) {
        this.getSession().saveOrUpdate( t );
    }

    @Override
    public List<T> findAll(T t) {
        return this.getSession().createCriteria( t.getClass() ).list();
    }

    @Override
    public Page<T> pageQuery(Page<T> page, List<Criterion> criterionList, T t) {
        Criteria criteria = this.getSession().createCriteria( t.getClass() );

        for (int i = 0; i < criterionList.size(); i++) {
            criteria.add( criterionList.get( i ) );
        }

        //获取总记录数
        Long totalCount = (Long) criteria.setProjection(
                Projections.rowCount() ).uniqueResult();
        page.setTotalCount( totalCount.intValue() );

        criteria.setProjection( null );
        criteria.setFirstResult( page.getBeginIndex() );
        criteria.setMaxResults( Integer.parseInt( page.getPageSize() ) );
        List<T> list = criteria.list();
        page.setResults( list );
        return page;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
