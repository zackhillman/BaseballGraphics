import java.util.Iterator;

public class CircleLinkedList<T> implements Iterable<T> {

	private ListNode<T> head; //used to track the head of the linked list
	private ListNode<T> current; //used to track the current node
	
	/**
	 * This is the constructor method, it sets head to null
	 * and sets the current node to head
	 */
	public CircleLinkedList(){
		head = null;
		current  = head;
	}
	/**
	 * This method adds an element to the end of the linked list 
	 * @param element - the T element to add to the list
	 */
	public void add(T element){
		if(head == null){
			head = new ListNode<T>(element,head);
			head.setNext(head);
			current = head;
		}else{
			ListNode<T> currentNode = head;
			while(currentNode.getNext()!=head){
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(new ListNode<T>(element,head));	
		}
	}
	
	/**
	 * This method replaces the current node's value with a new one
	 * @param newElement- the new element for the node
	 */
	public void set(T newElement){
		current.setValue(newElement);
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
	
	/**
	 * This method declares a new LinkedListIterator as the iterator for a circle linked list
	 * @return- the new iterator
	 */
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator();
	}
	
	/**
	 * This class iterates through the linked list
	 */
	public class LinkedListIterator implements Iterator<T>{
		//Instance Variables
		private ListNode<T> current; //Tracks the current node
		
		/**
		 * This is the constructor, current is instantiated
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
			if(current.getNext()==null)
				return false;
			if(current.getValue() == null)
				return true;
			return current.getNext()!=head;
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
