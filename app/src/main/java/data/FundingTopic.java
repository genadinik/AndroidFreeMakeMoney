package data;

public class FundingTopic 
{
	public String topicName;
	public String topicId;
	public String commentCount;
	public String displayOrder;

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	public String toString ( )
	{
		if ( commentCount != null )
		{
			return topicName + " (" + commentCount + ")";
		}
		else
		{
			return topicName;
		}
	}
}
