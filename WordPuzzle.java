import java.io.File;
import java.util.*;

public class WordPuzzle {
  public char[][] grid;
  public static final String dic = "abcdefghijklmnopqrstuvwxyz";
  private static final int DEFAULT_GRID_SIZE = 10;
  
  public WordPuzzle() {
    this(DEFAULT_GRID_SIZE, DEFAULT_GRID_SIZE);
  }
  public WordPuzzle(int row, int column) {
    grid = new char[row][column];
    Random r = new Random();
    Random c = new Random();
    for(int i=0; i<row; i++) {
      for(int j=0; j<column; j++) {
        grid[i][j] = dic.charAt(r.nextInt(26));
      }
    }
  }
  
  public void display() {
    if(grid == null || grid.length == 0) System.out.println("Plese create a new wordpuzzle.");
    System.out.println("Display the grid: ");
    for(int i=0; i < grid.length; i++) {
      for(int j=0; j < grid[0].length; j++) {
        System.out.print(grid[i][j] + "\t");
      }
      System.out.print("\n");
    }
  }
  
  public boolean contains(String word) {
    if(word == null || word.length() == 0) return true;
    if(grid == null || grid.length == 0 || word == null || word.length() == 0) return false;
    int m = grid.length;
    int n = grid[0].length;
    boolean[][] used = new boolean[m][n];
    for(int i=0; i<m; i++) {
        for(int j=0; j<n; j++) {
            if(exist(grid, word, used, i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}

private boolean exist(char[][] board, String word, boolean[][] used, int i, int j, int pos) {
    if(pos == word.length()) return true;
    if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || used[i][j] || board[i][j] != word.charAt(pos)) {
        return false;
    }
    used[i][j] = true;
    boolean res = exist(board, word, used, i - 1, j, pos + 1) || exist(board, word, used, i + 1, j, pos + 1) || exist(board, word, used, i, j - 1, pos + 1) || exist(board, word, used, i, j + 1, pos + 1)
        ||exist(board, word, used, i - 1, j - 1, pos + 1) || exist(board, word, used, i - 1, j + 1, pos + 1) || exist(board, word, used, i + 1, j - 1, pos + 1) || exist(board, word, used, i + 1, j + 1, pos + 1);
    used[i][j] = false;
    return res;
}
 
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Plese type in row number: ");
    int row = sc.nextInt();
    System.out.println("Plese type in column number: ");
    int column = sc.nextInt();
    WordPuzzle w  = new WordPuzzle(row, column);
    w.display();
    MyLinkedList<String> list = new MyLinkedList<>();
    AvlTree<String> tree = new AvlTree<>();
    MyHashTable<String> table = new MyHashTable<>();
    List<String> record = new ArrayList<>();
    try {
      File file = new File("dictionary.txt");
      Scanner s = new Scanner(file);
      while(s.hasNextLine()) {
        String str = s.nextLine();
        list.add(str);
        tree.insert(str);
        table.insert(str);
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    for(String s: list) {
      if(w.contains(s)) record.add(s);
    }
    System.out.println("The wordpuzzle contains " + record.size() + "words");
    System.out.println("They are : ");
    for(String s : record) {
      System.out.println(s);
    }
    
    record.clear();
    List<String> treeList = tree.traverse();
    for(String s : treeList) {
      if(w.contains(s)) record.add(s);
    }
    System.out.println("The wordpuzzle contains " + record.size() + "words");
    System.out.println("They are : ");
    for(String s : record) {
      System.out.println(s);
    }
    
    record.clear();
    List<String> hashList = table.traverse();
    for(String s : hashList) {
      if(w.contains(s)) record.add(s);
    }
    System.out.println("The wordpuzzle contains " + record.size() + "words");
    System.out.println("They are : ");
    for(String s : record) {
      System.out.println(s);
    }
    record.clear();
  }
}
