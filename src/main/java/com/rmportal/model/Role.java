package com.rmportal.model;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "RM_ROLE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Role")
/* hibernateLazyInitializer used 
 * 		to stop recursively error while serialization of user response*/
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","users"})
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 1301683850190647398L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String desc;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<User> users;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUser(Set<User> users) {
		this.users = users;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}