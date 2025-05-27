package com.interview.recursionBacktracking.C_permutationPatterns;

/*
 * https://leetcode.com/problems/next-permutation/
 * it is application of nextGreaterElement |||
 * Category: Medium, Facebook, FAANG
 * Related: https://leetcode.com/problems/palindrome-permutation-ii/ Medium locked
 * https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/ Medium
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]
Example 4:

Input: nums = [1]
Output: [1]
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
 * Related: https://leetcode.com/problems/permutations-ii/ Medium
 * https://leetcode.com/problems/permutation-sequence/ Hard
 * https://leetcode.com/problems/palindrome-permutation-ii/ Medium
 * https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/ Medium
 */
public class NextPermutation {
    public void nextGreaterElement(int[] input) {  
        int l = input.length;
        if (l == 1) {
            return;
        }
        int end = l-2;
        //find first dip
        while (end >= 0 && input[end] >= input[end+1]) {
            end--;
        }
        if (end == -1) {
            //System.out.println(-1);
            //reverse it
            int left = 0;
            int right = l -1;
            while (left < right) {
                int temp = input[right];
                input[right] = input[left];
                input[left] = temp;
                left++;
                right--;
            }
            return;
        }
        //if dip found then find just greater element, start from last, because from last it will be increasing order always
        int dipIndex = end;
        end = l-1;
        while(input[end] <= input[dipIndex]) {
            end--;
        }
        //swap
        int temp = input[end];
        input[end] = input[dipIndex];
        input[dipIndex] = temp;
        
        //reverse form dipIndex+1 to l-1
        int left = dipIndex+1;
        int right = l -1;
        while (left < right) {
            temp = input[right];
            input[right] = input[left];
            input[left] = temp;
            left++;
            right--;
        }
        
 
    }
    public void nextPermutation(int[] nums) {
        nextGreaterElement(nums);
    }
}