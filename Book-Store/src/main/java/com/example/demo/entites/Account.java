package com.example.demo.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "account", catalog = "book_ecommerce")
public class Account implements java.io.Serializable {

	private String username;
	private String password;
	private String fullname;
	private String email;
	private Set<Orders> orderses = new HashSet<Orders>(0);

	public Account() {
	}

	public Account(String username, String password, String fullname, String email) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
	}

	public Account(String username, String password, String fullname, String email, Set<Orders> orderses) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.orderses = orderses;
	}

	@Id

	@Column(name = "username", unique = true, nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "fullname", nullable = false, length = 50)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

}
