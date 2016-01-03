package data;

public class QuestionComment 
{
	public String commentId;
	public String comment;
	public String commentBy;
	public String authorName;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommentBy() {
		return commentBy;
	}
	public void setCommentBy(String commentBy) {
		this.commentBy = commentBy;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString ( )
	{
		return authorName + ": " + comment;
	}
}
