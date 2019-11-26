import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

// -------------------------------------------------------------------------
/**
 * Test class for Doubly Linked List
 *
 * @author Sean Cheetham
 * @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest {
	
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new DoublyLinkedList<Integer>();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	

	// TODO: add more tests here. Each line of code in DoublyLinkedList.java should
	// be executed at least once from at least one test.

	@Test
	public void testIsEmpty() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		//test for an empty list
		assertEquals("Checking if isEmpty proves true for an empty list", true, testDLL.isEmpty());
		
		//insert some data then test
		testDLL.insertBefore(0, 5);
		testDLL.insertBefore(0, 6);
		testDLL.insertBefore(0, 13);
		testDLL.insertBefore(0, 16);
		assertEquals("Checking if isEmpty returns false to a not empty list", false, testDLL.isEmpty());
		
	}

	/**
	 * Check if the insertBefore works
	 */
	@Test
	public void testInsertBefore() {
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);
		
		
		testDLL.insertBefore(0, 4);
		assertEquals("Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3",
				testDLL.toString());
		
		testDLL.insertBefore(1, 5);
		assertEquals("Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3",
				testDLL.toString());
		
		testDLL.insertBefore(2, 6);
		assertEquals("Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3",
				testDLL.toString());
		
		testDLL.insertBefore(-1, 7);
		assertEquals(
				"Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list",
				"7,4,5,6,1,2,3", testDLL.toString());
		
		testDLL.insertBefore(7, 8);
		assertEquals(
				"Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list",
				"7,4,5,6,1,2,3,8", testDLL.toString());
		
		testDLL.insertBefore(700, 9);
		assertEquals(
				"Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list",
				"7,4,5,6,1,2,3,8,9", testDLL.toString());

		
		// test empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position 0 - expected the element at the head of the list",
				"1", testDLL.toString());
		
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(10, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position 10 - expected the element at the head of the list",
				"1", testDLL.toString());
		
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(-10, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position -10 - expected the element at the head of the list",
				"1", testDLL.toString());
				
	}

	@Test
	public void testGet() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		//test getting from an empty list, should return null
		assertNull(testDLL.get(1));
		
		//insert data into the list
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(0, 2);
		testDLL.insertBefore(0, 3);
		testDLL.insertBefore(0, 4);
		testDLL.insertBefore(0, 5);
		
		//test getting from the front of a list
		assertTrue(5 == testDLL.get(0));
		
		//test getting from the end of a list
		assertTrue(1 == testDLL.get(4));
		
		//test getting from a negative number
		assertNull(testDLL.get(-4));
		
		//test getting from outside the list +ve
		assertNull(testDLL.get(12));
		
		//test getting a number from the middle of the list
		assertTrue(3 == testDLL.get(2));
	}

	@Test
	public void testDeleteAt() {		
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		//test deleting from an empty list
		assertFalse(testDLL.deleteAt(1));
		
		
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(0, 2);
		testDLL.insertBefore(0, 3);
		testDLL.insertBefore(0, 4);
		testDLL.insertBefore(0, 5);
			
		//test for deleting at the end
		assertTrue(testDLL.deleteAt(4));
		assertEquals("Make sure the item was removed from the end of the list", "5,4,3,2", testDLL.toString());
		
		//test deleting from the front of a list
		assertTrue(testDLL.deleteAt(0));
		assertEquals("Make sure the item was removed from the front of the list", "4,3,2", testDLL.toString());
		
		//test deleting from the middle of a list
		assertTrue(testDLL.deleteAt(1));
		assertEquals("Make sure the item was removed from the third position of the list", "4,2", testDLL.toString());		
	}

	@Test
	public void testReverse() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		//what happens when reversing an empty list;
		testDLL.reverse();
		assertEquals("Test the system handles a null reversed list", "", testDLL.toString());
		
		
		//create a 10 long list
		//then reverse and test
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(0, 2);
		testDLL.insertBefore(0, 3);
		testDLL.insertBefore(0, 4);
		testDLL.insertBefore(0, 5);
		testDLL.insertBefore(0, 6);
		testDLL.insertBefore(0, 7);
		testDLL.insertBefore(0, 8);
		testDLL.insertBefore(0, 9);
		testDLL.insertBefore(0, 10);
		
		assertEquals("The list is in normal shape", "10,9,8,7,6,5,4,3,2,1", testDLL.toString());
		
		//reverse the list
		testDLL.reverse();
		assertEquals("Test the list is reversed", "1,2,3,4,5,6,7,8,9,10", testDLL.toString());
		
	}

	@Test
	public void testMakeUnique() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		//test when making an empty list null
		//what happens when reversing an empty list;
		testDLL.makeUnique();
		assertEquals("Test the system handles a null unique list", "", testDLL.toString());
				
		//fill the list with lots of numbers
		testDLL.insertBefore(0, 5);
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(0, 3);
		testDLL.insertBefore(0, 5);
		testDLL.insertBefore(0, 6);
		testDLL.insertBefore(0, 5);
		testDLL.insertBefore(0, 5);
		testDLL.insertBefore(0, 6);
		testDLL.insertBefore(0, 7);
		testDLL.insertBefore(0, 7);
		testDLL.insertBefore(0, 1);
		
		//make sure the items are in the list as shown
		assertEquals("In order ", "1,7,7,6,5,5,6,5,3,1,5", testDLL.toString());
		
		testDLL.makeUnique();
		assertEquals("Unique in order", "1,7,6,5,3", testDLL.toString());
		
		DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
		
		testDLL2.insertBefore(0, 2);
		testDLL2.insertBefore(1, 2);
		testDLL2.insertBefore(2, 2);
		testDLL2.insertBefore(3, 1);
		testDLL2.insertBefore(4, 1);
		testDLL2.insertBefore(5, 1);
		testDLL2.insertBefore(6, 1);
		
		testDLL2.makeUnique();
		assertEquals("Check in reduced to 2,1", "2,1", testDLL2.toString());
		
		
	}

	@Test
	public void testPush() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		//test for empty Stack
		testDLL.push(1);
		assertEquals("Print stack and test ", "1", testDLL.toString());
		
		
		testDLL.push(2);
		testDLL.push(3);
		testDLL.push(4);
		//test for Stack with items already in it
		assertEquals("Print stack", "4,3,2,1", testDLL.toString());
		
	}

	@Test
	public void testPop() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		//test popping from an empty list
		assertNull(testDLL.pop());
		
		//test popping from a full list
		testDLL.push(1);
		testDLL.push(2);
		testDLL.push(3);
		testDLL.push(4);
		
		assertTrue(4 == testDLL.pop());
		assertTrue(3 == testDLL.pop());
		assertTrue(2 == testDLL.pop());
		assertTrue(1 == testDLL.pop());
		
		//test shows empty again
		assertNull(testDLL.pop());
		
	}

	@Test
	public void testEnqueue() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		//enqueue when empty 
		testDLL.enqueue(1);
		assertEquals("Print queue and test ", "1", testDLL.toString());
		
		//enqueue when full
		testDLL.enqueue(2);
		testDLL.enqueue(3);
		testDLL.enqueue(4);
		assertEquals("Print queue and test ", "4,3,2,1", testDLL.toString());
		
	}

	@Test
	public void testDequeue() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		//test dequeuing from an empty list
		assertNull(testDLL.dequeue());
		
		
		//test dequeuing from a full list
		testDLL.enqueue(1);
		testDLL.enqueue(2);
		testDLL.enqueue(3);
		testDLL.enqueue(4);
		
		assertTrue(1 == testDLL.dequeue());
		assertTrue(2 == testDLL.dequeue());
		assertTrue(3 == testDLL.dequeue());
		assertTrue(4 == testDLL.dequeue());
		
		//test shows empty again
		assertNull(testDLL.dequeue());
		
	}

}
