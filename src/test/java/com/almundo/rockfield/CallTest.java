package com.almundo.rockfield;

import com.almundo.rockfield.model.Operator;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class CallTest {
	
	@Test
	public void testHappyPath() {
		AgentHandler agentHandler = mock(AgentHandler.class);
		Operator operator = new Operator("Mocked");
		when(agentHandler.getFreeAgent()).thenReturn(operator);
		Call call = new Call(agentHandler);
		Date start = new Date();
		call.run();
		Assert.assertEquals(operator, call.agent);
		Date end = new Date();
		long duration = end.getTime() - start.getTime();
		Assert.assertTrue(duration >= 5000);
		Assert.assertTrue(duration <= 10000);
	}

	@Test
	public void testWaitForAgent() throws InterruptedException {
		AgentHandler agentHandler = mock(AgentHandler.class);
		when(agentHandler.getFreeAgent()).thenReturn(null);
		Call call = new Call(agentHandler);
		Thread thread = new Thread(call);
		thread.start();
		for (int i = 0; i < 3; i++) {
			Thread.sleep(1000);
			Assert.assertNull(call.agent);
		}
		Operator operator = new Operator("Mocked");
		when(agentHandler.getFreeAgent()).thenReturn(operator);
		Thread.sleep(1000);
		Assert.assertEquals(operator, call.agent);
	}
}
