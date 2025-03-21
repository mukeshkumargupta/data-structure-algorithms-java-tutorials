package com.interview.binarysearch.PartEBinarySearchonAnswerThatIsMinOrMaxPattern;

/*
    https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/
    Category: Medium
    Related:
    https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/ Medium
    Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result.
     Find the smallest divisor such that the result mentioned above is less than or equal to threshold.

    Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

    The test cases are generated so that there will be an answer.

    Example 1:

    Input: nums = [1,2,5,9], threshold = 6
    Output: 5
    Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
    If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
    Example 2:

    Input: nums = [44,22,33,11,1], threshold = 5
    Output: 44
 */
public class A_BS_14_FindTheSmallestDivisorGivenaThreshold {
    /*
        Complexity Analysis

        Time Complexity: O(max(arr[])*N), where max(arr[]) = maximum element in the array, N = size of the array.
        Reason: We are using nested loops. The outer loop runs from 1 to max(arr[]) and the inner loop runs for N times.

        Space Complexity: O(1) as we are not using any extra space to solve this problem.
     */
    private static class BruitForce {
        public static int sumByD(int[] arr, int div) {
            int n = arr.length; //size of array
            //Find the summation of division values:
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.ceil((double)(arr[i]) / (double)(div));
            }
            return sum;
        }
        public static int smallestDivisor(int[] arr, int limit) {
            int n = arr.length; //size of array.
            //Find the maximum element:
            int maxi = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                maxi = Math.max(maxi, arr[i]);
            }

            //Find the smallest divisor:
            for (int d = 1; d <= maxi; d++) {
                //Find the summation result:
                int sum = sumByD(arr, d);
                if (sum <= limit)
                    return d;
            }
            return -1;
        }
        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 5};
            int limit = 8;
            int ans = smallestDivisor(arr, limit);
            System.out.println("The minimum divisor is: " + ans);
        }
    }

    private static class Optimize {
        /*
            Complexity Analysis

            Time Complexity: O(log(max(arr[]))*N), where max(arr[]) = maximum element in the array, N = size of the array.
            Reason: We are applying binary search on our answers that are in the range of [1, max(arr[])]. For every possible divisor ‘mid’, we call the sumByD() function. Inside that function, we are traversing the entire array, which results in O(N).

            Space Complexity: O(1) as we are not using any extra space to solve this problem.
         */
        public static int sumByD(int[] arr, int div) {
            int n = arr.length; //size of array
            //Find the summation of division values:
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.ceil((double)(arr[i]) / (double)(div));
            }
            return sum;
        }
        public static int smallestDivisor(int[] arr, int limit) {
            int n = arr.length; //size of array.
            if(n > limit) return -1;
            //Find the maximum element:
            int maxi = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                maxi = Math.max(maxi, arr[i]);
            }

            int low = 1, high = maxi;

            int ans = -1;
            //Apply binary search:
            while (low <= high) {
                int mid = (low + high) / 2;
                if (sumByD(arr, mid) <= limit) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            //return low;
            return ans;
        }
        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 5};
            int limit = 8;
            int ans = smallestDivisor(arr, limit);
            System.out.println("The minimum divisor is: " + ans);
        }
    }
}
