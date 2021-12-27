import java.util.*;
import java.io.*;

public class J5
{

    static int n, k;
    static HashMap<String, Integer> memo = new HashMap<>();
    
    static BufferedReader br;
    
    
    public static int DFS(int piLeft, int peopleLeft, int minimum) {
        
        String key = piLeft + " " + peopleLeft + " " + minimum;
        
        // Special cases
        if (k == 1 || k == n) {
            return 1;
        }
        
        // Terminating case
        else if (peopleLeft == 0 && piLeft > 0) {
            return 0;
        }
        
        else if (piLeft == 0 && peopleLeft == 0) {
            return 1;
        }
        
        else if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        // Recursive case: traverse each possibility
        else {
            
            int count = 0;
            
            for (int i = minimum; i <= (int)Math.floor((double)piLeft / peopleLeft); i++) {
                
                count += DFS(piLeft - i, peopleLeft - 1, i);
                
            }
            
            memo.put(key, count);

            return count;
            
        }
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        
        System.out.println(DFS(n, k, 1));
        
    }
    
}
