package com.interview.stackqueue.A_BasicCalculatorPattern;

import java.util.*;
/*
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * Derived: evalPN which is exactly same but iterate from last and num1 will first before num2
 * Category: Medium, Must Do, Top150
 * Related:
 * https://leetcode.com/problems/basic-calculator/ Hard VVImp
 * https://leetcode.com/problems/expression-add-operators/ Hard VVImp
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Note that division between two integers should truncate toward zero.

It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.

 

Example 1:

Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 

Constraints:

1 <= tokens.length <= 104
tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
Accepted
341,990
Submissions
838,767
 */
public class B_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        /*
         * Runtime: 4 ms, faster than 93.43% of Java online submissions for Evaluate Reverse Polish Notation.
Memory Usage: 39.1 MB, less than 46.07% of Java online submissions for Evaluate Reverse Polish Notation.
TC: O(N)
TC: O(d) where dis no of digit like 1,4,3, 6, so in worst case it can go upto that size stack
         */
        Stack<Integer> stack = new Stack<>();
        for(String temp : tokens){
            if(temp.equals("+")){
                Integer num2 = stack.pop();//Imp num2 should be first
                Integer num1 = stack.pop();
                stack.add(num1 + num2);
            }else if(temp.equals("-")){
                Integer num2 = stack.pop();
                Integer num1 = stack.pop();
                stack.add(num1 - num2);
            }else if(temp.equals("*")){
                Integer num2 = stack.pop();
                Integer num1 = stack.pop();
                stack.add(num1 * num2);
            }else if(temp.equals("/")){
                Integer num2 = stack.pop();
                Integer num1 = stack.pop();
                stack.add(num1 / num2);
            }else {
                //Integer newNum = Integer.parseInt(temp);
                Integer newNum = Integer.valueOf(temp);//Both working
                stack.add(newNum);
            }
        }
        return stack.peek();
        
    }

    public int evalPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = tokens.length - 1; i >= 0; i--) {
            String temp = tokens[i];
            if (temp.equals("+")) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.add(num1 + num2);
            } else if (temp.equals("-")) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.add(num1 - num2);
            } else if (temp.equals("*")) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.add(num1 * num2);
            } else if (temp.equals("/")) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.add(num1 / num2);
            } else {
                Integer newNum = Integer.valueOf(temp); // Both Integer.parseInt(temp) and Integer.valueOf(temp) will work
                stack.add(newNum);
            }
        }
        return stack.peek();
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
