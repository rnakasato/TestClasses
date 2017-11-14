package com.rnakasato.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "username", "firstName", "lastName", "age", "address" })
@XmlRootElement(name = "userData")
public class UserXMLData {

	@XmlElement
	protected String username;

	@XmlElement
	protected String firstName;

	@XmlElement
	protected String lastName;

	@XmlElement
	protected Integer age;

	@XmlElement
	protected UserXMLData.Address address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public UserXMLData.Address getAddress() {
		return address;
	}

	public void setAddress(UserXMLData.Address address) {
		this.address = address;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(propOrder = { "street", "neighborhood", "number", "cep" })
	public static class Address {

		@XmlElement
		protected String street;

		@XmlElement
		protected String neighborhood;

		@XmlElement
		protected String number;

		@XmlElement
		protected String cep;

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getNeighborhood() {
			return neighborhood;
		}

		public void setNeighborhood(String neighborhood) {
			this.neighborhood = neighborhood;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getCep() {
			return cep;
		}

		public void setCep(String cep) {
			this.cep = cep;
		}

	}

}
