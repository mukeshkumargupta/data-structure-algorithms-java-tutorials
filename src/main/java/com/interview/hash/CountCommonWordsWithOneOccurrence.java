package com.interview.hash;

/*
 * https://leetcode.com/problems/count-common-words-with-one-occurrence/
 * Category: Medium
 * Derived: Try eaxctly two occurance:
 * Related: https://leetcode.com/problems/uncommon-words-from-two-sentences/ Easy Good Question
 * https://leetcode.com/problems/kth-distinct-string-in-an-array/ Easy Good Question
 * Given two string arrays words1 and words2, return the number of strings that appear exactly once in each of the two arrays.

 

Example 1:

Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
Output: 2
Explanation:
- "leetcode" appears exactly once in each of the two arrays. We count this string.
- "amazing" appears exactly once in each of the two arrays. We count this string.
- "is" appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
- "as" appears once in words1, but does not appear in words2. We do not count this string.
Thus, there are 2 strings that appear exactly once in each of the two arrays.
Example 2:

Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
Output: 0
Explanation: There are no strings that appear in each of the two arrays.
Example 3:

Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
Output: 1
Explanation: The only string that appears exactly once in each of the two arrays is "ab".
 

Constraints:

1 <= words1.length, words2.length <= 1000
1 <= words1[i].length, words2[j].length <= 30
words1[i] and words2[j] consists only of lowercase English letters.
 */
public class CountCommonWordsWithOneOccurrence {
    public int countWordsM2(String[] words1, String[] words2) {
        /*
         * Runtime: 8 ms, faster than 31.39% of Java online submissions for Count Common Words With One Occurrence.
Memory Usage: 42.7 MB, less than 39.57% of Java online submissions for Count Common Words With One Occurrence.
         */
        Map<String , Integer> map = new HashMap<>();
        for (String word: words1) {
            map.put(word, map.getOrDefault(word, 0) +1);
        }
        for (String word: words2) {
            if (map.containsKey(word)) {
                if (map.get(word) <= 1) {
                   map.put(word, map.get(word) -1); 
                }
                
            }
            
        }
        int count = 0;
        for (String word: words1) {
           if (map.get(word) == 0) {
               count++;
           }
            
        }
        return count;
        
    }
    
    public int countWordsMoreOtipizedButTricky(String[] words1, String[] words2) {
        //By seeing observation: Reference: https://leetcode.com/problems/count-common-words-with-one-occurrence/discuss/1641859/JAVA-%3A-Simple-or-2-Ways-or-Faster-than-100-or-Single-HashMap-or-Brief-Explanation
        /*
         * Runtime: 7 ms, faster than 38.28% of Java online submissions for Count Common Words With One Occurrence.
Memory Usage: 43.2 MB, less than 30.70% of Java online submissions for Count Common Words With One Occurrence.
         */
        Map<String , Integer> map = new HashMap<>();
        for (String word: words1) {
            map.put(word, map.getOrDefault(word, 0) +1);
        }
        int count = 0;
        for (String word: words2) {
            if (map.containsKey(word)) {
                int fr = map.get(word);
                if (fr <=1) {
                    if (fr ==1) {
                        count++;

                    } else if (fr ==0) {
                        count--;
                    }

                    map.put(word, map.get(word) -1); 

                    }

                
            }
            
        }
        return count;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
