package com.interview.stackqueue;

import java.util.Stack;

/*
https://www.youtube.com/watch?v=zMdbdGJNlh4&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=7
https://www.interviewbit.com/problems/nearest-smaller-element/
Category: Easy
 */
public class PartF_D_PreviousSmallerElement {
    public static class PreviousSmallerElementBruteForce {
        /*
        Time Complexity:
        O(n^2): For each element, we are checking all previous elements to find the smaller element.
        Space Complexity: O(1) extra space (besides the space for the result array).
         */
        public static int[] previousSmallerElement(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];

            // For each element, find the previous smaller element by checking all elements to its left
            for (int i = 0; i < n; i++) {
                result[i] = -1; // Initialize result for each element to -1 (no smaller element)
                for (int j = i - 1; j >= 0; j--) { // Traverse to the left of the current element
                    if (nums[j] < nums[i]) { // If a smaller element is found, update the result
                        result[i] = nums[j];
                        break; // Exit the loop as we found the previous smaller element
                    }
                }
            }

            return result;
        }

        public static void main(String[] args) {
            int[] nums = {4, 5, 2, 25};
            int[] res = previousSmallerElement(nums);
            for (int r : res) {
                System.out.print(r + " "); // Output: -1 4 -1 2
            }
        }
    }

    /*
        Time Complexity:
        O(n): Each element is pushed and popped from the stack at most once.
        Space Complexity:
        O(n): Stack space to store the elements.
     */

    public static class PreviousSmallerElement {
        public static int[] previousSmallerElement(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];
            Stack<Integer> stack = new Stack<>();

            // Traverse the array from left to right
            for (int i = 0; i < n; i++) {
                // Pop elements from the stack until the top is smaller than the current element
                while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                    stack.pop();
                }
                // If stack is not empty, the top is the previous smaller element
                result[i] = stack.isEmpty() ? -1 : stack.peek();
                // Push the current element to the stack for future comparisons
                stack.push(nums[i]);
            }

            return result;
        }

        public static void main(String[] args) {
            int[] nums = {4, 5, 2, 25};
            int[] res = previousSmallerElement(nums);
            for (int r : res) {
                System.out.print(r + " ");
            }
        }
    }
}
