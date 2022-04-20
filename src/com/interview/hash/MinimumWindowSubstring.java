package com.interview.hash;

/*
 * https://leetcode.com/problems/minimum-window-substring/submissions/
 * https://www.youtube.com/watch?v=e1HlptlipB0
 * Category: Hard, Must Do, Top150
 * Related: https://leetcode.com/problems/substring-with-concatenation-of-all-words/ Hard Bad
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/ Hard, VVImp Idserver refer this solution
 * https://leetcode.com/problems/minimum-window-subsequence/ Hard Locked
 * 
 * Given two strings s and tFreq of lengths m and n respectively, return the minimum window substring of s such that every character in tFreq (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "ADOBECODEBANC", tFreq = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string tFreq.
Example 2:

Input: s = "a", tFreq = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", tFreq = "aa"
Output: ""
Explanation: Both 'a's from tFreq must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == tFreq.length
1 <= m, n <= 105
s and tFreq consist of uppercase and lowercase English letters.
 

Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        /*
         * Runtime: 64 ms, faster than 12.29% of Java online submissions for Minimum Window Substring.
Memory Usage: 40.2 MB, less than 49.08% of Java online submissions for Minimum Window Substring.
         * It is slow answer find better solution
         */
        Map<Character, Integer> sFreq = new HashMap<>();
        Map<Character, Integer> tFreq = new HashMap<>();
        int sLength = s.length();
        int tLength = t.length();
        for (int i = 0; i < tLength; i++) {
            char ch = t.charAt(i);
            tFreq.put(ch, tFreq.getOrDefault(ch, 0) +1);
        }

        int desiredMatchCount = t.length();
        int matchCount = 0;
        int i = -1;
        int j = -1;
        String ans = "";
        while (true) {
            boolean f1 = false;
            boolean f2 = false;
            //accuire
            while(i < sLength -1 && matchCount < desiredMatchCount) {
                i++;
                char ch = s.charAt(i);
                sFreq.put(ch, sFreq.getOrDefault(ch, 0) +1);
                //if it is useful require then increment count
                if (sFreq.getOrDefault(ch, 0) <= tFreq.getOrDefault(ch, 0)) {
                    matchCount++;
                }
                f1 = true;
            }
            //collect ans and release it
            while (j < i && matchCount == desiredMatchCount) {
                String probableAns = s.substring(j+1, i+1);//i+1 because we take one extra from actual position
                if (ans.length() == 0 || probableAns.length() < ans.length()) {
                    ans = probableAns;
                }
                j++;
                char ch = s.charAt(j);
                if (sFreq.getOrDefault(ch, 0) == 1) {
                   sFreq.remove(ch); 
                } else {
                    sFreq.put(ch, sFreq.get(ch) -1);
                }
                //During minimizing the window if you found removing character making less count then reduce match count and go for accuire
                if (sFreq.getOrDefault(ch, 0) < tFreq.getOrDefault(ch, 0)) {
                    matchCount--;
                }
                f2 = true;
                
            }
            if (!f1 && !f2) {//if it is not going in above both while loop then you got desired ans
                break;
            }
            
        }
        return ans;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
