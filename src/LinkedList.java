import java.util.Iterator;
import java.util.ListIterator;

public class LinkedList<T> implements Iterable<T>{

	private ListNode<T> head; //Holds reference to the linkedlist's head
	
	/**
	 * This is the constructor method, it sets head to null
	 */
	public LinkedList() {
		head = null;
	}
	/**
	 * This method adds an element to the end of the linkedlist 
	 * @param element - the T element to add to the list
	 */
	public void add(T element){
		if(head == null)
			head = new ListNode<T>(element,null);
		else{
			ListNode<T> currentNode = head;
			while(currentNode.getNext()!=null){
				currentNode = currentNode.getNext();
			}
				currentNode.setNext(new ListNode<T>(element,null));	
		}
	}
	
	/**
	 * This method returns the iterator for a LinkedList
	 * @return- the new iterator
	 */
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	/**
	 * This class iterates through the linked list
	 */
	public class LinkedListIterator implements Iterator<T>{
		//Instance Variables
		private ListNode<T> current; //Tracks the current node
		
		/**
		 * This is the constructor, removable is set to false, current and previous are instantiated
		 */
		public LinkedListIterator(){
			current = new ListNode<T>(null, head);			
		}
		/**
		 * This method checks if there is a next node
		 * @return- true if a next node exists
		 * 			false if a next node does not exist
		 */
		public boolean hasNext() {
			return current.getNext() !=null;
		}
	
		/**
		 * This method gets the next node and increments the iterator
		 * @return- the node after the current	
		 */
		public T next() {
			current = current.getNext();
			return current.getValue();		
		}
		
		/**
		 * Throws error, unsupported operation
		 */
		public void remove(){
			throw new UnsupportedOperationException();		
		}
	}
}
