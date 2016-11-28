import java.util.Iterator;
import java.util.ListIterator;

public class LinkedList<T> implements Iterable<T>{

	private ListNode<T> head;
	private ListNode<T> current;
	
	public LinkedList() {
		head = null;
		current = head;
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
	 * This method gets the current element in the linked list
	 * @return- the current T element
	 */
	public T getCurrent(){
		return current.getValue();
	}
	
	/**
	 * This method increments the linked list current
	 */
	public void doNext(){
		current = current.getNext();
	}
	
	public Iterator<T> iterator() {
		
		return new LinkedListIterator();
	}
	
	/**
	 * This class iterates through the linked list
	 */
	public class LinkedListIterator implements Iterator<T>{
		//Instance Variables
		private ListNode<T> current; //Tracks the current node
		private boolean removable; //If removable, can use the remove method
		
		/**
		 * This is the constructor, removable is set to false, current and previous are instantiated
		 */
		public LinkedListIterator(){
			removable = false;
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
			removable = true;
			current = current.getNext();
			return current.getValue();
			
		}
	}


}
