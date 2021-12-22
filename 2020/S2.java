import java.io.*;
import java.util.*;

public class S2 {
    static int m, n;
    static int[][] room;
    static boolean[][] visited;
    static ArrayDeque<Coordinate> queue = new ArrayDeque<>();
    
    // Key: The value of a cell
    // Value: A list of coordinates containing that value
    static Map<Integer, ArrayList<Coordinate> > map = new HashMap<>();
    
    static BufferedReader br;
    static StringTokenizer st;
    
    static class Coordinate {
        
        int r, c;
        
        public Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        @Override
        public String toString() {
            return ("(" + this.r + ", " + this.c + ")");
        }
        
    }
    
    public static String solve(Coordinate c) {
        
        queue.add(c);
        
        while (!queue.isEmpty()) {
            
            Coordinate front = queue.removeFirst();
            ArrayList<Coordinate> adj = map.getOrDefault(front.r * front.c, new ArrayList<>() );
            
            // Traverse adjacent nodes
            for (Coordinate adjNode : adj) {
                
                // Root node found
                if (adjNode.r == 1 && adjNode.c == 1)
                    return "yes";
                
                // Node hasn't been travelled (mark it as visited and push to queue)
                else if (!visited[adjNode.r - 1][adjNode.c - 1]) {
                    visited[adjNode.r - 1][adjNode.c - 1] = true;
                    queue.addLast(adjNode);
                }
                
            }
            
        }
        
        return "no";
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        room = new int[m][n];
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < n; j++) {
                
                room[i][j] = Integer.parseInt(st.nextToken());
                
                int key = room[i][j];
                
                if (map.containsKey(key))
                    map.get(key).add(new Coordinate(i + 1, j + 1));
                else {
                    
                    ArrayList<Coordinate> temp = new ArrayList<>();
                    temp.add(new Coordinate(i + 1, j + 1));
                    map.put(key, temp);
                    
                }
                
            }
            
        }
        
        System.out.println(solve(new Coordinate(m, n)));
        
    }
}
