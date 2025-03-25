package com.interview.stackqueue;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*
🔗 Problem Link:
https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/?envType=problem-list-v2&envId=7p59281&utm_source=chatgpt.com

📌 Category: Medium, Facebook, FAANG

🔗 Related Problems:
- https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/ (Medium)
- https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/ (Medium)

📝 Problem Statement:
Given a string `s` consisting of `'('`, `')'`, and lowercase English characters,
remove the minimum number of parentheses (`'('` or `')'` in any positions) so that the
resulting parentheses string is valid and return any valid string.

✅ A parentheses string is valid if:
1️⃣ It is the empty string or contains only lowercase characters.
2️⃣ It can be written as `AB` (A concatenated with B), where A and B are valid strings.
3️⃣ It can be written as `(A)`, where A is a valid string.

🔹 Example 1:
📥 Input: `s = "lee(t(c)o)de)"`
📤 Output: `"lee(t(c)o)de"`
💡 Explanation: `"lee(t(co)de)"`, `"lee(t(c)ode)"` would also be accepted.

🔹 Example 2:
📥 Input: `s = "a)b(c)d"`
📤 Output: `"ab(c)d"`

🔹 Example 3:
📥 Input: `s = "))(("`
📤 Output: `""`
💡 Explanation: An empty string is also valid.

🔹 Constraints:
- `1 <= s.length <= 10^5`
- `s[i]` is either `'('`, `')'`, or a lowercase English letter.
*/
public class PartC_E_MinimumRemovetoMakeValidParentheses {
    /*
        🚀 Stack-Based Approach (Brute Force)

        🔹 Steps:
        1️⃣ Pass 1 (Left to Right):
           - Push index of '(' into a stack.
           - Pop when encountering a valid ')'.
           - If ')' has no matching '(', mark it for removal.

        2️⃣ Pass 2 (Construct Result):
           - Build a valid string without marked characters.

        ⏳ Time Complexity:
        - O(N) (Single pass for traversal and construction)

        📦 Space Complexity:
        - O(N) (Using a stack)

        ⚡ Code (Using Stack)
    */
    private static class BruitForce {
        public String minRemoveToMakeValid(String s) {
            Stack<Integer> stack = new Stack<>();
            Set<Integer> toRemove = new HashSet<>();
            StringBuilder result = new StringBuilder();

            // First pass: Mark invalid parentheses
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    stack.push(i);
                } else if (c == ')') {
                    if (stack.isEmpty()) {
                        toRemove.add(i); // Unmatched ')'
                    } else {
                        stack.pop(); // Valid match
                    }
                }
            }

            // Add remaining unmatched '(' to remove
            while (!stack.isEmpty()) {
                toRemove.add(stack.pop());
            }

            // Second pass: Construct valid string
            for (int i = 0; i < s.length(); i++) {
                if (!toRemove.contains(i)) {
                    result.append(s.charAt(i));
                }
            }

            return result.toString();
        }
    }

    /*
        🚀 Two-Pass Scan (Better)

        🔹 Idea:
        - No extra space for stack (besides output).
        - Use two passes:
          1️⃣ Left to Right: Remove extra ')'.
          2️⃣ Right to Left: Remove extra '('.

        🔹 Steps:
        1️⃣ Left to Right (Remove excess ')'):
           - Maintain a balance count.
           - Skip ')' if it exceeds '('.

        2️⃣ Right to Left (Remove excess '('):
           - Same logic in reverse.

        ⏳ Time Complexity:
        - O(N) (Two passes)

        📦 Space Complexity:
        - O(1) (No extra data structures)
    */
    private static class Better {
        public String minRemoveToMakeValid(String s) {
            StringBuilder sb = new StringBuilder();
            int open = 0;

            // First pass: Remove excess ')'
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    open++;
                } else if (c == ')') {
                    if (open == 0) continue; // Skip excess ')'
                    open--;
                }
                sb.append(c);
            }

            // Second pass: Remove excess '('
            StringBuilder result = new StringBuilder();
            for (int i = sb.length() - 1; i >= 0; i--) {
                if (sb.charAt(i) == '(' && open-- > 0) continue; // Skip excess '('
                result.append(sb.charAt(i));
            }

            return result.reverse().toString();
        }
    }


    /*
       💡 Idea:
       - Track open parentheses '(' count and unmatched closing parentheses ')'.
       - Perform one single pass without needing to reverse the string.

       🔹 Steps:
       1️⃣ First Pass (Left to Right)
          - Track extra ')' using `diff` (number of unmatched ')').
          - Count total '('.
          - Skip extra ')' while keeping a valid sequence.

       2️⃣ Second Pass (Same Direction, Left to Right)
          - Calculate effective '(' count: `totalOpen - diff`
          - Construct the final result in the same direction.

       ⏳ Time Complexity:
       - O(N) (Single pass)

       📦 Space Complexity:
       - O(1) (Only string builder for output)
   */
    private static class Optimised {
        public String minRemoveToMakeValid(String s) {
            StringBuilder sb = new StringBuilder();
            int totalOpen = 0, diff = 0;

            // First pass: Remove extra ')'
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    diff++;
                    totalOpen++;
                } else if (c == ')') {
                    if (diff == 0) {
                        continue;
                    }
                    diff--;
                }
                sb.append(c);
            }

            // Second pass: Remove extra '(' in the same direction
            StringBuilder result = new StringBuilder();
            int effectiveOpen = totalOpen - diff; // Only keep valid '('

            for (char c : sb.toString().toCharArray()) {
                if (c == '(') {
                    if (effectiveOpen > 0) {
                        effectiveOpen--; // Keep valid '('
                    } else {
                        continue; // Skip extra '('
                    }
                }
                result.append(c);
            }

            return result.toString();
        }
    }


}
