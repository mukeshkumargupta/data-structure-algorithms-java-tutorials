package com.interview.stackqueue.A_Basic;

import java.util.*;
/*
 * Reference : https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string
 * https://www.youtube.com/watch?v=0RSiKxwgE9U
 * Category: Easy
 */
public class C_B_RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        int l = s.length();
        for (int i= 0; i < l; i++) {
            if (stack.size() == 0 ) {
                stack.push(s.charAt(i));
                //System.out.println(s.charAt(i));
            } else if (stack.size() > 0 && stack.peek() != s.charAt(i)) {
                stack.push(s.charAt(i));
                //System.out.println(s.charAt(i));
            } else {
                while (stack.size() > 0 && stack.peek() == s.charAt(i)) {
                    //System.out.println(s.charAt(i));
                    stack.pop();
                }
            }
            
        }
        String result = "";
        while(!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        C_B_RemoveAllAdjacentDuplicatesInString r = new C_B_RemoveAllAdjacentDuplicatesInString();
        r.removeDuplicates("abbaca");
        
    }
    
}
