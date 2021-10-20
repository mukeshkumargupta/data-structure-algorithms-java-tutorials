package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/group-anagrams/
 * https://www.youtube.com/watch?v=0I6IL3TnIZs
 * Category: Medium, Must Do, Google
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]
 

Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        /*
         * Runtime: 24 ms, faster than 20.30% of Java online submissions for Group Anagrams.
Memory Usage: 56.1 MB, less than 7.96% of Java online submissions for Group Anagrams.
         */
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> lookup  = new HashMap<>();

        for (String val : strs) {
            int[] counter = new int[26];
            for (char ch: val.toCharArray()) {
                counter[ch - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int c : counter) {
                sb.append('#');
                sb.append(c);
            }
            String key = sb.toString();
            if (!lookup.containsKey(key)) {
                lookup.put(key, new ArrayList<>());
            }
            lookup.get(key).add(val);
        }
        return new ArrayList(lookup.values());
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
