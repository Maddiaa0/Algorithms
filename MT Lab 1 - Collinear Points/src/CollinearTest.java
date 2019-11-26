import org.junit.runner.RunWith;
import org.junit.runners.JUnit4; 
import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;


//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 27/09/18 09:32:35
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ....................................................

    // ----------------------------------------------------------
    /**
     * Check the method can handle an empty array
     */
    
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check in a 1 element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check in a 1 element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }
    
    // TODO: add more tests here. Each line of code and each decision in Collinear.java should
    // be executed at least once from at least one test.

    
    /** 
     * ===============TEST-SORT=================
     */

   
    @Test
    public void testIFThereArenMoreThanOne()
    {
    	//give the sort method an unsorted array and test if it returns a sorted array
    	int [] a = {10,6,9,12,34,11,4,3};
    	int [] expectedResult = {3,4,6,9,10,11,12,34};
    	
     	Collinear.sort(a);
    	
    	assertArrayEquals(expectedResult, a);	
    }
    
    
    @Test
    //tests if it is already sorted
    public void testIfSorted()
    {
    	int [] a = {1, 2, 3, 4, 5};
    	int [] expectedResult = {1, 2, 3, 4, 5};
    	
    	Collinear.sort(a);
    	
    	assertArrayEquals(expectedResult, a);
    }
    
    @Test
    public void testSingleValueSort()
    {
    	//test if the sorting algorithm can handle a single item array
    	int[] a = {1};
    	int[] expectedResult = {1};
    	
    	Collinear.sort(a);
    	
    	assertArrayEquals(expectedResult, a);
    	
    }
    
    public void testEmptyArray()
    {
    	//test if the sorting algorithm can handle an empty array
    	int[] a = {};
    	int[] expectedResult = {};
    	
    	Collinear.sort(a);
    	
    	assertArrayEquals(expectedResult, a);
    }
    
    
    /** 
     * ===============TEST-BINARY=================
     */
  
    
    @Test
    public void testIfBinary()
    {
    	int [] a = {1, 2, 3, 4, 5};
    	int x = 1; 
    	
    	assertTrue(Collinear.binarySearch(a, a.length, x));
    }
    
    @Test
    public void testIfBinaryFalsePositive()
    {
    	int [] a = {1, 2, 3, 4, 5};
    	int x = 6; 
    	
    	assertFalse(Collinear.binarySearch(a,a.length, x));
    }
    
    @Test
    public void testIfBinaryWhenArrayIsEven()
    {
    	int [] a = {1, 2, 3, 4};
    	int x = 1; 
    	
    	assertTrue(Collinear.binarySearch(a, a.length, x));
    }
    
    @Test
    public void testIfBinaryWhenArrayIsOdd()
    {
    	int [] a = {1, 2, 3, 4, 5};
    	int x = 3; 
    	
    	assertTrue(Collinear.binarySearch(a, a.length, x));
    }
    
    @Test
    public void testBinaryLowerLimit()
    {
    	int [] a = {1, 2, 3, 4, 5};
    	int x = 1; 
    	
    	assertTrue(Collinear.binarySearch(a, a.length, x));
    }
    
    @Test
    public void testBinaryUpper()
    {
    	int [] a = {1, 2, 3, 4, 5};
    	int x = 5; 
    	
    	assertTrue(Collinear.binarySearch(a, a.length, x));
    }
      
}