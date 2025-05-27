package com.interview.stackqueue.A_BasicCalculatorPattern;

public class A_B_InfixToPrefix {
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
            String postfix = A_A_InfixToPostfix.InfixToPostfix.infixToPostfix(modifiedExpression.toString());

            // Reverse the postfix to get the prefix
            return reverse(postfix);
        }
    }
}
