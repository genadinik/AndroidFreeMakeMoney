package data;

public class FundraisingPlan 
{
	public String planId;
	public String planName;
	public String isPrivate;
	public String hasNewComments;	
	
	public String getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	public String getHasNewComments() {
		return hasNewComments;
	}
	public void setHasNewComments(String hasNewComments) {
		this.hasNewComments = hasNewComments;
	}
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	public String toString ( )
	{
		if ( hasNewComments == null || hasNewComments.equals("0"))
		{
			return planName;
		}
		else
		{
			return planName + " (New Comments!)"; 			
		}
	}
}
