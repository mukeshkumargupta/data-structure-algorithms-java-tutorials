package com.interview.hash;

import java.util.*;

/*
 * https://leetcode.com/problems/intersection-of-two-arrays/
 * Related: https://leetcode.com/problems/intersection-of-three-sorted-arrays/ Easy (Lock)
 * Category: Easy
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.

 

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.

 */
public class IntersectionofTwoArraysNotSortedArray {
private static class Optimized {
    public static List<Integer> intersection(int[] A, int[] B) {
        Set<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        // Insert elements of A into set
        for (int num : A) {
            set.add(num);
        }

        // Check if elements of B exist in set
        for (int num : B) {
            if (set.contains(num)) {
                result.add(num);
                set.remove(num); // Remove to handle duplicates
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 3};
        int[] B = {2, 2, 4};
        System.out.println(intersection(A, B)); // Output: [2]
    }
}

    
}
