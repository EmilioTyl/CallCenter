package Models;

import java.util.concurrent.TimeUnit;

import Dispatcher.CallDispatcher;

public class Employee implements CallEmployee {

	private String name;
	private Hierarchy hierarchy;
	CallDispatcher callDispatcher;
	
	public Employee(String name, Hierarchy hierarchy, CallDispatcher callDispatcher) {
		this.name = name;
		this.hierarchy = hierarchy;
		this.callDispatcher = callDispatcher;
	}
	
	public void pickCall(Call call) {
		System.out.println("Employee Name:" + name + "Hierarchy:" + hierarchy + " Pick call id: " + call.getId() );
		try {
			TimeUnit.SECONDS.sleep(call.getDuration());
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

}
