package de.hfu;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class QueueTest {
	

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private Queue createQueue(int queueLength) {
		return new Queue(queueLength);
	}
	
	@Test
	public void testQueue_ValidLength() {
		int queueLength=6;
		  Queue testQueue = createQueue(queueLength);
		assertNotNull(testQueue);
	}
	
	@Test
	public void testQueue_InvalidLength() {
		int queueLength=0;
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Queue Arraylength " +queueLength );
		Queue testQueue = new Queue(queueLength);
		
	}
	
	@Test
	public void testDequeue()
	{	
	Queue testQueue= createQueue(3);
    testQueue.enqueue(1);
    testQueue.enqueue(2);
    int queueElement=testQueue.dequeue();
    assertNotNull(testQueue);
    assertNotNull(queueElement);
    assertEquals(1, queueElement);
	}
	
	@Test
	public void testdequeue_emptyQueue() {
		exception.expect(IllegalStateException.class);
		exception.expectMessage("dequeue on empty queue");
		Queue testQueue= createQueue(2);
		assertNotNull(testQueue);
		int queueElement= testQueue.dequeue();
	
	}
	
	@Test
	public void testEnque_overwrite() {
		Queue testQueue= createQueue(2);
	    testQueue.enqueue(0);
	    testQueue.enqueue(2);
	    int queueElement1 =testQueue.dequeue();
	    assertNotNull(queueElement1); 
	    assertEquals(0,queueElement1);
	    testQueue.enqueue(3);
	    int queueElement2 = testQueue.dequeue();
	    assertNotNull(queueElement2); 
	    assertEquals(2,queueElement2);
	    
	}

}
