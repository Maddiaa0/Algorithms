//import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
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
 *	times are in ms
 *	                    |  insert  |   quick  |   merge i |  merge r |  selection
 *	10 random           |  0.1863  |   0.1830 |   0.1878  |  0.1772  |  0.1737
 *	100 random          |  0.2134  |   0.2572 |   0.2574  |  0.2486  |  0.2526
 *	1000 random         |  2.2462  |   3.2518 |   1.2849  |  1.1571  |  5.0088
 *	1000 few unique     |  1.846   |   1.6884 |   1.1346  |  1.3160  |  3.7028
 *	1000 nearly ordered |  0.7060  |   2.4731 |   1.2431  |  1.1499	 |  4.9844
 *	1000 reverse order  |  3.2952  |   5.4625 |   1.1944  |  1.1299  |  4.9283
 *	1000 sorted         |  0.4474  |   4.2910 |   1.1150  |  1.3466  |  4.8648
 *  
 *  a. Which of the sorting algorithms does the order of input have an impact on? Why?
 *  	
 *  	Selection sort is most impacted by the order of impact due to the algorithmn needing to 
 *  	searching for the smallest min in the list consistently each time it passes. Leading to
 *  	an increasing amount of comparisons as the order increases 
 *  
 *  
 *  b. Which algorithm has the biggest difference between the best and worst performance, based
 *	   on the type of input, for the input of size 1000? Why?
 *		
 *		Insertion sort has a Largest difference in time taken as when the data is sorted nearly or is sorted 
 *		it takes O(n), but when the data is in reverse order it takes O(n^2) as the orst case.
 *	
 *
 *	c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
 *     based on the input size? Please consider only input files with random order for this answer.
 *		
 *		The best scalability comes from the merge sort algorithmns, due to their logarithmic growing time. While selection
 *		sort has the worst scalability due to its multiple swaps.
 *
 *
 *	d. Did you observe any difference between iterative and recursive implementations of merge
 *	   sort?
 *		
 *		The recursive implementation for mergesort has more consistent results than the iterative implementation
 *		but they appear to perform very similarly 
 *	
 *
 *	e. Which algorithm is the fastest for each of the 7 input files? 
 *		10 random			- selection sort
 *		100 random			- insertion sort
 *		1000 random			- merge sort recursive
 *		1000 few unique		- merge sort iterative
 *		1000 nearly ordered	- insertion sort
 *		1000 reverse order	- merge sort recursive
 *		1000 sorted			- insertion sort
 *  
 *  
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
	
	 /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
	/*
    public static void main(String[] args)
    { 	
        //TODO: implement this method
    	//repeated the steps below for each of the files and for each sort 
    	
    	
    	double[] tenNumbers = getFileContent(fileScanner("numbers/numbersNearlyOrdered1000.txt"));
    	
    	double[] copy = tenNumbers;
    	
    	long totalTime = 0;
    	
    	//test one
    	long startTime = System.nanoTime();
    	SortComparison.mergeSortIterative(copy);
    	long endTime = System.nanoTime();
    	totalTime += (endTime - startTime);
    	
    	//test two
    	copy = tenNumbers;
    	startTime = System.nanoTime();
    	SortComparison.mergeSortIterative(copy);
    	endTime = System.nanoTime();
    	totalTime += (endTime - startTime);
    	
    	//test 3
    	copy = tenNumbers;
    	startTime = System.nanoTime();
    	SortComparison.mergeSortIterative(copy);
    	endTime = System.nanoTime();
    	totalTime += (endTime - startTime);
    	
    	
    	long averageTime = totalTime / 3;
    	//divide by a million for milli secconds
    	System.out.print(averageTime );
    	
    	
    }
    */
	
	
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
    	
    	//selection sort Empty
    	assertEquals("Should return empty array", emptyArr,SortComparison.selectionSort(emptyArr));
    	
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
    	
    	//selection sort null
    	assertNull(SortComparison.selectionSort(nullArr));
    	
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
    
    
    @Test
    public void testMergeSortIterative() {
    	//for a pre sorted array
    	double[] arrSorted = {1,2,3,4,5,6};
    	assertEquals("The array should remain the same as 1,2,3,4,5,6", arrSorted, SortComparison.mergeSortIterative(arrSorted));
    	
    	
    	//for a random array
    	double[] arrRandom = {2,4,3,1,6,5};
    	double[] arrInsSort = SortComparison.mergeSortIterative(arrRandom);
    	assertEquals("The array should be sorted to 1,2,3,4,5,6", "1.0, 2.0, 3.0, 4.0, 5.0, 6.0", SortComparison.arrToString(arrInsSort));
    	
    	//almost sorted array
    	double[] arrAlmostSorted = {1,2,3,4,6,5};
    	double[] sortedAlmostSort = SortComparison.mergeSortIterative(arrAlmostSorted);
    	assertEquals("The array should be sorted to 1,2,3,4,5,6", "1.0, 2.0, 3.0, 4.0, 5.0, 6.0", SortComparison.arrToString(sortedAlmostSort));
    	
	}
    	
   
    
    @Test
    public void testMergeSortRecursive() {
    	//for a pre sorted array
    	double[] arrSorted = {1,2,3,4,5,6};
    	assertEquals("The array should remain the same as 1,2,3,4,5,6", arrSorted, SortComparison.mergeSortRecursive(arrSorted));
    	
    	
    	//for a random array
    	double[] arrRandom = {2,4,3,1,6,5};
    	double[] arrInsSort = SortComparison.mergeSortRecursive(arrRandom);
    	assertEquals("The array should be sorted to 1,2,3,4,5,6", "1.0, 2.0, 3.0, 4.0, 5.0, 6.0", SortComparison.arrToString(arrInsSort));
    	
    	//almost sorted array
    	double[] arrAlmostSorted = {1,2,3,4,6,5};
    	double[] sortedAlmostSort = SortComparison.mergeSortRecursive(arrAlmostSorted);
    	assertEquals("The array should be sorted to 1,2,3,4,5,6", "1.0, 2.0, 3.0, 4.0, 5.0, 6.0", SortComparison.arrToString(sortedAlmostSort));
    	
    }
    
    @Test
    public void testSelectionSort() {
    	//for a pre sorted array
    	double[] arrSorted = {1,2,3,4,5,6};
    	assertEquals("The array should remain the same as 1,2,3,4,5,6", arrSorted, SortComparison.selectionSort(arrSorted));
    	
    	
    	//for a random array
    	double[] arrRandom = {2,4,3,1,6,5};
    	double[] arrInsSort = SortComparison.selectionSort(arrRandom);
    	assertEquals("The array should be sorted to 1,2,3,4,5,6", "1.0, 2.0, 3.0, 4.0, 5.0, 6.0", SortComparison.arrToString(arrInsSort));
    	
    	//almost sorted array
    	double[] arrAlmostSorted = {1,2,3,4,6,5};
    	double[] sortedAlmostSort = SortComparison.selectionSort(arrAlmostSorted);
    	assertEquals("The array should be sorted to 1,2,3,4,5,6", "1.0, 2.0, 3.0, 4.0, 5.0, 6.0", SortComparison.arrToString(sortedAlmostSort));
    	
    }
    
    

    // ----------------------------------------------------------
   
    
   
   /* 
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
    */
    

}
