import java.util.*;

public class MyHashTable<AnyType> {
  private static final int DEFAULT_TABLE_SIZE = 101;
  private int tableSize;
  private HashKey<AnyType> [] array;
  
  public MyHashTable() {
    this(DEFAULT_TABLE_SIZE);
  }
  
  public MyHashTable(int size) {
    arrangeArray(size);
    doClear();
  }
  
  public List<AnyType> traverse() {
    List<AnyType> list = new ArrayList<>();
    for(int i=0; i<array.length; i++) {
      if(array[i] != null) {
        list.add(array[i].element);
      }
    }
    return list;
  }
  
  public void insert(AnyType x) {
    int currentPos = findPos(x);
    if(isActive(currentPos)) {
      return;
    }
    if(array[currentPos] == null) {
      array[currentPos] = new HashKey<AnyType>(x, true);
      tableSize++;
    }
    if(tableSize > array.length /2) {
      rehash();
    }
  }
  
  public void remove(AnyType x) {
    int current = findPos(x);
    if(array[current] != null) {
      array[current].isActive = false;
      array[current] = null;
    }
    else return;
  }
  
  public int size() {
    return tableSize;
  }
  
  public int capacity() {
    return array.length;
  }
  
  public boolean contains(AnyType x) {
    int current =  findPos(x);
    return isActive(current);
  }
  
  private void rehash() {
    HashKey<AnyType>[] oldArray = this.array;
    arrangeArray(2 * oldArray.length);
    tableSize = 0;
    for(HashKey<AnyType> entry : oldArray) {
      if(entry != null && entry.isActive) {
        insert(entry.element);
      }
    }
  }
  
  private int findPos(AnyType x) {
    int current = hash(x);
    int offset = 1;
    while(array[current] != null && !array[current].element.equals(x)) {
      current = current + offset;
      offset++;
      if(current >= array.length) {
        current = current - array.length;
      }
    }
    return current;
  }
  
  private boolean isActive(int currentPos) {
    return array[ currentPos ] != null && array[ currentPos ].isActive;
  }
  
  
  
  
  
  private void arrangeArray(int arraySize) {
    array = new HashKey[nextPrime(arraySize)];
  }
  
  private int nextPrime(int n) {
    if(n % 2 == 0) n++;
    for(; !isPrime(n); n += 2) {
      ;
    }
    return n;
  }
  
  private boolean isPrime(int n) {
    if(n == 2 || n == 3) return true;
    if(n == 1 || n %2 == 0) return false;
    for(int i=3; i * i <= n; i += 2) {
      if(n % i == 0) return false;
    }
    return true;
  }
  
  private void doClear() {
    tableSize = 0;
    for(int i=0; i<array.length; i++) {
      array[i] = null;
    }
  }
  
  private void empty() {
    doClear();
  }
  
  private int hash(AnyType x) {
    int hashVal = x.hashCode();
    hashVal = hashVal % array.length;
    if(hashVal < 0) hashVal = hashVal + array.length;
    return hashVal;
  }
  
//  private int hashTwo(AnyType x) {
//    int prime = prePrime(array.length);
//    int hashVal = x.hashCode();
//    hashVal = prime - (hashVal % prime);
//    while(hashVal >= array.length) {
//      hashVal = hashVal - array.length;
//    }
//    if(hashVal < 0 ) { 
//      hashVal = hashVal + array.length;
//    }
//    return hashVal;
//  }
  
  private int prePrime(int n) {
    if(n == 2) return n;
    n = n - 1;
    if(n % 2 == 0) n--;
    while(!isPrime(n) && n > 2) {
      n = n - 2;
    }
    return n;
  }
  
  private static class HashKey<AnyType>
  {
      public AnyType  element;   // the element
      public boolean isActive;  // false if marked deleted

      public HashKey( AnyType e )
      {
          this( e, true );
      }

      public HashKey( AnyType e, boolean i )
      {
          element  = e;
          isActive = i;
      }
  }
  
}
