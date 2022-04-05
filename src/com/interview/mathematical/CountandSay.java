package com.interview.mathematical;
/*
 * https://leetcode.com/problems/count-and-say/submissions/
 * Category: Medium, Top150
 * https://www.youtube.com/watch?v=1YUqtoT9YoE&t=42s
 * 
 * Related:
 * https://leetcode.com/problems/encode-and-decode-strings/ Medium
 * https://leetcode.com/problems/string-compression/ Medium
 * 
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

countAndSay(1) = "1"
countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
To determine how you "say" a digit string, split it into the minimal number of groups so that each group is a contiguous section all of the same character. Then for each group, say the number of characters, then say the character. To convert the saying into a digit string, replace the counts with a number and concatenate every saying.

For example, the saying and conversion for digit string "3322251":


Given a positive integer n, return the nth term of the count-and-say sequence.

 

Example 1:

Input: n = 1
Output: "1"
Explanation: This is the base case.
Example 2:

Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 

Constraints:

1 <= n <= 30
Accepted
608,133
Submissions
1,255,914
 */

public class CountandSay {
    public String countAndSaySlow(int n) {
        /*
         * Runtime: 60 ms, faster than 5.28% of Java online submissions for Count and Say.
Memory Usage: 72.9 MB, less than 5.04% of Java online submissions for Count and Say.
         */
        if (n ==1) {
            return "1";
        }
        if (n ==2) {
            return "11";
        }
        
        String result;
        String s = "11";
        for (int i = 3; i <= n; i++) {
            String temp  = s + "$";//You can put any character, this is done when it went last then you can add count and say
            int l = temp.length();
            int count =1;//bydefault count is 1
            String tempResult = "";
            for (int j = 1; j < l; j++) {
                if (temp.charAt(j) == temp.charAt(j-1)) {
                    count++;
                } else {//add all count
                    tempResult = tempResult+ count;
                    tempResult = tempResult + temp.charAt(j-1);
                    count = 1;
                }
                
            }
            s = tempResult;
            
        }
        return s;
        
    }
    
    public String countAndSay(int n) {
        /*
         * Runtime: 4 ms, faster than 74.21% of Java online submissions for Count and Say.
Memory Usage: 40 MB, less than 88.03% of Java online submissions for Count and Say.
         */
        if (n ==1) {
            return "1";
        }
        if (n ==2) {
            return "11";
        }
        
        String result;
        String s = "11";
        for (int i = 3; i <= n; i++) {
            String temp  = s + "$";//You can put any character, this is done when it went last then you can add count and say
            int l = temp.length();
            int count =1;//bydefault count is 1
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < l; j++) {
                if (temp.charAt(j) == temp.charAt(j-1)) {
                    count++;
                } else {//add all count
                    sb.append(count);
                    sb.append(temp.charAt(j-1));
                    count = 1;
                }
                
            }
            s = sb.toString();
            
        }
        return s;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
