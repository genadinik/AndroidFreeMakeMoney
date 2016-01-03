package data;

import java.util.ArrayList;

public class Question 
{
	public String questionId;
	public String question;
	public ArrayList <QuestionComment>  questionComments;
	public String questionByMemberId;
	public String authorName;
	public String hasNewComments;

	
	public String getHasNewComments() {
		return hasNewComments;
	}
	public void setHasNewComments(String hasNewComments) {
		this.hasNewComments = hasNewComments;
	}	
	
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public ArrayList<QuestionComment> getQuestionComments() {
		return questionComments;
	}
	public void setQuestionComments(ArrayList<QuestionComment> questionComments) {
		this.questionComments = questionComments;
	}
	public String getQuestionByMemberId() {
		return questionByMemberId;
	}
	public void setQuestionByMemberId(String questionByMemberId) {
		this.questionByMemberId = questionByMemberId;
	}
	
	@Override
	public String toString()
	{
		if (hasNewComments != null && hasNewComments.equals("1"))
		{
			return question + " (New comments! Tap this question to see the comments)";
		}
		else
		{
			return question;
		}
	}
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
