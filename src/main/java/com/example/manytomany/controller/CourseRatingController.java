package com.example.manytomany.controller;

import com.example.manytomany.entity.CourseRating;
import com.example.manytomany.repository.CourseRatingRepository;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author someone
 */
@Controller
@RequestMapping("/courseRating")
public class CourseRatingController {

	@Autowired
	private CourseRatingRepository courseRatingRepository;

	@GetMapping("/")
	public ModelAndView handleError(HttpServletRequest request) throws Exception {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("classpath:/skeleton/index.xml");
		Element documentElement = document.getDocumentElement();


		/*
		 資料
		 */
		Element elementData = document.createElement("data");
		for (CourseRating entity : courseRatingRepository.findAll()) {
			Element elementDatum = document.createElement("datum");
			elementDatum.setAttribute("id", entity.getId().toString());

			Element elementStudent = document.createElement("student");
			elementStudent.setTextContent(entity.getStudent().getFullName());
			elementDatum.appendChild(elementStudent);

			Element elementCourse = document.createElement("course");
			elementCourse.setTextContent(entity.getCourse().getDisplayName());
			elementDatum.appendChild(elementCourse);

			elementData.appendChild(elementDatum);
		}

		ModelAndView modelAndView = new ModelAndView("courseRating");
		modelAndView.getModelMap().addAttribute(new DOMSource(document));
		return modelAndView;
	}
}
