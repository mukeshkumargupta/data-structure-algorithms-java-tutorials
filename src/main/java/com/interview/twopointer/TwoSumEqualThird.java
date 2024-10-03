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
    /*
    Approach 1: Brute Force
The brute force approach uses three nested loops to check all possible triplets. This solution has a time complexity of
𝑂
(
𝑛
3
)
O(n
3
 ), which is inefficient for larger arrays.
     */

    public int countTripletBruteForce(int[] arr, int n) {
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] == arr[k]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /*
    Approach 2: Using Hashing
To improve efficiency, use a hash set to check if the sum of any two elements exists as a third element in the array.

Store All Elements in a Set: By storing all elements in a set, we can quickly check if the sum of two numbers exists in the array.
Iterate Through Pairs: For each pair (arr[i], arr[j]), check if arr[i] + arr[j] exists in the set.
This approach reduces the time complexity to
𝑂
(
𝑛
2
)
O(n
2
 ) on average.
 Explanation of the Hashing Solution
HashSet for Lookup: Adding elements to a HashSet allows
𝑂
(
1
)
O(1) average time complexity for lookup.
Counting Valid Triplets: For each pair (i, j), if the sum exists in the set, we increment the count.
This method efficiently finds the number of valid triplets with a time complexity of
𝑂
(
𝑛
2
)
O(n
2
 ) and a space complexity of
𝑂
(
𝑛
)
O(n).
     */

    public int countTripletBetter(int[] arr, int n) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;

        // Add all elements to the set for quick lookup
        for (int num : arr) {
            set.add(num);
        }

        // Check for each pair if their sum exists in the set
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = arr[i] + arr[j];
                if (set.contains(sum)) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
        To optimize this problem, we use a sorting and two-pointer approach.
        By leveraging the sorted order of the array, we can reduce the time complexity to O(n^2),
        eliminating the need for additional space.

        Optimized Approach: Sorting with Two-Pointer Technique

        1. Sort the Array:
           - Start by sorting the array to ensure elements are in ascending order.

        2. Iterate with Two Pointers:
           - For each element arr[i], treat it as the target sum (i.e., arr[i] = arr[j] + arr[k]).
           - Use two pointers:
               - Set `j` (left pointer) to the beginning of the array.
               - Set `k` (right pointer) to `i - 1`, just before the current element.

        3. Two-Pointer Sum Check:
           - If arr[j] + arr[k] equals arr[i], a valid triplet is found. Increment `count` and move both pointers inward.
           - If the sum is less than arr[i], increment `j` to increase the sum.
           - If the sum is greater than arr[i], decrement `k` to decrease the sum.

        4. Repeat the process for each possible i value until all potential triplets are checked.
    */
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
