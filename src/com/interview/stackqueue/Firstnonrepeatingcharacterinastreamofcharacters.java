package com.interview.stackqueue;

import java.util.*;
/*
 * https://www.interviewbit.com/problems/first-non-repeating-character-in-a-stream-of-characters/# Medium
 * Related:
 * https://leetcode.com/problems/first-unique-character-in-a-string/ Easy
 */

public class Firstnonrepeatingcharacterinastreamofcharacters {
    public static String solve(String A) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        Map<Character, Boolean> lookup = new HashMap<>();
    
        int l = A.length();
        for (int i = 0; i < l; i++) {
            Character ch = A.charAt(i);
            if (!lookup.containsKey(ch)) {
                lookup.put(ch, true);
                q.add(ch);
                //System.out.println(ch);
            } else {
                //Remove that TreeNode
                //System.out.println("Rem "+ ch);
                q.remove(ch);
                
            }
            if (q.size() > 0) {
                //System.out.println("First Unique" + q.peek());
                sb.append(q.peek());
            } else {
                sb.append('#');
            }
            

        }
        //System.out.println("O " + sb.toString());
        return sb.toString();
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String result = solve("abcabc");
        System.out.println(result);
        
    }
    
}
