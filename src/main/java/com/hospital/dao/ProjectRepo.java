package com.hospital.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hospital.model.Project;

public interface ProjectRepo extends CrudRepository<Project, Integer> {
	Optional<Project> findByProjectId(String id);
	List<Project> findByStartDateBetween(Date start,Date end);
	
}
