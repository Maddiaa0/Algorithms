import static org.junit.jupiter.api.Assertions.assertArrayEquals;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Sean Cheetham 17338129
 *  @version HT 2019
 */

 class SortComparison {
	

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){

        //todo: implement the sort
    	
    	if (a == null) {
    		return null;
    	}
    	
    	//uses the current min implementation 
    	int arraySize = a.length;
    	
    	double[] tempArr = a;
    	
    	for (int count = 0; count < arraySize ; count++) {
    		
    		double current = tempArr[count];
    		int count2 = count - 1;
    		
    		//shift items larger than the current the space after their current position
    		
    		while (count2 >= 0 && (tempArr[count2] > current)) {
    			//move the second item back
    			tempArr[count2 + 1] = tempArr[count2];
    			count2--;
    			
    		}
    		//replace with the current
    		tempArr[count2+ 1] = current;
    		
    	}
		return tempArr;
    	
    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
    	
    	//make both a partition method and a sort method, in this version of the algorithm, use the last element in the 
    	//array as the median to create the pivot to work from 
    	
		 //todo: implement the sort
    	
    	if (a == null) {
    		return null;
    	}
    	
    	//get the starting and ending indexes
    	int low = 0;
    	int high = a.length -1;
    	quickSort(a, low, high);
    	
    	return a;

    }//end quicksort
    
    private static double[] quickSort(double a[], int low, int high) {
    	
    	if (low < high) {
    		
    		int thisPartition = partition(a, low, high);
    		
    		
    		quickSort(a, low, thisPartition - 1);
    		quickSort(a, thisPartition + 1, high);
    	}
    	
    	return a;	
    }
    
    //partition algorithm
    //moves items smaller than the pivot of the partition to the left and items larger to the right
    private static int partition(double a[], int low, int high) {
    	
    	int i = low;;
		int j = high+1;
		double pivot = a[low];
		
		while(true) {
			while(a[++i]< pivot) { 
				if (i == high) {
					break;
				}
			}
			
			while(pivot<a[--j]) { 
				if(j == low) {
					break;
				}
			}
			
			if(i >= j) {
				break;
			}
			
			double temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		
		a[low] = a[j];
		a[j] = pivot;
		
		return j;
    }
    

    
    
    
    
    
    
    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) {

		 //todo: implement the sort
	 
	    //check null	
		if(a == null) {
			return null;
		}
				
		double [] aux = new double[a.length];
		
		int N = a.length;
		
		for(int currSize = 1; currSize < N ; currSize = 2 * currSize) {
			
			for(int low = 0; low < N - currSize; low += 2 * currSize) {
				
				merge(a, aux, low , low + currSize-1 , Math.min(low + 2 * currSize - 1, N-1));
			
			}
		}

		//return the sorted array
		return a;
    }//end mergesortIterative
    
        
    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {
    	//TODO: implement the sort
    	if (a == null) {
    		return null;
    	}
    	
    	double[] aux = new double[a.length];
    	mergeSortRecursive(a, aux, 0, a.length - 1);
    	
    	//return the sorted array
    	return a;
	
   }//end mergeSortRecursive
    
    private static void mergeSortRecursive(double a[], double aux[], int start, int end) {
    	
    	//done
    	if (end <= start) {
    		return;
    	}
    	
    	//recursively call the splitting algorithm
    	int mid = start + (end - start) / 2;
    	mergeSortRecursive(a,aux,start,mid);
    	mergeSortRecursive(a,aux,mid+1,end);
    	
    	//join the lists
    	merge(a,aux,start,mid,end);
    	
    }
   
    
    //merge algorithm
    private static void merge(double a[], double aux[], int low, int mid, int high) {
    	
    	for (int k = low; k <= high; k++) {
    		aux[k] = a[k];
    	}
    	
    	int i = low;
    	int j = mid +1;
    	
    	for (int k = low; k <= high; k++) {
    		
    		if (i > mid) {
    			a[k] = aux[j++];
    		}
    		else if (j > high) {
    			a[k] = aux[i++];
    		}
    		else if (aux[j] < aux[i]) {
    			a[k] = aux[j++];
    		} 
    		else {
    			a[k] = a[i++];
    		}
    	}
    	
    }
    
    
    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort(double a[]){

        //todo: implement the sort
    	//moves through the entire array performing a series of swaps on data in the array
    	
    	if (a == null) {
    		return null;
    	}
    	
    	int arraySize = a.length;
    	
    	//compare each item, looking for the next smallest after
    	for(int count = 0; count < arraySize -1; count++) {
    	 	
       	 //find min elem unsorted
       	 	int minIndex = count;
       	 	for(int count2 = count+1; count2<arraySize; count2++) {
       	 		if(a[count2]<a[minIndex]) {
       	 			minIndex = count2;
       	 		}
       	 	}
       	 	
    			
    		//swap the found minimum with the first count
    		double temp = a[minIndex];
    		a[minIndex] = a[count];
    		a[count] = temp;
    			
    	}
		return a;
    		
    	
    	    	
    }//end selectionsort
    
    
    
    
    
    
    
    
    //make results comparable
    public static String arrToString(double[] a) {
    	String returnString = "";
    	for (int i = 0; i < a.length - 1; i++) {
    		returnString += a[i] + ", ";
    	}
    	returnString += a[a.length-1];
    	
    	return returnString;
    }


    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
   	 double[] testArr = {5,6,43,1,2,3,43};
	 
	 double[] newArr = insertionSort(testArr);
	 
	 for (int i = 0; i < newArr.length; i++) {
		 System.out.println(newArr[i]);
	 }
	 
	 double[] testArr2 = {5,6,43,1,2,3,43};
	 double[] newArr2 = selectionSort(testArr2);
	 System.out.println();
	 
	 
	 double[] testArr3 = {5,6,43,1,2,3,43};
	 for (int i = 0; i < newArr2.length; i++) {
		 System.out.println(newArr2[i]);
	 }
	 
	
	 double[] newArr3 = quickSort(testArr3);
	 System.out.println();
	 
	 for (int i = 0; i < newArr3.length; i++) {
		 System.out.println(newArr3[i]);
	 }
	 
	 double[] testArr4 = {5,6,43,1,2,3,43};
	 double[] newArr4 = mergeSortIterative(testArr4);
	 System.out.println();
	 
	 for (int i = 0; i < newArr4.length; i++) {
		 System.out.println(newArr4[i]);
	 }
	 
	 double[] testArr5 = {5,6,43,1,2,3,43};
	 double[] newArr5 = mergeSortRecursive(testArr5);
	 System.out.println();
	 
	 for (int i = 0; i < newArr5.length; i++) {
		 System.out.println(newArr5[i]);
	 }
	 
	 
	 
	 
	 
   }    
 }//end class
