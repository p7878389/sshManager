package com.manage.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "ssh")
public class User implements java.io.Serializable {

    // Fields

    private Integer userId;
    private String userName;
    private String passWord;
    private String salt;
    private Integer state;
    private Set<Userrole> userroles = new HashSet<Userrole>(0);

    // Constructors

    /** default constructor */
    public User() {
    }

    /** full constructor */
    public User(String userName, String salt, Integer state,String passWord,
            Set<Userrole> userroles) {
        this.userName = userName;
        this.salt = salt;
        this.state = state;
        this.userroles = userroles;
        this.passWord = passWord;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "userId", unique = true, nullable = false)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "userName", length = 200,nullable = false)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "salt", length = 50)
    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(name = "state",nullable = false)
    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Userrole> getUserroles() {
        return this.userroles;
    }

    public void setUserroles(Set<Userrole> userroles) {
        this.userroles = userroles;
    }

    @Column(name="passWord" , length =200,nullable = false)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}