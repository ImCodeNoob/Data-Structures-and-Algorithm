import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Maze {
  
  private int[] grid;
  private int row;
  private int column;
  private static int UP = 1;
  private static int RIGHT = 2;
  private static int DOWN = 4;
  private static int LEFT = 8;
  
  public Maze(int m, int n) {
    this.row = m;
    this.column = n;
    this.grid = new int[m*n];
    for(int i=0; i< row * column; i++) {
      grid[i] = UP | RIGHT | DOWN | LEFT;
    }
  }
  
  public Maze() {
    this.row = 20;
    this.column = 20;
    this.grid = new int[20 * 20];
    for(int i=0; i < 20 * 20; i++) {
      grid[i] = UP | RIGHT | DOWN | LEFT;
    }
  }
  
  public static Maze createMaze(int row, int column) {
    Maze maze  = new Maze(row, column);
    DisjSets ds = new DisjSets(row * column);
    Random r = new Random();
    List<Wall> walls = new ArrayList<>();
    for(int i=0; i<row; i++) {
      for(int j=0; j<column; j++) {
        if(i > 0) {
          walls.add(new Wall(i * column + j, UP));
        }
        if(j > 0) {
          walls.add(new Wall(i * column + j, LEFT));
        }
      }
    }
    int dsSize = row * column;
    while(dsSize > 1) {
      int wallIndex = r.nextInt(walls.size());
      int cell1 = walls.get(wallIndex).cell;
      int cell2 = walls.get(wallIndex).direction == UP ? cell1 - column: cell1 - 1;
      if(ds.find(cell1) != ds.find(cell2)) {
        if(walls.get(wallIndex).direction == UP) {
          maze.grid[cell1] = maze.grid[cell1] ^ UP;
          maze.grid[cell2] = maze.grid[cell2] ^ DOWN;
        }
        else {
          maze.grid[cell1] = maze.grid[cell1] ^ LEFT;
          maze.grid[cell2] = maze.grid[cell2] ^ RIGHT;
        }
        ds.union(ds.find(cell1), ds.find(cell2));
        dsSize--;
      }
      walls.remove(wallIndex);
    }
    return maze;
  }
  
  public static class Wall {
    private int cell;
    private int direction;
    
    public Wall(int cell, int direction) {
      this.cell = cell;
      this.direction = direction;
    }
  }
  
  private int startX = 8;
  private int startY = 30;
  private int size = 15;

  public void display(Graphics2D g) {
      for (int i = 0; i < row; i++) {
          for (int j = 0; j < column; j++) {
              int cell = grid[j + i*column];
              if ((cell & LEFT) != 0 && (i != 0 || j != 0)) {
                  drawLeftWall(g, i, j);
              }
              if ((cell & RIGHT) != 0 && (i != row - 1 || j != column - 1)) {
                  drawRightWall(g, i, j);
              }
              if ((cell & UP) != 0 && (i != 0 || j != 0)) {
                  drawUpperWall(g, i, j);
              }
              if ((cell & DOWN) != 0 && (i != row - 1 || j != column - 1)) {
                  drawLowerWall(g, i, j);
              }
          }
      }
  }
  
  private void drawLeftWall(Graphics2D g, int ro, int col) {
      g.drawLine(startX + size*col, startY + size*ro,
                 startX + size*col, startY + size*(ro+1));       
  }
  
  private void drawRightWall(Graphics2D g, int ro, int col) {
      g.drawLine(startX + size*(col+1), startY + size*ro,
                 startX + size*(col+1), startY + size*(ro+1));
  }
  
  private void drawUpperWall(Graphics2D g, int ro, int col) {
      g.drawLine(startX + size*col,     startY + size*ro,
                 startX + size*(col+1), startY + size*ro);
  }
  
  private void drawLowerWall(Graphics2D g, int ro, int col) {
      g.drawLine(startX + size*col,     startY + size*(ro+1),
                 startX + size*(col+1), startY + size*(ro+1));
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt();
    int n = sc.nextInt();
    Maze mz = createMaze(m, n);
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
          JFrame frame = new JFrame() {
              private static final long serialVersionUID = 1L;

              @Override
              public void paint(Graphics g) {
                  mz.display((Graphics2D) g);
              }
          };
          
          frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          GroupLayout layout = new GroupLayout(frame.getContentPane());
          frame.getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
                  layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                          .addGap(0, 400, Short.MAX_VALUE));
          layout.setVerticalGroup(
                  layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                          .addGap(0, 400, Short.MAX_VALUE));
          frame.pack();
          
          frame.setVisible(true);
       }
    });
    
  }

}
