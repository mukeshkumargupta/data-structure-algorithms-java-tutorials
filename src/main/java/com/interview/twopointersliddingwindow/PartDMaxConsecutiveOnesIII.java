package com.interview.twopointersliddingwindow;

/*
https://www.youtube.com/watch?v=3E4JBHSLpYk&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=4
Category: Medium
Related:
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
https://leetcode.com/problems/longest-repeating-character-replacement/description/ Medium
https://leetcode.com/problems/max-consecutive-ones-ii/description/ Medium
 */
public class PartDMaxConsecutiveOnesIII {

    public int longestOnesBruteForce(int[] nums, int k) {
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int zeroCount = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) {
                    zeroCount++;
                }
                if (zeroCount > k) {
                    break;
                }
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }

    public int longestOnesBetter(int[] nums, int k) {
        int left = 0, right = 0;
        int zeroCount = 0;
        int maxLen = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If zero count exceeds k, move left pointer
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update the maximum length of valid window
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }

    public int longestOnesOptimal(int[] nums, int k) {
        int left = 0, right = 0;
        int zeroCount = 0;
        int maxLen = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If zero count exceeds k, move left pointer
            if (zeroCount > k) { //Only change here
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update the maximum length of valid window
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }
}
