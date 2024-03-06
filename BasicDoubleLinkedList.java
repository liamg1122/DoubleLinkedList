import java.util.Iterator;


import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Doubly linked generic list.
 * Supports adding elements or removing/retrieving from front and back, removal, and iteration
 * 
 *
 * @param <T> type of elements in list
 * @author Liam Ghershony
 */


public class BasicDoubleLinkedList<T> implements Iterable<T> {
    
	protected Node head;
    
	protected Node tail;
   
	protected int size;
	
	
    /**
     * Inner class node for nodes in the linked list. Each node has data, and pointer to the next and previous node.
     */
    
    class Node {
    	
        /** The data in node. */
        T data;
        
        /**
         * The previous and next nodes
         */
        
        Node previous, next;

        /**
         * Constructs a node.
         * 
         * @param dataNode -- the data to be stored in constructed node.
         */
        public Node(T dataNode) {
            this.data = dataNode;
            this.previous= null;
            this.next = null;
        }
    }

	
    /**
     * Constructor for list.
     */
    
    
    public BasicDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    
    /**
     * Adds item to front of the list.
     *
     * @param data -- generic element to be added
     */
    
    public void addToFront(T data) {
        Node myNode = new Node(data);
       
        if (head == null) {
            head = myNode;
            tail = myNode;
            
        } else {
            myNode.next = head;
            head.previous= myNode;
            head = myNode;
        }
        
        size++;
    }

    
    /**
     * Adds item to end of the list.
     *
     * @param data -- generic element to be added
     */
    
    public void addToEnd(T data) {
        Node myNode = new Node(data);
        if (tail == null) {
            head = myNode;
            tail = myNode;
        } else {
            tail.next = myNode;
            myNode.previous= tail;
            tail = myNode;
        }
        size++;
    }

    
    /**
     * Gets, but leaves in place the first element or the head of the list
     *
     * @return first element or null if list is empty
     */
    
    
    public T getFirst() {
        
    	if (head == null) 
        	
        	return null;
        
        return head.data;
    }

    /**
     * Gets, but leaves in place the last element or the tail of the list
     *
     * @return last element or null if list is empty
     */
    
    public T getLast() {
        
    	if (tail == null) 
        	
        	return null;
    	
        return tail.data;
    }

    /**
     * Return the number of elements or items in this list - known as size
     *
     * @return the number of items in this list as integer
     */
    
    public int getSize() {
        return size;
    }
    
    
    /**
     * Removes and returns the first element/ head of the list
     *
     * @return the first element/ or head of the list -- or null if the list is empty
     */
    
    public T retrieveFirstElement() {
        if (head == null) {
            return null;
        } else {
            T data = head.data;
            
            head = head.next;
            
            if (head != null) {
                head.previous= null;
            } else {
                tail = null;
            }
            
            size--;
            
            return data;
        }
    }
    
    
    /**
     * Removes and returns the last element/ tail of the list
     *
     * @return the last element/ or tail of the list -- or null if the list is empty
     */
    
    
    public T retrieveLastElement() {
       
    	if (tail == null) {
           
        	return null;
        	
        } 
        
        else {
            
        	T data = tail.data;
            
            tail = tail.previous;
            
            if (tail != null) {
            	
                tail.next = null;
                
            } else {
            	
                head = null;
            }
            
            size--;
            
            return data;
        }
    }
    
    /**
     * Removes the targeted element from this list if found.
     *
     * @param theData --  node to be removed from list
     * @param comparing -- the comparator that tests for the target node
     * @return the removed Node -- or null if not found.
     */
    
    public Node remove(T theData, Comparator<T> comparing) {
        
    	Node current = head;
       
        while (current != null) { //start
            
        	if (comparing.compare(current.data, theData) == 0) {
        		
                if (current.previous!= null) { 
                	
                    current.previous.next = current.next;
                } 
                
                else { 
                    head = current.next;
                }
                
                if (current.next != null) {
                	
                    current.next.previous = current.previous;
                } 
                
                else { 
                    tail = current.previous;
                }
                
                size--; 
                
                return current; 
            }
        	
            current = current.next; //end while loop
        }
        
        return null; 
    }

    /**
     * Returns ArrayList of all elements as found in the doubly linked list
     *
     * @return ArrayList of elements from doubly linked list.
     */
    
    public ArrayList<T> toArrayList() {
    	
    	ArrayList<T> toList = new ArrayList<T>();
    	
        Node current = head; 
        
        while (current != null) {
            toList.add(current.data);
            current = current.next;
        }
        
        return toList; 
    }


    /**
     * Returns iterator for elements of the list
     * 
     * @return list iterator for iterating over list
     */
    
    @Override
    public ListIterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    
    
   

    /**
     * An iterator for iterating over elements of list -- forwards and backwards.
     */
    
    private class DoubleLinkedListIterator implements ListIterator<T> {
        
    	/**
    	 * current point of iteration -- default set to head of the list
    	 */
    	private Node current = head;
    	
    	/**
    	 * the node accessed before the current point of iteration 
    	 */
        private Node lastAccessed = null;
        
        /**
    	 * boolean that marks whether iteration has surpassed the last element/ tail of the list -- default set to false.
    	 */
        private boolean isBeyondLast = false;

        /**
         * Checks if iteration can continue if there is an element to iterate over.
         * @return true if there is an element, false if not.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Checks if iteration can continue if there is an element to before the current element.
         * @return true if there is an element previous to the current, false if not, or true if iteration has gone past the tail.
         */
        
        public boolean hasPrevious() {
            return (current != null && current.previous!= null) || isBeyondLast;
        }
        
        /**
         * Iterates backwards to the previous element of the list and moves the cursor backwards.
         * @return returns the data from the previous element in the list
         * @throws NoSuchElementException if there is no previous element to iterate over.
         */

        public T previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
           
            if (isBeyondLast) {
                isBeyondLast = false; }
            
            if (current == null) {
                current = tail;
                
            } else {
                current = current.previous;
            }
            
            lastAccessed = current;
            
            return lastAccessed.data;
        }
        
        /**
         * Iterates forwards to the next element of the list and moves the cursor forwards.
         * @return returns the data from the next element in the list
         * @throws NoSuchElementException if there is no next element to iterate over.
         */
        
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastAccessed = current;
            current = current.next;
            
            if (current == null) {
                isBeyondLast = true;
            }
            
            return lastAccessed.data;
        }
        
        public void add(T e) {
            
        	throw new UnsupportedOperationException();
        }

        public void remove() {
            
        	throw new UnsupportedOperationException();
        }
        
        public int previousIndex() {
            
        	throw new UnsupportedOperationException();
        }


        public void set(T e) {
            throw new UnsupportedOperationException();
        }

        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

      
    }
}
