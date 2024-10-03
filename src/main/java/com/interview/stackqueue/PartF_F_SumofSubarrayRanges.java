package com.interview.stackqueue;


import java.util.Stack;

public class PartF_F_SumofSubarrayRanges {
    // Function to find the Next Smaller Element (right bounds)
    int[] findNSE(int[] arr) {
        int n = arr.length;
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Calculate right array
        for (int i = n - 1; i >= 0; i--) {
            // Find elements greater than or equal to arr[i] on the right
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            // If stack is empty, it means there are no smaller elements to the right
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return right;
    }

    // Function to find the Previous Smaller Element (left bounds)
    int[] findPSEE(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Calculate left array
        for (int i = 0; i < n; i++) {
            // Find elements greater than arr[i] on the left
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            // If stack is empty, it means there are no smaller elements to the left
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return left;
    }

    public long sumSubarrayMins(int[] arr) {
        int n = arr.length;

        int[] nse = findNSE(arr);   // Next Smaller Element indices
        int[] psee = findPSEE(arr); // Previous Smaller Element indices

        // Calculate the sum of minimums
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long leftCount = i - psee[i];   // Number of subarrays ending at i where arr[i] is the minimum
            long rightCount = nse[i] - i;   // Number of subarrays starting at i where arr[i] is the minimum

            // Contribution of arr[i] in all such subarrays
            sum = sum + (arr[i] * leftCount * rightCount);
        }
        return sum;
    }

    // Function to find the Next Larger Element (right bounds)
    int[] findNLE(int[] arr) {
        int n = arr.length;
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Calculate right array
        for (int i = n - 1; i >= 0; i--) {
            // Find elements smaller than or equal to arr[i] on the right
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            // If stack is empty, it means there are no larger elements to the right
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return right;
    }

    // Function to find the Previous Larger Element (left bounds)
    int[] findPLEE(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Calculate left array
        for (int i = 0; i < n; i++) {
            // Find elements smaller than arr[i] on the left
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            // If stack is empty, it means there are no larger elements to the left
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return left;
    }

    // Function to calculate the sum of subarray maximums
    public long sumSubarrayMax(int[] arr) {
        int n = arr.length;

        int[] nle = findNLE(arr);   // Next Larger Element indices
        int[] plee = findPLEE(arr); // Previous Larger Element indices

        // Calculate the sum of maximums
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long leftCount = i - plee[i];   // Number of subarrays ending at i where arr[i] is the maximum
            long rightCount = nle[i] - i;   // Number of subarrays starting at i where arr[i] is the maximum

            // Contribution of arr[i] in all such subarrays
            sum = sum + (arr[i] * leftCount * rightCount);
        }
        return  sum;
    }

    // Function to calculate the difference between subarray ranges
    public long subArrayRanges(int[] nums) {
        return sumSubarrayMax(nums) - sumSubarrayMins(nums);
    }


}
