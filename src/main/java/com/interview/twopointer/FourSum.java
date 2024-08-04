package com.interview.twopointer;

/*
 * https://www.youtube.com/watch?v=4ggF3tXIAp0&list=PLgUwDviBIf0rBT8io74a95xT-hDFZonNs&index=5
 * https://leetcode.com/problems/4sum/
 * https://www.youtube.com/watch?v=8ViERnSgPKs
 * 
 * Category: Medium, Must Do
 * Related: https://leetcode.com/problems/4sum-ii/ Medium
 * https://leetcode.com/problems/count-special-quadruplets/ Easy, =Hint: Sort then check three sum is eual to target , target is element from last which is bigger one
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

 

Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]] 
 * 
 * 
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        /*
         * Runtime: 32 ms, faster than 43.77% of Java online submissions for 4Sum.
Memory Usage: 44.5 MB, less than 59.78% of Java online submissions for 4Sum.
            Note: It is not optimized solution
         */
        Arrays.sort(nums);
        int len = nums.length;
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < len - 3; i++) {
            // If find solution for previous one the ignore current
            if (i != 0 && nums[i] == nums[i - 1])
                continue;
            

            for (int j = i + 1; j < len - 2; j++) {
                
                // If find solution for previous one the ignore current
                if (j != i + 1 && nums[j] == nums[j - 1])
                    continue;
  
                int left = j + 1;
                int right = len - 1;

                while (left < right) {
                    if (nums[i] + nums[j] + nums[left] + nums[right] == target) {
                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
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
                    } else if (nums[i] + nums[j] + nums[left] + nums[right]  < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
