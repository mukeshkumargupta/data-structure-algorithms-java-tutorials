package com.interview.hash;

/*
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 * https://www.youtube.com/watch?v=jK5a8T9q4pc&t=2s
 * Category: Easy
 * Related: https://leetcode.com/problems/minimize-malware-spread-ii/ Hard Good question
 * https://leetcode.com/problems/remove-vowels-from-a-string/ Easy Locked
 * https://leetcode.com/problems/count-common-words-with-one-occurrence/ Easy Must Do
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
 */
public class VerifyinganAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Verifying an Alien Dictionary.
Memory Usage: 38.8 MB, less than 62.17% of Java online submissions for Verifying an Alien Dictionary.
         */
        int[] mapping = new int[26];
        for (int i = 0; i < order.length(); i++) {
            mapping[order.charAt(i) -'a'] = i;
            
        }
        
        for (int i = 0; i < words.length -1; i++) {
            String current = words[i];
            String next = words[i+1];
            int min = Math.min(current.length() , next.length());
            int j;
            for (j = 0; j < min; j++) {
                if (mapping[current.charAt(j) -'a'] > mapping[next.charAt(j) -'a']) {
                    return false;
                }
                
                if (mapping[current.charAt(j) -'a'] < mapping[next.charAt(j) -'a']) {
                   break;
                }
                
            }
            //apple, app case "abcdefghijklmnopqrstuvwxyz", if second is exahsted
            if (j == min && current.length() > next.length()) {
                return false;
            }
        }
        return true;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
