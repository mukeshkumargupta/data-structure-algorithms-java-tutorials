package com.interview.binarysearch.PartCBinarySearchonRotatedSortedArray;

/*
    https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
    Category: Medium
    There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

    Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
     For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

    Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

    You must decrease the overall operation steps as much as possible.



    Example 1:

    Input: nums = [2,5,6,0,0,1,2], target = 0
    Output: true
    Example 2:

    Input: nums = [2,5,6,0,0,1,2], target = 3
    Output: false


    Constraints:

    1 <= nums.length <= 5000
    -104 <= nums[i] <= 104
    nums is guaranteed to be rotated at some pivot.
    -104 <= target <= 104

 */
public class A_B_BS_5_SearchInRotatedSortedArrayII {

    /*
        Time Complexity: O(N), N = size of the given array.
        Reason: We have to iterate through the entire array to check if the target is present in the array.

        Space Complexity: O(1)
        Reason: We have not used any extra data structures, this makes space complexity, even in the worst case as O(1).
     */
    private static class BruitForce {
        public static boolean searchInARotatedSortedArrayII(int []arr, int k) {
            int n = arr.length; // size of the array.
            for (int i = 0; i < n; i++) {
                if (arr[i] == k) return true;
            }
            return false;
        }

        public static void main(String[] args) {
            int[] arr = {7, 8, 1, 2, 3, 3, 3, 4, 5, 6};
            int k = 3;
            boolean ans = searchInARotatedSortedArrayII(arr, k);
            if (ans == false)
                System.out.println("Target is not present.");
            else
                System.out.println("Target is present in the array.");
        }
    }

    private static class Optimized {
        public static boolean searchInARotatedSortedArrayII(int []arr, int k) {
            int n = arr.length; // size of the array.
            int low = 0, high = n - 1;
            while (low <= high) {
                int mid = (low + high) / 2;

                //if mid points the target
                if (arr[mid] == k) return true;

                //Edge case:
                if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                    low = low + 1;
                    high = high - 1;
                    continue;
                }

                //if left part is sorted:
                if (arr[low] <= arr[mid]) {
                    if (arr[low] <= k && k < arr[mid]) {
                        //element exists:
                        high = mid - 1;
                    } else {
                        //element does not exist:
                        low = mid + 1;
                    }
                } else { //if right part is sorted:
                    if (arr[mid] < k && k <= arr[high]) {
                        //element exists:
                        low = mid + 1;
                    } else {
                        //element does not exist:
                        high = mid - 1;
                    }
                }
            }
            return false;
        }

        public static void main(String[] args) {
            int[] arr = {7, 8, 1, 2, 3, 3, 3, 4, 5, 6};
            int k = 3;
            boolean ans = searchInARotatedSortedArrayII(arr, k);
            if (ans == false)
                System.out.println("Target is not present.");
            else
                System.out.println("Target is present in the array.");
        }
    }
}
