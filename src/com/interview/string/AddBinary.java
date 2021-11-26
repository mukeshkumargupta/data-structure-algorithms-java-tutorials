package com.interview.string;

/*
 * Reference: https://leetcode.com/problems/add-binary/
 * Category: Easy, Facebook
 * Given two binary strings a and b, return their sum as a binary string.
 * 
 * Related: https://leetcode.com/problems/optimal-division/ Medium
 * https://leetcode.com/problems/new-21-game/ Medium
 * https://leetcode.com/problems/longest-happy-string/ Medium
 * https://leetcode.com/problems/palindrome-permutation/ Easy
 * https://leetcode.com/problems/output-contest-matches/ Medium
 * https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/ Medium

 

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 

Constraints:

1 <= a.length, b.length <= 104
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        /*
         * Runtime: 3 ms, faster than 50.01% of Java online submissions for Add Binary.
Memory Usage: 39.4 MB, less than 38.40% of Java online submissions for Add Binary.
         */
        StringBuilder result = new StringBuilder();
        int i = a.length()-1, j = b.length()-1;
        int carry = 0;
        while(i >= 0 || j >= 0){
            int sum = carry;
            if(i >= 0) sum += a.charAt(i--) - '0';
            if(j >= 0) sum += b.charAt(j--) - '0';
            carry = sum > 1 ? 1 : 0;
            result.append(sum%2);
        }
        if(carry != 0) result.append(carry);
        return result.reverse().toString();
    }
    
    public String addBinaryLongSolution(String a, String b) {
        /*
         * Runtime: 4 ms, faster than 35.25% of Java online submissions for Add Binary.
Memory Usage: 39.9 MB, less than 15.37% of Java online submissions for Add Binary.
         */
        a = new StringBuffer(a).reverse().toString();
        b = new StringBuffer(b).reverse().toString();
        int len1 = a.length();
        int len2 = b.length();
        int smallLength = len1 < len2 ? len1 : len2;
        int bigLength = len1 > len2 ? len1 : len2;
        boolean isFirstBig = len1 > len2 ? true : false;
        StringBuffer bf = new StringBuffer();
        int carry = 0;
        int i = 0;
        for (; i < smallLength ; i++) {
            int firstDigit = a.charAt(i) - '0';
            int secondDigit = b.charAt(i) - '0';

            if (firstDigit  + secondDigit + carry == 2) {
                bf.append('0');
                carry = 1;
            } else if (firstDigit  + secondDigit + carry == 3) {
                bf.append('1');
                carry = 1;
            } else {
                int sum = firstDigit  + secondDigit + carry;
                bf.append(sum);
                carry = 0; 
            }
            
        }
        
         for (; i < bigLength ; i++) {
             int digit = isFirstBig ? a.charAt(i) - '0' : b.charAt(i) - '0';
            

            if (digit + carry == 2) {
                bf.append('0');
                carry = 1;
            } else {
                int sum = digit + carry;
                bf.append(sum);
                carry = 0; 
            }
            
        }
        
        if (carry == 1) {
           bf.append('1'); 
        }
        return bf.reverse().toString();
 
    }
}
