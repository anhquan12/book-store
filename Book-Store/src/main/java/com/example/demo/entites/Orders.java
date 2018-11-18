package com.example.demo.entites;
// Generated Nov 14, 2018 8:30:50 AM by Hibernate Tools 5.2.11.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Orders generated by hbm2java
 */
@Entity
@Table(name = "orders", catalog = "book_ecommerce")
public class Orders implements java.io.Serializable {

	private Integer id;
	private Account account;
	private String name;
	private long createdDate;
	private int status; // 1. active  0. disable
	private Set<Ordersdetail> ordersdetails = new HashSet<Ordersdetail>(0);

	public Orders() {
	}

//	public Orders(Account account, String name, long createdDate, boolean status) {
//		this.account = account;
//		this.name = name;
//		this.createdDate = createdDate;
//		this.status = status;
//	}

	public Orders(Account account, String name, long createdDate, int status, Set<Ordersdetail> ordersdetails) {
		this.account = account;
		this.name = name;
		this.createdDate = createdDate;
		this.status = status;
		this.ordersdetails = ordersdetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Temporal(TemporalType.DATE)
	@Column(name = "createdDate", nullable = false, length = 10)
	public long getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "status", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<Ordersdetail> getOrdersdetails() {
		return this.ordersdetails;
	}

	public void setOrdersdetails(Set<Ordersdetail> ordersdetails) {
		this.ordersdetails = ordersdetails;
	}

}
