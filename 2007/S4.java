/* 
 * Charley Liu
 * 2021-12-16
 *
 * A graph theory problem that implements Dynamic Programming optimizations via. memoization
*/

import java.util.*;
import java.io.*;

public class S4
{
    
    static int n;
    static int paths;
    static Map<Integer, ArrayList<Integer> > slides = new HashMap<>();
    static Map<Integer, Integer> memo = new HashMap<>();
    
    public static int search(int i) {
        
        // Starting node found
        if (i == 1) 
            return 1;
        
        // Search parent nodes (if they exist) until starting node is found
        else {
            
            ArrayList<Integer> parentNodes = slides.getOrDefault(i, null);
            
            if (parentNodes != null) {
                
                // Find the amount of unique paths there are to the starting node
                int sum = 0;
                
                for (int node : parentNodes) {
                    
                    // Memo contains key
                    if (memo.containsKey(node))
                        sum += memo.get(node);
                    
                    // Memo doesn't contain key
                    else {
                        
                        int result = search(node);
                        sum += result;
                        
                        memo.put(node, result);
                        
                    }
                    
                }
                
                return sum;
                
            } 
            
        }
        
        return 0;
        
    }
    
    public static int solve() {
        
        paths = 0;
        
        ArrayList<Integer> start = slides.getOrDefault(n, null);
        
        if (start != null)
        {
            
            Collections.sort(start);
            
            // Go from biggest values to smallest
            for (int i = start.size() - 1; i >= 0; i--)
            {
                
                paths += search( start.get(i) );

            }

        }
        //System.out.println(memo);
        //System.out.println(slides);
        return paths;
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = null;
        
        while (true) {
            
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            if (x == 0 && y == 0)
                break;
            
            // Key: Child Slide
            // Value: Parent Slide(s)
            else if (slides.containsKey(y)) 
                slides.get(y).add(x);
            
            else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(x);
                slides.put(y, temp);
            }
            
        }
        
        System.out.println(solve());
        
    }
    
}
