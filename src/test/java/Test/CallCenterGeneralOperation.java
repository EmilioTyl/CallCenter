package Test;

import org.junit.jupiter.api.Test;

import Dispatcher.CallDispatcher;
import Models.Call;
import Models.Employee;
import Models.Hierarchy;

public class CallCenterGeneralOperation {
	
	
	CallDispatcher callDispatcher = new CallDispatcher();
	
	public CallCenterGeneralOperation() {
		callDispatcher.addCallEmployee(new Employee("Carl", Hierarchy.OPERATOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Alice", Hierarchy.SUPERVISOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Lucile", Hierarchy.OPERATOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Bob", Hierarchy.DIRECTOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Robert", Hierarchy.OPERATOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Jane", Hierarchy.DIRECTOR, callDispatcher));
	}
	@Test
	public void dispatcherGeneralOperation() {
		int CALLS = 20;
		for(int n =0 ; n < CALLS; n ++ ) 
			callDispatcher.receiveCall(new Call(n));		
		callDispatcher.close();
		
	}

}
