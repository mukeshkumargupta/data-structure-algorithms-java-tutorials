package com.interview.hash;

/*
 * https://leetcode.com/problems/minimum-window-substring/submissions/
 * https://www.youtube.com/watch?v=e1HlptlipB0
 * Category: Hard, Must Do
 * Related: https://leetcode.com/problems/substring-with-concatenation-of-all-words/ Hard
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/ Hard
 * https://leetcode.com/problems/minimum-window-subsequence/ Hard
 * 
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 

Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        /*
         * Runtime: 64 ms, faster than 12.29% of Java online submissions for Minimum Window Substring.
Memory Usage: 40.2 MB, less than 49.08% of Java online submissions for Minimum Window Substring.
         */
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map2.put(ch, map2.getOrDefault(ch, 0) +1);
        }

        int dmct = t.length();
        int mct = 0;
        int i = -1;
        int j = -1;
        String ans = "";
        while (true) {
            boolean f1 = false;
            boolean f2 = false;
            //accuire
            while(i < s.length() -1 && mct < dmct) {
                i++;
                char ch = s.charAt(i);
                map1.put(ch, map1.getOrDefault(ch, 0) +1);
                if (map1.getOrDefault(ch, 0) <= map2.getOrDefault(ch, 0)) {
                    mct++;
                }
                f1 = true;
            }
            //collect ans and release it
            while (j < i && mct == dmct) {
                String pans = s.substring(j+1, i+1);//i+1 because we take one extra from actual position
                if (ans.length() == 0 || pans.length() < ans.length()) {
                    ans = pans;
                }
                j++;
                char ch = s.charAt(j);
                if (map1.getOrDefault(ch, 0) == 1) {
                   map1.remove(ch); 
                } else {
                    map1.put(ch, map1.get(ch) -1);
                }
                
                if (map1.getOrDefault(ch, 0) < map2.getOrDefault(ch, 0)) {
                    mct--;
                }
                f2 = true;
                
            }
            if (!f1 && !f2) {
                break;
            }
            
        }
        return ans;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
