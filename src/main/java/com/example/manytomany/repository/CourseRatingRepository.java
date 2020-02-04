package com.example.manytomany.repository;

import com.example.manytomany.entity.CourseRating;
import com.example.manytomany.entity.CourseRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author someone
 */
public interface CourseRatingRepository extends JpaRepository<CourseRating, CourseRatingKey> {

	public CourseRating findOneById(CourseRatingKey id);
}
