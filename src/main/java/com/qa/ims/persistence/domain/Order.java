package com.qa.ims.persistence.domain;

import java.text.DateFormat;
import java.util.Date;
import java.util.TreeSet;

public class Order implements Comparable<Order> {

	private Long id;
	private Long customerId;
	private Date timePlaced;
	private TreeSet<OrderItem> orderItems;


	public Order(Long customerId, Date timePlaced) {
		this.setCustomerId(customerId);
		this.setTimePlaced(timePlaced);
		this.orderItems = new TreeSet<OrderItem>();
	}

	public Order(Long customerId, Long timePlaced) {
		this.setCustomerId(customerId);
		this.setTimePlaced(timePlaced);
		this.orderItems = new TreeSet<OrderItem>();
	}

	public Order(Customer customer, Date timePlaced) {
		this(customer.getId(), timePlaced);
	}

	public Order(Customer customer, Long timePlaced) {
		this(customer.getId(), timePlaced);
	}

	public Order(Long id, Long customerId, Date timePlaced) {
		this(customerId, timePlaced);
		this.id = id;
	}

	public Order(Long id, Long customerId, Long timePlaced) {
		this(customerId, timePlaced);
		this.id = id;
	}

	public Order(Long id, Customer customer, Date timePlaced) {
		this(customer, timePlaced);
		this.id = id;
	}

	public Order(Long id, Customer customer, Long timePlaced) {
		this(customer, timePlaced);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setCustomerId(Customer customer) {
		this.setCustomerId(customer.getId());
	}

	public Date getTimePlaced() {
		return timePlaced;
	}

	public String getTimePlacedStr() {
		return DateFormat.getDateTimeInstance().format(timePlaced);
	}

	public void setTimePlaced(Date timePlaced) {
		this.timePlaced = timePlaced;
	}

	public void setTimePlaced(Long timePlaced) {
		setTimePlaced(new Date(timePlaced));
	}

	public void addOrderItem(OrderItem oi) {
		orderItems.add(oi);
	}

	public OrderItem[] getOrderItems() {
		return orderItems.toArray(new OrderItem[orderItems.size()]);
	}

	@Override
	public String toString() {
		return "id:" + id + " customerId:" + customerId + " timePlaced:" + getTimePlacedStr();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((timePlaced == null) ? 0 : timePlaced.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// NB: does not compare the contents of orderItems
		try {
			if (this.compareTo((Order) obj) == 0) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException | ClassCastException e) {
			return false;
		}
		/* // premade implementation
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;

		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;

		if (getTitle() == null) {
			if (other.getTitle() != null)
				return false;
		} else if (!getTitle().equals(other.getTitle()))
			return false;

		if (getPrice() == null) {
			if (other.getPrice() != null)
				return false;
		} else if (!getPrice().equals(other.getPrice()))
			return false;

		return true;
		*/
	}

	public int compareTo(Order other) throws NullPointerException, ClassCastException {
		// NB: does not compare contents of orderItems
		if (other == null) {
			throw new NullPointerException();
		}
		if (this.getClass() != other.getClass()) {
			throw new ClassCastException();
		}

		int temp = this.getId().compareTo(other.getId());
		if (temp != 0) {
			return temp;
		} else {
			temp = this.getCustomerId().compareTo(other.getCustomerId());
			if (temp != 0) {
				return temp;
			} else {
				return this.getTimePlaced().compareTo(other.getTimePlaced());
			}
		}
	}
}
