/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author Sean Cheetham  
 *  @version 09/10/18 11:13:22
 */

/**
* Class DoublyLinkedList: implements a *generic* Doubly Linked List.
* @param <T> This is a type parameter. T is used as a class name in the
* definition of this class.
*
* When creating a new DoublyLinkedList, T should be instantiated with an
* actual class name that extends the class Comparable.
* Such classes include String and Integer.
*
* For example to create a new DoublyLinkedList class containing String data:
*    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
*
* The class offers a toString() method which returns a comma-separated sting of
* all elements in the data structure.
*
* This is a bare minimum class you would need to completely implement.
* You can add additional methods to support your code. Each method will need
* to be tested by your jUnit tests -- for simplicity in jUnit testing
* introduce only public methods.
*/

import static org.junit.Assert.assertTrue;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T extends Comparable<T>> {

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor of an empty DLL
	 * 
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() {
		// set the head and tail values to null to create an empty list
		this.head = null;
		this.tail = null;
	}

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode {
		public final T data;
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * 
		 * @param theData
		 *            : data of type T, to be stored in the node
		 * @param prevNode
		 *            : the previous Node in the Doubly Linked List
		 * @param nextNode
		 *            : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}
	
	public static void main(String[] args) {
		DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
		
		testDLL2.insertBefore(0, 2);
		testDLL2.insertBefore(1, 2);
		testDLL2.insertBefore(2, 2);
		testDLL2.insertBefore(3, 1);
		testDLL2.insertBefore(4, 1);
		testDLL2.insertBefore(5, 1);
		testDLL2.insertBefore(6, 1);
		
		testDLL2.makeUnique();
	}
	
	
	

	// isEmpty (works)
	// create the isEmpty subroutine
	// in theory if the list is empty the head and the tail should point to each
	// other?
	/**
	 * Tests if the doubly linked list is empty
	 * 
	 * @return true if list is empty, and false otherwise
	 *
	 *         Worst-case asymptotic running time cost: O(1)
	 *
	 *         Justification: The subroutine consists of one comparison
	 *         and so has a linear path, so the subroutine has a constant 
	 *         time complexity
	 */
	public boolean isEmpty() {
		// return false if both the head and tail are null
		if (this.head == null && this.tail == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Inserts an element in the doubly linked list
	 * 
	 * @param pos
	 *            : The integer location at which the new data should be inserted in
	 *            the list. We assume that the first position in the list is 0
	 *            (zero). If pos is less than 0 then add to the head of the list. If
	 *            pos is greater or equal to the size of the list then add the
	 *            element at the end of the list.
	 * @param data
	 *            : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 *         Worst-case asymptotic running time cost: O(n)
	 *
	 *         Justification: the subroutine consists of one while loop, 
	 *         which will take as long to loop through as the place in the array
	 *         in which the data is being entered into
	 */
	public boolean insertBefore(int pos, T data) {

		// check array is not empty
		if (this.isEmpty()) {
			// if the list is empty make the data at the head of the list
			this.head = new DLLNode(data, null, null);
			this.tail = this.head;
			// exit with success
			return true;

		} else if (pos <= 0) {
			// create a new head and point to the previous head
			DLLNode temp = this.head;
			this.head = new DLLNode(data, null, temp);
			temp.prev = this.head;
			return true;

		} else if (pos > 0) {

			DLLNode current = head;
			int counter = 0;

			
			while (current.next != null && counter < (pos-1)) {
				// iterate through the items in the list
				// until the index is found or the end of the list is reached
				current = current.next;
				counter++;
			}

			
			// add to tail if at the end, or add before if counter was within pos
			if (current.next == null || counter >= pos) {
				// add to the tail
				DLLNode temp = new DLLNode(data, current, null);
				current.next = temp;
				this.tail = temp;
				return true;
				
			} 
			else if (current.next != null && current.prev != null) {
				DLLNode temp = new DLLNode(data, current, current.next);
				temp.prev.next = temp;
				temp.next.prev = temp;
				return true;
			} 
			else if (current.prev == null) {
				//if inserting to the first item
				DLLNode temp = new DLLNode(data, current, current.next);
				temp.prev.next = temp;
				temp.next.prev = temp;
				return true;
			}

		}

		// subroutine fails
		return false;

	}

	/**
	 * Returns the data stored at a particular position
	 * 
	 * @param pos
	 *            : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null
	 *         otherwise.
	 *
	 *         Worst-case asymptotic running time cost: O(n)
	 *
	 *         Justification: The running time is dependent on the 
	 *         location of the data being found in the List, making
	 *         its time complexity N
	 *
	 */
	public T get(int pos) {
		if (pos < 0) {
			return null;
		}
		
		// make sure the number exists within the list
		if (this.head != null && this.tail != null) {
			if (pos == 0) {
				return this.head.data;
			}
			DLLNode current = this.head;
			int counter = 0;
			while (current.next != null && counter < pos) {
				current = current.next;
				counter++;
			}
	
			if (counter == pos) {
				return current.data;
			} else if (counter < pos) {
				// this instance will only occur if the item exists with the head full
				return null;
			}
		} else {
			return null;
		}
		// if failure
		return null;
	}

	/**
	 * Deletes the element of the list at position pos. First element in the list
	 * has position 0. If pos points outside the elements of the list then no
	 * modification happens to the list.
	 * 
	 * @param pos
	 *            : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified.
	 *
	 *         Worst-case asymptotic running time cost: O(n)
	 *
	 *         Justification: The time complexity is dependent of the 
	 *         location of the piece of data within the list, iterating 
	 *         within one while loop until the data is found.
	 */
	public boolean deleteAt(int pos) {
		if (pos < 0) {
			return false;
		}

		DLLNode current = head;
		
		if(this.head != null && this.tail != null) {
			
			if (pos == 0) {
				// just change the pointer to the head
				this.head = this.head.next;
				return true;
			} else if (pos > 0) {
				
				int counter = 0;
				while (current.next != null && counter < pos) {
					current = current.next;
					counter++;
				}
	
				if (counter == pos) {
					// the index is found
					// remove current by adjusting pointers
					// make sure there is another item to the list
					if (current.next != null) {

						current.prev.next = current.next;
						current.next.prev = current.prev;
						return true;
					} else {
						current.prev.next = null;
						this.tail = current.prev;
						return true;
					}
					
				} else if (counter < pos) {
					// this instance will only occur if the item exists with the head full
					return false;
				}
			}
		}else {
			return false;
		}
		// will go here if the position given is larger than the list
		return false;
	}

	/**
	 * Reverses the list. If the list contains "A", "B", "C", "D" before the method
	 * is called Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: O(n)
	 *
	 * Justification: Due to a single while loop the operation will run for aslong
	 * as the list is 
	 */
	public void reverse() {
		// works 
		DLLNode headBefore = this.head;

		DLLNode flip = null;
		DLLNode current = this.head;

		
		//flip the next and prev pointers for the whole list
		
		while (current != null) {
			flip = current.prev;
			current.prev = current.next;
			current.next = flip;
			current = current.prev;
		}

		
		 // Before changing head, check for the cases like empty list and list with only one node
		if (flip != null) {
			this.head = flip.prev;
		}
		// configure the new tail
		this.tail = headBefore;
	}

	/**
	 * Removes all duplicate elements from the list. The method should remove the
	 * _least_number_ of elements to make all elements uniqueue. If the list
	 * contains "A", "B", "C", "B", "D", "A" before the method is called Then it
	 * should contain "A", "B", "C", "D" after it returns. The relative order of
	 * elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: O(N^2)
	 *
	 * Justification: Due to there being two for loops he worst case
	 * will be N^2 as the program will have to cycle through the length of the array
	 * twice
	 */
	public void makeUnique() {

		// iterate through each of the items
		if (this.head != null && this.tail != null) {
			for (DLLNode count1 = this.head; (count1.next.next != null); count1 = count1.next) {
				T searchKey = count1.data;
	
				// go through each node after the first count node
				for (DLLNode count2 = count1.next; (count2 != null); count2 = count2.next) {
	
					if (count2.data == searchKey) {
						// rearrange the pointers to delete the item
						// fix this here
	
						if (count2.next != null) {
							count2.next.prev = count2.prev;
						}
						// if the item is the tail
						if (count2.next == null) {
							this.tail = count2.prev;
						}
						// do anyway
						if (count2.prev != null) {
							count2.prev.next = count2.next;
						}
					}
				}
			}
		} 
	}

	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure. How exactly this will be
	 * represented in the Doubly Linked List is up to the programmer.
	 * 
	 * @param item
	 *            : the item to push on the stack
	 *
	 *            Worst-case asymptotic running time cost: 0(1)
	 *
	 *            Justification: The method performs a linear 
	 *            instruction each time, by just adding to the head
	 *            of the list
	 */
	public void push(T data) {
		// check for empty list
		if (this.head == null && this.tail == null) {
			// the list is empty and set both the head and tail to the data
			DLLNode tempHead = new DLLNode(data, null, null);

			// set the values and change the pointers
			this.head = tempHead;
			this.tail = tempHead;

		} else if (this.head != null) {
			// place the new data at the front of the stack
			DLLNode temp = new DLLNode(data, null, null);
			DLLNode tempTwo = this.head;

			// place the new data at the front of the stack and move pointers
			tempTwo.prev = temp;
			temp.next = tempTwo;
			this.head = temp;
		}
	}

	/**
	 * This method returns and removes the element that was most recently added by
	 * the push method.
	 * 
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 *         Worst-case asymptotic running time cost: o(1)
	 *
	 *         Justification: The method performs a linear action by just
	 *         removing the item at the head of the list
	 */
	public T pop() {
		// remove and return the head of the stack
		if (this.head != null) {
			// get the data to return
			T toReturn = this.head.data;

			// check there is an item after the head
			if (this.head.next != null) {

				// set the new head to be the item after the head then return the data
				DLLNode newHead = this.head.next;
				this.head = newHead;
				return toReturn;
			} else if (this.head == this.tail) {
				// there is only one piece of data in the stack
				this.head = null;
				this.tail = null;
				// set both the head and tail to null
				return toReturn;
			}
		}

		// otherWise just return null
		return null;
	}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure. How exactly this will be
	 * represented in the Doubly Linked List is up to the programmer.
	 * 
	 * @param item
	 *            : the item to be enqueued to the stack
	 *
	 *            Worst-case asymptotic running time cost: O(1);
	 *
	 *            Justification: The subroutine insertBefore just
	 *            performs a linear set of steps to add to the head of the
	 *            list
	 */
	public void enqueue(T data) {

		insertBefore(0, data);
	}

	// remove from the end
	/**
	 * This method returns and removes the element that was least recently added by
	 * the enqueue method.
	 * 
	 * @return the earliest item inserted with an equeue; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic running time cost: O(1)
	 *
	 *         Justification: The run time is linear as the method 
	 *         simply removes the data by adjusting the pointers
	 *         associated with the tail of the list.
	 */
	public T dequeue() {
		// check for an empty list
		if (this.head == null && this.tail == null) {
			// in this case return null as the list is empty
			return null;
		}
		// check for only one item in the list
		else if (this.tail.prev == null) {
			//remove this item for the list then set pointers to null
			T returnItem = this.tail.data;
			this.head = null;
			this.tail = null;
			return returnItem;
		}
		// if there are more than one item in the list
		else if (this.head.data != null && this.tail.prev != null && this.head.next != null) {
			// get item to remove and set pointers
			DLLNode returnItem = this.tail;
			this.tail = this.tail.prev;
			return returnItem.data;
		}

		// otherwise return null for failure
		return null;
	}

	/**
	 * @return a string with the elements of the list as a comma-separated list,
	 *         from beginning to end
	 *
	 *         Worst-case asymptotic running time cost: Theta(n)
	 *
	 *         Justification: We know from the Java documentation that
	 *         StringBuilder's append() method runs in Theta(1) asymptotic time. We
	 *         assume all other method calls here (e.g., the iterator methods above,
	 *         and the toString method) will execute in Theta(1) time. Thus, every
	 *         one iteration of the for-loop will have cost Theta(1). Suppose the
	 *         doubly-linked list has 'n' elements. The for-loop will always iterate
	 *         over all n elements of the list, and therefore the total cost of this
	 *         method will be n*Theta(1) = Theta(n).
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next) {
			if (!isFirst) {
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}

}

// 8 hours spent
