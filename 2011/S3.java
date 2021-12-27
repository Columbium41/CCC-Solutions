// 10/10 on DMOJ
// really bad solution lol

import java.util.*;
import java.io.*;

public class S3
{
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static String isCrystal(int m, int x, int y) {
        
        double pow = Math.pow(5, m); // 5 & 25
        double lowPow = Math.pow(5, m - 1); // 1 & 5
        double middle = (pow - 1) / 2; // 2 & 12
        
        if (m == 0) {
            return "empty";
        }
        
        // out of bounds
        if ( (x < lowPow || x > pow - lowPow - 1) || (y >= Math.floor( pow / 2.0 )) ) {
            return "empty";
        }
        
        // within the bottom of the T-shape
        if (y < lowPow) {
            return "crystal";
        }
        
        // within top-middle of the T-shape
        if ( (Math.abs(middle - x) < Math.ceil(lowPow / 2)) && y < 2 * lowPow) {
            return "crystal";
        }
        
        // recursive case
        else {
            
            int newX = x;
            int newY;
            
            while (newX > lowPow) {
                newX -= lowPow;
            }
            
            if (y >= 2 * lowPow){
                newY = y - (2 * (int)lowPow);
            }
            else {
                newY = y - ((int)lowPow);
            }
            
            return isCrystal(m - 1, newX, newY);
            
        }
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < t; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            int m = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            System.out.println(isCrystal(m, x, y));
            
        }
        
    }
    
}
