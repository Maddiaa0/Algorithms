import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  Dervla Brennan 17325863
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class lala
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    
    //tests isEmpty() method
    @Test
    public void testIsEmpty()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	boolean result;
    	//test empty list
    	result = testDLL.isEmpty();
    	assertTrue("Checking isEmpty returns true to an empty list", result);
    	
    	//test non-empty list
    	testDLL.insertBefore(0, 1);
    	result = testDLL.isEmpty();
    	assertFalse("Checking isEmpty returns false to a non-empty list", result);
    }
    
    //tests get() method
    @Test
    public void testGet()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	Integer expResult;
    	
    	//tests that get method returns null if empty list
    	assertNull(testDLL.get(0));
    	
    	//tests index 0 from list with only 1 node
    	testDLL.insertBefore(0, 1);
    	expResult = 1;
    	assertEquals("Check if get method returns correct value at index 0", expResult, testDLL.get(0));
    	
    	//tests index -2 returns null
    	assertNull("Check if get method returns null at non-existent index", testDLL.get(-2));
    	
    	//tests middle index from non-empty list
    	testDLL.insertBefore(0, 2);
    	testDLL.insertBefore(1, 3);
    	testDLL.insertBefore(3, 4);
    	expResult = 3;
    	assertEquals("Check if get method returns correct value at index 1", expResult, testDLL.get(1));
    	
    	//tests last index from non-empty list
    	testDLL.insertBefore(4,5);
    	expResult = 5;
    	assertEquals("Check if get method returns correct value at last index", expResult, testDLL.get(4));
    }
    
    //tests deleteAt() method
    @Test
    public void testDeleteAt()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	boolean result;
    	
    	//tests from empty list
    	result = testDLL.deleteAt(0);
    	assertFalse("Checks that false is returned from empty list", result);
    	
    	//tests list with only one node
    	testDLL.insertBefore(0, 1);
    	result = testDLL.deleteAt(0);
    	assertTrue("Checks that true is returned list with one node", result); 	
    	
    	//tests list with 1 node and non-existent index
    	testDLL.insertBefore(0, 1);
    	result = testDLL.deleteAt(-2);
    	assertFalse("Checks that false is returned for non-existent index", result);
    	
    	//tests last index on list with only two nodes
    	testDLL.insertBefore(1, 2);
    	result = testDLL.deleteAt(1);
    	assertTrue("Checks that true is returned from last index of list with only two nodes", result);
    	
    	//tests index 0 on list with multiple nodes
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 3);
    	testDLL.insertBefore(3, 4);
    	result = testDLL.deleteAt(0);
    	assertTrue("Checks that true is returned from first index of list with multiple nodes", result);
    	
    	//tests last index on list with multiple nodes
    	testDLL.insertBefore(0, 1);
    	result = testDLL.deleteAt(3);
    	assertTrue("Checks that true is returned from last index of list with multiple nodes", result);
    	
    	//tests middle of list with multiple nodes
    	result = testDLL.deleteAt(1);
    	assertTrue("Checks that true is returned from middle index of list with multiple nodes", result);
    	
    	//test if pos passed does not exist
    	result = testDLL.deleteAt(6);
    	assertFalse("Checks that false is returned from index that is not occupied", result);
    }
    
    
    //tests reverse() method
    @Test
    public void testReverse()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
    	testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.reverse();
        assertEquals("Checking if list '1,2,3' is reversed correctly", "3,2,1", testDLL.toString() );
    }
    
    //tests makeUnique() method
    @Test
    public void testMakeUnique()
    {
    	
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	//tests if null on empty list
    	assertEquals("Checking if list is empty", "", testDLL.toString());
    	
    	//tests if correct with 1 pair
    	testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,1);
        testDLL.insertBefore(4,5);
        
        testDLL.makeUnique();
        assertEquals("Checking if the elements of list '1,2,3,1,5' are made unique", "1,2,3,5", testDLL.toString()); 
    	
    	//tests if correct with 2 pairs
    	DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
    	
    	testDLL2.insertBefore(0,1);
    	testDLL2.insertBefore(1,2);
    	testDLL2.insertBefore(2,1);
    	testDLL2.insertBefore(3,2);
    	testDLL2.insertBefore(4,5);
        
    	testDLL2.makeUnique();
        assertEquals("Checking if the elements of list '1,2,1,2,5' are made unique", "1,2,5", testDLL2.toString());   
        
        //tests if correct with 1 triple
        DoublyLinkedList<Integer> testDLL3 = new DoublyLinkedList<Integer>();
    	
        testDLL3.insertBefore(0,6);
        testDLL3.insertBefore(1,2);
        testDLL3.insertBefore(2,1);
        testDLL3.insertBefore(3,4);
        testDLL3.insertBefore(4,1);
        
    	testDLL3.makeUnique();
        assertEquals("Checking if the elements of list '6,2,1,4,1' are made unique", "6,2,1,4", testDLL3.toString());   
        
        //tests if correct when all are unique
        DoublyLinkedList<Integer> testDLL4 = new DoublyLinkedList<Integer>();
    	
        testDLL4.insertBefore(0,1);
        testDLL4.insertBefore(1,2);
        testDLL4.insertBefore(2,3);
        testDLL4.insertBefore(3,4);
        testDLL4.insertBefore(4,5);
        
        testDLL4.makeUnique();
        assertEquals("Checking if the elements of list '1,2,3,4,5' are made unique", "1,2,3,4,5", testDLL4.toString());  
        
        //tests if correct when all are not unique 
        DoublyLinkedList<Integer> testDLL5 = new DoublyLinkedList<Integer>();
        
        testDLL5.insertBefore(0,2);
        testDLL5.insertBefore(1,2);
        testDLL5.insertBefore(2,2);
        testDLL5.insertBefore(3,1);
        testDLL5.insertBefore(4,1);
        testDLL5.insertBefore(5,2);
        testDLL5.insertBefore(6,1);
        
        testDLL5.makeUnique();
        assertEquals("Checking if the elements of list '2,2,2,1,1' are made unique", "2,1", testDLL5.toString());  
        
        //tests index is deleted if made unique
        DoublyLinkedList<Integer> testDLL6 = new DoublyLinkedList<Integer>();
        
        testDLL6.insertBefore(0, 1);
        testDLL6.insertBefore(1, 1);
        testDLL6.makeUnique();
        assertEquals("Checking elements of list '1,1'", "1", testDLL6.toString());
        boolean result = testDLL6.deleteAt(1);
        assertFalse("Checking index is deleted if made unique", result);
    }
    
    
    //tests push() method
    @Test
    public void testPush()
    {
    	//pushing to full list 
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);
    	testDLL.push(2);
    	testDLL.push(3);
    	
    	assertEquals("Checking push to a list containing 3 elements", "3,2,1", testDLL.toString());
    	
    	//pushing to an empty
    	DoublyLinkedList <Integer> testDLL2 = new DoublyLinkedList<Integer>();
    	
    }
    
    
    //tests pop() method
    @Test
    public void testPop()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
    	assertNull("Checking pop returns null if list is empty", testDLL.pop());
    	
    	testDLL.push(1);
    	testDLL.push(2);
    	testDLL.push(3);
    	
    	Integer expResult = 3;
    	assertEquals("Checking pop returns the data attribute of the head of list", expResult, testDLL.pop());	
    }
    
    
    //tests enqueue() method
    @Test
    public void testEnqueue()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
    	testDLL.enqueue(1);
    	testDLL.enqueue(2);
    	testDLL.enqueue(3);
    	
    	assertEquals("Checking enqueue to a list containing 3 elements", "3,2,1", testDLL.toString());
    }
    
    
    // tests dequeue() method
    @Test
    public void testDequeue()
    {
    	
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	//test dequeue from empty list
    	assertNull("Checking dequeue returns null if list is empty", testDLL.dequeue());
    	
    	//test dequeue from fully list
    	testDLL.enqueue(1);
    	testDLL.enqueue(2);
    	testDLL.enqueue(3);
    	
    	Integer expResult = 1;
    	assertEquals("Checking dequeue returns the data attribute of the tail of list", expResult, testDLL.dequeue());
    	
    }
    
   
    // tests toString() method
    @Test
    public void testToString()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 3);
    	testDLL.insertBefore(3,	4);
    	
    	assertEquals("Checking toString successfully converts list '1,2,3,4' to a string", "1,2,3,4", testDLL.toString());
    }
}s
