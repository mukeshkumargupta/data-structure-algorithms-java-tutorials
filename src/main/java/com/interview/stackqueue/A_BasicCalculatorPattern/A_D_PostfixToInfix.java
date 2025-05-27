package com.interview.stackqueue.A_BasicCalculatorPattern;

import java.util.Stack;

public class A_D_PostfixToInfix {
        /*
    4. Postfix to Infix Conversion
        Approach:
        Traverse the postfix expression from left to right.
        Use a stack to store intermediate infix expressions.
        For every operand, push it onto the stack.
        For every operator, pop two operands from the stack, create a combined infix expression, and push it back onto the stack.

        Time Complexity:
        O(n): Each character in the expression is processed once.
        Space Complexity:
        O(n): Stack is used to store operands and intermediate results.
     */

    public static class PostfixToInfix {
        // Function to convert postfix to infix
        public static String postfixToInfix(String expression) {
            Stack<String> stack = new Stack<>();

            // Traverse the postfix expression from left to right
            for (char c : expression.toCharArray()) {
                // If the character is an operand, push it to the stack
                if (Character.isLetterOrDigit(c)) {
                    stack.push(c + "");
                }
                // If the character is an operator, pop two operands from the stack
                else {
                    String operand2 = stack.pop();
                    String operand1 = stack.pop();
                    String infix = "(" + operand1 + c + operand2 + ")";
                    stack.push(infix);
                }
            }

            // The remaining element in the stack is the result
            return stack.pop();
        }
    }
}
