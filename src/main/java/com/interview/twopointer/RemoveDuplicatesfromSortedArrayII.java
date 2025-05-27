package com.interview.twopointer;

/*
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * Category: Medium, Tricky
 * Related:
 * https://leetcode.com/problems/smallest-range-i/ Easy
 * https://leetcode.com/problems/satisfiability-of-equality-equations/ Medium
 * https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/ Medium
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice.
 *  The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums.
*  More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
*  It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

 

Example 1:

Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
 

Constraints:

1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.
Accepted
355,181
Submissions
745,735
 */
public class RemoveDuplicatesfromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array II.
Memory Usage: 38.8 MB, less than 90.39% of Java online submissions for Remove Duplicates from Sorted Array II.
         */
        int l = nums.length;
        if (l < 3) {
            return l;
        }
        int slow = 2;
        int fast = 2;
        
        while (fast < l) {
            if (nums[fast] != nums[slow-2]) {//increase both with copy
                nums[slow++] = nums[fast];
            }
            fast++;//otherwise only increase fast// here min 3 can be extended then slow fats will start from 3
            
        }
        return slow;
        
    }
    
    //Derived question: for 3 repeated allowed
    /*
     * inp: [1,1,1,1, 1, 2,2,2,2,3]
     * o/p: [1,1,1,2,2,2,3]
     */
    public int removeDuplicatesMoreThanThree(int[] nums) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array II.
Memory Usage: 38.8 MB, less than 90.39% of Java online submissions for Remove Duplicates from Sorted Array II.
         */
        int l = nums.length;
        if (l < 4) {
            return l;
        }
        int slow = 3;
        int fast = 3;
        
        while (fast < l) {
            if (nums[fast] != nums[slow-3]) {//increase bith with copy
                nums[slow++] = nums[fast];
            }
            fast++;//otherwise only increase fast// here min 3 can be extended then slow fats will start from 3
            
        }
        return slow;
        
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
