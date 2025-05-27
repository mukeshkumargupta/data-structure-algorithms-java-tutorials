package com.interview.sliddingwindow.slidingwindowMax;

import java.util.*;
/*
🔗 Problem: https://leetcode.com/problems/frequency-of-the-most-frequent-element/description/
📂 Category: Medium, Tricky, VVImp
🏷️ Tags: Sliding Window + prefix sum pattern, Greedy, Sorting

🔗 Related Problems:
- https://leetcode.com/problems/find-all-lonely-numbers-in-the-array/ (Medium)
- https://leetcode.com/problems/longest-nice-subarray/ (Medium)
- https://leetcode.com/problems/apply-operations-to-maximize-frequency-score/ (Hard)
- https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-i/ (Medium)
- https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-ii/ (Hard)
- https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-ii/ (Hard)

🧠 Problem Description:
The frequency of an element is the number of times it occurs in an array.

You are given an integer array `nums` and an integer `k`. In one operation, you can choose an index of `nums` and increment the element at that index by 1.

Return the **maximum possible frequency** of an element after performing at most `k` operations.

📘 Examples:

Example 1:
Input: nums = [1,2,4], k = 5
Output: 3
Explanation: Increment the first element 3 times and the second element 2 times to get [4,4,4].
Now, 4 has a frequency of 3.

Example 2:
Input: nums = [1,4,8,13], k = 5
Output: 2
Explanation:
- [4,4,8,13] → frequency of 4 is 2.
- [1,8,8,13] → frequency of 8 is 2.
- [1,4,13,13] → frequency of 13 is 2.

Example 3:
Input: nums = [3,9,6], k = 2
Output: 1

📏 Constraints:
- 1 <= nums.length <= 10⁵
- 1 <= nums[i] <= 10⁵
- 1 <= k <= 10⁵
*/
public class FrequencyoftheMostFrequentElement {
    /*
    🚀 Brute Force Approach
🔧 Idea:
For each element, try to make previous elements equal to it (by incrementing),
 and count how many can be made equal within k cost.
 ⏱ Time Complexity:
O(n²) in worst case

Space: O(1)
     */

    private static class BruitForce {
        public int maxFrequency(int[] nums, int k) {
            Arrays.sort(nums);
            int maxFreq = 0;
            int n = nums.length;

            for (int i = n - 1; i >= 0; i--) {
                int curr = nums[i];
                long cost = 0;
                int freq = 1;

                for (int j = i - 1; j >= 0; j--) {
                    cost += curr - nums[j];
                    if (cost <= k) {
                        freq++;
                    } else {
                        break;
                    }
                }

                maxFreq = Math.max(maxFreq, freq);
            }
            return maxFreq;
        }
    }
/*
 💡 Optimal Approach (Sliding Window)

 🔧 Idea:
 Sort the array. Use a sliding window [left...right]. For each window:
 - Check if we can make all elements equal to nums[right] using at most k operations.
 - Use the formula: cost = nums[right] * windowSize - windowSum

 ⏱ Time and Space Complexity:
 ┌────────────┬───────────┬─────────────────────────────────────┐
 │ Complexity │   Value   │               Reason                │
 ├────────────┼───────────┼─────────────────────────────────────┤
 │   Time     │ O(n log n)│ Sorting dominates overall time      │
 │   Space    │   O(1)    │ Only uses a few pointers/variables  │
 └────────────┴───────────┴─────────────────────────────────────┘
*/

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);//Why sorting because on each right, we can make all elements equal to nums[right] that is trick
        long windowSum = 0;
        int left = 0, maxFreq = 0;

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];

            // Check if current window is valid
            while ((long) nums[right] * (right - left + 1) - windowSum > k) {
                windowSum -= nums[left];
                left++;
            }

            maxFreq = Math.max(maxFreq, right - left + 1);//Here all element are equal withing that window so range will give the count
        }

        return maxFreq;
    }


}
