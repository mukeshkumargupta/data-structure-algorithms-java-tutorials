package com.interview.array;

/**
 * Date 04/28/2020
 * @author Mukesh Kumar Gupta
 *
 *
 * https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3300/
 * Category: Medium
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
