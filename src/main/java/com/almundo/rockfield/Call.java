package com.almundo.rockfield;

import com.almundo.rockfield.model.Employee;

/**
 * A class to handle calls
 */
public class Call implements Runnable {

	private static final int MIN_DURATION = 5;
	private final AgentHandler agentHandler;
	Employee agent;
	
	public Call(AgentHandler agentHandler) {
		this.agentHandler = agentHandler;
	}

	/**
	 * When the dispatcher makes the call, this method is executed.
	 */
	@Override
	public void run() {
		findAFreeAgent();
		makeTheCall();
		releaseAgentFromCall();
	}

	private void findAFreeAgent() {
		while (agent == null) {
			agent = agentHandler.getFreeAgent();
			sleepThreadInSeconds(1);
		}
	}

	private void sleepThreadInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			// ToDo: handle InterruptedException
		}
	}

	private void makeTheCall() {
		int callDuration = (int) Math.round(Math.random() * 5) + MIN_DURATION;
		System.out.println(Thread.currentThread().getName() + " - Call duration " + callDuration + " seconds handled by " + agent);
		sleepThreadInSeconds(callDuration);
	}

	private void releaseAgentFromCall() {
		agentHandler.addAgent(agent);
	}
}
