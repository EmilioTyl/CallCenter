package CallCenterMain;

import Dispatcher.CallDispatcher;
import Models.Call;
import Models.Employee;
import Models.Hierarchy;

public class CallCenterMain {
	 public static void main(String[] args) {
		 
		int calls = 20;
		CallDispatcher callDispatcher = new CallDispatcher();
		callDispatcher.addCallEmployee(new Employee("Carl", Hierarchy.OPERATOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Alice", Hierarchy.SUPERVISOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Lucile", Hierarchy.OPERATOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Bob", Hierarchy.DIRECTOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Robert", Hierarchy.OPERATOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Jane", Hierarchy.DIRECTOR, callDispatcher));
		
		
		for(int n =0 ; n < calls; n ++ ) 
			callDispatcher.receiveCall(new Call(n));		
		callDispatcher.close();
	}
}
