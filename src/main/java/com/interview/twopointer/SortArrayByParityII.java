package com.interview.twopointer;

/*
 * https://leetcode.com/problems/sort-array-by-parity-ii/
 * Category: Easy, Tricky
 * Try with extension of parity 1 problem same approach like start and end
 * https://www.youtube.com/watch?v=pHRFRZGbK5s
 * Related: 
 * https://leetcode.com/problems/rearrange-array-elements-by-sign/ Medium VVImp Hint take positive Index and negativeIndex, exactly same way
 * https://leetcode.com/problems/sort-even-and-odd-indices-independently/ Easy VVImp Take in two group, sort and merge
 * https://leetcode.com/problems/insertion-sort-list/ Medium, Hint this is insertion sort list, Use remove and add operation to arrange link list, but merge sort also works here to solve problem
 * https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/ Medium Locked, Same as insertion-sort-list
 * https://leetcode.com/problems/reverse-words-in-a-string-iii/ Easy
 * https://leetcode.com/problems/smallest-range-ii/ Medium
 * 
 * Given an array of integers nums, half of the integers in nums are odd, and the other half are even.

Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.

Return any answer array that satisfies this condition.

 

Example 1:

Input: nums = [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
Example 2:

Input: nums = [2,3]
Output: [2,3]
 

Constraints:

2 <= nums.length <= 2 * 104
nums.length is even.
Half of the integers in nums are even.
0 <= nums[i] <= 1000
 

Follow Up: Could you solve it in-place?
 */
public class SortArrayByParityII {
    public int[] sortArrayByParityII(int[] nums) {
        /*
         * Runtime: 3 ms, faster than 85.00% of Java online submissions for Sort Array By Parity II.
Memory Usage: 44.7 MB, less than 87.74% of Java online submissions for Sort Array By Parity II.
         */
        int l = nums.length;
        int evenIndex = 0;
        int oddIndex = 1;
        
        
        while (evenIndex < l && oddIndex < l) {
            if (evenIndex %2 == 0 && nums[evenIndex] % 2 ==0) {
                evenIndex+= 2;
                continue;
            }
            
            if (oddIndex %2 != 0 && nums[oddIndex] % 2 !=0) {
                oddIndex+=2;
                continue;
            }
            
            //swap
            int temp = nums[evenIndex];
            nums[evenIndex] = nums[oddIndex];
            nums[oddIndex] = temp;
            
            
        }
        return nums;
        
    }
    
    //Even value of odd position and odd value on even position
    public int[] sortArrayByParityIIDerived(int[] nums) {
        
        int l = nums.length;
        int evenIndex = 0;
        int oddIndex = 1;
        
        
        while (evenIndex < l && oddIndex < l) {
            if (evenIndex %2 == 0 && nums[evenIndex] % 2 !=0) {
                evenIndex+= 2;
                continue;
            }
            
            if (oddIndex %2 != 0 && nums[oddIndex] % 2 ==0) {
                oddIndex+=2;
                continue;
            }
            
            //swap
            int temp = nums[evenIndex];
            nums[evenIndex] = nums[oddIndex];
            nums[oddIndex] = temp;
            
            
        }
        return nums;
        
    }
    
}
