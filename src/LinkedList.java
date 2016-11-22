
public class LinkedList<T>{

	private ListNode<T> head;
	private ListNode<T> current;
	
	public LinkedList(){
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
}
