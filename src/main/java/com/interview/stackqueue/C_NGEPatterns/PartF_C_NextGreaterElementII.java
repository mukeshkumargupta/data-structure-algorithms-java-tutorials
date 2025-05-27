package com.interview.stackqueue.C_NGEPatterns;

import java.util.Stack;

/*

https://www.youtube.com/watch?v=7PrncD7v9YQ&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=6
 * https://leetcode.com/problems/next-greater-element-ii/
 * Category: Medium, Monotonic stack (in some order stack maintain  either increasing or decreasing or some pattern)
 *
 * Related:
 * https://leetcode.com/problems/detonate-the-maximum-bombs/description/ Medium
 * https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum/description/ Hard
 * https://leetcode.com/problems/find-the-closest-marked-node/description/ Medium
 * 
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

 

Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number. 
The second 1's next greater number needs to search circularly, which is also 2.
Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
 */
public class PartF_C_NextGreaterElementII {
    /*
    Problem: Next Greater Element
Given an array of integers, for each element in the array, find the next greater element. The next greater element for an element is the first element on the right
side of the array that is larger than the current element. If no such element exists, return -1 for that position.
     */
    /*
    Approach 1: Brute Force
        The brute force approach is to check for each element whether there is any element to its right that is greater. If found, return that value; otherwise, return -1.

        Steps:
        For each element, start a new inner loop to look for the next greater element.
        If found, break the loop and store the value.
        If no greater element is found after the inner loop ends, store -1.
        Time Complexity:
        O(n²): Two nested loops over the array, one for the element and another to check the right side.
        Space Complexity:
        O(n): For storing the result
     */

    public static class NextGreaterElementBruteForce {
        public static int[] nextGreaterElement(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = -1;
                for (int j = i + 1; j < n + n; j++) {
                    int index = j %n ;
                    if (nums[index] > nums[i]) {
                        result[i] = nums[index];
                        break;
                    }
                }
            }

            return result;
        }

        public static void main(String[] args) {
            int[] nums = {4, 5, 2, 25};
            int[] res = nextGreaterElement(nums);
            for (int r : res) {
                System.out.print(r + " ");
            }
        }
    }

    /*
    Approach 2: Better Approach Using Stack (Right-to-Left Traversal)
        Instead of using brute force, we can use a stack to solve the problem efficiently in one pass. The idea is to maintain a stack where we store the elements in decreasing order (from right to left). If we encounter an element that is smaller than the top of the stack, we pop elements from the stack until we find the next greater element.

        Steps:
        Initialize an empty stack and an array for the result initialized to -1.
        Traverse the array from right to left.
        For each element:
        Pop from the stack while the stack is not empty and the top of the stack is less than or equal to the current element.
        If the stack is not empty, the top of the stack is the next greater element.
        Push the current element onto the stack.
        Continue this process until all elements are processed.
        Time Complexity:
        O(4n): Each element is pushed and popped from the stack at most once.
        Space Complexity:
        O(2n): Stack space to store the elements.
     */

    public class NextGreaterElementStack {
        public static int[] nextGreaterElement(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];
            Stack<Integer> stack = new Stack<>();

            for (int i = 2*n - 1; i >= 0; i--) {
                // Pop elements from the stack until we find a greater element
                while (!stack.isEmpty() && stack.peek() <= nums[i%n]) {
                    stack.pop();
                }
                // If stack is not empty, the top of the stack is the next greater element
                if (i < n) {
                    result[i] = stack.isEmpty() ? -1 : stack.peek();
                }

                // Push the current element to the stack
                stack.push(nums[i%n]);
            }

            return result;
        }

        public static void main(String[] args) {
            int[] nums = {4, 5, 2, 25};
            int[] res = nextGreaterElement(nums);
            for (int r : res) {
                System.out.print(r + " ");
            }
        }
    }
}
