package com.interview.binarysearch.PartEBinarySearchonAnswerThatIsMinOrMaxPattern;

/*
    https://leetcode.com/problems/kth-missing-positive-number/description/
    Category: Easy, Tricky, FAANG, Google, Facebook, Amazon
    https://www.youtube.com/watch?v=uZ0N_hZpyps&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=17
    Related:
    https://leetcode.com/problems/append-k-integers-with-minimal-sum/ Medium
    Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

    Return the kth positive integer that is missing from this array.



    Example 1:

    Input: arr = [2,3,4,7,11], k = 5
    Output: 9
    Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
    Example 2:

    Input: arr = [1,2,3,4], k = 2
    Output: 6
    Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.


    Constraints:

    1 <= arr.length <= 1000
    1 <= arr[i] <= 1000
    1 <= k <= 1000
    arr[i] < arr[j] for 1 <= i < j <= arr.length
 */
public class A_BS_16_KthMissingPositiveNumber {

    /*
        Complexity Analysis

        Time Complexity: O(N), N = size of the given array.
        Reason: We are using a loop that traverses the entire given array in the worst case.

        Space Complexity: O(1) as we are not using any extra space to solve this problem.
     */
    private static class BruitForce {
        public static int missingK(int[] vec, int n, int k) {
            for (int i = 0; i < n; i++) {
                if (vec[i] <= k) k++; //shifting k
                else break;
            }
            return k;
        }
        public static void main(String[] args) {
            int[] vec = {4, 7, 9, 10};
            int n = 4, k = 4;
            int ans = missingK(vec, n, k);
            System.out.println("The missing number is: " + ans);
        }
    }

    /*
        Complexity Analysis

        Time Complexity: O(logN), N = size of the given array.
        Reason: We are using the simple binary search algorithm.

        Space Complexity: O(1) as we are not using any extra space to solve this problem.
     */
    private static class Optimized {//Ignore because it is easy category
        public static int missingK(int[] vec, int n, int k) {
            int low = 0, high = n - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                int missing = vec[mid] - (mid + 1);
                if (missing < k) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return k + high + 1;
        }
        public static void main(String[] args) {
            int[] vec = {4, 7, 9, 10};
            int n = 4, k = 4;
            int ans = missingK(vec, n, k);
            System.out.println("The missing number is: " + ans);
        }
    }
}
