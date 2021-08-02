package com.interview.recursionBacktracking;

import java.util.*;
//Not working
public class GenerateParentheses {
    private  void generateParenthesis(int n, int open, int close, StringBuilder s, List<String> results) {
        if(open == n-1 && close == n-1) {
            results.add(s.toString()); 
        } else {
            if (open < n ) {
                generateParenthesis(n, open+1, close, s.append("("), results);
            } 
            
            if (close < open) {
               generateParenthesis(n, open, close+1, s.append(")"), results); 
            }
            
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<String>();
        StringBuilder s = new StringBuilder();
        generateParenthesis(n, 0, 0, s, results);
        return results;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GenerateParentheses gp = new GenerateParentheses();
        List<String> arrayList = gp.generateParenthesis(3);
        for(String s: arrayList) {
            System.out.println(s);
            System.out.println("\n");
        }

        
    }
    
}
