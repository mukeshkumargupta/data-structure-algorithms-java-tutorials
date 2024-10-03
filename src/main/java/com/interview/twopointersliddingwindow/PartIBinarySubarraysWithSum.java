package com.interview.twopointersliddingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PartIBinarySubarraysWithSum {
    /*
    1. Brute Force Approach
        Approach:
        Iterate over all possible subarrays using two nested loops.
        For each subarray, calculate the sum.
        If the sum of the subarray equals the goal, increment the count.
        Time Complexity:
        O(n²), where n is the length of the array. This is because we are generating all subarrays and calculating their sum.
        Space Complexity:
        O(1), only using constant extra space for storing count and sum.
     */

    public int numSubarraysWithSumBruitforce(int[] nums, int goal) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == goal) {
                    count++;
                } else if(sum > goal) {
                    break;
                }
            }
        }
        return count;
    }

/*
    3. Better Approach (Prefix Sum with HashMap)
    Approach:
    Use the prefix sum concept with a hashmap to store the frequency of prefix sums.
    Traverse through the array, and for each index i, calculate the prefix sum up to index i.
    The problem boils down to checking how many times prefixSum - goal has been encountered so far because if prefixSum[i] - prefixSum[j] == goal, then the sum of the subarray nums[j+1:i] is goal.
    Use a hashmap to efficiently store and retrieve the prefix sum frequencies.
    Time Complexity:
    O(n), where n is the length of the array. The array is traversed once, and hashmap operations (insert and lookup) are done in constant time.
    Space Complexity:
    O(n), for storing the prefix sums in the hashmap.
 */

    public int numSubarraysWithSumBetter(int[] nums, int goal) {
        int count = 0;
        int sum = 0;
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);  // To handle subarrays starting from index 0

        for (int num : nums) {
            sum += num;
            if (prefixSumMap.containsKey(sum - goal)) {
                count += prefixSumMap.get(sum - goal);
            }
            prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    /*
        4. Time Complexity:
    O(n): Both the left and right pointers traverse the array once, making the time complexity linear.
    5. Space Complexity:
    O(1): Only constant extra space is used for pointers and variables.
     */

    // Main function to count subarrays with sum exactly equal to the goal.
    public int numSubarraysWithSum(int[] nums, int goal) {
        // We calculate the difference between subarrays with at most `goal` sum
        // and subarrays with at most `goal - 1` sum. This gives us the subarrays
        // that have exactly `goal` sum.
        return countSubarraysWithAtMostSum(nums, goal) - countSubarraysWithAtMostSum(nums, goal - 1);
    }

    // Helper function to count subarrays with sum at most equal to the given goal.
    private int countSubarraysWithAtMostSum(int[] nums, int goal) {
        // If the goal is negative, no subarray can have a valid sum, so return 0.
        if (goal < 0) return 0;

        int left = 0;  // Left pointer of the sliding window
        int subarrayCount = 0;  // Stores the total count of subarrays
        int currentSum = 0;  // Sum of the current window (subarray)

        // Loop through the array with the right pointer expanding the window.
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];  // Add the right element to the current window sum.

            // If the current sum exceeds the goal, shrink the window from the left.
            while (currentSum > goal) {
                currentSum -= nums[left];  // Remove the left element from the window.
                left++;  // Move the left pointer to the right.
            }

            // All subarrays between `left` and `right` are valid because they have
            // sum <= goal. The number of such subarrays is (right - left + 1).
            subarrayCount += (right - left + 1);
        }

        // Return the total count of subarrays with sum at most equal to the goal.
        return subarrayCount;
    }


    /*
https://www.youtube.com/watch?v=j_QOv9OT9Og&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=10
Category: Medium
https://leetcode.com/problems/count-number-of-nice-subarrays/
Related:
https://leetcode.com/problems/k-divisible-elements-subarrays/description/ Medium
https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/ Hard
https://leetcode.com/problems/ways-to-split-array-into-good-subarrays/description/ Medium
 */
    public static class PartJCountnumberofNicesubarrays {
        /*
            4. Time Complexity:
        O(n): Both the left and right pointers traverse the array once, making the time complexity linear.
        5. Space Complexity:
        O(1): Only constant extra space is used for pointers and variables.

         */
        public int numSubarraysWithSum(int[] nums, int goal) {
            // We calculate the difference between subarrays with at most `goal` sum
            // and subarrays with at most `goal - 1` sum. This gives us the subarrays
            // that have exactly `goal` sum.
            return countSubarraysWithAtMostSum(nums, goal) - countSubarraysWithAtMostSum(nums, goal - 1);
        }

        // Helper function to count subarrays with sum at most equal to the given goal.
        private int countSubarraysWithAtMostSum(int[] nums, int goal) {
            // If the goal is negative, no subarray can have a valid sum, so return 0.
            if (goal < 0) return 0;

            int left = 0;  // Left pointer of the sliding window
            int subarrayCount = 0;  // Stores the total count of subarrays
            int currentSum = 0;  // Sum of the current window (subarray)

            // Loop through the array with the right pointer expanding the window.
            for (int right = 0; right < nums.length; right++) {
                currentSum += nums[right] %2;  // Add the right element to the current window sum.

                // If the current sum exceeds the goal, shrink the window from the left.
                while (currentSum > goal) {
                    currentSum -= nums[left] %2;  // Remove the left element from the window.
                    left++;  // Move the left pointer to the right.
                }

                // All subarrays between `left` and `right` are valid because they have
                // sum <= goal. The number of such subarrays is (right - left + 1).
                subarrayCount += (right - left + 1);
            }

            // Return the total count of subarrays with sum at most equal to the goal.
            return subarrayCount;
        }
        public int numberOfSubarrays(int[] nums, int k) {
            return numSubarraysWithSum(nums, k);
        }
    }

    /*
    https://www.youtube.com/watch?v=7wYGbV_LsX4&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=11
    Category: Hard
    https://leetcode.com/problems/subarrays-with-k-different-integers/description/
    Related:
    https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/description/ Medium
    https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/ Medium
    https://leetcode.com/problems/count-vowel-substrings-of-a-string/description/ Easy
     */
    public static class Subarraywithkdifferentintegers {
        /*
        Approach:
        Generate all possible subarrays.
        For each subarray, count the number of distinct integers.
        If the count of distinct integers equals k, increment the result.

        Explanation:
        We iterate over all possible subarrays (nested loops).
        For each subarray, we use a HashSet to track distinct integers.
        If the size of the set equals k, we increment the count.
        TC: n^2
         */
        public int subarraysWithKDistinctBruteForce(int[] nums, int k) {
            int n = nums.length;
            int count = 0;

            // Generate all subarrays
            for (int i = 0; i < n; i++) {
                Set<Integer> distinct = new HashSet<>();
                for (int j = i; j < n; j++) {
                    distinct.add(nums[j]);
                    // If the number of distinct integers equals k, increment the count
                    if (distinct.size() == k) {
                        count++;
                    }
                }
            }

            return count;
        }


        public int subarraysWithKDistinctBetter(int[] nums, int k) {
            int n = nums.length;
            int count = 0;

            // Generate all subarrays
            for (int i = 0; i < n; i++) {
                Set<Integer> distinct = new HashSet<>();
                for (int j = i; j < n; j++) {
                    distinct.add(nums[j]);
                    // If the number of distinct integers equals k, increment the count
                    if (distinct.size() == k) {
                        count++;
                    } else if (distinct.size() > k) {
                        break;
                    }
                }
            }

            return count;
        }

    /*
    3. Optimal Approach (O(n)):
Approach:
Use the sliding window with two helper functions:
atMostKDistinct(nums, k) to calculate the number of subarrays with at most k distinct integers.
The answer to the problem is:
exactlyKDistinct = atMostKDistinct(nums, k) - atMostKDistinct(nums, k-1)
This works because the number of subarrays with exactly k distinct integers is the difference between the number of subarrays with at most k and at most k-1 distinct integers.

Explanation:
Sliding Window with atMostKDistinct:
We maintain a sliding window and count the number of distinct integers using a frequency map.
If the number of distinct integers exceeds k, we shrink the window from the left until we have k or fewer distinct integers.
For every valid window, the number of valid subarrays is the difference between the right and left pointers.
Final Result:
We subtract the result of atMostKDistinct(nums, k-1) from atMostKDistinct(nums, k) to get the subarrays with exactly k distinct integers.
Time Complexity:
O(n) for both atMostKDistinct calls, making the overall time complexity O(n).
     */

        public int subarraysWithKDistinctOptimal(int[] nums, int k) {
            // Find the number of subarrays with exactly k distinct integers
            return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
        }

        // Helper function to find the number of subarrays with at most k distinct integers
        private int atMostKDistinct(int[] nums, int k) {
            int left = 0;
            int count = 0;
            Map<Integer, Integer> freqMap = new HashMap<>();

            for (int right = 0; right < nums.length; right++) {
                // Add the current element to the window
                freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

                // If we have more than k distinct elements, shrink the window
                while (freqMap.size() > k) {
                    freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                    if (freqMap.get(nums[left]) == 0) {
                        freqMap.remove(nums[left]);
                    }
                    left++;
                }

                // All subarrays between left and right are valid
                count += (right - left + 1);
            }

            return count;
        }
    }
}
