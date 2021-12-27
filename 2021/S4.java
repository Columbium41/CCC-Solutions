// 4/15 on DMOJ
import java.util.*;
import java.io.*;

public class S4
{

    static int N, W, D;
    static Map<Integer, ArrayList<Integer> > walkways;
    static int[] subways;
    static Swap[] days;
    
    static BufferedReader br;
    static StringTokenizer st;
    
    static class Swap {
        
        int s1, s2;
        
        public Swap(int s1, int s2) {
            this.s1 = s1;
            this.s2 = s2;
        }
        
        @Override
        public String toString() {
            return (this.s1 + " swaps to " + this.s2);
        }
        
    }
    
    public static void changeSubway(Swap s) {
        
        int temp = subways[s.s1 - 1];
        subways[s.s1 - 1] = subways[s.s2 - 1];
        subways[s.s2 - 1] = temp;
        
    }
    
    // Returns a list of subway nodes that are adjacent to node n
    public static void getAdjacent(int node, int time, HashSet<Integer> hs) {
        
        // Add subway node
        if (time + 1 < subways.length) {
            
            hs.add(subways[time + 1]);
            
        }
        
        // Add nodes from walkways
        if (walkways.containsKey(node))
        {
            for (int adjNode : walkways.get(node))
            {

                hs.add(adjNode);

            }
        }
        
    }
    
    public static int BFS() {
        
        int mins = 0;
        
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        
        q1.addLast(1);
        
        boolean qOneUsed = true;
        
        // Deque all elements from the queue and add new elements to the next queue
        while (!q1.isEmpty() || !q2.isEmpty()) {
            
            HashSet<Integer> adjacentNodes = new HashSet<>();
                
            // Get the adjacent nodes
            if (qOneUsed) {
                int size = q1.size();
                for (int i = 0; i < size; i++) {
                    getAdjacent(q1.removeFirst(), mins, adjacentNodes);
                }
            }
            
            else {
                int size = q2.size();
                for (int i = 0; i < size; i++) {
                    getAdjacent(q2.removeFirst(), mins, adjacentNodes);
                }
            }
            
            // Traverse each adjacent node and add to queue
            for (int adjNode : adjacentNodes) {
                
                // Nth subway found
                if (adjNode == N) {
                    return mins + 1;
                }
                
                // Add adjacent node to either queue
                else {
                    
                    if (qOneUsed) {
                        q2.addLast(adjNode);
                    }
                    else {
                        q1.addLast(adjNode);
                    }
                    
                }
                
            }
            
            qOneUsed = !qOneUsed;
            mins++;
            
        }
        
        return Integer.MAX_VALUE;
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        walkways = new HashMap<>();
        subways = new int[N];
        days = new Swap[D];

        // Walkways
        for (int i = 0; i < W; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            
            if (walkways.containsKey(key))
                walkways.get(key).add(value);
            
            else {
                
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(value);
                walkways.put(key, temp);
                
            }
            
        }
        
        // Subway nodes
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            subways[i] = Integer.parseInt(st.nextToken());
        
        // swaps
        for (int i = 0; i < D; i++) {
            st = new StringTokenizer(br.readLine());
            days[i] = new Swap( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) );
        } 
        
        for (int i = 0; i < days.length; i++) {
            
            changeSubway(days[i]);
            
            System.out.println(BFS());
            
        }

    }
    
}
