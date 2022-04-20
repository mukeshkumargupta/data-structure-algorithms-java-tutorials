package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/valid-perfect-square/
 * https://leetcode.com/problems/valid-perfect-square/
 * Category: Easy, VVImp, patterofsquareroot
 * Related: https://leetcode.com/problems/sum-of-square-numbers/ Medium
 * https://leetcode.com/problems/count-largest-group/ Easy Bad
 * https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/ Hard VVImp
 * https://leetcode.com/problems/stone-game-vii/ Medium, VVImp, It has many version try to solve all
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

Follow up: Do not use any built-in library function such as sqrt.

 

Example 1:

Input: num = 16
Output: true
Example 2:

Input: num = 14
Output: false
 

Constraints:

1 <= num <= 2^31 - 1
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Valid Perfect Square.
Memory Usage: 42 MB, less than 6.36% of Java online submissions for Valid Perfect Square.
TC: Log(N)
         */
        if (num == 0) {
             return true;
         }
         //if x is not 0 then probable ans is 1 so start from x
         //so range is 1 to x
         int start = 1;
         int end = num;
         while (start <= end) {
             int mid = start + (end - start)/2; //to avoud overflow no (start + end)/2
             if (mid*mid == num) {
                 return true;
             }
             else if (mid < num/mid) {//no mid*mid to avoid over flow because it fails for 2147483647
                 start = mid+1;
             } else {
                 end = mid -1;
             }
         }
         return false;
     }
    /*
     * Above solution is risky so u cant remember so better tak long every where where mulitplication is there
     */
    public boolean isPerfectSquare(int num) {
        /*
         * Easy to remember
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Valid Perfect Square.
Memory Usage: 40.7 MB, less than 60.90% of Java online submissions for Valid Perfect Square.
         */
        if (num == 0) {
             return true;
         }
         //if x is not 0 then probable ans is 1 so start from x
         //so range is 1 to x
         long start = 1;
         long end = num;
         while (start <= end) {
             long mid = start + (end - start)/2;
             if (mid*mid == num) {
                 return true;
             }
             else if (mid*mid < num) {
                 start = mid+1;
             } else {
                 end = mid -1;
             }
         }
         return false;
     }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
