package com.interview.stackqueue;

/*
 * https://leetcode.com/problems/remove-duplicate-letters
 * https://www.youtube.com/watch?v=muDlIlVE1q4
 * Category: Medium,Tricky
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
 * 
 * 
 */
import java.util.*;
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Stack<Integer> stack = new Stack();
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> lastIndexMap = new HashMap<>();
        int l = s.length();
        //Maintain last index
        for (int i = 0; i < l; i++) {
            lastIndexMap.put(s.charAt(i) - 'a', i);
        }
        for (int i = 0; i < l; i++) {
            int ch = s.charAt(i) - 'a';
            if (set.contains(ch)) {
               continue;
            }
            
            while (stack.size() > 0 && stack.peek() > ch && lastIndexMap.get(stack.peek()) > i) {
                set.remove(stack.peek());
                stack.pop();
            }
            stack.push(ch);
            set.add(ch);
            
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append((char) (stack.pop() + 'a'));
        }
        return sb.reverse().toString();
        
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RemoveDuplicateLetters rdl = new RemoveDuplicateLetters();
        rdl.removeDuplicateLetters("bcabc");
    }
    
}
