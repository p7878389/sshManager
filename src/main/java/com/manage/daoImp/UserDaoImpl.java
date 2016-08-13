package com.manage.daoImp;

import com.manage.dao.UserDao;
import com.manage.entity.User;
import com.manage.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User,Integer> implements UserDao{

    /***
     * 根据用户名密码查询
     * @param user
     * @return
     */
    @Override
    public List<User> getUser(User user) {
        Criteria criteria = super.getSession().createCriteria(User.class);
        if(!StringUtil.isNull(user.getUserName())){
            criteria.add(Restrictions.eq("userName",user.getUserName()));
        }
        if(!StringUtil.isNull(user.getPassWord())){
            criteria.add(Restrictions.eq("passWord",user.getPassWord()));
        }
        return criteria.list();
    }
}
