package com.example.manytomany;

import com.example.manytomany.entity.Course;
import com.example.manytomany.entity.Student;
import com.example.manytomany.repository.CourseRepository;
import com.example.manytomany.repository.StudentRepository;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SpringBootApplication
public class Application {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping(path = "/course/{id}.json", produces = "application/json;charset=UTF-8")
	ResponseEntity<Course> course(@PathVariable("id") Long id) {
		Optional<Course> optional = courseRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(path = "/student/{id}.json", produces = "application/json;charset=UTF-8")
	ResponseEntity<Student> student(@PathVariable("id") Long id) {
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping(path = "/course/{id}.json", produces = "application/json;charset=UTF-8")
	@SuppressWarnings("UnusedAssignment")
	ResponseEntity<Course> course(@PathVariable("id") Long id, @RequestParam(name = "students") Set<Student> students) {
		Course course = null;
		Optional<Course> optional = courseRepository.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		course = optional.get();

//		Set<Student> students = new HashSet<>();
//		for (Long studentId : studentIds) {
//			Student student = studentRepository.findOneById(studentId);
//			if (null != student) {
//				students.add(student);
//			}
//		}
		course.setStudents(students);

		return ResponseEntity.ok(courseRepository.saveAndFlush(course));
	}

	@PostMapping(path = "/student/{id}.json", produces = "application/json;charset=UTF-8")
	@SuppressWarnings("UnusedAssignment")
	ResponseEntity<Student> student(@PathVariable Long id, @RequestParam(defaultValue = "") String fullName, @RequestParam Set<Course> courses) {
		Student student = null;
		Optional<Student> optional = studentRepository.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		student = optional.get();

		if (null == fullName || fullName.isEmpty()) {
			fullName = Long.toHexString(new GregorianCalendar().getTimeInMillis());
		}
		student.setFullName(fullName);

		student.setCourses(courses);

		return ResponseEntity.ok(studentRepository.saveAndFlush(student));
	}
}
