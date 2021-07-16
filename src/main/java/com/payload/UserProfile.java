package com.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import com.blogapi.model.user.Address;
import com.blogapi.model.user.Company;

@Data

public class UserProfile {
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private Instant joinedAt;
	private String email;
	private Address address;
	private String phone;
	private String website;
	private Company company;
	private Long postCount;
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserProfile(Long id, String username, String firstName, String lastName, Instant joinedAt, String email,
			Address address, String phone, String website, Company company, Long postCount) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joinedAt = joinedAt;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.website = website;
		this.company = company;
		this.postCount = postCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Instant getJoinedAt() {
		return joinedAt;
	}
	public void setJoinedAt(Instant joinedAt) {
		this.joinedAt = joinedAt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Long getPostCount() {
		return postCount;
	}
	public void setPostCount(Long postCount) {
		this.postCount = postCount;
	}
	
	
}
