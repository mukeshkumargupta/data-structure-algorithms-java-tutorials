package com.interview.stackqueue;

import java.util.Stack;

/*
https://leetcode.com/problems/removing-stars-from-a-string/description/?envType=study-plan-v2&envId=leetcode-75Category:
Category: top75, Medium
Related:
https://leetcode.com/problems/backspace-string-compare/ Easy
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/ Easy

You are given a string s, which contains stars *.

In one operation, you can:

Choose a star in s.
Remove the closest non-star character to its left, as well as remove the star itself.
Return the string after all stars have been removed.

Note:

The input will be generated such that the operation is always possible.
It can be shown that the resulting string will always be unique.


Example 1:

Input: s = "leet**cod*e"
Output: "lecoe"
Explanation: Performing the removals from left to right:
- The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
- The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
- The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
There are no more stars, so we return "lecoe".
Example 2:

Input: s = "erase*****"
Output: ""
Explanation: The entire string is removed, so we return an empty string.


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters and stars *.
The operation above can be performed on s.
 */
public class PartA_B_RemovingStarsFromaString {
    /*
✅ Time Complexity: O(n)
- Loop through the string once: O(n)
- Stack operations (`push` and `pop`) are O(1) each
- Final while-loop pops all characters from the stack: O(n)
- Reverse operation on StringBuilder: O(n)

👉 Total: O(n) + O(n) + O(n) = O(n)

✅ Space Complexity: O(n)
- In the worst case (no '*' in input), all characters go into the stack → O(n)
- StringBuilder also stores up to n characters → O(n)

👉 So overall auxiliary space used is O(n)
*/
    private static class Better {
        public String removeStars(String s) {
            int len = s.length();
            Stack<Character> st = new Stack<>();

            for (char ch: s.toCharArray()) {
                if (!st.isEmpty() && ch == '*') {
                    st.pop();
                    continue;
                }

                if (ch != '*') {
                    st.push(ch);
                }
            }
            StringBuilder sb = new StringBuilder();
            while(!st.isEmpty()) {
                sb.append(st.pop());
            }
            return sb.reverse().toString();

        }
    }

    /*
✅ Time Complexity: O(n)
- We iterate through the string once (length = n)
- Each operation on StringBuilder (append or delete last) takes O(1)

✅ Space Complexity: O(n)
- In the worst case (no `*` characters), the resulting string is of length n
- StringBuilder grows up to O(n) space
*/
    private static class Optimal {
        public String removeStars(String s) {
            StringBuilder sb = new StringBuilder();

            for (char ch : s.toCharArray()) {
                if (ch == '*') {
                    sb.deleteCharAt(sb.length() - 1);  // Remove the last character
                } else {
                    sb.append(ch);
                }
            }

            return sb.toString();
        }
    }
}
