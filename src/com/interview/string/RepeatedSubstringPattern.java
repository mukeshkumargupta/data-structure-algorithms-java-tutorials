package com.interview.string;

/*
 * https://leetcode.com/problems/repeated-substring-pattern/
 * https://www.youtube.com/watch?v=p92_kEjyJAo&list=PL1w8k37X_6L-bCZ3m0FFBZmRv4onE7Zjl&index=30
 * Other solution: take map which keep index of element present, and if found then take diff of current emlemnt and that element and keep comapre
 * Category: Easy, Google, Tricky
 * Related:
 * https://leetcode.com/problems/repeated-string-match/ Medium
 * Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.

 

Example 1:

Input: s = "abab"
Output: true
Explanation: It is the substring "ab" twice.
Example 2:

Input: s = "aba"
Output: false
Example 3:

Input: s = "abcabcabcabc"
Output: true
Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
 */
public class RepeatedSubstringPattern {
    /*
     * This solution is to trick and hard to remember, so take map and count each element and check each frequency is same, if yes return true otherwise false
     */
    public boolean repeatedSubstringPattern(String s) {
        /*
         * Runtime: 71 ms, faster than 37.93% of Java online submissions for Repeated Substring Pattern.
Memory Usage: 39.5 MB, less than 70.41% of Java online submissions for Repeated Substring Pattern.
         */
        return (s + s).substring(1, s.length() *2 -1).contains(s); 

        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
