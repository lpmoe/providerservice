package com.lineage.providerservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.lineage.providerservices.model.OfferedService;
import com.lineage.providerservices.model.Provider;
import com.lineage.providerservices.service.ProviderService;

@RestController
public class ProviderController {

	@Autowired
	private ProviderService providerService;

	@GetMapping("/students")
	public List<Provider> retrieveAllStudent() {
		return providerService.retrieveAllStudents();
	}
	
	@GetMapping("/students/{studentId}/courses")
	public List<OfferedService> retrieveCoursesForStudent(@PathVariable String studentId) {
		return providerService.retrieveCourses(studentId);
	}
	
	@GetMapping("/students/{studentId}/courses/{courseId}")
	public OfferedService retrieveDetailsForCourse(@PathVariable String studentId,
			@PathVariable String courseId) {
		return providerService.retrieveCourse(studentId, courseId);
	}
	
	@PostMapping("/students/{studentId}/courses")
	public ResponseEntity<Void> registerStudentForCourse(
			@PathVariable String studentId, @RequestBody OfferedService newCourse) {

		OfferedService course = providerService.addCourse(studentId, newCourse);

		if (course == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(course.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

}
