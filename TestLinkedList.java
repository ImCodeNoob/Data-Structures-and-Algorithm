public class TestLinkedList
	{
	    public static void main( String [ ] args )
	    {
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

//	        java.util.Iterator<Integer> itr = lst.iterator( );
//	        while( itr.hasNext( ) )
//	        {
//	                itr.next( );
//	                itr.remove( );
//	                System.out.println( lst );
//	        }
	    }
	}

