package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
 * It is really a straight up fibonacci series with values
 * 1,2,3,5,8,13....
 * Look how we assign a[i] value of a[i-1] + b[i-1] and then b[i] becomes a[i]
 * Category: Easy, Must Do
 * 
 * Given a positive integer N, count all possible distinct binary strings of length N such that there are no consecutive 1’s.

Examples: 

Input:  N = 2
Output: 3
// The 3 strings are 00, 01, 10

Input: N = 3
Output: 5
// The 5 strings are 000, 001, 010, 100, 101
Recommended: Please solve it on “PRACTICE ” first, before moving on to the solution. 
 
This problem can be solved using Dynamic Programming. Let a[i] be the number of binary strings of length i which do not contain any two consecutive 1’s and which end in 0. Similarly, let b[i] be the number of such strings which end in 1. We can append either 0 or 1 to a string ending in 0, but we can only append 0 to a string ending in 1. This yields the recurrence relation: 

a[i] = a[i - 1] + b[i - 1]
b[i] = a[i - 1] 
The base cases of above recurrence are a[1] = b[1] = 1. The total number of strings of length i is just a[i] + b[i].
Following is the implementation of above solution. In the following implementation, indexes start from 0. So a[i] represents the number of binary strings for input length i+1. Similarly, b[i] represents binary strings for input length i+1.
 */
public class CountNumberOfBinaryWithoutConsecutive1s {

    public int countDPRecursion(int n, int[] dp) {//Mine
        if (n ==1) {
            dp[1] = 2;
            return dp[1];
        }
        if (n ==2) {
            dp[2] = 3;
            return dp[2];
        }
        
        if (dp[n] != 0) {
            return dp[n];
        }
        dp[n] = countDPRecursion(n-1, dp) + countDPRecursion(n-2, dp);
        return dp[n];
        
    }
    public int count(int n){
        int a[] = new int[n];
        int b[] = new int[n];
        
        a[0] = 1;
        b[0] = 1;
        
        for(int i=1; i < n; i++){
            a[i] = a[i-1] + b[i-1];
            b[i] = a[i-1];
        }
        
        return a[n-1] + b[n-1];
    }
    
    public int countSimple(int n){
        int a = 1;
        int b = 1;
        
        for(int i=1; i < n; i++){
            int tmp = a;
        	a = a + b;
            b = tmp;
        }
        
        return a + b;
    }
    
    public static void main(String args[]){
        CountNumberOfBinaryWithoutConsecutive1s cnb = new CountNumberOfBinaryWithoutConsecutive1s();
        System.out.println(cnb.count(5));
        int[] dp = new int[6];
        System.out.println(cnb.countDPRecursion(5, dp));//Mukesh approach
    }
}
