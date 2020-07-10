package com.hospital.controller;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dao.DoctorRepo;
import com.hospital.dao.ProjectRepo;
import com.hospital.model.Doctor;
import com.hospital.model.Project;
import com.hospital.util.PasswordGenerator;

@RestController
public class TrackingService {

	@Autowired
	DoctorRepo doctorRepo;

	@Autowired
	ProjectRepo projectRepo;

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public ResponseEntity<Object> greetings() {

		return new ResponseEntity<>("Hello Mother Fuckers", HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/doctor/new")
	public ResponseEntity<String> createDoctor(@RequestParam("email") String email) {
		String password = PasswordGenerator.generateCommonLangPassword();
		Doctor doctor = new Doctor(password, email);
		if (!doctorRepo.existsByUserEmail(email)) {
			doctorRepo.save(doctor);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("email exists", HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "/doctor/login")
	public ResponseEntity<Doctor> getDoctor(@RequestHeader("email") String email,
			@RequestHeader("pass") String password) {

		Optional<Doctor> bdResponse = doctorRepo.findByUserEmailAndPassword(email, password);
		if (bdResponse.isPresent()) {
			return new ResponseEntity<>(bdResponse.get(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new Doctor(-1), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/doctor/delete/{email}")
	@Transactional
	public ResponseEntity<String> deleteDoctor(@PathVariable(name = "email") String email) {
		if (doctorRepo.existsByUserEmail(email)) {
			doctorRepo.deleteByUserEmail(email);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else
			return new ResponseEntity<>("user not exists", HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, path = "/doctor/update")
	public ResponseEntity<String> updateDoctor(@RequestBody Doctor newdoctor) {

		if (doctorRepo.existsByUserEmail(newdoctor.getUserEmail())) {
			Optional<Doctor> bdResponse = doctorRepo.findByUserEmail(newdoctor.getUserEmail());
			Doctor doctor = bdResponse.get();
			doctor.setPassword(newdoctor.getPassword());
			doctor.setUserEmail(newdoctor.getUserEmail());
			doctorRepo.save(doctor);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else
			return new ResponseEntity<>("user not exists", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/project/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createProject(@RequestBody Project project) {
		Doctor doctor = doctorRepo.findByUserEmail(project.getDoctor().getUserEmail()).get();
		project.setDoctor(doctor);
		projectRepo.save(project);
		return new ResponseEntity<>("success", HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, path = "/project/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> getProjectByID(@PathVariable("id") int projectId) {

		Optional<Project> dbResponse = projectRepo.findByProjectId(projectId);

		if (dbResponse.isPresent())
			return new ResponseEntity<Project>(dbResponse.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Project>(new Project(-1), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/project/between", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Project>> getProjectBetweenDates(@RequestParam("start") Date startDate,
			@RequestParam(name = "end", required = false) Date endDate) {

		  //long millis=System.currentTimeMillis(); 
		endDate = endDate == null ? new Date() : endDate;
		List<Project> proList = projectRepo.findByStartDateBetween(startDate, endDate);
		return new ResponseEntity<List<Project>>(proList, HttpStatus.OK);
	}

}
