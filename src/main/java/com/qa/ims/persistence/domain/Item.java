package com.qa.ims.persistence.domain;

public class Item implements Comparable<Item> {

	private Long id;
	private String title;
	private Long price;	//price stored as an integer of pence

	public Item(String title, Long price) {
		this.setTitle(title);
		this.setPrice(price);
	}

	public Item(String title, Long pounds, Long pence) {
		this.setTitle(title);
		this.setPrice(pounds, pence);
	}

	public Item(String title, double price) {
		this.setTitle(title);
		this.setPrice(price);
	}

	public Item(Long id, String title, Long price) {
		this(title, price);
		this.setId(id);
	}

	public Item(Long id, String title, Long pounds, Long pence) {
		this(title, pounds, pence);
		this.setId(id);
	}

	public Item(Long id, String title, double price) {
		this(title, price);
		this.setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price / 100.0;
	}

	public String getPriceStr() {
		return "£" + (price / 100) + "." + (price % 100);
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public void setPrice(Long pounds, Long pence) {
		this.price = (pounds * 100) + pence;
	}

	public void setPrice(double price) {
		setPrice((Long) Math.round(price * 100));
	}

	@Override
	public String toString() {
		return "id:" + id + " title:" + title + " price:" + getPriceStr();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			if (this.compareTo((Item) obj) == 0) {
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

	public int compareTo(Item other) throws NullPointerException, ClassCastException {
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
			temp = this.getTitle().compareTo(other.getTitle());
			if (temp != 0) {
				return temp;
			} else {
				return this.getPrice().compareTo(other.getPrice());
			}
		}
	}
}
