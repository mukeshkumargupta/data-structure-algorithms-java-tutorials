package com.interview.hash;

import java.util.HashSet;
import java.util.Set;

/*
 * https://leetcode.com/problems/check-if-the-sentence-is-pangram/
 * Category: Easy
 * A pangram is a sentence where every letter of the English alphabet appears at least once.

Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.

 

Example 1:

Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
Output: true
Explanation: sentence contains at least one of every letter of the English alphabet.
Example 2:

Input: sentence = "leetcode"
Output: false
 */
public class CheckiftheSentenceIsPangram {
    public boolean checkIfPangram(String sentence) {//runtime 54.54
        int l = sentence.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < l ; i++) {
            set.add(sentence.charAt(i));
            
        }
        
        for (int i = 0; i < 26; i++) {
            char ch = (char)('a' + i);
            if (!set.contains(ch)) {
                return false;
            }
        }
        return true;
        
    }
}
