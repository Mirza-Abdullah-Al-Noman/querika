package com.management.querika.Querika.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int askedBy;
	
	private int answeredBy;
	
	private boolean ifAnswered;
	
	private String questionText;
	
	private String answerText;
	
	private int courseId;


	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAskedBy() {
		return askedBy;
	}

	public void setAskedBy(int askedBy) {
		this.askedBy = askedBy;
	}

	public int getAnsweredBy() {
		return answeredBy;
	}

	public void setAnsweredBy(int answeredBy) {
		this.answeredBy = answeredBy;
	}

	public boolean isIfAnswered() {
		return ifAnswered;
	}

	public void setIfAnswered(boolean ifAnswered) {
		this.ifAnswered = ifAnswered;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

}
