package com.example.demo.entites;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "orders", catalog = "book_ecommerce")
public class Orders implements java.io.Serializable {

	private Integer id;
	private Account account;
	private String name;
	private Date createdDate;
	private int status;
	private Set<OrdersDetail> ordersDetails = new HashSet<OrdersDetail>(0);

	public Orders() {
	}

	public Orders(Account account, String name, Date createdDate, int status) {
		this.account = account;
		this.name = name;
		this.createdDate = createdDate;
		this.status = status;
	}

	public Orders(Account account, String name, Date createdDate, int status, Set<OrdersDetail> ordersDetails) {
		this.account = account;
		this.name = name;
		this.createdDate = createdDate;
		this.status = status;
		this.ordersDetails = ordersDetails;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "createdDate", nullable = false, length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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
	public Set<OrdersDetail> getOrdersDetails() {
		return this.ordersDetails;
	}

	public void setOrdersDetails(Set<OrdersDetail> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

}
