package com.example.demo.entites;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class OrdersDetailId implements java.io.Serializable {

	private int bookId;
	private int ordersId;

	public OrdersDetailId() {
	}

	public OrdersDetailId(int bookId, int ordersId) {
		this.bookId = bookId;
		this.ordersId = ordersId;
	}

	@Column(name = "book_id", nullable = false)
	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	@Column(name = "orders_id", nullable = false)
	public int getOrdersId() {
		return this.ordersId;
	}

	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrdersDetailId))
			return false;
		OrdersDetailId castOther = (OrdersDetailId) other;

		return (this.getBookId() == castOther.getBookId()) && (this.getOrdersId() == castOther.getOrdersId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getBookId();
		result = 37 * result + this.getOrdersId();
		return result;
	}

}
