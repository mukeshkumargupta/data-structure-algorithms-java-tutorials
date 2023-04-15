package com.interview.dynamic;

import java.util.*;
/*
 * 
 * https://leetcode.com/problems/longest-string-chain/
 * Category: Medium, Tricky, lis
 * Refer this solution as well dfs approach
 * https://leetcode.com/problems/longest-string-chain/discuss/1986391/Java-beats-93-DFS-with-memo
 * Related:
 * https://leetcode.com/problems/fraction-to-recurring-decimal/ Medium Bad
 * https://leetcode.com/problems/largest-sum-of-averages/ Medium VVImp
 * https://leetcode.com/problems/sum-of-special-evenly-spaced-elements-in-array/ Hard, Locked
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/ Hard
 * https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/ Hard
 * https://leetcode.com/problems/robot-return-to-origin/ Easy
 * 
 * 1048. Longest String Chain
Medium

3364

165

Add to List

Share
You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.

 

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of lowercase English letters.
 */
public class LongestStringChain {
    boolean checkPossible(String s1, String s2) {
        int p1 = 0;
        int p2 = 0;
        int firstStringLen = s1.length();
        int secondStringLen = s2.length();
        if (firstStringLen != secondStringLen +1) {//alway should be 1 greater
            return false;
        }
        //compare
        while(p1 < firstStringLen && p2 < secondStringLen) {//bigger shoud always exhast
            //System.out.println(p1);
            //System.out.println(p2);
            //System.out.println(firstStringLen);
            //System.out.println(secondStringLen);
            if (s1.charAt(p1) == s2.charAt(p2)) {
                p1++;
                p2++;
            } else {
                p1++;
            }
        }
        //after exhast
        return (p1 == firstStringLen || p1 == firstStringLen -1) && p2 == secondStringLen;
        
    }
    public int longestStrChain(String[] words) {
        /*
        TC: N2 * lenngth of string + NlogN for sorting
        SC: O(N)
        Runtime: 125 ms, faster than 9.92% of Java online submissions for Longest String Chain.
Memory Usage: 45.1 MB, less than 69.46% of Java online submissions for Longest String Chain.
        */
        
        int l = words.length;
        Arrays.sort(words, (a, b) -> {
            return a.length() - b.length();//sort by length
        });
        int[] dp = new int[l];
        //for 1 size
        for (int i = 0; i < l; i++) {
            dp[i] = 1;
        }
        int maxLen = 1;
        for (int i = 1; i < l; i++) {
            for (int j = 0; j < i; j++) {
                if (checkPossible(words[i], words[j])) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        if (dp[i] > maxLen) {
                            maxLen = dp[i];
                        }
                        
                    }
                }
                
            }
            
            
        }
        return maxLen;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] input = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        LongestStringChain instance = new LongestStringChain();
        instance.longestStrChain(input);
        
    }
    
}
