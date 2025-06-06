package com.interview.stackqueue;
import java.util.*;

/*
https://leetcode.com/problems/decode-string/description/?envType=study-plan-v2&envId=leetcode-75
Category: top75, Medium, tricky
Related:
https://leetcode.com/problems/encode-string-with-shortest-length/ Hard
https://leetcode.com/problems/number-of-atoms/ Hard
https://leetcode.com/problems/brace-expansion/ Medium
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"


Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
 */
public class DecodeString {
    /*
        ✅ Time Complexity: O(n * k)
        ---------------------------------
        Where:
        - n = Length of the input string
        - k = Maximum number of repetitions (e.g., "100[a]" → 100 repetitions)

        🔍 Breakdown:
        - Each character in the input is processed once → O(n)
        - For repeated substrings like "3[abc]", we generate "abcabcabc" → appending O(k) characters
        - In the worst case, the total length of repeated substrings dominates the runtime

        ⏱ Final Time Complexity:
        O(n + total length of repeated substrings) → O(n * k)


        ✅ Space Complexity: O(n + m)
        ---------------------------------
        Where:
        - n = Space used by stacks to hold characters and numbers
        - m = Size of the final decoded output string

        🔍 Breakdown:
        - Stack can hold up to O(n) elements (characters, digits)
        - Output string takes O(m) space, where m is total characters in the decoded result

        🧪 Worst-case Example:
        Input: "100[a]"
        - Stack holds partial results (O(n))
        - Output: "aaaaaaaaaa... (100 times)" → O(m)

        ✅ Summary
        ---------------------------------
        | Aspect        | Complexity      |
        |---------------|-----------------|
        | Time          | O(n * k)        |
        | Space         | O(n + m)        |
    */
    public String decodeString(String s) {

        Stack<Integer> numStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        int k = 0;

        for (char c : s.toCharArray()) {

            if (Character.isDigit(c)) {
                k = (k * 10) + (c - '0');
                continue;
            }

            if (c == '[') {
                numStack.push(k);
                k = 0;
                stringStack.push(String.valueOf(c));
                continue;
            }

            if (c != ']') {
                stringStack.push(String.valueOf(c));
                continue;
            }

            StringBuilder temp = new StringBuilder();
            while (!stringStack.peek().equals("["))
                temp.insert(0, stringStack.pop());

            // remove the "["
            stringStack.pop();

            // Get the new string
            StringBuilder replacement = new StringBuilder();
            int count = numStack.pop();
            for (int i = 0; i < count; i++)
                replacement.append(temp.toString());

            // Add it to the stack
            stringStack.push(replacement.toString());
        }

        StringBuilder result = new StringBuilder();
        while (!stringStack.empty()) {
            result.insert(0, stringStack.pop());
        }
        return result.toString();
    }
}
