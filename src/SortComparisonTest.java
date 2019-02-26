import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

//import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *  
 *  
 *  @author Sean Cheetham 17338129
 *  @version HT 2019
 *  
 *	 *  *all results below in ms*
 *	                    |  insert  |   quick  |    merge iter |  merge recur |  selection
 *	10 random           |          |          |    		      |  	         |  
 *	100 random          |          |          |    		      |  		     |  
 *	1000 random         |          |		  |    		      |  		     |  
 *	1000 few unique     |          |   		  |    		      |  		     |  
 *	1000 nearly ordered |          |   		  |    		      | 		     |  
 *	1000 reverse order  |          |   		  |    		      |  		     |  
 *	1000 sorted         |          |   		  |     	      |   	 	     |  
 *  
 *  
 *  
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
	/*
	//~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
       new SortComparison();
    }
    */

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	
    	//all methods should return null...
    	//selection sort empty
    	double[] emptyArr = {};
    	assertEquals("Should return empty array", emptyArr,SortComparison.insertionSort(emptyArr));
    	
    	//merge sort iterative empty 
    	assertEquals("Should return empty array", emptyArr,SortComparison.mergeSortIterative(emptyArr));
    	
    	//merge sort recursive empty
    	assertEquals("Should return empty array", emptyArr,SortComparison.mergeSortRecursive(emptyArr));

    	//insertion sort empty
    	assertEquals("Should return empty array", emptyArr,SortComparison.insertionSort(emptyArr));
    	
    	//quick sort empty
    	assertEquals("Should return empty array", emptyArr,SortComparison.quickSort(emptyArr));
    	
    	
    }
    
    @Test
    public void testNull()
    {
    	//all methods should return null...
    	//selection sort null
    	double[] nullArr = null;
    	assertNull(SortComparison.insertionSort(nullArr));
    	
    	//merge sort iterative null
    	assertNull(SortComparison.mergeSortIterative(nullArr));
    	
    	//merge sort recursive null
    	assertNull(SortComparison.mergeSortRecursive(nullArr));

    	//insertion sort null
    	assertNull(SortComparison.insertionSort(nullArr));
    	
    	//quick sort null
    	assertNull(SortComparison.quickSort(nullArr));
    	
    }


    // TODO: add more tests here. Each line of code and each decision in Collinear.java should
    // be executed at least once from at least one test.
    @Test 
    public void testInsertionSort(){
    	//for a pre sorted array
    	double[] arrSorted = {1,2,3,4,5,6};
    	assertEquals("The array should remain the same as 1,2,3,4,5,6", arrSorted, SortComparison.insertionSort(arrSorted));
    	
    	
    	//for a random array
    	double[] arrRandom = {2,4,3,1,6,5};
    	double[] arrInsSort = SortComparison.insertionSort(arrRandom);
    	assertEquals("The array should be sorted to 1,2,3,4,5,6", "1.0, 2.0, 3.0, 4.0, 5.0, 6.0", SortComparison.arrToString(arrInsSort));
    	
    	double[] arrAlmostSorted = {1,2,3,4,6,5};
    	double[] sortedAlmostSort = SortComparison.insertionSort(arrAlmostSorted);
    	assertEquals("The array should be sorted to 1,2,3,4,5,6", "1.0, 2.0, 3.0, 4.0, 5.0, 6.0", SortComparison.arrToString(sortedAlmostSort));
    	
    	
    }
    
    /*
    @Test
    public void testQuickSort() {
    	//for a pre sorted array
    	double[] arrSorted = {1,2,3,4,5,6};
    	assertEquals("The array should remain the same as 1,2,3,4,5,6", arrSorted, SortComparison.quickSort(arrSorted));
    	
    	
    	//for a random array
    	double[] arrRandom = {2,4,3,1,6,5};
    	double[] arrInsSort = SortComparison.quickSort(arrRandom);
    	assertEquals("The array should be sorted to 1,2,3,4,5,6", "1.0, 2.0, 3.0, 4.0, 5.0, 6.0", SortComparison.arrToString(arrInsSort));
    	
    	//almost sorted array
    	double[] arrAlmostSorted = {1,2,3,4,6,5};
    	double[] sortedAlmostSort = SortComparison.quickSort(arrAlmostSorted);
    	assertEquals("The array should be sorted to 1,2,3,4,5,6", "1.0, 2.0, 3.0, 4.0, 5.0, 6.0", SortComparison.arrToString(sortedAlmostSort));
    	
    	
    }
    */
    
    @Test
    public void testMergeSortIterative() {
	}
    	
   
    
    @Test
    public void testMergeSortRecursive() {
    	
    }
    
    @Test
    public void testSelectionSort() {
    	
    }
    
    

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    { 	
        //TODO: implement this method
    	//repeated the steps below for each of the files and for each sort 
    	
    	
    	double[] tenNumbers = getFileContent(fileScanner("numbers/numbers10.txt"));
    	
    	double[] copy = tenNumbers;
    	
    	long totalTime = 0;
    	
    	//test one
    	long startTime = System.nanoTime();
    	SortComparison.selectionSort(copy);
    	long endTime = System.nanoTime();
    	totalTime += (endTime - startTime);
    	
    	//test two
    	copy = tenNumbers;
    	startTime = System.nanoTime();
    	SortComparison.selectionSort(copy);
    	endTime = System.nanoTime();
    	totalTime += (endTime - startTime);
    	
    	//test 3
    	copy = tenNumbers;
    	startTime = System.nanoTime();
    	SortComparison.selectionSort(copy);
    	endTime = System.nanoTime();
    	totalTime += (endTime - startTime);
    	
    	
    	long averageTime = totalTime / 3;
    	//divide by a million for milli secconds
    	System.out.print(averageTime );
    	
    	
    }
    
   
    
    //get the items from the file
    public static double[] getFileContent(Scanner fileScan) {
    	ArrayList<String> content = new ArrayList<String>();
    	
    	//while the scanner has content
    	while (fileScan.hasNext()) {
    		content.add(fileScan.next());
    	}
    	
    	double[] returnArray = new double[content.size()];
    	for (int i = 0; i < returnArray.length; i++) {
    		returnArray[i] = Double.parseDouble(content.get(i));
    	}
    	
    	return returnArray;
    }
    
    //method to open file
    public static Scanner fileScanner(String fileName) {
    	try {
    		//use a file scanner and file.io to return the file
    		Scanner fileScan = new Scanner(new File(fileName));
    		return fileScan;
    	}catch (Exception e) {
    		System.out.println("Cannot open file");
    		return null;
    	}
    }
    

}
