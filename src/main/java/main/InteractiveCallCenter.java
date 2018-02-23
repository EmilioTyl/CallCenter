package main;

import java.io.IOException;

import dispatcher.CallDispatcher;
import models.Call;
import models.Employee;
import models.Hierarchy;

public class InteractiveCallCenter {
	public static void main(String[] args) {
		boolean cancel = false;
		int callId = 0;
		int calls = 20;
		
		Call call;
		CallDispatcher callDispatcher = new CallDispatcher();
		callDispatcher.addCallEmployee(new Employee("Carl", Hierarchy.OPERATOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Alice", Hierarchy.SUPERVISOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Lucile", Hierarchy.OPERATOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Bob", Hierarchy.DIRECTOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Robert", Hierarchy.OPERATOR, callDispatcher));
		callDispatcher.addCallEmployee(new Employee("Jane", Hierarchy.DIRECTOR, callDispatcher));
		
		System.out.println("\n\n\n Write as many 'c' characters as calls you want to place or just 'e' to exit  \n\n\n"  );
		while(!cancel) {
			try {
				char c = (char) System.in.read();
				switch(c) {
				case 'c':
					callDispatcher.receiveCall(new Call(callId++));
					break;
				case 'e':
					cancel = true;
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		callDispatcher.close();
	
	}

}
