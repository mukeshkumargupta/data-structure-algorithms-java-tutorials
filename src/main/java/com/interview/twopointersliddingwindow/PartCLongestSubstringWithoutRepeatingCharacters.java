package com.interview.twopointersliddingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
https://www.youtube.com/watch?v=-zSxTJkcdAo&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=4
/*
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Category: Medium, Tricky, Amazon, Top150, VVImp
 * https://www.youtube.com/watch?v=qtVh-XEpsJo&t=1090s
 * Related:
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/ Medium
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/ Medium
 * https://leetcode.com/problems/subarrays-with-k-different-integers/ Hard
 * https://leetcode.com/problems/maximum-erasure-value/ Medium
 * https://leetcode.com/problems/number-of-equal-count-substrings/ Medium
 * Related: Find string also, so extract the string in result;
 * Given a string s, find the length of the longest substring without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:

Input: s = ""
Output: 0
 *
 */
public class PartCLongestSubstringWithoutRepeatingCharacters {
  private static class bruitforce {
      /*
 Output: The length of the longest substring without repeating characters is 9

 Time Complexity: O(N^2)
 Space Complexity: O(N) where N is the size of HashSet used for storing elements
 */
      static int solve(String str) {
          if (str.length() == 0) {
              return 0;
          }

          int maxLength = Integer.MIN_VALUE;

          // Outer loop for traversing the string
          for (int i = 0; i < str.length(); i++) {
              Set<Character> set = new HashSet<>();

              // Inner loop to consider substrings starting at index 'i'
              for (int j = i; j < str.length(); j++) {
                  if (set.contains(str.charAt(j))) {
                      // If the character is repeated, break the loop
                      break;
                  }
                  set.add(str.charAt(j)); // Add current character to the set
                  maxLength = Math.max(maxLength, j - i + 1); // Update max length
              }
          }
          return maxLength;
      }

      public static void main(String[] args) {
          String str = "abcabcbb";
          System.out.println("The length of the longest substring without repeating characters is " + solve(str));
      }
  }

    /*
    Output: The length of the longest substring without repeating characters is 9

Time Complexity: O( 2*N ) (sometimes left and right both have to travel complete array)

Space Complexity: O(N) where N is the size of HashSet taken for storing the elements
     */

    static int solveBetter(String str) {

        if(str.length()==0)
            return 0;
        int maxans = Integer.MIN_VALUE;
        Set < Character > set = new HashSet < > ();
        int l = 0;
        for (int r = 0; r < str.length(); r++) // outer loop for traversing the string
        {
            if (set.contains(str.charAt(r))) //if duplicate element is found
            {
                while (l < r && set.contains(str.charAt(r))) {
                    set.remove(str.charAt(l));
                    l++;
                }
            }
            set.add(str.charAt(r));
            maxans = Math.max(maxans, r - l + 1);
        }
        return maxans;
    }

    static int solveOptimize(String s) {
        HashMap < Character, Integer > mpp = new HashMap< Character, Integer >();

        int left = 0, right = 0;
        int n = s.length();
        int len = 0;
        while (right < n) {
            if (mpp.containsKey(s.charAt(right))) left = Math.max(mpp.get(s.charAt(right)) + 1, left);

            mpp.put(s.charAt(right), right);

            len = Math.max(len, right - left + 1);
            right++;
        }
        return len;
    }

    public static void main(String args[]) {
        String str = "takeUforward";
        System.out.println("The length of the longest substring without repeating characters is " + solve(str));

    }
}
