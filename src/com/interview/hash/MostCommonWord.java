package com.interview.hash;

import java.util.*;


/*
 * https://leetcode.com/problems/most-common-word/
 * Category: Easy
 * Related:
 * https://leetcode.com/problems/remove-comments/ Medium
 * https://leetcode.com/problems/binary-trees-with-factors/ Medium
 * https://leetcode.com/problems/determine-if-two-strings-are-close/ Medium
 * Given a string paragraph and a string array of the banned words banned, return the most frequent word that is not banned. It is guaranteed there is at least one word that is not banned, and that the answer is unique.

The words in paragraph are case-insensitive and the answer should be returned in lowercase.

 

Example 1:

Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
Example 2:

Input: paragraph = "a.", banned = []
Output: "a"
 

Constraints:

1 <= paragraph.length <= 1000
paragraph consists of English letters, space ' ', or one of the symbols: "!?',;.".
0 <= banned.length <= 100
1 <= banned[i].length <= 10
banned[i] consists of only lowercase English letters.
 * 
 */
public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        /*
         * Runtime: 19 ms, faster than 43.18% of Java online submissions for Most Common Word.
Memory Usage: 39.2 MB, less than 65.21% of Java online submissions for Most Common Word.
         */
        Set<String> set = new HashSet<String>();
        for (String b : banned) {
            set.add(b);
        }
        String p = paragraph.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();
        String[] arr = p.split("\\s+");
        String result = "";
        int maxCount = 0;
        Map<String, Integer> lookup = new HashMap<>();
        for (String token : arr) {
            if (!set.contains(token)) {
                int count = lookup.getOrDefault(token, 0) +1;
                lookup.put(token, count);
                if (count > maxCount) {
                    maxCount = count;
                    result = token;
                }
            }
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
