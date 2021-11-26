package com.interview.recursionBacktracking;

import java.util.*;
/*
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 * https://www.youtube.com/watch?v=bHZkCAcj3dc
 * Some part in last not clear need to lookback
 * Try to solve using iterative solution to make better understanding
 * Category: Must Do, Medium, Top150,
 * Related: https://leetcode.com/problems/longest-subsequence-repeated-k-times/ Hard
 * https://leetcode.com/problems/number-of-equal-count-substrings/ Medium  
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

 

Example 1:

Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 

Constraints:

1 <= s.length <= 104
s consists of only lowercase English letters.
1 <= k <= 105
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        /*
         * Runtime: 1 ms, faster than 82.24% of Java online submissions for Longest Substring with At Least K Repeating Characters.
Memory Usage: 37.2 MB, less than 72.47% of Java online submissions for Longest Substring with At Least K Repeating Characters.
TC: O(N)
in Map, only 26 character feed so space is constant
O(1)
         */
        int l = s.length();
        if (l == 0 || l < k)
            return 0;
        if (k <= 1)
            return l;
        
        Map<Character, Integer> counts = new HashMap();
        for (char c : s.toCharArray())
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        
        int start = 0;
        while (start < l && counts.get(s.charAt(start)) >= k)
            start++;
        // either first cond break or second above condition
        if (start >= l - 1)
            return start;// Note: it can not be == cond sufficient, > also required
            
        int ls1 = longestSubstring(s.substring(0, start), k);// here start is already one more so no need to add +1
        // Optimization , check how many still invalid keep ignoring those cases
        while (start < l && counts.get(s.charAt(start)) < k)
            start++;
        
        //This part is not clear need to look back
        int ls2 = (start < l) ? longestSubstring(s.substring(start), k) : 0;
        return Math.max(ls1, ls2);
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
