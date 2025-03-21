package com.interview.binarysearch.PartFMinOfMaxOrMaxOfMinPattern;

/*
 * https://practice.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1
 * Category: Hard, Must Do
 * https://www.youtube.com/watch?v=nv7F4PiLUzo
 * Related: median of two sorted array, here Kth Smallest is asked, but it can asked Kth largest then think what position of smallest it will be and apply same algo
 * https://practice.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1
 * Other practice link:
 * https://www.codingninjas.com/codestudio/problems/k-th-element-of-2-sorted-array_1164159
 */
public class A_BS_22_KthElementoftwoSortedArrays {
    public long kthElement(int arr1[], int arr2[], int n, int m, int k) {
        /*
         * Runtime: 2 ms, faster than 99.90% of Java online submissions for Median of
         * Two Sorted Arrays. Memory Usage: 40.3 MB, less than 67.80% of Java online
         * submissions for Median of Two Sorted Arrays. TC: log(min(l1, l2)
         */
        if (arr2.length < arr1.length)
            return kthElement(arr2, arr1, m, n, k);
        
        int l1 = arr1.length;
        int l2 = arr2.length;
        int start = Math.max(0, k - l2), end = Math.min(l1, k);//start u can make 0 as well, should work
        
        while (start <= end) {
            // int cut1 = (start+end) >> 1;
            int cut1 = start + (end - start) / 2;
            int cut2 = k - cut1;
            
            int left1 = cut1 == 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
            
            int right1 = cut1 == l1 ? Integer.MAX_VALUE : arr1[cut1];
            int right2 = cut2 == l2 ? Integer.MAX_VALUE : arr2[cut2];
            
            if (left1 <= right2 && left2 <= right1) {
                return Math.max(left1, left2);
            } else if (left1 > right2) {
                end = cut1 - 1;
            } else {
                start = cut1 + 1;
            }
        }
        return -1;
        
    }

    /*Brute Force Approach
    Steps:
    Merge the two arrays into a single sorted array.
    Access the k-th element in the merged array.
    Time Complexity:
    Merging two sorted arrays: O(n + m)
    Accessing the k-th element: O(1)
    Overall, the time complexity is O(n + m), which is inefficient for large arrays, given the constraints.*/

    public static int findKthElementBruteForce(int[] arr1, int[] arr2, int k) {
        int n = arr1.length;
        int m = arr2.length;
        int[] merged = new int[n + m];

        int i = 0, j = 0, l = 0;
        while (i < n && j < m) {
            if (arr1[i] < arr2[j]) {
                merged[l++] = arr1[i++];
            } else {
                merged[l++] = arr2[j++];
            }
        }
        while (i < n) {
            merged[l++] = arr1[i++];
        }
        while (j < m) {
            merged[l++] = arr2[j++];
        }

        return merged[k - 1];
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
