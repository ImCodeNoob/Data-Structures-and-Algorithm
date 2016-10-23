import java.util.*;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;
	/**
	 * LinkedList class implements a doubly-linked list.
	 */
	public class MyLinkedList<AnyType> implements Iterable<AnyType>
	{
	    /**
	     * Construct an empty LinkedList.
	     */
	    public MyLinkedList( )
	    {
	        doClear( );
	    }
	    
	    private void clear( )
	    {
	        doClear( );
	    }
	    
	    /**
	     * Change the size of this collection to zero.
	     */
	    public void doClear( )
	    {
	        beginMarker = new Node<>( null, null, null );
	        endMarker = new Node<>( null, beginMarker, null );
	        beginMarker.next = endMarker;
	        
	        theSize = 0;
	        modCount++;
	    }
	    
	    /**
	     * Returns the number of items in this collection.
	     * @return the number of items in this collection.
	     */
	    public int size( )
	    {
	        return theSize;
	    }
	    
	    public boolean isEmpty( )
	    {
	        return size( ) == 0;
	    }
	    
	    /**
	     * Adds an item to this collection, at the end.
	     * @param x any object.
	     * @return true.
	     */
	    public boolean add( AnyType x )
	    {
	        add( size( ), x );   
	        return true;         
	    }
	    
	    /**
	     * Adds an item to this collection, at specified position.
	     * Items at or after that position are slid one position higher.
	     * @param x any object.
	     * @param idx position to add at.
	     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
	     */
	    public void add( int idx, AnyType x )
	    {
	        addBefore( getNode( idx, 0, size( ) ), x );
	    }
	    
	    /**
	     * Adds an item to this collection, at specified position p.
	     * Items at or after that position are slid one position higher.
	     * @param p Node to add before.
	     * @param x any object.
	     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
	     */    
	    private void addBefore( Node<AnyType> p, AnyType x )
	    {
	        Node<AnyType> newNode = new Node<>( x, p.prev, p );
	        newNode.prev.next = newNode;
	        p.prev = newNode;         
	        theSize++;
	        modCount++;
	    }   
	    
	    /** swap two node in the list
	     * 
	     * @param pos1
	     * @param pos2
	     */
	    public void swap(int pos1, int pos2) {
	    	if(pos1 > size() || pos2 > size() || pos1 < 0 || pos2 < 0) {
	    		throw new IndexOutOfBoundsException();
	    	}
	    	Node<AnyType> p = getNode(pos1);
	    	Node<AnyType> pPre = p.prev;
	    	Node<AnyType> pNext = p.next;
	    	Node<AnyType> q = getNode(pos2);
	    	Node<AnyType> qPre = q.prev;
	    	Node<AnyType> qNext = q.next;
	    	pPre.next = q;
	    	pNext.prev = q;
	    	qPre.next = p;
	    	qNext.prev = p;
	    	p.prev = qPre;
	    	p.next = qNext;
	    	q.prev = pPre;
	    	q.next = pNext;
	    	modCount++;
	    }
	    
	    /** shift the list
	     * 
	     * @param i
	     */
	    public void shift(int i) {
	        if(i == 0) return;
	    	if(i > 0) forward(i);
	        else backward(i);
	    }
	    
	    /** shift forward the list
	     * 
	     * @param i
	     */
	    private void forward(int i) {
	    	if(i < 0) throw new IndexOutOfBoundsException();
	    	while(i > size()) {
	    		i = i - size();
	    	}
	    	Node<AnyType> newHead = getNode(i);
	    	Node<AnyType> newTail = newHead.prev;
	    	Node<AnyType> oldHead = getNode(0);
	    	Node<AnyType> oldTail = getNode(theSize - 1);
	    	oldHead.prev = oldTail;
	    	oldTail.next = oldHead;
	    	beginMarker.next = newHead;
	    	newHead.prev = beginMarker;
	    	newTail.next = endMarker;
	    	endMarker.prev = newTail;
	    	modCount++;
	    }
	    
	    /** shift the list backward
	     * 
	     * @param i
	     */
	    private void  backward(int i) {
			if(i > 0) throw new IndexOutOfBoundsException();
			i = - i;
			while(i > size()) {
				i = i -size();
			}
			forward(size() - i);
		}
	    
	    /** erase elements from a specific position
	     * 
	     * @param i
	     * @param nums
	     */
	    public void erase(int i, int nums) {
			if(i < 0 || i > size()  - 1 || nums < 0 || nums > size()) throw new IndexOutOfBoundsException();
			if(nums == 0) return;
			Node<AnyType> cur = getNode(i);
			Node<AnyType> previous = cur.prev;
			if(nums > size() - i) nums = size() - i;
			for(int j=0; j<nums; j++) {
				cur = cur.next;
			}
			previous.next = cur;
			cur.prev = previous;
			theSize = theSize - nums;
			modCount++;
		}
	    
	    /** insert a list from a specific position
	     * 
	     * @param list
	     * @param index
	     * @return
	     */
	    public Node<AnyType> insertList(MyLinkedList<AnyType> list, int index) {
			if(index < 0 || index > size()-1) throw new IndexOutOfBoundsException();
			Node<AnyType> cur = getNode(index);
			Node<AnyType> back = cur.next;
			Node<AnyType> head = list.getNode(0);
			Node<AnyType>  tail = list.getNode(list.theSize - 1);
			cur.next = head;
			head.prev = cur;
			back.prev = tail;
			tail.next = back;
			theSize = theSize + list.theSize;
			modCount++;
			return this.getNode(0);
		}
	    /**
	     * Returns the item at position idx.
	     * @param idx the index to search in.
	     * @throws IndexOutOfBoundsException if index is out of range.
	     */
	    public AnyType get( int idx )
	    {
	        return getNode( idx ).data;
	    }
	        
	    /**
	     * Changes the item at position idx.
	     * @param idx the index to change.
	     * @param newVal the new value.
	     * @return the old value.
	     * @throws IndexOutOfBoundsException if index is out of range.
	     */
	    public AnyType set( int idx, AnyType newVal )
	    {
	        Node<AnyType> p = getNode( idx );
	        AnyType oldVal = p.data;
	        
	        p.data = newVal;   
	        return oldVal;
	    }
	    
	    /**
	     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	     * @param idx index to search at.
	     * @return internal node corresponding to idx.
	     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
	     */
	    private Node<AnyType> getNode( int idx )
	    {
	        return getNode( idx, 0, size( ) - 1 );
	    }

	    /**
	     * Gets the Node at position idx, which must range from lower to upper.
	     * @param idx index to search at.
	     * @param lower lowest valid index.
	     * @param upper highest valid index.
	     * @return internal node corresponding to idx.
	     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
	     */    
	    private Node<AnyType> getNode( int idx, int lower, int upper )
	    {
	        Node<AnyType> p;
	        
	        if( idx < lower || idx > upper )
	            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
	            
	        if( idx < size( ) / 2 )
	        {
	            p = beginMarker.next;
	            for( int i = 0; i < idx; i++ )
	                p = p.next;            
	        }
	        else
	        {
	            p = endMarker;
	            for( int i = size( ); i > idx; i-- )
	                p = p.prev;
	        } 
	        
	        return p;
	    }
	    
	    /**
	     * Removes an item from this collection.
	     * @param idx the index of the object.
	     * @return the item was removed from the collection.
	     */
	    public AnyType remove( int idx )
	    {
	        return remove( getNode( idx ) );
	    }
	    
	    /**
	     * Removes the object contained in Node p.
	     * @param p the Node containing the object.
	     * @return the item was removed from the collection.
	     */
	    private AnyType remove( Node<AnyType> p )
	    {
	        p.next.prev = p.prev;
	        p.prev.next = p.next;
	        theSize--;
	        modCount++;
	        
	        return p.data;
	    }
	    
	    /**
	     * Returns a String representation of this collection.
	     */
	    public String toString( )
	    {
	        StringBuilder sb = new StringBuilder( "[ " );

	        for( AnyType x : this )
	            sb.append( x + " " );
	        sb.append( "]" );

	        return new String( sb );
	    }

	    /**
	     * Obtains an Iterator object used to traverse the collection.
	     * @return an iterator positioned prior to the first element.
	     */
	    public java.util.Iterator<AnyType> iterator( )
	    {
	        return new LinkedListIterator( );
	    }

	    /**
	     * This is the implementation of the LinkedListIterator.
	     * It maintains a notion of a current position and of
	     * course the implicit reference to the MyLinkedList.
	     */
	    private class LinkedListIterator implements java.util.Iterator<AnyType>
	    {
	        private Node<AnyType> current = beginMarker.next;
	        private int expectedModCount = modCount;
	        private boolean okToRemove = false;
	        
	        public boolean hasNext( )
	        {
	            return current != endMarker;
	        }
	        
	        public AnyType next( )
	        {
	            if( modCount != expectedModCount )
	                throw new java.util.ConcurrentModificationException( );
	            if( !hasNext( ) )
	                throw new java.util.NoSuchElementException( ); 
	                   
	            AnyType nextItem = current.data;
	            current = current.next;
	            okToRemove = true;
	            return nextItem;
	        }
	        
	        public void remove( )
	        {
	            if( modCount != expectedModCount )
	                throw new java.util.ConcurrentModificationException( );
	            if( !okToRemove )
	                throw new IllegalStateException( );
	                
	            MyLinkedList.this.remove( current.prev );
	            expectedModCount++;
	            okToRemove = false;       
	        }
	    }
	    
	    /**
	     * This is the doubly-linked list node.
	     */
	    private static class Node<AnyType>
	    {
	        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
	        {
	            data = d; prev = p; next = n;
	        }
	        
	        public AnyType data;
	        public Node<AnyType>   prev;
	        public Node<AnyType>   next;
	    }
	    
	    private int theSize;
	    private int modCount = 0;
	    private Node<AnyType> beginMarker;
	    private Node<AnyType> endMarker;
	    
	    public static void main(String[] args) {
	    	MyLinkedList<Integer> lst = new MyLinkedList<>( );
	        MyLinkedList<Integer> list = new MyLinkedList<>();
	        for( int i = 0; i < 10; i++ ) {
	        	lst.add( i );
	        	list.add(i);
	        }
	                
	        System.out.println( lst);
	        lst.swap(4, 6);
	        System.out.println( lst);
	        
	        lst.shift(3);
	        System.out.println(lst);
	        lst.shift(- 12);
	        System.out.println(lst);
	        
	        lst.erase(2, 5);
	        System.out.println(lst);
	        
	        lst.insertList(list, 2);
	        System.out.println(lst);
	    }
	}
