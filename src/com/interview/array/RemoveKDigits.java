package com.interview.array;
import java.util.*;
/*
 * https://leetcode.com/problems/remove-k-digits/
 * Category: Medium, Tricky, VVImp
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

 

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 

Constraints:

1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        Stack<Integer> s = new Stack<>();
        int length = num.length();
        s.push(num.charAt(0) -'0');
  
        for (int i = 1; i < length; i++) {
            int number = num.charAt(i) - '0';
            while (k > 0 && s.size() > 0 && s.peek() > number) {
                s.pop();
                k--;
            }

            if (!(s.isEmpty() && number == 0)) {//ignore if stack empty and element is 0
                s.push(number);
            }
                
            if (k == 0) {
                //Add rest element in stack
                i = i+1;
                while (i < length) {
                    number = num.charAt(i) - '0';
                    if (!(s.isEmpty() && number == 0)) {//ignore if stack empty and element is 0
                        s.push(number); 
                    }
                    i++;
                }
                break; 
            }
            
        }
        
        while (k > 0 && !s.isEmpty()) {//if any k left like in ase of increasing element
            s.pop();
            k--;
        }
        String result = "";
        while(!s.isEmpty()) {
            result = s.pop() + result;
        }
        System.out.println(result);

        if (result.isEmpty()) {
            return "0";
        }
        return result;
        
    }
}
