package com.manage.daoImp;

import com.manage.dao.admin.UserDao;
import com.manage.entity.User;
import com.manage.util.StringUtil;
import com.manage.vo.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

    /***
     * 根据user对象查询
     *
     * @param user
     * @return
     */
    @Override
    public List<User> getUser(User user) {
        StringBuilder sb = new StringBuilder( "from User as model " );
        sb.append( " where " );
        sb.append( " model.userName  = " ).append( ":userName" ).append( " and " );
        sb.append( " model.passWord  = " ).append( ":passWord" );
//        sb.append( " model.passWord  = " ).append( ":passWord" ).append( " and " );
//        sb.append( " model.salt  = " ).append( ":salt" ).append( " and " );
//        sb.append( " model.state  = " ).append( ":state" );
        Query query = this.getSession().createQuery( sb.toString() );
        query.setParameter( "userName", user.getUserName() );
        query.setParameter( "passWord", user.getPassWord() );
//        query.setParameter( "salt", user.getSalt() );
//        query.setParameter( "state", user.getState() );

        return query.list();
    }

    public List<Criterion> getCriterion(User user) {
        List<Criterion> criterions = new ArrayList<>();
        if (!StringUtil.isNull( user.getUserName() )) {
            criterions.add( Restrictions.ilike( USERNAME, user.getUserName(), MatchMode.ANYWHERE ) );
        }
        if (!StringUtil.isNull( user.getSalt() )) {
            criterions.add( Restrictions.eq( SALT, user.getSalt() ) );
        }
        if (user.getState() != null) {
            criterions.add( Restrictions.eq( STATE, user.getState() ) );
        }
        return criterions;
    }

}
