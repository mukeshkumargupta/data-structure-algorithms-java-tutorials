package com.interview.hash;
import java.util.*;
/*
 * https://leetcode.com/problems/partition-labels/
 * Category: Medium, Tricky, Top100
 * https://www.youtube.com/watch?v=kS4P0vXbGmc
 * Related:
 *  https://leetcode.com/problems/merge-intervals/  Medium
 * https://leetcode.com/problems/optimal-partition-of-string/   Medium
 *  https://leetcode.com/problems/longest-word-in-dictionary/ Medium
 * https://leetcode.com/problems/construct-k-palindrome-strings/ Medium
 * https://leetcode.com/problems/maximum-repeating-substring/ Easy
 *
 * 
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
Derived Question: if it asked all substring then also you can find it
Return a list of integers representing the size of these parts.

 

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
Example 2:

Input: s = "eccbbbbdec"
Output: [10]
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 * 
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        /*
         * Runtime: 2 ms, faster than 99.42% of Java online submissions for Partition Labels.
Memory Usage: 37.5 MB, less than 85.70% of Java online submissions for Partition Labels.
TC: O(N)
SC: O(N)
         */
        int[] lookup = new int[26];
        int l = s.length();
        //sacn all element and store its last occurance index
        for (int i = 0; i < l ; i++) {
            lookup[s.charAt(i) - 'a'] = i;
        }
        int start = 0, end = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < l; i++) {
            end = Math.max(lookup[s.charAt(i) - 'a'], end);
            if (i == end) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
