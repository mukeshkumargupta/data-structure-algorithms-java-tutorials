package com.interview.prefixsum.leftrightarraypattern;

/*
 * Date: 04/28/2020
 * Author: Mukesh Kumar Gupta
 *
 * Problem:
 * https://leetcode.com/problems/product-of-array-except-self/description/
 *
 * Category: Medium
 *
 * Description:
 * Given an integer array nums, return an array answer such that answer[i] is equal to
 * the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 * Input: nums = [1, 2, 3, 4]
 * Output: [24, 12, 8, 6]
 *
 * Example 2:
 * Input: nums = [-1, 1, 0, -3, 3]
 * Output: [0, 0, 9, 0, 0]
 *
 * Constraints:
 * - 2 <= nums.length <= 10^5
 * - -30 <= nums[i] <= 30
 * - The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 *
 * Follow-up:
 * Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity analysis.)
 */

public class ArrayProductExceptSelf {
	
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        // Initialize memory to all arrays 
        int left[] = new int[n]; 
        int right[] = new int[n]; 
        int prod[] = new int[n]; 
  
        int i, j; 
  
        /* Left most element of left array is always 1 */
        left[0] = 1; 
  
        /* Rightmost most element of right array is always 1 */
        right[n - 1] = 1; 
  
        /* Construct the left array */
        for (i = 1; i < n; i++) 
            left[i] = nums[i - 1] * left[i - 1]; 
  
        /* Construct the right array */
        for (j = n - 2; j >= 0; j--) 
            right[j] = nums[j + 1] * right[j + 1]; 
  
        /* Construct the product array using  
        left[] and right[] */
        for (i = 0; i < n; i++) 
            prod[i] = left[i] * right[i]; 
  
        /* print the constructed prod array */
        /*for (i = 0; i < n; i++) 
            System.out.print(prod[i] + " ");*/
  
        return prod; 
    }

	public static void main(String[] args) {
	    ArrayProductExceptSelf pa = new ArrayProductExceptSelf(); 
        int arr[] = { 10, 3, 5, 6, 2 }; 
        pa.productExceptSelf(arr);

	}

}
