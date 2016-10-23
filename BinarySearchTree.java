import java.util.*;
// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new NullPointerException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new NullPointerException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }
    
    public int nodeCount() {
      if(isEmpty()) return 0;
      else return nodeCount( root);
    }
    
    public boolean isFull() {
      if(isEmpty()) return true;
      else return isFull(root);
    }
    
    public boolean compareStructure(BinarySearchTree t2) {
      if(isEmpty() && t2.isEmpty()) return true;
      else return compareStructure(root, t2.root);
    }
    
    public boolean equals(BinarySearchTree t2) {
      if(isEmpty() && t2.isEmpty()) return true;
      else return equals(root, t2.root);
    }
    
    public BinarySearchTree copy() {
      BinarySearchTree newTree = new BinarySearchTree();
      if(isEmpty()) return newTree;
      else {
        LinkedList<BinaryNode<AnyType>> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode<AnyType> current = null;
        while(! queue.isEmpty()) {
          current = queue.poll();
          newTree.insert(current.element);
          if(current.left != null) {
            queue.add(current.left);
          }
          if(current.right != null) {
            queue.add(current.right);
          }
        }
      }
      return  newTree;
    }
    
    public BinarySearchTree mirror() {
      BinarySearchTree m = new BinarySearchTree();
      if(isEmpty()) return m;
      else {
        m = this.copy();
        m.root = mirror(m.root);
      }
      return m;
    }
    
    public boolean isMirror(BinarySearchTree b) {
      BinarySearchTree m = b.mirror();
      return this.equals(m);
    }
    
    public void rotateRight(AnyType x) {
      if(!contains(x)) System.out.println("Can not rotate because there is no such element");
      if(root.element.compareTo(x) == 0) {
        if(root.left == null) {
          System.out.println("There is no left node can not rotate right");
        }
        else {
          BinaryNode<AnyType> k1 = root.left;
          root.left = k1.right;
          k1.right = root;
          root =  k1;
        }
      }
      else if(root.element.compareTo(x) < 0) {
        root.right = rotateRight(root.right, x);
      }
      else {
        root.left = rotateRight(root.left, x);
      }
      
     
      
//        if(root.left == null)  {
//          System.out.println("Can not rotate");
//          return;
//        }
//        else if(root.left.right == null) {
//          BinaryNode<AnyType> l = root.left;
//          root.left = null;
//          l.right = root;
//          this.root = l;
//        }
//        else {
//          BinaryNode<AnyType> l = root.left;
//          root.left = l.right;
//          l.right = root;
//          this.root = l;
//        }
//      }
//      else rotateRight(root, x);
        
    }
    
    public void rotateLeft(AnyType x) {
      if(!contains(x)) System.out.println("Can not rotate because there is no such element");
      if(root.element.compareTo(x) == 0) {
        if(root.right == null) {
          System.out.println("There is no right node can not rotate left");
        }
        else {
          BinaryNode<AnyType> k1 = root.right;
          root.right = k1.left;
          k1.left = root;
          root =  k1;
        }
      }
      else if(root.element.compareTo(x) < 0) {
        root.right = rotateLeft(root.right, x);
      }
      else {
        root.left = rotateLeft(root.left, x);
      }
//      if(!contains(x)) System.out.println("Can not rotate because there is no such element");
//      if(root.element.compareTo(x) == 0) {
//        if(root.right == null) {
//          System.out.println("Can not rotate");
//          return;
//        }
//        else if(root.left == null) {
//          BinaryNode<AnyType> r = root.right;
//          root.right = null;
//          r.left = root;
//          this.root = r;
//        }
//        else {
//          BinaryNode<AnyType> r = root.right;
//          root.right = r.left;
//          r.left = root;
//          this.root = r;
//        }
//      }
//      else rotateLeft(root, x);
    }
    
    public void printLevels() {
      if(isEmpty()) System.out.println(" Empty Tree");
      else printLevels(root);
    }
    
    
    public List<AnyType> traverse() {
      List<AnyType> list = new ArrayList<AnyType>();
      if(root == null) return list;
      traverse(root, list);
      return list;
    }
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private void traverse(BinaryNode<AnyType> root, List<AnyType> list) {
      if(root == null) return;
      list.add(root.element);
      traverse(root.left, list);
      traverse(root.right, list);
    }
    
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> k, AnyType x) {
      BinaryNode<AnyType> r = null;
      if(k.element.compareTo(x) == 0) {
        if(k.left == null) {
          System.out.println("There is no left node can not rotate right");
        }
        else {
          BinaryNode<AnyType> k1 = k.left;
          k.left = k1.right;
          k1.right = k;
          r = k1;
        } 
      }
      else if(k.element.compareTo(x) < 0) {
        r = rotateRight(k.right, x);
      }
      else {
        r = rotateRight(k.left, x);
      }
      return r;
    }
    
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> k, AnyType x) {
      BinaryNode<AnyType> r = null;
      if(k.element.compareTo(x) == 0) {
        if(k.right == null) {
          System.out.println("There is no right node can not rotate left");
        }
        else {
          BinaryNode<AnyType> k1 = k.right;
          k.right = k1.left;
          k1.left = k;
          r = k1;
        } 
      }
      else if(k.element.compareTo(x) < 0) {
        r = rotateLeft(k.right, x);
      }
      else {
        r = rotateLeft(k.left, x);
      }
      return r;
    }
    
    
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    private int nodeCount(BinaryNode<AnyType> t) {
      if(t == null) return 0;
      else {
        return 1 + nodeCount(t.left) + nodeCount(t.right);
      }
    }
    
    private boolean compareStructure(BinaryNode<AnyType> r1, BinaryNode<AnyType> r2) {
      if(r1 == null && r2 == null) return true;
      if(r1 == null || r2 == null) return false;
      return compareStructure(r1.left, r2.left) && compareStructure(r1.right, r2.right);
    }
    
    private boolean isFull(BinaryNode<AnyType> t) {
      if(t == null) return true;
      else if(t.left != null && t.right == null || t.left == null && t.right != null) return false;
      return (isFull(t.left) && isFull(t.right));
    }
    
    private boolean equals(BinaryNode<AnyType> r1, BinaryNode<AnyType> r2) {
      if(r1== null && r2 == null) return true;
      else if(r1== null || r2 == null || r1.element.compareTo(r2.element) != 0) return false;
      return equals(r1.left, r2.left) && equals(r1.right, r2.right);
    }
    
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> root) {
      if(root != null) {
        root.left = mirror(root.left);
        root.right = mirror(root.right);
        BinaryNode<AnyType> tmp = root.left;
        root.left = root.right;
        root.right = tmp;
      }
      return root;
    }
    
//    private void rotateRight(BinaryNode<AnyType> root, AnyType x) {
//      if(root.left.element.compareTo(x) == 0) {
//          if(root.left.left == null) System.out.println("Can not rotateRight");
//          else if(root.left.left.right == null) {
//            BinaryNode<AnyType> newLeft = root.left.left;
//            root.left.left = null;
//            newLeft.right = root.left;
//            root.left = newLeft;
//          }
//          else {
//            BinaryNode<AnyType> newLeft = root.left.left;
//            BinaryNode<AnyType> r = root.left.left.right;
//            newLeft.right = root.left;
//            root.left.left = r;
//            root.left = newLeft;
//          }
//      }
//      else if(root.right.element.compareTo(x) == 0) {
//        if(root.right.left == null) System.out.println("Can not rotateRight");
//        else if(root.right.left.right == null) {
//          BinaryNode<AnyType> newRight = root.right.left;
//          root.right.left = null;
//          newRight.right = root.right;
//          root.right = newRight;
//        }
//        else {
//          BinaryNode<AnyType> newRight = root.right.left;
//          BinaryNode<AnyType> l = root.right.left.right;
//          newRight.right = root.right;
//          root.right.left = l;
//          root.right = newRight;
//        }
//      } 
//      else if(x.compareTo(root.element) < 0) rotateRight(root.left, x);
//      else rotateRight(root.right, x);
//    }
//    
//    private void rotateLeft(BinaryNode<AnyType> root, AnyType x) {
//      if(root.left.element.compareTo(x) == 0) {
//        if(root.left.right == null) System.out.println("Can not rotateLeft");
//        else if(root.left.right.left == null) {
//          BinaryNode<AnyType> newLeft = root.left.right;
//          root.left.right = null;
//          newLeft.left = root.left;
//          root.left = newLeft;
//        }
//        else {
//          BinaryNode<AnyType> newLeft = root.left.right;
//          BinaryNode<AnyType> r = root.left.right.left;
//          newLeft.left = root.left;
//          root.left.right = r;
//          root.left = newLeft;
//        }
//      }
//      else if(root.right.element.compareTo(x) == 0) {
//        if(root.right.right == null) System.out.println("Can not rotateLeft");
//        else if(root.right.right.left == null) {
//          BinaryNode<AnyType> newRight = root.right.right;
//          root.right.right = null;
//          newRight.left = root.right;
//          root.right = newRight;
//        }
//        else {
//          BinaryNode<AnyType> newRight = root.right.right;
//          BinaryNode<AnyType> r = root.right.right.left;
//          newRight.left = root.right;
//          root.right.right = r;
//          root.right = newRight;
//        }
//      } 
//      else if(x.compareTo(root.element) < 0) rotateLeft(root.left, x);
//      else rotateLeft(root.right, x);
//    }
    
    private void printLevels(BinaryNode<AnyType> root) {
      if(root == null || root.element == null) {
        return;
      }
      LinkedList<BinaryNode<AnyType>> queue = new LinkedList<>();
      BinaryNode<AnyType> current = null;
      queue.offer(root);
      int currentLvl = 1;
      int nextLvl = 0;
      while(!queue.isEmpty()) {
        current = queue.poll();
        currentLvl--;
        if(current != null) System.out.print(current.element + "->");
        if(current.left != null) {
          queue.offer(current.left);
          nextLvl++;
        }
        if(current.right != null) {
          queue.offer(current.right);
          nextLvl++;
        }
        if(currentLvl == 0) {
          System.out.println("");
          currentLvl = nextLvl;
          nextLvl = 0;
        }
      }
    }
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
          BinarySearchTree t = new BinarySearchTree();
          int[] nums = {10, 5, 20, 2, 8, 15, 30};
          for(int i =0; i<nums.length; i++) {
            t.insert(nums[i]);
          }
          System.out.println(t.nodeCount());
          t.printLevels();
          System.out.println(t.isFull());
          t.remove(15);
          System.out.println(t.isFull());
          t.insert(15);
          BinarySearchTree p = new BinarySearchTree();
          int [] nums2 = {10, 5, 20, 2, 8, 15, 25};
          for(int i=0; i<nums.length; i++) {
            p.insert(nums2[i]);
          }
          System.out.println(t.compareStructure(p));
          System.out.println(t.equals(p));
          p.remove(25);
          p.insert(30);
          System.out.println(t.equals(p));
          BinarySearchTree c = t.copy();
          c.printLevels();
          BinarySearchTree m = t.mirror();
          m.printLevels();
          System.out.println(c.isMirror(t));
          System.out.println(m.isMirror(t));
          t.rotateRight(10);
          t.printLevels();
          c.rotateLeft(10);
          c.printLevels();
          t.rotateLeft(10);
          t.printLevels();
          t.rotateLeft(20);
          t.printLevels();

    }
}