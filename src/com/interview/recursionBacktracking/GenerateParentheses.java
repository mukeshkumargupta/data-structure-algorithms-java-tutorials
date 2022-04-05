package com.interview.recursionBacktracking;

import java.util.*;
/*
 * https://www.youtube.com/watch?v=pD2VNU2x8w8&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=8
 * https://leetcode.com/problems/generate-parentheses
 * Category: Medium, Must Do, Top150
 * Related:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/ Medium
 */
public class GenerateParentheses {
    private  void generateParenthesis(int n, int open, int close, String s, List<String> results) {  //runtime 73%
        if(open == n && close == n) {
            results.add(s.toString());
            //System.out.println(s.toString());
            return;
        }
        
        if (open < n ) {
            generateParenthesis(n, open+1, close, s + "(", results);
        } 
        
        if (close < open) {
           generateParenthesis(n, open, close+1, s + ")", results); 
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<String>();
        generateParenthesis(n, 0, 0, "", results);
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
