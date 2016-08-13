package com.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Userrole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userrole", catalog = "ssh")
public class Userrole implements java.io.Serializable {

    // Fields

    private Integer userRoleId;
    private User user;
    private Role role;

    // Constructors

    /** default constructor */
    public Userrole() {
    }

    /** full constructor */
    public Userrole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "userRoleId", unique = true, nullable = false)
    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", nullable = false)
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}