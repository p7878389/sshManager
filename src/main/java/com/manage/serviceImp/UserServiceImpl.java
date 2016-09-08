package com.manage.serviceImp;

import com.manage.annotations.Cacheable;
import com.manage.daoImp.UserDaoImpl;
import com.manage.entity.User;
import com.manage.service.UserService;
import com.manage.util.BaseResult;
import com.manage.util.ErrorCodeInfo;
import com.manage.util.JsonUtil;
import com.manage.vo.Page;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger( UserServiceImpl.class );
    @Autowired
    private UserDaoImpl userDaoImpl;

    @Override
    public User findByUser(User user) {
        List<User> users = userDaoImpl.getUser( user );
        if (users.size() > 0) {
            return users.get( 0 );
        }
        return null;
    }


    @Override
    @Cacheable(key = "userLogin", fieldKey = "#user.getUserName()")
    public User userLogin(User user) {
        List<User> users = userDaoImpl.getUser( user );
        if (users.size() > 0) {
            return users.get( 0 );
        }
        return null;
    }

    @Override
    public BaseResult saveOrUpdate(User user) {
        BaseResult result = new BaseResult();
        try {
            userDaoImpl.saveOrUpdate( user );
            result.setErrorCode( 0 );
        } catch (Exception e) {
            log.error( "saveOrUpdate for:{} user{}", JsonUtil.INSTANCE.objectToJson( user ), e );
            result = ErrorCodeInfo.getBaseResult( ErrorCodeInfo.USER_UPDATE_ERROR );
        }
        return result;
    }

    @Override
    public Page<User> pageQuery(Page<User> page, User user) {
        List<Criterion> criterias = userDaoImpl.getCriterion( user );
        return userDaoImpl.pageQuery( page, criterias, user );
    }

    @Override
    public User findById(Integer id) {
        return userDaoImpl.findById( id );
    }

    public UserDaoImpl getUserDaoImpl() {
        return userDaoImpl;
    }

    public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }
}
