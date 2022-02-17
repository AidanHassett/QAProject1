package com.qa.ims.persistence.domain;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Collection;
import java.util.TreeMap;

public class Order implements Comparable<Order> {

	private Long id;
	private Long customerId;
	private Timestamp timePlaced;
	private TreeMap<Long, OrderItem> orderItems;


	public Order(Long customerId, Timestamp timePlaced) {
		this.setCustomerId(customerId);
		this.setTimePlaced(timePlaced);
		this.orderItems = new TreeMap<Long, OrderItem>();
	}

	public Order(Long customerId, Long timePlaced) {
		this.setCustomerId(customerId);
		this.setTimePlaced(timePlaced);
		this.orderItems = new TreeMap<Long, OrderItem>();
	}

	public Order(Customer customer, Timestamp timePlaced) {
		this(customer.getId(), timePlaced);
	}

	public Order(Customer customer, Long timePlaced) {
		this(customer.getId(), timePlaced);
	}

	public Order(Long id, Long customerId, Timestamp timePlaced) {
		this(customerId, timePlaced);
		this.id = id;
	}

	public Order(Long id, Long customerId, Long timePlaced) {
		this(customerId, timePlaced);
		this.id = id;
	}

	public Order(Long id, Customer customer, Timestamp timePlaced) {
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

	public Timestamp getTimePlaced() {
		return timePlaced;
	}

	public String getTimePlacedStr() {
		return DateFormat.getDateTimeInstance().format(timePlaced);
	}

	public void setTimePlaced(Timestamp timePlaced) {
		this.timePlaced = timePlaced;
	}

	public void setTimePlaced(Long timePlaced) {
		setTimePlaced(new Timestamp(timePlaced));
	}

	public OrderItem getOrderItem(Long itemId) {
		return orderItems.get(itemId);
	}

	public Collection<OrderItem> getAllOrderItems() {
		return orderItems.values();
	}

	public OrderItem addOrderItem(Long itemId, Long quantity) {
		if (orderItems.containsKey(itemId)) {
			OrderItem oi = orderItems.get(itemId);
			oi.addQuantity(quantity);
			return oi;
		} else {
			return replaceOrderItem(itemId, quantity);
		}
	}

	public OrderItem replaceOrderItem(Long itemId, Long quantity) {
		return orderItems.put(itemId, new OrderItem(this.id, itemId, quantity));
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