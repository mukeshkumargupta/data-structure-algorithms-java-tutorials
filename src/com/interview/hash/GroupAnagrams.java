package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/group-anagrams/
 * https://www.youtube.com/watch?v=0I6IL3TnIZs
 * 
 * Category: Medium, Must Do, Google, Top150
 * Related: https://leetcode.com/problems/group-shifted-strings/ Locked
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
        Map<String, List<String>> lookup  = new HashMap<>();

        for (String val : strs) {
            int[] counter = new int[26];
            for (char ch: val.toCharArray()) {
                counter[ch - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            //Note both are working, even map we can take but result is not matching with that
            for (int i = 0; i < 26; i++) {
                sb.append('a' + i);
                sb.append(counter[i]);
            }
            /*for (int c : counter) {
                sb.append('#');
                sb.append(c);
            }*/
            String key = sb.toString();
            if (!lookup.containsKey(key)) {
                lookup.put(key, new ArrayList<>());
            }
            lookup.get(key).add(val);
        }
        return new ArrayList(lookup.values());
        
    }
    
    public List<List<String>> groupAnagrams_P1(String[] strs) {
        /*
         * Runtime: 55 ms, faster than 11.18% of Java online submissions for Group Anagrams.
Memory Usage: 70.5 MB, less than 6.12% of Java online submissions for Group Anagrams.
         */
        List<List<String>> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Map<String, List<String>> groupMap = new HashMap<>();
        
        
        for (String str : strs) {
            int length = str.length();
            Map<Character, Integer> freq = new HashMap<>();
            for (int i = 0; i < length; i++) {
                char ch = str.charAt(i);
                freq.put(ch, freq.getOrDefault(ch, 0) +1);
            }
            
            //create hash code and check
            String hash = "";
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (freq.containsKey(ch)) {
                    hash = hash + ch + freq.get(ch);
                }
            }
            List<String> temp = null;
            if (!groupMap.containsKey(hash)) {
                temp = new ArrayList<>();

            } else {
                temp = groupMap.get(hash); 
            }
            temp.add(str);
            groupMap.put(hash, temp);
            
        }
        for (String key : groupMap.keySet()) {
            result.add(groupMap.get(key));
        }
        
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] input = {"eat","tea","tan","ate","nat","bat"};
        GroupAnagrams ga = new GroupAnagrams();
        ga.groupAnagrams(input);
        
    }
    
}
