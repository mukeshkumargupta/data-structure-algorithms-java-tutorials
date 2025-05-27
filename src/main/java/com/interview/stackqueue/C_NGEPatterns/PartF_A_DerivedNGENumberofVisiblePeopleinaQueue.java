package com.interview.stackqueue.C_NGEPatterns;

import java.util.Stack;

/*
https://leetcode.com/problems/number-of-visible-people-in-a-queue/description/
Category: Hard, Facebook, FAANG
Relared:
https://leetcode.com/problems/buildings-with-an-ocean-view/ Medium
https://leetcode.com/problems/sum-of-subarray-ranges/ Medium
https://leetcode.com/problems/sum-of-total-strength-of-wizards/ Hard
https://leetcode.com/problems/number-of-people-that-can-be-seen-in-a-grid/ Medium Locked
https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/ Hard
There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order. You are given an array heights of distinct integers where heights[i] represents the height of the ith person.

A person can see another person to their right in the queue if everybody in between is shorter than both of them. More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).

Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.



Example 1:



Input: heights = [10,6,8,5,11,9]
Output: [3,1,2,1,1,0]
Explanation:
Person 0 can see person 1, 2, and 4.
Person 1 can see person 2.
Person 2 can see person 3 and 4.
Person 3 can see person 4.
Person 4 can see person 5.
Person 5 can see no one since nobody is to the right of them.
Example 2:

Input: heights = [5,1,2,3,10]
Output: [4,1,1,1,0]


Constraints:

n == heights.length
1 <= n <= 105
1 <= heights[i] <= 105
All the values of heights are unique.
 */
public class PartF_A_DerivedNGENumberofVisiblePeopleinaQueue {
    /*
        💡 Problem Intuition
        You're given a list of people with different heights, and you're standing from left to right.
        Each person can see others in front of them (to their right) until someone taller or equal blocks their view.

        We need to return, for each person, how many people they can see.

        ✅ Core Idea (Modified Next Greater Element)
        We'll traverse from right to left, and for each person:

        - We'll use a monotonic decreasing stack (taller to shorter).
        - We'll count:
            - All people shorter than current (we pop them — visible).
            - The first taller person (we stop here — also visible).

        This is similar to Next Greater Element, but with a count of visible people.

        🔁 Step-by-Step Example
        Input:
            heights = [10, 6, 8, 5, 11, 9]
                       0   1  2  3   4  5

        We move from right to left:

        i = 5 (height = 9)
        - Stack: empty → sees 0
        - Push 9

        i = 4 (height = 11)
        - Stack: [9]
        - 9 is shorter → pop 1 person (seen)
        - No one left → sees only 1
        - Push 11

        i = 3 (height = 5)
        - Stack: [11]
        - 11 is taller → see only 1
        - Push 5

        i = 2 (height = 8)
        - Stack: [11, 5]
        - Pop 5 (seen)
        - 11 is taller → see 2 total
        - Push 8

        i = 1 (height = 6)
        - Stack: [11, 8]
        - 8 is taller → see only 1
        - Push 6

        i = 0 (height = 10)
        - Stack: [11, 8, 6]
        - Pop 6 → seen
        - Pop 8 → seen
        - 11 is taller → see 3 total
        - Push 10

        ✅ Final Output:
            [3, 1, 2, 1, 1, 0]

        ⏱️ Time Complexity
        ➤ O(n)
            Each height is pushed and popped at most once from the stack.
            Hence, linear time.

        ➤ O(n) space
            Stack stores at most n elements.
     */
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // Monotonic decreasing stack

        for (int i = n - 1; i >= 0; i--) {
            int count = 0;

            // Pop all shorter people: they can be seen
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.pop();
                count++;
            }

            // If someone remains, they are the next greater (can be seen too)
            // taller person blocks but is visible
            if (!stack.isEmpty()) {
                count++;
            }

            result[i] = count;
            stack.push(heights[i]);
        }

        return result;
    }
}
