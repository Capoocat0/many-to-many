package com.example.manytomany.repository;

import com.example.manytomany.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author someone
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	public Student findOneById(Long id);
}
