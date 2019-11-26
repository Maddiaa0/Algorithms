
// -------------------------------------------------------------------------
/**
 * This class contains only two static methods that search for points on the
 * same line in three arrays of integers.
 *
 * @author
 * @version 18/09/18 12:21:09
 */
class Collinear {

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in
	 * arrays a1, a2, a3. This method is static, thus it can be called as
	 * Collinear.countCollinear(a1,a2,a3)
	 * 
	 * @param a1:
	 *            An UNSORTED array of integers. Each integer a1[i] represents the
	 *            point (a1[i], 1) on the plain.
	 * @param a2:
	 *            An UNSORTED array of integers. Each integer a2[i] represents the
	 *            point (a2[i], 2) on the plain.
	 * @param a3:
	 *            An UNSORTED array of integers. Each integer a3[i] represents the
	 *            point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a
	 *         horizontal line.
	 *
	 *         Array a1, a2 and a3 contain points on the horizontal line y=1, y=2
	 *         and y=3, respectively. A non-horizontal line will have to cross all
	 *         three of these lines. Thus we are looking for 3 points, each in a1,
	 *         a2, a3 which lie on the same line.
	 *
	 *         Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they
	 *         are on the same line) if
	 * 
	 *         x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0
	 *
	 *         In our case y1=1, y2=2, y3=3.
	 *
	 *         You should implement this using a BRUTE FORCE approach (check all
	 *         possible combinations of numbers from a1, a2, a3)
	 *
	 *         ----------------------------------------------------------
	 *
	 *         Order of Growth -------------------------
	 *
	 *         Caclulate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of growth: The most relevant order of growth for this algorithm is N^3
	 *
	 *         Explanation: Each time that the system goes through a loop the order of growth for the algorithm
	 *         is multiplied by N, as 3 for loops exist within the system there is an order of growth for the system 
	 *         of N^3
	 */
	static int countCollinear(int[] a1, int[] a2, int[] a3) {

		int totalCollinear = 0;
		// TODO: implement this method
		for (int count1 = 0; (count1 < a1.length); count1++) {
			for (int count2 = 0; (count2 < a2.length); count2++) {
				{
					for (int count3 = 0; (count3 < a3.length); count3++) {
						// colinnear check formula
						if (a1[count1] * (2 - 3) + a2[count2] * (3 - 1) + a3[count3] * (1 - 2) == 0) {
							totalCollinear++;
						}
					}
				}
			}
		}
		return totalCollinear;
	}

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in
	 * arrays a1, a2, a3. This method is static, thus it can be called as
	 * Collinear.countCollinearFast(a1,a2,a3)
	 * 
	 * @param a1:
	 *            An UNSORTED array of integers. Each integer a1[i] represents the
	 *            point (a1[i], 1) on the plain.
	 * @param a2:
	 *            An UNSORTED array of integers. Each integer a2[i] represents the
	 *            point (a2[i], 2) on the plain.
	 * @param a3:
	 *            An UNSORTED array of integers. Each integer a3[i] represents the
	 *            point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a
	 *         horizontal line.
	 *
	 *         In this implementation you should make non-trivial use of
	 *         InsertionSort and Binary Search. The performance of this method
	 *         should be much better than that of the above method.
	 *
	 *
	 *         Order of Growth -------------------------
	 *
	 *         Caclulate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of Growth: N^2 
	 *
	 *         Explanation: As the largest order of growth for the program will be N^2 the overall 
	 *         order of growth for the algorithm will be N^2. Due to the removal of the for loop seen
	 *         within the brute force method.
	 * 
	 *
	 *
	 *
	 *
	 *         find the collinear that would be required to make the points match
	 *         for two then search the third array using binary search to see if
	 *         that point exists, rather than to iterate through all of arrays each
	 *         time
	 *
	 */
	static int countCollinearFast(int[] a1, int[] a2, int[] a3) {
		// TODO: implement this method
		int collinearsFound = 0;
		int numberRequired = 0;
		Collinear.sort(a3);
		for (int count1 = 0; (count1 < a1.length); count1++) {
			for (int count2 = 0; (count2 < a2.length); count2++) {
				// rearrange formula to find the point required, then use binary search to find
				// x3 = (x1(y2-y3) + x2(y3-y1))/(y1-y2)
				numberRequired = ((a1[count1] * (2 - 3) + a2[count2] * (3 - 1)) / (1 - 2));

				// find the number required
				if (binarySearch(a3, a3.length, numberRequired) == true) {
					collinearsFound++;
				}
			}
		}
		return collinearsFound;
	}

	// ----------------------------------------------------------
	/**
	 * Sorts an array of integers according to InsertionSort. This method is static,
	 * thus it can be called as Collinear.sort(a)
	 * 
	 * @param a:
	 *            An UNSORTED array of integers.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 *
	 *         ----------------------------------------------------------
	 *
	 *         Order of Growth -------------------------
	 *
	 *         Caclulate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of Growth: N^2
	 *
	 *         Explanation: Two linear for-loops.
	 *
	 */
	static void sort(int[] a) {
		for (int j = 1; j < a.length; j++) {
			int i = j - 1;
			while (i >= 0 && a[i] > a[i + 1]) {
				int temp = a[i];
				a[i] = a[i + 1];
				a[i + 1] = temp;
				i--;
			}
		}
	}

	// ----------------------------------------------------------
	/**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     * @param a: A array of integers SORTED in ascending order.
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     *
     * ----------------------------------------------------------
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: log2(N)
     *
     *  Explanation: As the size of the list will decrease by half each time half of the list is removed 
     *  from the search, the order of growth will exist as log2 (e.g. if there are 64 items it will go
     *  64, 32, 16, 8, 4, 2, 1 until the item is found.
     *
     */
    static boolean binarySearch(int[] a, int length, int x)
    {
      //TODO: implement this method
    	
    	//classic binary search algorithmn
    	//check for array to be null first
    	if(a == null) {
    		return false;
    	} else if (a.length == 0) {
    		return false;
    	}
    	
	    int lowerBound = 0;
	    int upperBound = length - 1;
	    while (lowerBound <= upperBound) {
	        int middle = Math.floorDiv(lowerBound + upperBound, 2);
    	    if (a[middle] == x) {
    	    	return true;
    	    }else if (a[middle] < x) {
    	        lowerBound = middle + 1;
    	    } else if (a[middle] > x) {
    	    	upperBound = middle - 1;
    	    }
	    }
	    return false;
    }

}