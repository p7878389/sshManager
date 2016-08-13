package com.manage.result.bean;

import com.manage.entity.Role;
import com.manage.entity.User;
import com.manage.entity.Userrole;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administr on 2016/8/13.
 */

public class UserResult {

    private Integer userId;
    private String userName;
    private String passWord;
    private String salt;
    private Integer state;
    private List<String> roles=new ArrayList<String>();

    public UserResult(Integer userId, String userName, String passWord, String salt, Integer state) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.salt = salt;
        this.state = state;
    }

    public UserResult(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.passWord = user.getPassWord();
        this.salt = user.getSalt();
        this.state = user.getState();
        Set<Userrole> userroles=user.getUserroles();

        //roles=
    }

    public UserResult(){}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
