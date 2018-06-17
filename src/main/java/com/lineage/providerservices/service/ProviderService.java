package com.lineage.providerservices.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.lineage.providerservices.model.OfferedService;
import com.lineage.providerservices.model.Provider;

@SpringBootApplication
public class ProviderService {

	private static List<Provider> students = new ArrayList<>(); 
    
	@Autowired
	private ProviderRepository providerRepository;
	
	static {
		//Initialize Data
		OfferedService course1 = new OfferedService("Course1", "Spring", "10 Steps", Arrays
				.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		OfferedService course2 = new OfferedService("Course2", "Spring MVC", "10 Examples",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		OfferedService course3 = new OfferedService("Course3", "Spring Boot", "6K Students",
				Arrays.asList("Learn Maven", "Learn Spring",
						"Learn Spring MVC", "First Example", "Second Example"));
		OfferedService course4 = new OfferedService("Course4", "Maven",
				"Most popular maven course on internet!", Arrays.asList(
						"Pom.xml", "Build Life Cycle", "Parent POM",
						"Importing into Eclipse"));

		Provider ranga = new Provider("Student1", "Ranga Karanam",
				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));

		Provider satish = new Provider("Student2", "Satish T",
				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));


		Provider ranga2 = new Provider("Student1", "Ranga Karanam",
				"Hiker, Programmer and Architect", null);

		Provider satish2 = new Provider("Student2", "Satish T",
				"Hiker, Programmer and Architect", null);
		
		students.add(ranga);
		students.add(satish);
	//	addStudent(ranga2);
	//	addStudent(satish2);
		
	}

	@PostConstruct
	public void init(){

		Provider ranga2 = new Provider("Student1", "Ranga Karanam",
				"Hiker, Programmer and Architect", null);

		Provider satish2 = new Provider("Student2", "Satish T",
				"Hiker, Programmer and Architect", null);
		
		addStudent(ranga2);
		addStudent(satish2);
		
	}
	
	public void addStudent(Provider student){
		
		//providerRepository.save(student);
		try{
			System.out.println("it get here and student repository is not empty 1" + providerRepository.count());

			providerRepository.save(student);
			System.out.println("it get here and student repository is not empty 2" + providerRepository.count());
		}catch (Exception e){
			System.out.println("student description here" + student.getDescription() );
			//System.out.println("student description here" + providerRepository.toString());
		}
		
	}
	
	//@PostConstruct
	public List<Provider> retrieveAllStudents() {
		//return students;
		System.out.println("is student repository has something:"+ providerRepository.count());
		for (Provider student : providerRepository.findAll()){
			System.out.println(student);
		}
		
		return providerRepository.findAll();
	}

	public Provider retrieveStudent(String studentId) {
		for (Provider student : students) {
			if (student.getId().equals(studentId)) {
				return student;
			}
		}
		return null;
	}

	public List<OfferedService> retrieveCourses(String studentId) {
		Provider student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		return student.getOfferedServices();
	}

	public OfferedService retrieveCourse(String studentId, String courseId) {
		Provider student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		for (OfferedService course : student.getOfferedServices()) {
			if (course.getId().equals(courseId)) {
				return course;
			}
		}

		return null;
	}

	private SecureRandom random = new SecureRandom();

	public OfferedService addCourse(String studentId, OfferedService course) {
		Provider student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		course.setId(randomId);
		student.getOfferedServices().add(course);

		return course;
	}
}