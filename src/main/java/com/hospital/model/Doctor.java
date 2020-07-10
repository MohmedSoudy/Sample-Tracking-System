package com.hospital.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "doctors")
public class Doctor {

	@Id
	@GeneratedValue

	private int id;

	@Column(nullable = false,unique = true)

	private String userEmail;

	@Column(nullable = false)

	private String password;

	@OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
	@JsonIgnoreProperties("doctor")
	private List<Project> projects = new ArrayList<>();

	public Doctor() {

	}
	
	public Doctor(Doctor newDoctor) {
		this.password=newDoctor.password;
		this.userEmail= newDoctor.userEmail;
		
	}
	
	public Doctor(String password,String email) {
		this.password=password;
		this.userEmail= email;
		
	}

	public Doctor(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}
