package com.interview.hash;

import java.util.HashSet;
import java.util.Set;

/*
 * https://leetcode.com/problems/determine-if-string-halves-are-alike/
 * Category: Easy
 * https://www.youtube.com/watch?v=752uISt9sCs
 * Related:
 * https://leetcode.com/problems/repeated-string-match/ Medium
 * https://leetcode.com/problems/number-of-distinct-substrings-in-a-string/ Medium Locked
 * https://leetcode.com/problems/longest-substring-of-all-vowels-in-order/ Medium Locked
 * 
 * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.

Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.

Return true if a and b are alike. Otherwise, return false.

 

Example 1:

Input: s = "book"
Output: true
Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
Example 2:

Input: s = "textbook"
Output: false
Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
Notice that the vowel o is counted twice.
 

Constraints:

2 <= s.length <= 1000
s.length is even.
s consists of uppercase and lowercase letters.
 */
public class DetermineifStringHalvesAreAlike {
    public boolean halvesAreAlike(String s) {
        int l = s.length();
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        int count = 0;
        int rightIndex = l/2;
        for (int i = 0; i < l/2; i++) {
            char leftChar = s.charAt(i);
            char rightChar = s.charAt(i+rightIndex);
            if (set.contains(leftChar)) {
                count++;
            }
            if (set.contains(rightChar)) {
                count--;
            }
            
        }
        return count == 0;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
