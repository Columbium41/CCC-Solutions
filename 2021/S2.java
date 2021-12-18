import java.io.*;
import java.util.*;

public class S2
{
    
    static int m, n, k;
    static int numGold = 0;
    static boolean[][] canvas;
    static boolean[] rows;
    static boolean[] cols;
    // false = black
    // true = gold
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static int numGold() {
        
        for (int i = 0; i < rows.length; i++) {
            
            if (rows[i]) {
                
                for (int j = 0; j < n; j++)
                {

                    canvas[i][j] = !canvas[i][j];

                }
                
            }
            
        }
        
        for (int i = 0; i < cols.length; i++) {
            
            if (cols[i]) {
                
                for (int j = 0; j < m; j++)
                {

                    canvas[j][i] = !canvas[j][i];

                }
                
            }
            
        }
        
        int count = 0;
        
        for (boolean[] arr : canvas)
            for (boolean b : arr)
                if (b)
                    count++;
        
        return count;
        
    }
    
    public static void stroke(String s, int num) {
        
        if (s.equals("R")) {
            
            rows[num - 1] = !rows[num - 1];
            
        }
        
        else {
            
            cols[num - 1] = !cols[num - 1];
            
        }
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        
        m = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        
        canvas = new boolean[m][n];
        rows = new boolean[m];
        cols = new boolean[n];
        for (int i = 0; i < k; i++) {
            
            st = new StringTokenizer(br.readLine());
            stroke( st.nextToken(), Integer.parseInt(st.nextToken()) );
            
        }
        
        System.out.println(numGold());
        
    }
    
}
