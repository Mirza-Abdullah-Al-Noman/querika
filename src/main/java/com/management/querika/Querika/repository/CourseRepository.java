package com.management.querika.Querika.repository;


import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.querika.Querika.model.Course;


public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	public List<Course> findAll();
	
	public Course findById(int id);
	
	
	@Query(value = "SELECT EXISTS (SELECT * FROM querika_db.course WHERE course_code =?1)", nativeQuery = true)
	public BigInteger existsByCourseCode(String courseCode);

}
