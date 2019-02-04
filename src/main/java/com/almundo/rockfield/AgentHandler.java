package com.almundo.rockfield;

import com.almundo.rockfield.model.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

/**
 * A class to handle concurrently the availability of agents.
 * A valid agent might be Operator, Supervisor, or Manager.
 */
public class AgentHandler {

	private static final AgentHandler instance = new AgentHandler();
	private final Queue<Operator> operatorQueue = new ConcurrentLinkedQueue();
	private final Queue<Supervisor> supervisorQueue = new ConcurrentLinkedQueue();
	private final Queue<Manager> managerQueue = new ConcurrentLinkedQueue();

	/**
	 * The given agent is removed from the queue and no longer available until 
	 * returned to the queue by calling the addAgent method.
	 * @return A free agent that might be an Operator, Supervisor or Manager. 
	 * It may return null if there are no free agents.
	 */
	public Employee getFreeAgent() {
		Employee agent = operatorQueue.poll();
		if (agent == null) {
			agent = supervisorQueue.poll();
		}
		if (agent == null) {
			agent = managerQueue.poll();
		}
		return agent;
	}

	/**
	 * The agent is added to its priority queue.
	 * @param agent Employee to become available for a call.
	 */
	public void addAgent(Employee agent) {
		if (agent instanceof Manager) {
			managerQueue.add((Manager) agent);
		} else if (agent instanceof Supervisor) {
			supervisorQueue.add((Supervisor) agent);
		} else if (agent instanceof Operator) {
			operatorQueue.add((Operator) agent);
		}
	}
}
