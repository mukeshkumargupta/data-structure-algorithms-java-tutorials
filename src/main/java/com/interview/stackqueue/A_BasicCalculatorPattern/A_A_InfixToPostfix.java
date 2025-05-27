package com.interview.stackqueue.A_BasicCalculatorPattern;

import java.util.Stack;
/*
https://www.youtube.com/watch?v=4pIc9UBHJtk&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=3
The video you're referring to discusses three types of notations used in mathematical expressions—Prefix,
Infix, and Postfix—and provides solutions to problems that involve converting between these notations.
 Let's go through the concepts and solutions for conversion between these notations.

Key Definitions:
Infix Notation: The operator is placed between operands (e.g., A + B).
Prefix Notation: The operator is placed before the operands (e.g., + A B).
Postfix Notation: The operator is placed after the operands (e.g., A B +).
Notation Conversions:
Infix to Postfix Conversion
Infix to Prefix Conversion
Prefix to Infix Conversion
Postfix to Infix Conversion
Prefix to Postfix Conversion
Postfix to Prefix Conversion
 */
public class A_A_InfixToPostfix {
        /*
    1. Infix to Postfix Conversion
        Approach:
        Use a stack to temporarily hold operators and parentheses.
        Traverse the infix expression from left to right:
        If it's an operand, append it to the result.
        If it's an operator, pop from the stack to the result until the top of the stack has an operator of lower precedence, then push the current operator.
        If it's a left parenthesis (, push it onto the stack.
        If it's a right parenthesis ), pop operators to the result until a left parenthesis is encountered.
        Time Complexity:
        O(n): Each character in the infix expression is processed once. Pushing or popping an operator from the stack takes constant time.
        Space Complexity:
        O(n): The stack can store at most n operators, where n is the length of the infix expression.
     */


    public static class InfixToPostfix {
        // Function to get precedence of operators
        private static int precedence(char ch) {
            switch (ch) {
                case '+':
                case '-':
                    return 1;
                case '*':
                case '/':
                    return 2;
                case '^':
                    return 3;
            }
            return -1;
        }

        // Function to convert infix expression to postfix expression
        public static String infixToPostfix(String expression) {
            StringBuilder result = new StringBuilder();
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                // If the scanned character is an operand, add it to the output
                if (Character.isLetterOrDigit(c)) {
                    result.append(c);
                }
                // If the scanned character is '(', push it to the stack
                else if (c == '(') {
                    stack.push(c);
                }
                // If the scanned character is ')', pop and output from the stack until '(' is found
                else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        result.append(stack.pop());
                    }
                    stack.pop(); // Pop '(' from the stack
                }
                // Operator encountered
                else {
                    while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                        result.append(stack.pop());
                    }
                    stack.push(c);
                }
            }

            // Pop all remaining operators from the stack
            while (!stack.isEmpty()) {
                result.append(stack.pop());
            }

            return result.toString();
        }
    }
}
