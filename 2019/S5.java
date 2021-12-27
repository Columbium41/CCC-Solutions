// 0/15 WA
package DataStructures;

import java.util.*;
import java.io.*;

public class S5
{

    static int n, k;
    static ArrayList<Integer>[] triangle;
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static int getMax(int level, int cell, int depth) {

        // Bottom node
        if (depth == k - 1) {
            return triangle[level].get(cell);
        }
        
        // Recursive case
        else {
            
            int max = triangle[level].get(cell);
            
            max = Math.max(max, getMax(level + 1, cell, depth + 1));
            max = Math.max(max, getMax(level + 1, cell + 1, depth + 1));
            
            return max;
            
        }
        
    }
    
    public static int solve() {
        
        int sum = 0;
        
        // Iterate over each level
        for (int i = 0; i <= n - k; i++) {
            
            // Iterate over each cell at the top level
            for (int j = 0; j < triangle[i].size(); j++) {
                
                sum += getMax(i, j, 0);
                
            }
            
        }
        
        return sum;
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        triangle = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            triangle[i] = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                triangle[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        
        System.out.println(solve());
        
    }
    
}
