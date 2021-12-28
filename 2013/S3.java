package Recursion;

import java.util.*;
import java.io.*;

public class S3
{
    
    static int t, g;
    static int[] points = new int[4];
    static ArrayList<UnorderedPair> played = new ArrayList<>();
    
    static BufferedReader br;
    static StringTokenizer st;
    
    static class UnorderedPair {
        
        int a, b;
        
        public UnorderedPair(int a, int b) {
            this.a = a;
            this.b = b;
        }
        
        // check if two pairs are the same (order doesn't matter)
        public boolean isEqual(UnorderedPair up) {
            return ( (this.a == up.a && this.b == up.b)  || (this.a == up.b && this.b == up.a) );
        }
        
        @Override
        public String toString() {
            return (this.a + " " + this.b);
        }
        
    }
    
    // Get the index of the max element of an array (return -1 if there are duplicate max values)
    public static int indexMax(int[] arr) {
        
        int max = Integer.MIN_VALUE;
        int index = -1;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
            else if (arr[i] == max) {
                index = -1;
            }
        }
        
        return index;
        
    }
    
    // Check if a team has been played or not via an unordered pair
    public static boolean hasPlayed(ArrayList<UnorderedPair> al, UnorderedPair pair) {
        
        for (UnorderedPair up : al) {
            if (up.isEqual(pair))
                return true;
        }
        
        return false;
        
    }
    
    public static int solve(int gamesPlayed, int[] scores, ArrayList<UnorderedPair> played) {
        
        // Terminating case
        if (gamesPlayed == 6) {
            
            // favorite team won
            if (indexMax(scores) == t - 1) {
                return 1;
            }
            
            return 0;
            
        }
        
        // Recursive case
        int count = 0;
        
        // Get the next match
        boolean found = false;
        for (int teamOne = 1; teamOne <= 4 && !found; teamOne++) {
            for (int teamTwo = teamOne + 1; teamTwo <= 4 && !found; teamTwo++) {
                
                UnorderedPair match = new UnorderedPair(teamOne, teamTwo);
                
                if (!hasPlayed(played, match)) {
                    
                    found = true;
                    
                    // Clone objects so that they don't change the original object
                    ArrayList<UnorderedPair> clone = (ArrayList<UnorderedPair>) played.clone();
                    clone.add(match);
                    
                    int[] scores1 = scores.clone();
                    int[] scores2 = scores.clone();
                    int[] scores3 = scores.clone();
                    
                    scores1[teamOne - 1] += 3;
                    scores2[teamTwo - 1] += 3;
                    scores3[teamOne - 1]++;
                    scores3[teamTwo - 1]++;
                    
                    // calculate every game possibility
                    count += solve(gamesPlayed + 1, scores1, clone);
                    count += solve(gamesPlayed + 1, scores2, clone);
                    count += solve(gamesPlayed + 1, scores3, clone);
                    
                }
                
            }
        }
        
        return count;
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        g = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < g; i++) {
            
            st = new StringTokenizer(br.readLine());
            int a, b, as, bs;
            
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            as = Integer.parseInt(st.nextToken());
            bs = Integer.parseInt(st.nextToken());
            
            played.add(new UnorderedPair(a, b));
            
            if (as > bs) {
                points[a - 1] += 3;
            }
            else if (bs > as) {
                points[b - 1] += 3;
            }
            else {
                points[a - 1]++;
                points[b - 1]++;
            }
            
        }
        
        System.out.println(solve(g, points, played));
        
    }
    
}
