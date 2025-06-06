package com.interview.recursionBacktracking.A_RecursionBasic;

/*
 * https://leetcode.com/problems/powx-n/
 * Category: Medium, Tricky, Top150
 * Related: https://leetcode.com/problems/super-pow/ Medium, Very Bad
 * https://leetcode.com/problems/sqrtx/ Easy
 * https://leetcode.com/problems/count-collisions-of-monkeys-on-a-polygon/ Mediun
 */
public class D_PowerXtoN {
    public double myPowUtil(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n ==1) {
           return x; 
        }
        //if given power is negative make it positive
        //hence we use long int, as int ranges between -2^31 to 2^31-1
        //if n=-2^31 and n=n*-1=2^31 whuch is out of the limit.
        long temp_n = n;
        if (temp_n < 0) {
           temp_n = temp_n *-1; 
        }
        double temp = myPowUtil(x, (int)(temp_n/2));
        if (temp_n %2 != 0) {//odd
            return x*temp*temp;
        } else {
            return temp*temp;
        }
        
    }
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1/myPowUtil(x, n);
        } else {
            return myPowUtil(x, n);
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
