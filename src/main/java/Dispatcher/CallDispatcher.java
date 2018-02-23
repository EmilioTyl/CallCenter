package dispatcher;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import models.Call;
import models.CallEmployee;

public class CallDispatcher {
	
	private static int SIMULTANEUS_CALLS = 10;
	private PriorityBlockingQueue<CallEmployee> callEmployees = new PriorityBlockingQueue<CallEmployee>();
	ExecutorService executor = Executors.newFixedThreadPool(SIMULTANEUS_CALLS);
	private AtomicInteger simultaneousCalls = new AtomicInteger(0);
	
	public CallDispatcher() {
		System.out.println("Call Dispatcher Created: simultaneus_calls: " + SIMULTANEUS_CALLS);
	}
	public void addCallEmployee(CallEmployee callEmployee) {
		
		System.out.println("Employee " + callEmployee.getName() + " now in queue");
		callEmployees.put(callEmployee);
		
	}
	public void receiveCall(Call call) {
		System.out.println("Receive call"+ call.getId());
		
		executor.submit(() -> {
			CallEmployee employee = null;
			System.out.println("Call "+ call.getId() + " waiting ... ");
			try {
				employee = callEmployees.take();
				simultaneousCalls.incrementAndGet();
				employee.pickCall(call);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		});

	}
	
	public void endCall(CallEmployee callEmployee) {
		simultaneousCalls.decrementAndGet();
		addCallEmployee(callEmployee);		
	}
	
	public void close() {
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
	
	public int getSimultaneousCalls() {	
		return simultaneousCalls.get();
	}
	
}
