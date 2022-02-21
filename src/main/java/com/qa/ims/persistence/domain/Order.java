package com.qa.ims.persistence.domain;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Order implements Comparable<Order> {

	private Long id;
	private Long customerId;
	private Timestamp timePlaced;
	private Map<Long, OrderItem> orderItems;


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
		this.setId(id);
	}

	public Order(Long id, Long customerId, Long timePlaced) {
		this(customerId, timePlaced);
		this.setId(id);
	}

	public Order(Long id, Customer customer, Timestamp timePlaced) {
		this(customer, timePlaced);
		this.setId(id);
	}

	public Order(Long id, Customer customer, Long timePlaced) {
		this(customer, timePlaced);
		this.setId(id);
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
		if (timePlaced != null) {
			setTimePlaced(new Timestamp(timePlaced));
		}
	}

	public OrderItem getOrderItem(Long itemId) {
		try {
			return orderItems.get(itemId);
		} catch (NullPointerException e) {
			return null;
		}
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
		if (quantity <= 0) {
			deleteOrderItem(itemId);
			return null;
		} else {
			return orderItems.put(itemId, new OrderItem(this.id, itemId, quantity));
		}
	}

	public void deleteOrderItem(Long itemId) {
		orderItems.remove(itemId);
	}

	@Override
	public String toString() {
		String result = "id:" + id + " customerId:" + customerId + " timePlaced:" + getTimePlacedStr() + "\r\nItems:";
		for (OrderItem oi : getAllOrderItems()) {
			result += "\r\n  " + oi.toString();
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((timePlaced == null) ? 0 : timePlaced.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
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

		if (getCustomerId() == null) {
			if (other.getCustomerId() != null)
				return false;
		} else if (!getCustomerId().equals(other.getCustomerId()))
			return false;

		if (getTimePlaced() == null) {
			if (other.getTimePlaced() != null)
				return false;
		} else if (!getTimePlaced().equals(other.getTimePlaced()))
			return false;

		return true;
		*/
	}

	public int compareTo(Order other) throws NullPointerException, ClassCastException {
		//NB: does not compare the orderItems within the orders
		if (other == null) {
			throw new NullPointerException();
		}
		if (this.getClass() != other.getClass()) {
			throw new ClassCastException();
		}

		int temp = 0;
		if (this.getId() == null) {
			if (other.getId() != null) {
				temp = -1;
			}
		} else if (other.getId() == null) {
			temp = 1;
		} else {
			temp = this.getId().compareTo(other.getId());
		}
		if (temp != 0) {
			return temp;
		} else if (this.getCustomerId() == null) {
			if (other.getCustomerId() != null) {
				temp = -1;
			}
		} else if (other.getCustomerId() == null) {
			temp = 1;
		} else {
			temp = this.getCustomerId().compareTo(other.getCustomerId());
		}
		if (temp != 0) {
			return temp;
		} else if (this.getTimePlaced() == null) {
			if (other.getTimePlaced() != null) {
				temp = -1;
			}
		} else if (other.getTimePlaced() == null) {
			temp = 1;
		}  else {
			temp = this.getTimePlaced().compareTo(other.getTimePlaced());
		}
		return temp;
	}
}
