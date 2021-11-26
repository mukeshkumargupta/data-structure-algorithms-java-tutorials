package com.interview.array;

/*
 * https://leetcode.com/problems/move-zeroes/
 * Category: Easy, Facebook, Tricky
 * Related:
 * https://leetcode.com/problems/make-array-strictly-increasing/ Hard
 * https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/ Medium
 * https://leetcode.com/problems/minimum-skips-to-arrive-at-meeting-on-time/ Hard
 * https://leetcode.com/problems/dungeon-game/ Hard
 * https://leetcode.com/problems/meeting-rooms-ii/ Medium
 * https://leetcode.com/problems/find-missing-observations/ Medium
 * 
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

 

Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]
 

Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1
 

Follow up: Could you minimize the total number of operations done?
 */
public class MoveZeroes {
    
    public void moveZeroes(int[] nums) {
        /*
         * Runtime: 2 ms, faster than 46.44% of Java online submissions for Move Zeroes.
Memory Usage: 52.4 MB, less than 19.97% of Java online submissions for Move Zeroes.
TC: O(N)
SC: O(1)
         */
        int lastNonZeroFoundAt = 0;
        // If the current element is not 0, then we need to
        // append it just in front of last non 0 element we found. 
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt++] = nums[i];
            }
        }
        // After we have finished processing new elements,
        // all the non-zero elements are already at beginning of array.
        // We just need to fill remaining array with 0's.
        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }
        
        
    }
    
    private void moveZero(int[] nums, int start, int end) {
        for (int i = start; i < end; i++) {
            nums[i+1] = nums[i];
        }    
    }
    private int findNonZeroFirstIndex(int[] nums, int start, int l) {
        for (int i = start; i < l; i++) {
            if (nums[i] != 0) {
               return i; 
            }
        }
        return -1;
        
    }
    public void moveZeroes1(int[] nums) {
        /*
         * Bruitforce O(N2)
         * Runtime: 101 ms, faster than 5.11% of Java online submissions for Move Zeroes.
Memory Usage: 40.2 MB, less than 53.94% of Java online submissions for Move Zeroes.
         */
        int l = nums.length;
        for (int i = 0; i < l ; i++) {
            if (nums[i] == 0) {
                int index = findNonZeroFirstIndex(nums, i+1, l);
                if (index != -1) {
                    int temp = nums[index];
                    moveZero(nums, i, index);
                    nums[i] = temp;
                }
                
            }
            
        }
        
    }
}
