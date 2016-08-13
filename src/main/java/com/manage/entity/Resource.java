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
 * Resource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "resource", catalog = "ssh")
public class Resource implements java.io.Serializable {

    // Fields

    private Integer resourceId;
    private String name;
    private Integer type;
    private String url;
    private Integer parentId;
    private Integer priority;
    private String permission;
    private Integer state;
    private Set<Roleresource> roleresources = new HashSet<Roleresource>(0);

    // Constructors

    /** default constructor */
    public Resource() {
    }

    /** minimal constructor */
    public Resource(String name, Integer type, Integer state) {
        this.name = name;
        this.type = type;
        this.state = state;
    }

    /** full constructor */
    public Resource(String name, Integer type, String url, Integer parentId,
            Integer priority, String permission, Integer state,
            Set<Roleresource> roleresources) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.parentId = parentId;
        this.priority = priority;
        this.permission = permission;
        this.state = state;
        this.roleresources = roleresources;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "resourceId", unique = true, nullable = false)
    public Integer getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", nullable = false)
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "url", length = 200)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "parentId")
    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "priority")
    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Column(name = "permission", length = 100)
    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Column(name = "state", nullable = false)
    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resource")
    public Set<Roleresource> getRoleresources() {
        return this.roleresources;
    }

    public void setRoleresources(Set<Roleresource> roleresources) {
        this.roleresources = roleresources;
    }

}