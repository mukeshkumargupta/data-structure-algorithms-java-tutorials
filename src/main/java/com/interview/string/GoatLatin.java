package com.interview.string;

import java.util.*;
/*
 * https://leetcode.com/problems/goat-latin/
 * Category: Easy, Facebook,
 * 
 * Related: https://leetcode.com/problems/string-transforms-into-another-string/ Hard
 * https://leetcode.com/problems/brace-expansion-ii/ Hard
 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/ Medium
 * 
 * You are given a string sentence that consist of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.) The rules of Goat Latin are as follows:

If a word begins with a vowel ('a', 'e', 'i', 'o', or 'u'), append "ma" to the end of the word.
For example, the word "apple" becomes "applema".
If a word begins with a consonant (i.e., not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end, and so on.
Return the final sentence representing the conversion from sentence to Goat Latin.

 

Example 1:

Input: sentence = "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
Example 2:

Input: sentence = "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 

Constraints:

1 <= sentence.length <= 150
sentence consists of English letters and spaces.
sentence has no leading or trailing spaces.
All the words in sentence are separated by a single space.
 */
public class GoatLatin {
    public String toGoatLatin(String sentence) {
        Character[] vowels = {'a', 'i', 'e', 'o', 'u', 'A', 'I', 'E', 'O', 'U'};
        Set<Character> set = new HashSet<>(Arrays.asList(vowels));
          
        StringBuilder result = new StringBuilder();
        StringBuilder suffix = new StringBuilder();
        for (String word : sentence.split(" ")) {
            suffix.append("a");
            if (!set.contains(word.charAt(0))) {
                result.append(word.substring(1)).append(word.charAt(0));
            } else {
                result.append(word);
            }
            result.append("ma").append(suffix).append(" ");
        }
        result.deleteCharAt(result.length()-1);
        return result.toString();
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
