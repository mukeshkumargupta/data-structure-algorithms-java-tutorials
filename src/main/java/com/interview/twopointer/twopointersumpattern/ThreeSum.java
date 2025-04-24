package com.interview.twopointer.twopointersumpattern;

import java.util.*;

/*
 * Reference: https://leetcode.com/problems/3sum/
 * Category: Medium, Top150
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Example 2:

Input: nums = []
Output: []
Example 3:

Input: nums = [0]
Output: []
 

Constraints:

0 <= nums.length <= 3000
-105 <= nums[i] <= 105
 * Category: Medium, Tricky, Google
 * Derived: Find all triplet whose sum is given sum, less than sum,. greater than sum or zero 
 * Given an array of distinct integers. The task is to count all the triplets such that sum of two elements equals the third element.
 */
public class ThreeSum {
    /*
        ✅ Time Complexity: O(n^2)
        --------------------------
        - The input array is sorted first using Arrays.sort(nums), which takes O(n log n) time.
        - Then, a loop runs from i = 0 to n - 2 → O(n).
        - Inside this loop, a two-pointer approach (left and right) is used to find pairs such that
          nums[i] + nums[left] + nums[right] == 0.
        - The two-pointer traversal takes O(n) time for each i.

        ➡️ Total time complexity becomes:
           O(n log n + n^2) → Dominated by O(n^2)

        ✅ Space Complexity: O(1) (excluding output)
        --------------------------------------------
        - No extra data structures are used; the algorithm works in-place.
        - The output list `result` does not count toward auxiliary space.

        ➡️ Auxiliary space used: O(1)

        📝 Final Answer:
        ----------------
        - Time Complexity: O(n^2)
        - Space Complexity: O(1) (excluding output)
    */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < len - 2; i++) {
            // If find solution for previous one the ignore current
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            
            int left = i + 1;
            int right = len - 1;
            
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    result.add(temp);
                    left++;
                    right--;
                    while (left < len && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (right >= 0 && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
            
        }
        return result;
    }

}
