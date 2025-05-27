package com.interview.hash;

import java.util.HashMap;
import java.util.Map;

/*
Category: Easy
Related:
https://leetcode.com/problems/majority-element/ Easy
https://leetcode.com/problems/majority-element-ii/ Medium
https://leetcode.com/problems/top-k-frequent-elements/ Medium
https://leetcode.com/problems/sort-characters-by-frequency/ Medium

Given an integer array nums, return the most frequent even element.

If there is a tie, return the smallest one. If there is no such element, return -1.



Example 1:

Input: nums = [0,1,2,2,4,4,1]
Output: 2
Explanation:
The even elements are 0, 2, and 4. Of these, 2 and 4 appear the most.
We return the smallest one, which is 2.
Example 2:

Input: nums = [4,4,4,9,2,4]
Output: 4
Explanation: 4 is the even element appears the most.
Example 3:

Input: nums = [29,47,21,41,13,37,25,7]
Output: -1
Explanation: There is no even element.


Constraints:

1 <= nums.length <= 2000
0 <= nums[i] <= 105
 */
public class MostFrequentEvenElement {
    /*
    1. 🚀 Brute Force
✅ Idea:

For each even element:

Count its frequency by iterating again.

Keep track of maximum frequency and corresponding even element.
⏱ Complexity:
Time: O(n²) (nested loop)

Space: O(1)
     */
    private static class BruiteForce {
        public int mostFrequentEven(int[] nums) {
            int maxFreq = 0;
            int result = -1;

            for (int num1 : nums) {
                if (num1 % 2 != 0) continue; // skip odd numbers
                int freq = 0;
                for (int num2 : nums) {
                    if (num1 == num2) {
                        freq++;
                    }
                }
                if (freq > maxFreq || (freq == maxFreq && num1 < result)) {
                    maxFreq = freq;
                    result = num1;
                }
            }

            return result;
        }
    }

    /*
    ⏱ Complexity:
Time: O(n) (one pass to build map + one pass to find answer)

Space: O(n) (HashMap)

2. 🛠️ Better Approach (HashMap)
✅ Idea:

Use a HashMap to count frequencies.

Then find the max frequency and smallest even element.
     */
    private static class Optimal {
        public int mostFrequentEven(int[] nums) {
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int num : nums) {
                if (num % 2 == 0) {
                    freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
                }
            }

            int maxFreq = 0;
            int result = -1;
            for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
                int num = entry.getKey();
                int freq = entry.getValue();

                if (freq > maxFreq || (freq == maxFreq && num < result)) {
                    maxFreq = freq;
                    result = num;
                }
            }

            return result;
        }
    }

}
