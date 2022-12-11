package com.management.querika.Querika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.querika.Querika.model.Course;
import com.management.querika.Querika.model.Question;

public interface QuestionRepository extends  JpaRepository<Question, Integer>{
	
	@Query(value = "SELECT * FROM querika_db.question where if_answered = 0", nativeQuery = true)
	public List<Question> findUnansweredQuestion ();
	
	public Question findById(int id);

}
