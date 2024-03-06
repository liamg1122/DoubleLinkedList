import java.util.Comparator;


/**
 * Sorted double linked list. Can only add elements in their sorted position - not to front or back.
 * Uses comparator to automatically add elements to the correct position.
 * 
 * @author Liam Ghershony
 * @param <T> type of elements in the list
 */

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>  {

	
		 /**
	     * Comparator used to sort list.
	     */
	
	    private Comparator<T> comparator;

	    
		 /**
		 * Constructor for new sorted list.
		 * 
		 * @param comparator -- used to sort elements.
		 */
	    
	    
	    public SortedDoubleLinkedList(Comparator<T> comparator) {
	        super(); 
	        this.comparator = comparator;
	    }
	    
	    
	    
	    /**
	     * Operation not supported
	     *
	     * @param data -- element to add to front of list
	     * @throws UnsupportedOperationException 
	     */
	    
	    
	    @Override
	    public void addToFront(T data) {
	        throw new UnsupportedOperationException("Invalid operation for sorted list");
	    }

	    
	    /**
	     * Operation not supported
	     *
	     * @param data -- element to add to back of list
	     * @throws UnsupportedOperationException 
	     */
	    
	    @Override
	    public void addToEnd(T data) {
	        throw new UnsupportedOperationException("Invalid operation for sorted list");
	    }
	    
	    
	    
	    /**
	     * Adds new element to list in the sorted position, using comparator to sort elements.
	     *
	     * @param data -- element to add.
	     */
	    
	    
	    public void add(T data) {
	    	
	        Node newNode = new Node(data);
	        
	        if (head == null) {
	        	
	            head = newNode;
	            tail = newNode;
	        }
	        
	        else {
	            
	        	Node current = head;
	            
	            while (current != null && comparator.compare(current.data, data) < 0) {
	                current = current.next;
	            }
	            
	            if (current == head) {
	                newNode.next = head;
	                head.previous= newNode;
	                head = newNode;
	            } 
	            
	            else if (current == null) {
	                tail.next = newNode;
	                newNode.previous= tail;
	                tail = newNode;
	            } 
	            
	            else {
	                newNode.previous= current.previous;
	                newNode.next = current;
	                current.previous.next = newNode;
	                current.previous= newNode;
	            }
	        }
	        size++;
	    }


	}


