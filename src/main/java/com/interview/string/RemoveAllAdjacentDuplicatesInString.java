package com.interview.string;
import java.util.*;

/*
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 * Category: Easy, Tricky, Stack
 *
 * Related Problems:
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/ (Medium)
 *
 * Problem Statement:
 * You are given a string `s` consisting of lowercase English letters. A duplicate removal consists
 * of choosing two adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on `s` until we can no longer do so.
 *
 * Return the final string after all such duplicate removals have been made. It can be proven that
 * the answer is unique.
 *
 * Example 1:
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation:
 * - Remove "bb" -> "aaca"
 * - Remove "aa" -> "ca"
 *
 * Example 2:
 * Input: s = "azxxzy"
 * Output: "ay"
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters only.
 */
public class RemoveAllAdjacentDuplicatesInString {
    //String builder and stack both are same approach but string builder one is fast
    public String removeDuplicates(String s) {
        /*
         * Runtime: 9 ms, faster than 83.40% of Java online submissions for Remove All Adjacent Duplicates In String.
Memory Usage: 39.8 MB, less than 58.68% of Java online submissions for Remove All Adjacent Duplicates In String.
         */
        StringBuilder sb = new StringBuilder(); 
        
        for(char c : s.toCharArray()){ 
            if(sb.length() == 0){ 
                sb.append(c);
            } else{ 
                if(sb.charAt(sb.length()-1) == c){ 
                    sb.deleteCharAt(sb.length()-1);
                } else{ 
                    sb.append(c);
                }  
            }    
        }
        
        return sb.toString();
    }
    //Using stack, it is slow
    public String removeDuplicatesM2(String s) {//Prefer this approach
        /*
         * Runtime: 140 ms, faster than 7.02% of Java online submissions for Remove All Adjacent Duplicates In String.
Memory Usage: 41.2 MB, less than 34.82% of Java online submissions for Remove All Adjacent Duplicates In String.
         */
        Stack<Character> st = new Stack<>();
        int l = s.length();
        for (int i = 0; i < l; i++) {
            if (st.empty()) {
                st.push(s.charAt(i));
            } else {
                if (st.peek() == s.charAt(i)) {
                    st.pop();
                } else {
                    st.push(s.charAt(i));
                }
                
            }
        }
        String result = "";
        while (!st.empty()) {
            result = st.pop() + result;
        }
        return result;
    }
}
