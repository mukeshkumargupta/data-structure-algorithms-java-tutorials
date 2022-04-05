package com.interview.array;

/*
 * https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/
 * Category: Medium, Tricky
 * https://www.youtube.com/watch?v=Wmb3YdVYfqM
 * This problem can be solve in many way but time complexity matters
 * Related:https://leetcode.com/problems/wiggle-sort/
 * 
 * You are given a 0-indexed array nums of distinct integers. You want to rearrange the elements in the array such that every element in the rearranged array is not equal to the average of its neighbors.

More formally, the rearranged array should have the property such that for every i in the range 1 <= i < nums.length - 1, (nums[i-1] + nums[i+1]) / 2 is not equal to nums[i].

Return any rearrangement of nums that meets the requirements.

 

Example 1:

Input: nums = [1,2,3,4,5]
Output: [1,2,4,5,3]
Explanation:
When i=1, nums[i] = 2, and the average of its neighbors is (1+4) / 2 = 2.5.
When i=2, nums[i] = 4, and the average of its neighbors is (2+5) / 2 = 3.5.
When i=3, nums[i] = 5, and the average of its neighbors is (4+3) / 2 = 3.5.
Example 2:

Input: nums = [6,2,0,9,7]
Output: [9,7,6,2,0]
Explanation:
When i=1, nums[i] = 7, and the average of its neighbors is (9+6) / 2 = 7.5.
When i=2, nums[i] = 6, and the average of its neighbors is (7+2) / 2 = 4.5.
When i=3, nums[i] = 2, and the average of its neighbors is (6+0) / 2 = 3.
 

Constraints:

3 <= nums.length <= 105
0 <= nums[i] <= 105
 * 
 */
public class ArrayWithElementsNotEqualtoAverageofNeighbors {
    public int[] rearrangeArray(int[] nums) {
        /* Approach 1
         * Wiggle sort 2
         * TC(NlogN)
         * SC O(N)
         */
        Arrays.sort(nums);
        
        int l = nums.length;
        int i = 1; int j = l-1;
        int[] result = new int[l];
        while (i < l ) {
            result[i] = nums[j];
            i +=2;
            j--;
        }
        i = 0;
        while (i < l) {
            result[i] = nums[j];
            i +=2;
            j--;
        }
        
        for (i = 0; i < l ; i++) {
            nums[i] = result[i];
        }
        return nums;
    }
    
    //Without sorting
    void swap (int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
        
    }
    public int[] rearrangeArray(int[] nums) {
        /* if element is in between then swap with next
         * Runtime: 7 ms, faster than 82.68% of Java online submissions for Array With Elements Not Equal to Average of Neighbors.
Memory Usage: 177 MB, less than 5.12% of Java online submissions for Array With Elements Not Equal to Average of Neighbors.
         */
        int n = nums.length;
        for(int i = 1 ; i < n -1  ; i++)
        {
            int prevIndex = i -1 ;
            int nextIndex = i + 1;
            
            if(nums[i] > nums[prevIndex] &&  nums[i] < nums[nextIndex])
            {
                swap(nums , i, nextIndex);
            }

            else if(nums[i] < nums[prevIndex] &&  nums[i] > nums[nextIndex])
            {
                swap(nums, i, nextIndex);
            }
        }
        return nums; 
    }
    
    
}
