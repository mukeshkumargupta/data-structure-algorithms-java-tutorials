package com.interview.string;

/*
 * https://leetcode.com/problems/excel-sheet-column-title/
 * Category: Easy, Google
 * Related:
 * https://leetcode.com/problems/longest-word-in-dictionary/ Medium
 * https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/ Hard
 * https://leetcode.com/problems/xor-operation-in-an-array/ Easy
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...
 

Example 1:

Input: columnNumber = 1
Output: "A"
Example 2:

Input: columnNumber = 28
Output: "AB"
Example 3:

Input: columnNumber = 701
Output: "ZY"
Example 4:

Input: columnNumber = 2147483647
Output: "FXSHRXW"
 */
public class ExcelSheetColumnTitle {
    public String convertToTitle(int columnNumber) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Excel Sheet Column Title.
Memory Usage: 37.9 MB, less than 29.50% of Java online submissions for Excel Sheet Column Title.
         */
        StringBuilder sb = new StringBuilder();
        
        while (columnNumber > 0) {
            columnNumber = columnNumber-1;
            int n = columnNumber % 26;
            char ch = (char)(n + 'A');
            sb.append(ch);
            columnNumber = columnNumber/26;
        }
        return sb.reverse().toString();
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
