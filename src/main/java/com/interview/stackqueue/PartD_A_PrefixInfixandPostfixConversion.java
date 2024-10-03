package com.interview.stackqueue;

import java.util.Stack;

/*
https://www.youtube.com/watch?v=4pIc9UBHJtk&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=3
The video you're referring to discusses three types of notations used in mathematical expressions—Prefix, Infix, and Postfix—and provides solutions to problems that involve converting between these notations. Let's go through the concepts and solutions for conversion between these notations.

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
public class PartD_A_PrefixInfixandPostfixConversion {
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

    /*
    2. Infix to Prefix Conversion
        Approach:
        Reverse the infix expression.
        Swap the left and right parentheses.
        Convert the reversed expression to postfix.
        Reverse the postfix expression to get the prefix result.

        Time Complexity:
        O(n): We traverse the expression multiple times (for reversing, conversion to postfix, and final reversal).
        Space Complexity:
        O(n): Stack is used to store operators and result.
     */


    public static class InfixToPrefix {
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

        // Function to reverse a string
        private static String reverse(String expression) {
            StringBuilder sb = new StringBuilder(expression);
            return sb.reverse().toString();
        }

        // Function to convert infix to prefix
        public static String infixToPrefix(String expression) {
            // Reverse the infix expression
            expression = reverse(expression);

            // Replace '(' with ')' and vice versa
            StringBuilder modifiedExpression = new StringBuilder();
            for (char ch : expression.toCharArray()) {
                if (ch == '(') {
                    modifiedExpression.append(')');
                } else if (ch == ')') {
                    modifiedExpression.append('(');
                } else {
                    modifiedExpression.append(ch);
                }
            }

            // Convert the modified expression to postfix
            String postfix = InfixToPostfix.infixToPostfix(modifiedExpression.toString());

            // Reverse the postfix to get the prefix
            return reverse(postfix);
        }
    }

    /*
    3. Prefix to Infix Conversion
        Approach:
        Traverse the prefix expression from right to left.
        Use a stack to store intermediate infix expressions.
        For every operand, push it onto the stack.
        For every operator, pop two operands from the stack, create a combined infix expression, and push it back onto the stack.

        Time Complexity:
        O(n): Each character in the expression is processed once.
        Space Complexity:
        O(n): Stack is used to store operands and operators.
     */

    public static class PrefixToInfix {
        // Function to convert prefix to infix
        public static String prefixToInfix(String expression) {
            Stack<String> stack = new Stack<>();

            // Traverse the prefix expression from right to left
            for (int i = expression.length() - 1; i >= 0; i--) {
                char c = expression.charAt(i);

                // If the character is an operand, push it to the stack
                if (Character.isLetterOrDigit(c)) {
                    stack.push(c + "");
                }
                // If the character is an operator, pop two operands from the stack
                else {
                    String operand1 = stack.pop();
                    String operand2 = stack.pop();
                    String infix = "(" + operand1 + c + operand2 + ")";
                    stack.push(infix);
                }
            }

            // The remaining element in the stack is the result
            return stack.pop();
        }
    }

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

    /*
    5. Prefix to Postfix Conversion
        Approach:
        Traverse the prefix expression from right to left.
        Use a stack to store intermediate postfix expressions.
        For every operand, push it onto the stack.
        For every operator, pop two operands from the stack, create a combined postfix expression, and push it back onto the stack.

        Time Complexity:
        O(n): Each character is processed once.
        Space Complexity:
        O(n): Stack stores operands and intermediate results.
     */

    public static class PrefixToPostfix {
        // Function to convert prefix to postfix
        public static String prefixToPostfix(String expression) {
            Stack<String> stack = new Stack<>();

            // Traverse the prefix expression from right to left
            for (int i = expression.length() - 1; i >= 0; i--) {
                char c = expression.charAt(i);

                // If the character is an operand, push it to the stack
                if (Character.isLetterOrDigit(c)) {
                    stack.push(c + "");
                }
                // If the character is an operator, pop two operands from the stack
                else {
                    String operand1 = stack.pop();
                    String operand2 = stack.pop();
                    String postfix = operand1 + operand2 + c;
                    stack.push(postfix);
                }
            }

            // The remaining element in the stack is the result
            return stack.pop();
        }
    }

   /*
   6. Postfix to Prefix Conversion
    Approach:
    Traverse the postfix expression from left to right.
    Use a stack to store intermediate prefix expressions.
    For every operand, push it onto the stack.
    For every operator, pop two operands from the stack, create a combined prefix expression, and push it back onto the stack.

    Time Complexity:
    O(n): Each character is processed once.
    Space Complexity:
    O(n): Stack stores operands and intermediate results.
    */

    public static class PostfixToPrefix {
        // Function to convert postfix to prefix
        public static String postfixToPrefix(String expression) {
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
                    String prefix = c + operand1 + operand2;
                    stack.push(prefix);
                }
            }

            // The remaining element in the stack is the result
            return stack.pop();
        }
    }

}
