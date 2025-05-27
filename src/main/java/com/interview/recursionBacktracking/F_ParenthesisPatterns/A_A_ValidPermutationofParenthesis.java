package com.interview.recursionBacktracking.F_ParenthesisPatterns;

/*
 * https://www.youtube.com/watch?v=pD2VNU2x8w8&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=8
 * Category: Medium, Tricky
 *
 * https://leetcode.com/problems/generate-parentheses/description/
 * Related:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/ Medium
 * https://leetcode.com/problems/valid-parentheses/ Easy
 * https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/ Medium
 * 
 */
public class A_A_ValidPermutationofParenthesis {
    private static void  printAllValidBracket(int n, int open , int close, String output) {
        if (open == n && close == n) {
            System.out.println(output);
            return;
        }
        
        if (open < n) {
            printAllValidBracket(n, open+1, close, output + "(");
        } 
        if (close < open) {
            printAllValidBracket(n, open, close+1, output + ")");
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String output = new String();
        printAllValidBracket(3, 0, 0, output);
        
    }
    
}
