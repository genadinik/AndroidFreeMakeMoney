package data;

import android.util.Log;

public class DiscussionMessage 
{
	public String messageId;
	public String message;
	public String authorId;
	public String authorName;
	public Boolean isPrivate; 
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}	
	
	@Override
	public String toString ( )
	{   		
        if ( isPrivate == null )
        {

        }        

        if ( isPrivate == null )
        {

        }          
        
		if ( authorName == null || (isPrivate == null || isPrivate == true ) )
		{			
			return message;
		}
		else
		{
			return authorName + ": " + message;
		}
	}
}
