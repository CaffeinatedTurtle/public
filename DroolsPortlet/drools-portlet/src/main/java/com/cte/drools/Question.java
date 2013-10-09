package com.cte.drools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Question {
	protected static Logger logger = LoggerFactory.getLogger( Question.class );

	public static final String YES="yes";
	public static final String NO="no";

	private String question;
	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		logger.error("Answer to "+question+" is "+answer);
		this.answer = answer;
	}
	
	public boolean isAnswered(){
		return (answer != null && !answer.isEmpty());
	}
	public boolean equals(Question q){
		return q.getQuestion().equals(this.question);
	}
}
