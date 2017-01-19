package com.manage.service.imp;

import com.manage.constant.ErrorsDiscriptor;
import com.manage.dao.Imp.UserDaoImpl;
import com.manage.entity.User;
import com.manage.service.UserService;
import com.manage.util.JsonUtil;
import com.manage.vo.Page;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	public void deleteUser(Integer id) {
		try {
			userDaoImpl.delete( id );
		} catch (Exception e) {
			log.error( "delete user error for: id{}", id, e );
			throw ErrorsDiscriptor.USER_DELETE_ERROR.e();
		}
	}

	@Override
	public void saveOrUpdate(User user) {
		try {
			userDaoImpl.saveOrUpdate( user );
		} catch (Exception e) {
			log.error( "saveOrUpdate for:{} user{}", JsonUtil.INSTANCE.objectToJson( user ), e );
			throw ErrorsDiscriptor.USER_SAVE_ERROR.e();
		}
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

}
