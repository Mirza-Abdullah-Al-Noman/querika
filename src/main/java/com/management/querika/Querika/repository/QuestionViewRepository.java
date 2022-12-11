package com.management.querika.Querika.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;


@Repository
public class QuestionViewRepository {
	
	 @PersistenceContext
	 private EntityManager entityManager;
	 
	 public List<Object []> viewQuestionsByUserId(int id) {
		 Query query = entityManager.createQuery("SELECT ques.questionText,ques.id, cour.courseName from Question ques, Course cour WHERE ques.courseId = cour.id AND ques.askedBy =: id");
		 query.setParameter("id", id);
		 List<Object[]> resultList = query.getResultList();
		 return (List<Object []>)  resultList; 
	 }
	 
	 public List<Object []> viewQuestionsByQuestionId(int id) {
		 Query query = entityManager.createQuery("SELECT ques.questionText,ques.id, cour.courseName, ques.answerText from Question ques, Course cour WHERE ques.courseId = cour.id AND ques.id =: id");
		 query.setParameter("id", id);
		 List<Object[]> resultList = query.getResultList();
		 return (List<Object []>)  resultList; 
	 }
	 
	 public List<Object []> viewQuestionsByCourseId(int id) {
		 Query query = entityManager.createQuery("SELECT ques.questionText,ques.id, cour.courseName from Question ques, Course cour WHERE ques.courseId = cour.id AND cour.id =: id");
		 query.setParameter("id", id);
		 List<Object[]> resultList = query.getResultList();
		 return (List<Object []>)  resultList; 
	 }

	 public List<Object []> viewUnansweredQuestions() {
		 Query query = entityManager.createQuery("SELECT ques.questionText,ques.id, cour.courseName from Question ques, Course cour WHERE ques.courseId = cour.id AND ques.ifAnswered = 0");
		 List<Object[]> resultList = query.getResultList();
		 return (List<Object []>)  resultList; 
	 }
	 
	 
	 public List<Object []> viewUnansweredQuestionById(int id) {
		 Query query = entityManager.createQuery("SELECT ques.questionText,ques.id, cour.courseName from Question ques, Course cour WHERE ques.courseId = cour.id AND ques.id =: id");
		 query.setParameter("id", id);
		 List<Object[]> resultList = query.getResultList();
		 return (List<Object []>)  resultList; 
	 }
	 
	 public List<Object []> viewMyAnsweredQuestions(int id) {
		 Query query = entityManager.createQuery("SELECT ques.questionText, cour.courseName, ques.answerText from Question ques, Course cour WHERE ques.courseId = cour.id AND ques.answeredBy =: id");
		 query.setParameter("id", id);
		 List<Object[]> resultList = query.getResultList();
		 return (List<Object []>)  resultList; 
	 }
	 
	 
}
