package com.interview.hash;

import java.util.*;

/*
 * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 * Category: Medium, Tricky Open Text
 * Related: https://leetcode.com/problems/removing-minimum-and-maximum-from-array/
 * 
 * A string s is called good if there are no two different characters in s that have the same frequency.

Given a string s, return the minimum number of characters you need to delete to make s good.

The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

 

Example 1:

Input: s = "aab"
Output: 0
Explanation: s is already good.
Example 2:

Input: s = "aaabbbcc"
Output: 2
Explanation: You can delete two 'b's resulting in the good string "aaabcc".
Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
Example 3:

Input: s = "ceabaacb"
Output: 2
Explanation: You can delete both 'c's resulting in the good string "eabaab".
Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 */
public class MinDeletion {
    public int minDeletions(String s) {
        int[] freqCounter = new int[26];
        for (char ch : s.toCharArray()) {
            freqCounter[ch - 'a']++;
        }
        Set<Integer> seenCharacterFrequency = new HashSet<>();
        int result = 0;
        for (int i = 0; i < 26; i++) {
            while (freqCounter[i] > 0) {
                if (!seenCharacterFrequency.contains(freqCounter[i])) {
                    seenCharacterFrequency.add(freqCounter[i]);
                    break;
                }
                freqCounter[i]--;
                result++;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
