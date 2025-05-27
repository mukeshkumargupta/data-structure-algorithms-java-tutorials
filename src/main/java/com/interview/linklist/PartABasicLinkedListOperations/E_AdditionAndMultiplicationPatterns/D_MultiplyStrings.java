package com.interview.linklist.PartABasicLinkedListOperations.E_AdditionAndMultiplicationPatterns;

/*
 * https://leetcode.com/problems/multiply-strings/description/
 * https://www.youtube.com/watch?v=rUVg2Vewbo8
 * Category: Medium, Facebook, FAANG, Tricky, Google
 * Related:
 * https://leetcode.com/problems/add-two-numbers/ Medium
 * https://leetcode.com/problems/plus-one/ Easy
 * https://leetcode.com/problems/add-binary/ Easy
 * https://leetcode.com/problems/add-strings/ Easy
 * https://leetcode.com/problems/apply-discount-to-prices/ Medium
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 *
 * Constraints:
 *
 * 1 <= num1.length, num2.length <= 200
 * num1 and num2 consist of digits only.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class D_MultiplyStrings {
    /*
    🟡 Approach 2: Better (Using Intermediate Array)
    💡 Idea:

    Instead of summing up partial results separately, store them directly in an array.

    🔹 Steps:
    1. Create an array `product` of size `m + n` (max possible length).
    2. Multiply each digit from `num1[i]` with `num2[j]` and store it at `i + j`.
    3. Handle carry properly.
    4. Convert the array to a string, removing leading zeros.

    🔵 Time Complexity: O(m × n)
    🔵 Space Complexity: O(m + n)

    ✅ Why This is Better?
    - Efficient storage: Uses a single array for calculations.
    - Avoids repeated string addition (which is expensive).
    - Directly builds the result, eliminating unnecessary steps.
    */
    private static class Solution {
        public static String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) return "0";

            int m = num1.length(), n = num2.length();
            int[] product = new int[m + n];

            // Multiply each digit of num1 with each digit of num2
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {//upper string visualise this
                    int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                    int sum = mul + product[i + j + 1]; //Tricky

                    product[i + j] += sum / 10;
                    product[i + j + 1] = sum % 10; //Tricky since already its previous value added in mul
                }
            }

            // Convert product array to string
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < product.length; i++) {
                if (i == 0 && product[i] == 0) {
                    continue;
                }
                sb.append(product[i]);

            }
            return sb.toString();
        }

        public static void main(String[] args) {
            System.out.println(multiply("123", "456")); // Output: "56088"
        }
    }

}
