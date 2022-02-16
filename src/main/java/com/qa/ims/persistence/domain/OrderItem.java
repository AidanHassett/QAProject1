package com.qa.ims.persistence.domain;

public class OrderItem implements Comparable<OrderItem> {

	private Long orderId;
	private Long itemId;
	private Long quantity;

	public OrderItem(Long orderId, Long itemId, Long quantity) {
		this.setOrderId(orderId);
		this.setItemId(itemId);
		this.setQuantity(quantity);
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "orderId:" + orderId + " itemId:" + itemId + " quantity:" + quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// NB: does not compare the contents of orderItems
		try {
			if (this.compareTo((OrderItem) obj) == 0) {
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

	public int compareTo(OrderItem other) throws NullPointerException, ClassCastException {
		// NB: does not compare contents of orderItems
		if (other == null) {
			throw new NullPointerException();
		}
		if (this.getClass() != other.getClass()) {
			throw new ClassCastException();
		}

		int temp = this.getOrderId().compareTo(other.getOrderId());
		if (temp != 0) {
			return temp;
		} else {
			temp = this.getItemId().compareTo(other.getItemId());
			if (temp != 0) {
				return temp;
			} else {
				return this.getQuantity().compareTo(other.getQuantity());
			}
		}
	}
}
