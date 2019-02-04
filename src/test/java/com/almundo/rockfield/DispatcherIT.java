package com.almundo.rockfield;

import com.almundo.rockfield.model.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.*;

/**
 * Integration test. It will not run on compilation, it must be run manually
*/
@FixMethodOrder // Do not run tests in parallel.
public class DispatcherIT {

	@Test
	public void tooFewAgentsTest() throws InterruptedException, ExecutionException {
		System.out.println("Starting tooFewAgentsTest...");
		runIntegrationTest(2, 20);
	}
	
	@Test
	public void moreCallsThanThreadsTest() throws InterruptedException, ExecutionException {
		System.out.println("Starting moreCallsThanThreadsTest...");
		runIntegrationTest(10, 100);
	}
	
	public void runIntegrationTest(int maxAgentsPerRole, int maxCalls) throws InterruptedException, ExecutionException {
		Dispatcher dispatcher = new Dispatcher();
		for (int i = 1; i <= maxAgentsPerRole; i++) {
			dispatcher.agentHandler.addAgent(new Operator("" + i));
			dispatcher.agentHandler.addAgent(new Supervisor("" + i));
			dispatcher.agentHandler.addAgent(new Manager("" + i));
		}
		LinkedList<Future> callList = new LinkedList();
		for (int i = 0; i < maxCalls; i++) {
			callList.add(dispatcher.dispatchCall());
		}
		for (Future call : callList) {
			call.get();
		}
	}
}
