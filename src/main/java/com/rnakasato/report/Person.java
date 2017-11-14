package com.rnakasato.report;

import java.io.Serializable;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4768529732928639583L;

	private String firstName;
	private String surname;
	private Integer age;

	public Person() {
	}

	public Person(String firstName, String surname, Integer age) {
		this.firstName = firstName;
		this.surname = surname;
		this.age = age;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
