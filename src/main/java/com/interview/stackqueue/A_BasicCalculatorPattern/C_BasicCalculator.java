package com.interview.stackqueue.A_BasicCalculatorPattern;

import java.util.Stack;

/*
 * https://leetcode.com/problems/basic-calculator/description/
 * https://www.youtube.com/watch?v=3AEKyHx3tzU Very good channel
 * Category: Hard, Facebook, FAANG
 * Related: https://leetcode.com/problems/evaluate-reverse-polish-notation/ Medium
 * https://leetcode.com/problems/basic-calculator-ii/ Medium
 * https://leetcode.com/problems/expression-add-operators/Hard
 https://leetcode.com/problems/basic-calculator-iii/ Hard
 * https://leetcode.com/problems/the-score-of-students-solving-math-expression/ Hard
 * https://leetcode.com/problems/minimize-result-by-adding-parentheses-to-expression/ Medium
 */

public class C_BasicCalculator {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1; // 1 means positive, -1 means negative

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                // Push the result and sign onto the stack
                stack.push(result);
                stack.push(sign);
                // Reset result and sign for the new sub-expression
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop(); // Pop the sign
                result += stack.pop(); // Pop the previous result
            }
        }

        if (number != 0) {
            result += sign * number;
        }

        return result;
    }

    public static void main(String[] args) {
        C_BasicCalculator calculator = new C_BasicCalculator();
        System.out.println(calculator.calculate("1 + 1")); // Output: 2
        System.out.println(calculator.calculate(" 2-1 + 2 ")); // Output: 3
        System.out.println(calculator.calculate("(1+(4+5+2)-3)+(6+8)")); // Output: 23
    }


}
