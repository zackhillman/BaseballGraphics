import java.util.Iterator;
import java.util.ListIterator;

public class CircleLinkedList<T> implements Iterable<T> {

	private ListNode<T> head;
	private ListNode<T> current;
	
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
	
	public String toString(){
		ListNode<T> currentNode = head;
		String str = "\n\n"+currentNode.getValue().toString();
		
		while(currentNode.getNext()!=head){
			currentNode = currentNode.getNext();		
			str+="\n\n"+currentNode.getValue().toString();
		}
		return str;
	}
	
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
			removable = true;
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
