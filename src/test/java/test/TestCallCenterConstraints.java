package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import dispatcher.CallDispatcher;
import models.Call;
import models.Employee;
import models.Hierarchy;

public class TestCallCenterConstraints {
CallDispatcher callDispatcher = new CallDispatcher();
	
	
	@Test
	public void SimultaneousCalls() {
		int calls = 20;
		int employees = 20;
		int duration = 10;
		int simultaneousCalls = 10;
		
		for (int e = 0 ; e < employees; e++)
			callDispatcher.addCallEmployee(new Employee(Hierarchy.OPERATOR, callDispatcher));
		for(int n = 0 ; n < calls; n ++ )
			callDispatcher.receiveCall(new Call(n,duration));
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 assertEquals(callDispatcher.getSimultaneousCalls(), simultaneousCalls);
		
	}
	
	@Test
	public void LessEmployeesThanMaxCalls() {
		int calls = 20;
		int employees = 5;
		int duration = 10;
		int simultaneousCalls = 5;
		
		for (int e = 0 ; e < employees; e++)
			callDispatcher.addCallEmployee(new Employee(Hierarchy.OPERATOR, callDispatcher));
		for(int n = 0 ; n < calls; n ++ )
			callDispatcher.receiveCall(new Call(n,duration));
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 assertEquals(callDispatcher.getSimultaneousCalls(), simultaneousCalls);
		
	}
	
	@Test
	public void HierarchyOrder() {
		int calls = 20;
		int duration = 10;
		int simultaneousCalls = 10;
		
		Employee supervisor = new Employee(Hierarchy.SUPERVISOR, callDispatcher);
		callDispatcher.addCallEmployee(supervisor);
		Employee director = new Employee(Hierarchy.DIRECTOR, callDispatcher);
		callDispatcher.addCallEmployee(director);
		Employee operator = new Employee(Hierarchy.OPERATOR, callDispatcher);
		callDispatcher.addCallEmployee(operator);
		
		
		Call callOp = new Call(1 ,duration);
		callDispatcher.receiveCall(callOp);
		Call callSup = new Call(2 ,duration);
		callDispatcher.receiveCall(callSup);
		Call callDir = new Call(3 ,duration);
		callDispatcher.receiveCall(callDir);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(operator.getCall().getId(), callOp.getId());
		assertEquals(supervisor.getCall().getId(), callSup.getId());
		assertEquals(director.getCall().getId(), callDir.getId());
		
	}
	
	
}
