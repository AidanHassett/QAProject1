package com.qa.ims.persistence.domain;

public class Customer implements Comparable<Customer> {

	private Long id;
	private String email;
	private String firstName;
	private String surname;

	public Customer(String email, String firstName, String surname) {
		this.setEmail(email);
		this.setFirstName(firstName);
		this.setSurname(surname);
	}

	public Customer(Long id, String email, String firstName, String surname) {
		this.setId(id);
		this.setEmail(email);
		this.setFirstName(firstName);
		this.setSurname(surname);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "id:" + id + " email:" + email + " first name:" + firstName + " surname:" + surname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			if (this.compareTo((Customer) obj) == 0) {
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
		Customer other = (Customer) obj;

		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;

		if (getEmail() == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!getEmail().equals(other.getEmail()))
			return false;

		if (getFirstName() == null) {
			if (other.getFirstName() != null)
				return false;
		} else if (!getFirstName().equals(other.getFirstName()))
			return false;

		if (getSurname() == null) {
			if (other.getSurname() != null)
				return false;
		} else if (!getSurname().equals(other.getSurname()))
			return false;

		return true;
		*/
	}

	public int compareTo(Customer other) throws NullPointerException, ClassCastException {
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
		} else if (this.getSurname() == null) {
			if (other.getSurname() != null) {
				temp = -1;
			}
		} else if (other.getSurname() == null) {
			temp = 1;
		} else {
			temp = this.getSurname().compareTo(other.getSurname());
		}
		if (temp != 0) {
			return temp;
		} else if (this.getFirstName() == null) {
			if (other.getFirstName() != null) {
				temp = -1;
			}
		} else if (other.getFirstName() == null) {
			temp = 1;
		} else {
			temp = this.getFirstName().compareTo(other.getFirstName());
		}
		if (temp != 0) {
			return temp;
		} else if (this.getEmail() == null) {
			if (other.getEmail() != null) {
				temp = -1;
			}
		} else if (other.getEmail() == null) {
			temp = 1;
		}  else {
			temp = this.getEmail().compareTo(other.getEmail());
		}
		return temp;
	}
}
