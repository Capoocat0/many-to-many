package com.example.manytomany;

import com.example.manytomany.entity.Student;
import com.example.manytomany.repository.StudentRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class ManyToManyApplication {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApplication.class, args);
	}

	@GetMapping(path = "/{id}.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	String index(@PathVariable("id") Long id) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		Student student = studentRepository.findOneById(id);
		
		jsonArray.put(student.getCourseCollection());
		jsonObject.put("results", jsonArray);

		return jsonObject.toString();
	}
}
