package com.interview.twopointer;

import java.util.*;

/*
 * Given an array of distinct integers. The task is to count all the triplets such that sum of two elements equals the third element.
 * https://practice.geeksforgeeks.org/problems/count-the-triplets4615/1
 * Category: Must Do
 * Example 1:

Input:
N = 4
arr[] = {1, 5, 3, 2}
Output: 2
Explanation: There are 2 triplets: 
1 + 2 = 3 and 3 +2 = 5 
â€‹Example 2:

Input: 
N = 3
arr[] = {2, 3, 4}
Output: 0
Explanation: No such triplet exits
Your Task:  
You don't need to read input or print anything. Your task is to complete the function countTriplet() which takes the array arr[] and N as inputs and returns the triplet count

Expected Time Complexity: O(N2)
Expected Auxiliary Space: O(1)

Constraints:
1 ≤ N ≤ 103
1 ≤ arr[i] ≤ 105
 */
public class TwoSumEqualThird {
        int countTriplet(int arr[], int n) {
            // code here
            Arrays.sort(arr);
            int l = arr.length;
            int count = 0;
            for (int i = l-1; i >1; i--) {
                int left = 0;
                int right = i-1;
                while (left < right) {
                    if (arr[i] == arr[left] + arr[right]) {
                        count++;
                        left++;
                        right--;
                    } else if (arr[i] > arr[left] + arr[right]) {
                        left++;
                    } else {
                        right--;
                    }
                    
                }
                
            }
            return count;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
