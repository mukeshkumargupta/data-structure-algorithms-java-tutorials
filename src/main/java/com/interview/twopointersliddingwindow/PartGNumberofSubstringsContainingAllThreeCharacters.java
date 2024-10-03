package com.interview.twopointersliddingwindow;

import java.util.HashSet;
import java.util.Set;

/*
https://www.youtube.com/watch?v=xtqN4qlgr8s&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=7
 */
public class PartGNumberofSubstringsContainingAllThreeCharacters {
    /*
    1. Brute Force Approach:
        Approach:
        Iterate through all possible substrings of s.
        For each substring, check if it contains all three characters 'a', 'b', and 'c'.
        If a substring contains all three, increase the count.
        Time Complexity:
        O(n^3): Generating all substrings is O(n^2) and checking if each substring contains all three characters is O(n), resulting in O(n^3) complexity.
     */
    public int numberOfSubstringsBruitforce(String s) {
        int count = 0;
        // Generate all possible substrings
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String subStr = s.substring(i, j + 1);
                // Check if substring contains all three characters 'a', 'b', 'c'
                if (containsAll(subStr)) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
    2. Better Approach (Sliding Window with Set):
        Approach:
        Use a sliding window to find substrings that contain all three characters.
        Use a sliding window technique to find valid substrings, and keep track of the distinct characters in the window.
        When a valid window (i.e., contains 'a', 'b', and 'c') is found, all substrings ending at the current right boundary and starting from any position up to the left boundary are also valid.
        Time Complexity:
        O(n^2): This approach reduces the number of substring checks but still requires quadratic time in the worst case for finding valid windows.
     */
    public int numberOfSubstringsBetter(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> windowSet = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                windowSet.add(s.charAt(j));
                if (windowSet.contains('a') && windowSet.contains('b') && windowSet.contains('c')) {
                    count++;
                }
            }
        }
        return count;
    }

/*
3. Optimal Approach (Sliding Window with HashMap/Count Array):

   Approach:
   - Use a sliding window approach with two pointers (left and right).
   - Maintain a count array (or a hashmap) to track the frequency of 'a', 'b', and 'c' in the current window.
   - Expand the window by moving the right pointer, and if all characters 'a', 'b', and 'c' are present,
     shrink the window from the left (left pointer) while still maintaining the validity of the substring.
   - Once you find a valid window, every substring starting from the left pointer to any point before the right pointer
     will also be valid, which significantly reduces the number of checks.

   Time Complexity:
   - O(n): Both left and right pointers traverse the string once, and updating the count array is O(1),
     so this approach is linear.

   Explanation:
   - We use an array count of size 3 to track the frequency of 'a', 'b', and 'c' in the current window.
   - The sliding window starts with the right pointer expanding to include new characters.
     As soon as the window contains at least one 'a', one 'b', and one 'c', we count all substrings starting
     from left to right.
   - We then shrink the window by moving the left pointer until the window is no longer valid
     (i.e., it doesn't contain all three characters).
   - Every time a valid window is found, the number of substrings is the difference between the length of the string
     and the current position of the right pointer, since every substring ending at any point after right is also valid.

   Key Differences Between Approaches:

   - Brute Force Approach:
     - Generates all substrings and checks each one.
     - Time Complexity: O(n^3)

   - Better Approach (Sliding Window with Set):
     - Uses a sliding window but checks if all characters are present in each window.
     - Time Complexity: O(n^2)

   - Optimal Approach (Sliding Window with Count Array):
     - Uses a sliding window with a frequency array to efficiently count valid substrings.
     - Time Complexity: O(n)

   Conclusion:
   - The brute force approach is inefficient for large inputs.
   - The optimal solution using the sliding window with a count array is much more efficient, with linear time complexity,
     and is suitable for solving this problem efficiently for larger strings.
*/

    public int numberOfSubstringsOptimal(String s) {
        int[] count = new int[3];  // To count occurrences of 'a', 'b', and 'c'
        int left = 0, result = 0;

        // Sliding window with 'right' pointer
        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'a']++;

            // When all three characters are present
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                // All substrings from current 'left' to 'right' are valid
                result += s.length() - right;
                count[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return result;
    }

    // Helper function to check if the substring contains 'a', 'b', and 'c'
    private boolean containsAll(String s) {
        return s.contains("a") && s.contains("b") && s.contains("c");
    }
}
