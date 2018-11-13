package com.example.demo.entites;
// Generated Nov 13, 2018 12:10:37 PM by Hibernate Tools 5.2.11.Final

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "orders_detail", catalog = "book_ecommerce")
public class OrdersDetail implements java.io.Serializable {

	private OrdersDetailId id;
	private Orders orders;
	private BigDecimal price;
	private int quantity;

	public OrdersDetail() {
	}

	public OrdersDetail(OrdersDetailId id, Orders orders, BigDecimal price, int quantity) {
		this.id = id;
		this.orders = orders;
		this.price = price;
		this.quantity = quantity;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "bookId", column = @Column(name = "book_id", nullable = false)),
			@AttributeOverride(name = "ordersId", column = @Column(name = "orders_id", nullable = false)) })
	public OrdersDetailId getId() {
		return this.id;
	}

	public void setId(OrdersDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders_id", nullable = false, insertable = false, updatable = false)
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Column(name = "price", nullable = false, precision = 10, scale = 1)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
