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
    	if (a == null) {
    		return null;
    	}
    	
        int length = a.length;
        double[] aux = new double[length];
        for (int i = 1; i < length; i = i + i) {
            for (int low = 0; low < length - i; low += i + i) {
                merge(a, aux, low, low+i-1, Math.min(low+i+i-1,length-1));
            }
        }
        return a;

    }//end mergeSortIterative



    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {
    	if (a == null) {
    		return null;
    	}
    	
        double[] aux = new double[a.length];
        sort(a, aux, 0, a.length-1);
        return a;

    }

    private static void sort(double[] a, double[] aux, int low, int high) {
        if (high <= low) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid+1, high);
        merge(a, aux, low, mid, high);
    }

    private static void merge(double[] a, double[] aux, int low, int mid, int high) {
        
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        
        int i = low, j = mid+1;
        for (int k = low; k <= high; k++) {
            if      (i > mid) {
            	a[k] = aux[j++];
            }
            else if (j > high) {
            	a[k] = aux[i++];
            }
            else if (aux[j] < aux[i]) {
            	a[k] = aux[j++];
            }
            else {
            	a[k] = aux[i++];
            }
        }
    }
    //end mergeSortRecursive

    
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
 }//end class
