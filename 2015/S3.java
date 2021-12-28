// AC on DMOJ
// Use a TreeSet to store the available gates so that they can be retrieved in logarithmic time

import java.util.*;
import java.io.*;

public class S3
{

    static int G, P;
    
    static BufferedReader br;
    static TreeSet<Integer> availableGates = new TreeSet<>();
    
    public static void main(String[] args) throws IOException
    {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        
        int count = 0;
        
        // Instantiate TreeSet
        for (int i = 1; i <= G; i++) {
            availableGates.add(i);
        }
        
        for (int i = 0; i < P; i++) {
            
            int n = Integer.parseInt(br.readLine());
            
            // Get the highest gate that can be used
            // Holds null if there is no value
            Integer highest = availableGates.floor(n);
            
            if (highest == null) {
                break;
            }
            else {
                availableGates.remove(highest);
                count++;
            }
            
        }
        
        System.out.println(count);
        
    }
    
}
