package com.almundo.rockfield;

import com.almundo.rockfield.model.*;
import org.junit.Assert;
import org.junit.Test;

public class AgentHandlerTest {
	
	@Test
	public void testPollingOrder() {
		AgentHandler handler = new AgentHandler();
		Assert.assertNull(handler.getFreeAgent());
		for (int i = 1; i <= 3; i++) {
			handler.addAgent(new Manager("" + i));
			handler.addAgent(new Supervisor("" + i));
			handler.addAgent(new Operator("" + i));
		}
		Assert.assertEquals("Operator 1", handler.getFreeAgent().toString());
		Assert.assertEquals("Operator 2", handler.getFreeAgent().toString());
		Assert.assertEquals("Operator 3", handler.getFreeAgent().toString());
		Assert.assertEquals("Supervisor 1", handler.getFreeAgent().toString());
		Assert.assertEquals("Supervisor 2", handler.getFreeAgent().toString());
		Assert.assertEquals("Supervisor 3", handler.getFreeAgent().toString());
		Assert.assertEquals("Manager 1", handler.getFreeAgent().toString());
		Assert.assertEquals("Manager 2", handler.getFreeAgent().toString());
		Assert.assertEquals("Manager 3", handler.getFreeAgent().toString());
		Assert.assertNull(handler.getFreeAgent());
	}
}
