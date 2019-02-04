package com.almundo.rockfield;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Entry class to dispatch calls
 */
public class Dispatcher {

	private final int NUMBER_OF_PARALLEL_THREADS = 10;
	private final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_PARALLEL_THREADS);
	final AgentHandler agentHandler = new AgentHandler();

	/**
	 * Dispatch a new call
	 * @return a Future instance to wait for the call to finish
	 */
	public Future dispatchCall() {
		return executor.submit(new Call(agentHandler));
	}
}
