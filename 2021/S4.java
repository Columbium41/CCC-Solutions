// 7/15 on DMOJ

import java.util.*;
import java.io.*;

public class S4
{

    static int N, W, D;
    static ArrayList<Integer>[] graph;
    static int[] subways;
    static Swap[] days;
    
    static int[] dist;
    
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
    
    public static void DFS(int current, int depth) {
        
        int d = dist[current] + 1;
        
        // check subway
        if (depth < subways.length - 1 && current == subways[depth]) {
            dist[subways[depth + 1]] = d;
            DFS(subways[depth + 1], depth + 1);
        }
        
        // check walkways
        for (int v : graph[current]) {
            if (dist[v] > d) {
                dist[v] = d;
                DFS(v, depth + 1);
            }
        }
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        subways = new int[N];
        days = new Swap[D];

        // Walkways
        for (int i = 0; i < W; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            
            graph[key].add(value);
            
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
            
            Arrays.fill(dist, Integer.MAX_VALUE);
            changeSubway(days[i]);
            dist[1] = 0;
            DFS(1, 0);
            System.out.println(dist[N]);
            
        }

    }
    
}
