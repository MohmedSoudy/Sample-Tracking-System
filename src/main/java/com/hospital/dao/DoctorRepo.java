package com.hospital.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hospital.model.Doctor;

public interface DoctorRepo extends CrudRepository<Doctor, Integer> {
	Optional<Doctor> findByUserEmailAndPassword(String userEmail,String password);
	void deleteByUserEmail(String userEmail);
	boolean existsByUserEmail(String userEmail);
	Optional<Doctor> findByUserEmail(String userEmail);
}
