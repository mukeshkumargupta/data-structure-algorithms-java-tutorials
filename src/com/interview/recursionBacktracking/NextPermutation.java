package com.interview.recursionBacktracking;
import java.util.*;

/*
 * https://leetcode.com/problems/next-permutation/
 * https://www.youtube.com/watch?v=6qXO72FkqwM&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=28
 * Category: Medium
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
    public void nextPermutation(int[] nums) {
        int l = nums.length;
        
        int lastPeekIndex = 0;;
        for (int i = 1; i < l; i++) {
            if (nums[i] > nums[i-1]) {
                lastPeekIndex = i;
            }
        }
        if (lastPeekIndex ==0) {//means array is in decreasing order, no need to sort because it will take log n so rever it
            int start = 0;
            int end = l-1;
            while (start < end) {
                int temp = nums[end];
                nums[end--] = nums[start];
                nums[start++] = temp;
            }
            return;
            
        }
        
        int findNextGreater = lastPeekIndex;
        for (int i = lastPeekIndex+1; i < l; i++) {
            if (nums[i] > nums[lastPeekIndex-1] && nums[i] < nums[findNextGreater]) {//tricky condition
                findNextGreater = i;
            }
        }
        
        //swap
        int temp = nums[findNextGreater];
        nums[findNextGreater] = nums[lastPeekIndex-1];
        nums[lastPeekIndex-1] = temp;
        
        //now sort elemet
        Arrays.sort(nums, lastPeekIndex, l);//last peek to end element
        
    }
    
}
