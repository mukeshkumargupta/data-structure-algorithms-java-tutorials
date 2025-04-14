package com.interview.stackqueue;

import java.util.Stack;

/*
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/description/
Category: Medium, Facebook, FAANG
https://www.youtube.com/watch?v=RJpy4A7LJrs
Related:
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/ Easy
https://leetcode.com/problems/replace-non-coprime-numbers-in-array/ Hard
https://leetcode.com/problems/minimize-string-length/ Easy
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.



Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"


Constraints:

1 <= s.length <= 105
2 <= k <= 104
s only contains lowercase English letters.
 */
public class RemoveAllAdjacentDuplicatesinStringII {

    private static class Better {
        public String removeDuplicates(String s, int k) {
            Stack<Character> stack1 = new Stack<>();

            for (char c : s.toCharArray()) {
                Stack<Character> stack2 = new Stack<>();
                stack2.push(c);

                while (!stack1.isEmpty() && stack1.peek() == c) {
                    stack2.push(stack1.pop());
                }

                if (stack2.size() != k) {
                    while (!stack2.isEmpty()) {
                        stack1.push(stack2.pop());
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            while (!stack1.isEmpty()) {
                sb.append(stack1.pop());
            }

            return sb.reverse().toString();
        }

        private static class Optimal {
            /*
            TC: O(N)
            SC: O(N)
             */
            public String removeDuplicates(String s, int k) {
                Stack<int[]> stack = new Stack<>();

                for (char c : s.toCharArray()) {
                    if (!stack.isEmpty() && stack.peek()[0] == c) {
                        stack.peek()[1]++;
                    } else {
                        stack.push(new int[]{c, 1});
                    }

                    if (stack.peek()[1] == k) {
                        stack.pop();
                    }
                }

                StringBuilder sb = new StringBuilder();

                while (!stack.isEmpty()) {
                    int[] top = stack.pop();

                    while (top[1]-- > 0)
                        sb.append((char) top[0]);
                }

                return sb.reverse().toString();
            }
        }
    }
}
