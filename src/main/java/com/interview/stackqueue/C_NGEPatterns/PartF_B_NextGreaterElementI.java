package com.interview.stackqueue.C_NGEPatterns;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * https://leetcode.com/problems/next-greater-element-i/ faster than 98.57%
 * Category: Easy
 * Related
 * https://leetcode.com/problems/next-greater-element-ii/ Medium faster than 15.56% of Java   find better solution
 * https://leetcode.com/problems/next-greater-element-iii/
 * https://leetcode.com/problems/daily-temperatures/
 */
public class PartF_B_NextGreaterElementI {

    /*
    Problem: 496. Next Greater Element I
        Given two arrays nums1 and nums2, where nums1 is a subset of nums2, find the next greater element for each element in nums1 based on the corresponding positions in nums2. The next greater element for an element x in nums1 is the first element in nums2 that is greater than x to its right. If no such element exists, return -1.

        Approach 1: Brute Force
        Steps:
        For each element in nums1, find its position in nums2.
        Start from that position in nums2 and search for the next greater element.
        If found, store that value; if not, store -1.
        Time Complexity:
        O(m * n): For each element in nums1 (m elements), we may search through nums2 (n elements) to find the next greater element.
        Space Complexity:
        O(1): Only extra space used is for the result array.
     */
    public static class NextGreaterElementBruteForce {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] result = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                int j = 0;
                // Find the position of nums1[i] in nums2
                while (nums2[j] != nums1[i]) {
                    j++;
                }
                // Look for the next greater element in nums2
                result[i] = -1; // Default value
                for (int k = j + 1; k < nums2.length; k++) {
                    if (nums2[k] > nums1[i]) {
                        result[i] = nums2[k];
                        break;
                    }
                }
            }
            return result;
        }

        public static void main(String[] args) {
            int[] nums1 = {4, 1, 2};
            int[] nums2 = {1, 3, 4, 2};
            NextGreaterElementBruteForce solution = new NextGreaterElementBruteForce();
            System.out.println(Arrays.toString(solution.nextGreaterElement(nums1, nums2)));
        }
    }
    public static class  NextGreaterElementStackRightToLeftIteration {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            // Stack to store the elements for which we are finding the next greater element
            Stack<Integer> stack = new Stack<>();

            // Map to store the next greater element for each value in nums2
            Map<Integer, Integer> nextGreaterMap = new HashMap<>();

            // Traverse nums2 from right to left
            for (int i = nums2.length - 1; i >= 0; i--) {
                // Pop elements from stack that are smaller or equal to the current element
                while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                    stack.pop();
                }

                // If stack is empty, no greater element found, otherwise peek the top element
                nextGreaterMap.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());

                // Push current element to stack
                stack.push(nums2[i]);
            }

            // Prepare result for nums1 based on the map
            int[] result = new int[nums1.length];
            int index = 0;
            for (int num : nums1) {
                result[index++] = nextGreaterMap.get(num);
            }

            return result;
        }
    }

    /*
    Approach 2: Better Approach Using Stack and HashMap
        Instead of brute force, we can use a stack to find the next greater element in nums2 for all elements and store the result in a hashmap for quick look-up when processing nums1.

        Steps:
        Traverse nums2 from left to right.
        Maintain a monotonic decreasing stack. If the current element is greater than the top of the stack, pop from the stack and store the current element as the next greater element in the map for the popped value.
        After processing nums2, look up the next greater element for each element in nums1 from the map.
        If no greater element exists, return -1.
        Time Complexity:
        O(m + n): We traverse nums2 once to build the map and traverse nums1 to fill the result array.
        Space Complexity:
        O(n): We use a hashmap to store the next greater element for each element in nums2.
     */

    public static class NextGreaterElementStack {
        /*
        Key Concepts:
            HashMap (map):
            This stores the mapping of each number in nums2 to its next greater element.
            Stack (stack):
            This is used to keep track of numbers whose next greater elements we haven't found yet. The stack stores numbers in a decreasing order.
            Step-by-Step Process:
            Initialize the Data Structures:

            map: A HashMap where the key is a number from nums2, and the value is its next greater element.
            stack: A Stack that helps us keep track of elements in nums2 that we haven't yet processed (i.e., haven't found their next greater element).
            Traverse nums2:

            For each element num in nums2, we check the stack:
            While the stack is not empty and num is greater than the element on top of the stack (let's call it top), pop the top from the stack and set num as its next greater element in the map.
            After this, push num onto the stack.
            The logic here ensures that any number which doesn't have a next greater element will remain in the stack, but for those which do, we process them immediately.
            Build the Result for nums1:

            For each element in nums1, simply look it up in the map. If it's found, return the corresponding next greater element, otherwise return -1.
            Example Walkthrough:
            Let's walk through the example with:

            nums1 = {4, 1, 2}
            nums2 = {1, 3, 4, 2}
            Step 1: Traverse nums2 and Find Next Greater Elements
            We start with an empty stack and map.

            For num = 1:

            Stack is empty, so we push 1 onto the stack.
            Stack: [1]
            For num = 3:

            stack.peek() = 1 and 1 < 3, so we pop 1 and set map[1] = 3.
            Stack is now empty, so we push 3 onto the stack.
            Stack: [3]
            Map: {1 -> 3}
            For num = 4:

            stack.peek() = 3 and 3 < 4, so we pop 3 and set map[3] = 4.
            Stack is now empty, so we push 4 onto the stack.
            Stack: [4]
            Map: {1 -> 3, 3 -> 4}
            For num = 2:

            stack.peek() = 4 but 4 > 2, so we leave 4 on the stack and push 2.
            Stack: [4, 2]
            Map remains unchanged: {1 -> 3, 3 -> 4}
            Step 2: Build Result for nums1
            For nums1[0] = 4:

            map.get(4) is not present, so the result is -1.
            For nums1[1] = 1:

            map.get(1) = 3, so the result is 3.
            For nums1[2] = 2:

            map.get(2) is not present, so the result is -1.
            Final result array:
            result = {-1, 3, -1}
         */
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            HashMap<Integer, Integer> map = new HashMap<>(); // num -> next greater num
            Stack<Integer> stack = new Stack<>();

            // Traverse nums2 and find the next greater for every element
            for (int num : nums2) {
                while (!stack.isEmpty() && stack.peek() < num) {
                    map.put(stack.pop(), num);
                }
                stack.push(num);
            }

            // Build the result for nums1
            int[] result = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                result[i] = map.getOrDefault(nums1[i], -1);
            }

            return result;
        }

        public static void main(String[] args) {
            int[] nums1 = {4, 1, 2};
            int[] nums2 = {1, 3, 4, 2};
            NextGreaterElementStack solution = new NextGreaterElementStack();
            System.out.println(Arrays.toString(solution.nextGreaterElement(nums1, nums2)));
        }
    }
}
