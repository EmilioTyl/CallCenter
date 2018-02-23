package models;

import java.util.concurrent.TimeUnit;

import dispatcher.CallDispatcher;

public class Employee implements CallEmployee {

	private String name;
	private Hierarchy hierarchy;
	CallDispatcher callDispatcher; 
	Call call;
	public Employee(String name, Hierarchy hierarchy, CallDispatcher callDispatcher) {
		this.name = name;
		this.hierarchy = hierarchy;
		this.callDispatcher = callDispatcher;
	}
	public Employee(Hierarchy hierarchy, CallDispatcher callDispatcher) {
		this.name = "";
		this.hierarchy = hierarchy;
		this.callDispatcher = callDispatcher;
	}
	
	public void pickCall(Call call) {
		System.out.println( " Pick call id: " + call.getId() + " Employee Name: " + name + " Hierarchy:" + hierarchy );
		try {
			this.call = call;
			TimeUnit.SECONDS.sleep(call.getDuration());
			System.out.println("Hang Call Employee Name: " + name + " Hierarchy:" + hierarchy );
			call = null;
			hangCall();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

	public void hangCall() {
		callDispatcher.endCall(this);
	}

	public int compareTo(CallEmployee o) {
		return hierarchy.compareTo(o.getPickUpPriority());			
	}
	
	public Hierarchy getPickUpPriority() {
		return hierarchy;
	}
	
	public String getName() {
		return name;
	}
	public Call getCall() {
		return call;
	}
}
