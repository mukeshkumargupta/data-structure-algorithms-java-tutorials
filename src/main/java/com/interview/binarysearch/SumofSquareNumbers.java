package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/sum-of-square-numbers/
 * https://leetcode.com/problems/sum-of-square-numbers/solution/
 * Category: Medium, VImp, patternofperfectsquire, valid-perfect-square application
 * Related: 
 * https://leetcode.com/problems/132-pattern/ Medium
 * https://leetcode.com/problems/snapshot-array/ Medium VVImp
 * https://leetcode.com/problems/circular-permutation-in-binary-representation/ Medium Imp
 * https://leetcode.com/problems/minimum-area-rectangle/ VImp
 * https://leetcode.com/problems/max-consecutive-ones-iii/ VVVImp
 * https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/ Easy Bad
 */
public class SumofSquareNumbers {
    public boolean judgeSquareSum(int c) {
        /*
         * Complexity Analysis

Time complexity : O(c)O(c). Two loops upto \sqrt{c} 
c
​
 . Here, cc refers to the given integer(sum of squares).

Space complexity : O(1)O(1). Constant extra space is used.
         */
        for (long a = 0; a * a <= c; a++) {
            for (long b = 0; b * b <= c; b++) {
                if (a * a + b * b == c)
                    return true;
            }
        }
        return false;
    }
    /*
     * Other approach
     */
    public boolean judgeSquareSum(int c) {
        /*
         * Complexity Analysis

Time complexity : underscore c *log c , here log c for sqrt and underscore time outer loop will run
​


Space complexity : O(1)O(1). Constant extra space is used.
         */
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);//since mupliplication take so long is taken
            if (b == (int) b)
                return true;
        }
        return false;
    }
    
    public boolean isPerfectSquare(int num) {
        if (num == 0) {
             return true;
         }
         //if x is not 0 then probable ans is 1 so start from x
         //so range is 1 to x
         long start = 1;
         long end = num;
         while (start <= end) {
             long mid = start + (end - start)/2; //to avoud overflow no (start + end)/2
             if (mid*mid == num) {//
                 return true;
             }
             else if (mid*mid < num) {//no mid*mid to avoid over flow
                 start = mid+1;
             } else {
                 end = mid -1;
             }
         }
         return false;
     }

    public boolean isPerfectSquareRecursive(long start, long end, int n) {
        if (start > end)
            return false;
        long mid = start + (end - start) / 2;
        if (mid * mid == n)
            return true;
        if (mid * mid > n)
            return isPerfectSquareRecursive(start, mid - 1, n);
        return isPerfectSquareRecursive(mid + 1, end, n);
        
     }
    
    //Bynary search approach
    //extend problem like a^2 +b ^2 + c^ 2 = n then you can solve n - b^2 - c^ 2 , so for b and c loop required and balance apply binary search
    public boolean judgeSquareSum(int c) {
        /*
         * Complexity Analysis

Time complexity : underscore c *log c , here log c for sqrt and underscore time outer loop will run
​


Space complexity : O(1)O(1). Constant extra space is used.
is we use reqursibe binary search the space for recursion that is logc
         * Runtime: 405 ms, faster than 5.19% of Java online submissions for Sum of Square Numbers.
Memory Usage: 41.4 MB, less than 26.93% of Java online submissions for Sum of Square Numbers.
         */
        for (long a = 0; a * a <= c; a++) {
            int b = c - (int)(a * a);

           if (isPerfectSquareRecursive(0, b,b))
            if (isPerfectSquare(b))
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
