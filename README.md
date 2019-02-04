This is Luis Roca's test for almundo.com

For testing the functionality, go to [DispatcherIT](src/test/java/com/almundo/rockfield/DispatcherIT.java).

This Integration test won't run on compilation, it must be executed manually.

# About the extras...

- When there is no free employee, the [AgentHandler](src/main/java/com/almundo/rockfield/AgentHandler.java) returns null and the [Call](src/main/java/com/almundo/rockfield/AgentHandler.java) class sleeps for one second and asks again for a free agent. This process is going to keep repeating until a valid agent is returned.

- When there are more than 10 incoming calls at the same time, the 11th call is sent to the Executor's queue and will be attended when a thread is freed. 

# NOTES

- The Employee class is missing the unit tests

- An extra unit test may be added to the [AgentHandlerTest](src/test/java/com/almundo/rockfield/AgentHandlerTest.java) class to test the adding/polling concurrency.

