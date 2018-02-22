package Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Dispatcher.CallDispatcher;
import Models.Call;
import Models.Employee;
import Models.Hierarchy;

public class CallCenterConstraints {
CallDispatcher callDispatcher = new CallDispatcher();
	
	
	@Test
	public void ConcurrentCalls() {
		int calls = 20;
		int employees = 20;
		int duration = 10;
		int simultaneousCalls = 10;
		
		for (int e = 0 ; e < employees; e++)
			callDispatcher.addCallEmployee(new Employee(Hierarchy.OPERATOR, callDispatcher));
		for(int n = 0 ; n < calls; n ++ )
			callDispatcher.receiveCall(new Call(n,duration));
		
		 assertEquals(callDispatcher.getSimultaneousCalls(), simultaneousCalls);
		
	}
	
	@Test
	public void HierarchyOrder() {
		int calls = 20;
		int duration = 10;
		int simultaneousCalls = 10;
		
		Employee operator = new Employee(Hierarchy.OPERATOR, callDispatcher);
		callDispatcher.addCallEmployee(operator);
		Employee supervisor = new Employee(Hierarchy.SUPERVISOR, callDispatcher);
		callDispatcher.addCallEmployee(supervisor);
		Employee director = new Employee(Hierarchy.DIRECTOR, callDispatcher);
		callDispatcher.addCallEmployee(director);
		
		Call callOp = new Call(1 ,duration);
		callDispatcher.receiveCall(callOp);
		Call callSup = new Call(2 ,duration);
		callDispatcher.receiveCall(callSup);
		Call callDir = new Call(3 ,duration);
		callDispatcher.receiveCall(callDir);
		
		assertEquals(operator.getCall().getId(), callOp.getId());
		assertEquals(supervisor.getCall().getId(), callSup.getId());
		assertEquals(director.getCall().getId(), callDir.getId());
		
	}

}
