package com.interview.linklist.PartABasicLinkedListOperations.E_AdditionAndMultiplicationPatterns;

/*
https://leetcode.com/problems/add-strings/description/
Category: Easy, Must Do, Facebook, FAANG
Related:
https://leetcode.com/problems/add-two-numbers/ Medium
https://leetcode.com/problems/multiply-strings/ Medium
https://leetcode.com/problems/add-to-array-form-of-integer/ Easy
Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.



Example 1:

Input: num1 = "11", num2 = "123"
Output: "134"
Example 2:

Input: num1 = "456", num2 = "77"
Output: "533"
Example 3:

Input: num1 = "0", num2 = "0"
Output: "0"


Constraints:

1 <= num1.length, num2.length <= 104
num1 and num2 consist of only digits.
num1 and num2 don't have any leading zeros except for the zero itself.
 */
public class C_AddStrings {
    /*
        Approach:
        - Use two pointers starting from the end of both strings (num1 and num2).
        - Maintain a carry for handling sums greater than 9.
        - Iterate until both numbers are processed, appending the sum digit to a StringBuilder.
        - If any carry remains, add it at the end.
        - Finally, reverse the StringBuilder to get the correct sum.

        Complexity Analysis:
        - Time Complexity: O(max(N, M))
            - We iterate over the longer of the two strings once.
        - Space Complexity: O(max(N, M))
            - The result is stored in a StringBuilder, proportional to the length of the sum.
    */
    private static class Optimal {
        public String addStrings(String num1, String num2) {
            StringBuilder result = new StringBuilder();
            int i = num1.length() - 1, j = num2.length() - 1, carry = 0;

            while (i >= 0 || j >= 0 || carry > 0) {
                int sum = carry;
                if(i >= 0)  sum += num1.charAt(i--) - '0';
                if(j >= 0)  sum += num2.charAt(j--) - '0';

                carry = sum / 10;
                result.append(sum % 10);
            }

            return result.reverse().toString();
        }
    }
}
