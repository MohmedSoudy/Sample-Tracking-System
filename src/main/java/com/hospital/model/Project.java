package com.hospital.model;





import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false, unique = true)
	private int projectId;
	
	@Column(nullable = false)
	private String projectType;
	
	@Column(nullable = false)
	private int status;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	@JsonIgnoreProperties("projects")
	private Doctor doctor;
	
	@Column(nullable = false)
	private int samplesNumber;
	
	
	public Project() {
		
	}
	
	
	public Project(int projectId) {
		this.projectId = projectId;
	}

	public int getSamplesNumber() {
		return samplesNumber;
	}

	public void setSamplesNumber(int samplesNumber) {
		this.samplesNumber = samplesNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}
