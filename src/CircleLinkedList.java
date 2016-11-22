import java.util.Iterator;

public class CircleLinkedList<T> {

	private ListNode<T> head;
	private ListNode<T> current;
	
	public CircleLinkedList(){
		head = null;
		current = head;
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
			ListNode<T> currentNode = head.getNext();
			while(currentNode.getNext()!=head){
				currentNode = currentNode.getNext();
			}
				currentNode.setNext(new ListNode<T>(element,head));	
		}
	}
	/**
	 * This method gets the current element in the linked list
	 * @return- the current T element
	 */
	public T getCurrent(){
	//	System.out.println(current);
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
}
