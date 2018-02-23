package models;

public interface CallEmployee extends Comparable<CallEmployee> {

	public void pickCall(Call call);
	public void hangCall();
	public Hierarchy getPickUpPriority();
	public String getName();
}
