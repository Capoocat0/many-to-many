package com.example.manytomany.repository;

import com.example.manytomany.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 歐炫
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	public Course findOneById(Long id);
}
