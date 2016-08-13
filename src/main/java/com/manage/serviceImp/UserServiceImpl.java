package com.manage.serviceImp;

import com.manage.daoImp.UserDaoImpl;
import com.manage.entity.User;
import com.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Override
    public User findByUser(User user) {
        List<User> users = userDaoImpl.getUser(user);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User findById(Integer id) {
        return userDaoImpl.findById(id);
    }

    public UserDaoImpl getUserDaoImpl() {
        return userDaoImpl;
    }

    public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }
}
