import java.io.File;
import java.util.*;


public class Kruskals {
  public List<Edge> edges;
  public int num;
  public List<Vertex>vertexs;
  public Kruskals(File file) {
    try {
      Scanner sc = new Scanner(file);
      vertexs = new ArrayList<>();
      while(sc.hasNextLine()) {
        String str = sc.nextLine();
        Vertex v = new Vertex(str);
        v.id = num;
        vertexs.add(v);
        num++;
      }
      edges = new ArrayList<>();
      int len = vertexs.size();
      for(int i=0; i < len; i++) {
        Vertex u = vertexs.get(i);
        for(int j=i; j < len; j++) {
          Vertex v = vertexs.get(j);
          if(u.isExist(v)) {
            edges.add(new Edge(u, v));
          }
        }
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public ArrayList<Edge> kruskal( List<Edge> edges, int numVertices ) {
      DisjSets ds = new DisjSets( numVertices );
      PriorityQueue<Edge> pq = new PriorityQueue<>( 30, new Comparator<Edge>() {
        public int compare(Edge o1, Edge o2) {
          return o1.distance - o2.distance;
        }
      });
      for(Edge e : edges) {
        pq.add(e);
      }
      ArrayList<Edge> mst = new ArrayList<>( );
      while( mst.size( ) != numVertices - 1 ) {
          Edge e = pq.poll();       // Edge e = (u, v)
          int uset = ds.find( e.getu( ) );
          int vset = ds.find( e.getv( ) );
          if( uset != vset ) {
              // Accept the edge
              mst.add( e );
              ds.union( uset, vset );
          } 
       }
      return mst; 
  }
  
  class Edge {
    
    public int distance;
    public Vertex start;
    public Vertex end;
    
    public Edge(Vertex start, Vertex end) {
      this.start = start;
      this.end = end;
      this.distance = start.getDistance(end);
    }
    
    public int getu() {
      return start.id;
    }
    
    public int getv() {
      return end.id;
    }
    
  }
  
  class Vertex {
    public String name;
    public int id;
    public HashMap<String, Integer> ajEdges;
    
    public Vertex(String s) {
      String[] array = s.split(",");
      name = array[0];
      ajEdges = new HashMap<>();
      for(int i=1; i< array.length; i = i+2) {
        ajEdges.put(array[i], Integer.valueOf(array[i+1]));
      }
      id = 0;
    }
    
    public boolean isExist(Vertex end) {
      return this.ajEdges.containsKey(end.name);
    }
    
    public int getDistance(Vertex end) {
      return this.ajEdges.get(end.name);
    }
  }
  
  public static void main(String[] args) {
    try {
      File file = new File("assn9_data.csv");
      Kruskals ks = new Kruskals(file);
      ArrayList<Edge> mst = ks.kruskal(ks.edges, ks.num);
      int sum = 0;
      for(Edge e : mst) {
        System.out.println(e.start.name + " ---> " + e.end.name + "  " + e.distance);
        sum = sum + e.distance;
      }
      System.out.println("The total distances is" + " " + sum);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
