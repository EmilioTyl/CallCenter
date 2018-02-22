package Dispatcher;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Models.Call;
import Models.CallEmployee;

public class CallDispatcher {
	
	private static int SIMULTANEUS_CALLS = 10;
	private PriorityQueue<CallEmployee> callEmployees = new PriorityQueue<CallEmployee>();
	private Queue<Call> pendingCalls =  new LinkedList<Call>();
	ExecutorService executor = Executors.newFixedThreadPool(SIMULTANEUS_CALLS);
	
	public CallDispatcher() {
		System.out.println("Call Dispatcher Created: simultaneus_calls: " + SIMULTANEUS_CALLS);
	}
	public void addCallEmployee(CallEmployee callEmployee) {
		synchronized(callEmployees) {
			System.out.println("Employee " + callEmployee.getName() + " now in queue");
			callEmployees.add(callEmployee);
		}
	}
	
	public void receiveCall(Call call) {
		System.out.println("Receive call"+ call.getId());
		
		executor.submit(() -> {
			CallEmployee employee = null;
			synchronized(callEmployees) {
				if(!callEmployees.isEmpty()) 
		    			employee = callEmployees.remove();
			}
			synchronized (pendingCalls) {
				if(employee == null) {
					System.out.println("Call "+ call.getId() + " waiting ");
			    		pendingCalls.add(call);	
				}		
			}
			if(employee != null)
				employee.pickCall(call);
		});
	}
	
	public void endCall(CallEmployee callEmployee) {
		Call call = null; 
		addCallEmployee(callEmployee);
		synchronized (pendingCalls) {	
			if(!pendingCalls.isEmpty())
				call = pendingCalls.remove();
		}
		if(call != null)
			receiveCall(call);
			
		
	}
	
	public void close() {
		while (!pendingCalls.isEmpty()) {
            try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
		try {
		    executor.shutdown();
		    executor.awaitTermination(600, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		}
		finally {
		    if (!executor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.out.println("shutdown finished");
		}
	}
	
}
