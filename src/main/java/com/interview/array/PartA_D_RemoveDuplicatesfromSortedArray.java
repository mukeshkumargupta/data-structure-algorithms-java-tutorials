package com.interview.array;

import java.util.HashSet;

/*
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * Category: Easy, Top150, Tricky
 * https://www.youtube.com/watch?v=37E9ckMDdTk&t=27s
 * https://www.youtube.com/watch?v=Fm_p9lJ4Z_8&t=24s
 * Related:
 * https://leetcode.com/problems/surface-area-of-3d-shapes/ Easy
 * https://leetcode.com/problems/find-latest-group-of-size-m/ Medium
 * https://leetcode.com/problems/maximum-number-of-ways-to-partition-an-array/ Hard
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

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

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
 

Constraints:

0 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.
 */
public class PartA_D_RemoveDuplicatesfromSortedArray {
    /*
    Complexity Analysis

    Time complexity: O(n*log(n))+O(n)

    Space Complexity: O(n)
     */
    private static class BruitForce {
        public static void main(String[] args) {
            int arr[] = {1,1,2,2,2,3,3};
            int k = removeDuplicates(arr);
            System.out.println("The array after removing duplicate elements is ");
            for (int i = 0; i < k; i++) {
                System.out.print(arr[i] + " ");
            }
        }
        static int removeDuplicates(int[] arr) {
            HashSet< Integer > set = new HashSet < > ();
            for (int i = 0; i < arr.length; i++) {
                set.add(arr[i]);
            }
            int k = set.size();
            int j = 0;
            for (int x: set) {
                arr[j++] = x;
            }
            return k;
        }
    }

    private static class Optimize {
        public static void main(String[] args) {
            int arr[] = {1,1,2,2,2,3,3};
            int k = removeDuplicates(arr);
            System.out.println("The array after removing duplicate elements is ");
            for (int i = 0; i < k; i++) {
                System.out.print(arr[i] + " ");
            }
        }
        static int removeDuplicates(int[] arr) {
            int i = 0;
            for (int j = 1; j < arr.length; j++) {
                if (arr[i] != arr[j]) {
                    i++;
                    arr[i] = arr[j];
                }
            }
            return i + 1;
        }
    }
    
}
