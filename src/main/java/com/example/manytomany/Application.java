package com.example.manytomany;

import com.example.manytomany.entity.Course;
import com.example.manytomany.entity.Student;
import com.example.manytomany.repository.CourseRepository;
import com.example.manytomany.repository.StudentRepository;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.Set;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@GetMapping(path = "/student", produces = "application/json;charset=UTF-8")
	@ResponseBody
	String student() {
		JSONObject jSONObject = new JSONObject();
		for (Student student : studentRepository.findAll()) {
			for (Course course : student.getCourses()) {
				jSONObject.append(student.getFullName(), course.getDisplayName());
			}
		}
		return jSONObject.toString();
	}

	@GetMapping(path = "/students", produces = "application/json;charset=UTF-8")
	ResponseEntity<Collection<Student>> students() {
		return ResponseEntity.ok(studentRepository.findAll());
	}

	@GetMapping(path = "/course", produces = "application/json;charset=UTF-8")
	@ResponseBody
	String course() {
		JSONObject jSONObject = new JSONObject();
		for (Course course : courseRepository.findAll()) {
			for (Student student : course.getStudents()) {
				jSONObject.append(course.getDisplayName(), student.getFullName());
			}
		}
		return jSONObject.toString();
	}

	@GetMapping(path = "/courses", produces = "application/json;charset=UTF-8")
	ResponseEntity<Collection<Course>> courses() {
		return ResponseEntity.ok(courseRepository.findAll());
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
