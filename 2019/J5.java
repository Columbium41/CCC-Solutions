// 4/15 on DMOJ
// 14/15 on CCC grader

import java.util.*;
import java.io.*;

public class J5
{
    
    static int S;
    static String I, F;
    
    static BufferedReader br;
    static StringTokenizer st;
    
    static String[] keys = new String[3];
    static String[] values = new String[3];
    
    static class Instruction {
        
        int r, p;
        String s;
        
        public Instruction(int r, int p, String s) {
            this.r = r;
            this.p = p;
            this.s = s;
        }
        
        @Override
        public String toString() {
            return (this.r + " " + this.p + " " + this.s);
        }
        
    }
    
    public static ArrayList<Instruction> solve(ArrayList<Instruction> arr) {
        
        // ending node
        if (arr.size() == S) {
            
            // Right solution
            if (arr.get(S - 1).s.equals(F)) {
                return arr;
            }
            
            // Wrong solution
            else {
                return null;
            }
            
        }
        
        // recursive case
        String sequence;
        if (arr.isEmpty()) {
            sequence = I;
        } 
        else {
            sequence = arr.get(arr.size() - 1).s;
        }
        
        // Traverse each possible key
        for (int i = 0; i < keys.length; i++) {
            
            String key = keys[i];
            
            // Traverse the sequence and check if the sequence contains the key
            for (int j = 0; j <= sequence.length() - key.length(); j++) {
                
                String sub = sequence.substring(j, j + key.length());
                
                if (sub.equals(key)) {
                    
                    ArrayList<Instruction> clone = (ArrayList<Instruction>)arr.clone();
                    
                    String prev = sequence.substring(0, j);
                    String after = sequence.substring(j + key.length(), sequence.length());
                    String newSequence = prev + values[i] + after;
                    
                    clone.add(new Instruction(i + 1, j + 1, newSequence));
                    
                    ArrayList<Instruction> ans = solve(clone);

                    if (ans != null) {
                        return ans;
                    }
                    
                }
                
            }
            
        }
        
        return null;
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            keys[i] = st.nextToken();
            values[i] = st.nextToken();
        }
        
        st = new StringTokenizer(br.readLine());
        
        S = Integer.parseInt(st.nextToken());
        I = st.nextToken();
        F = st.nextToken();
        
        ArrayList<Instruction> temp = new ArrayList<>();
        ArrayList<Instruction> solution = solve(temp);
        
        for (Instruction i : solution) {
            System.out.println(i);
        }
        
    }
    
}
