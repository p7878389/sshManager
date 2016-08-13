package com.manage.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role", catalog = "ssh")
public class Role implements java.io.Serializable {

    // Fields

    private Integer roleId;
    private String roleName;
    private String description;
    private Set<Userrole> userroles = new HashSet<Userrole>(0);
    private Set<Roleresource> roleresources = new HashSet<Roleresource>(0);

    // Constructors

    /** default constructor */
    public Role() {
    }

    /** minimal constructor */
    public Role(String roleName) {
        this.roleName = roleName;
    }

    /** full constructor */
    public Role(String roleName, String description, Set<Userrole> userroles,
            Set<Roleresource> roleresources) {
        this.roleName = roleName;
        this.description = description;
        this.userroles = userroles;
        this.roleresources = roleresources;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "roleId", unique = true, nullable = false)
    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name = "roleName", nullable = false, length = 20)
    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "description", length = 200)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public Set<Userrole> getUserroles() {
        return this.userroles;
    }

    public void setUserroles(Set<Userrole> userroles) {
        this.userroles = userroles;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public Set<Roleresource> getRoleresources() {
        return this.roleresources;
    }

    public void setRoleresources(Set<Roleresource> roleresources) {
        this.roleresources = roleresources;
    }

}