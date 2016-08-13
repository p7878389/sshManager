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
 * Roleresource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roleresource", catalog = "ssh")
public class Roleresource implements java.io.Serializable {

    // Fields

    private Integer roleResourceId;
    private Resource resource;
    private Role role;

    // Constructors

    /** default constructor */
    public Roleresource() {
    }

    /** full constructor */
    public Roleresource(Resource resource, Role role) {
        this.resource = resource;
        this.role = role;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "roleResourceId", unique = true, nullable = false)
    public Integer getRoleResourceId() {
        return this.roleResourceId;
    }

    public void setRoleResourceId(Integer roleResourceId) {
        this.roleResourceId = roleResourceId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourceId", nullable = false)
    public Resource getResource() {
        return this.resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
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