package com.example.manytomany.repository;

import com.example.manytomany.entity.Course;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author someone
 */
public interface CourseRepository extends CrudRepository<Course, Long> {

	public Course findOneById(Long id);
}
