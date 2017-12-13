package com.bolster.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int id;
	
	@Column(name = "address_line1")
	private String addressLine1;
	
	@Column(name = " address_line2 ")
	private String addressLine2;
	
	@Column(name = "address_city ")
	private String city;
	
	@Column(name = "address_state")
	private String state;
	
	@Column(name = "address_country")
	private String country;
	
	@Column(name = " address_postcode")
	private String postcode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();

		st.append("Address line 1: " +addressLine1 + ", ");
		st.append("Address line 2: " + addressLine2 + ", ");
		st.append("City: " + city + ", ");
		st.append("State: " + state + ", ");
		st.append("Country: " + country + ", ");
		
		return st.toString();
	}
	
}
