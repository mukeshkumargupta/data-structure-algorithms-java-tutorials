package com.interview.dynamic;

/**
 * https://practice.geeksforgeeks.org/problems/rod-cutting0840/1
 * Related Question: https://leetcode.com/problems/minimum-cost-to-cut-a-stick/ Hard
 * Category: Must Do
 * https://www.youtube.com/watch?v=eQuJb5gBkkc at 1:5:52 Best explanation after it
 * 
 * Given a rod of length N inches and an array of prices, price[] that contains prices of all pieces of size smaller than N. Determine the maximum value obtainable by cutting up the rod and selling the pieces.

 

Example 1:

Input:
N = 8
Price[] = {1, 5, 8, 9, 10, 17, 17, 20}
Output:
22
Explanation:
The maximum obtainable value is 22 by
cutting in two pieces of lengths 2 and 
6, i.e., 5+17=22.
Example 2:

Input:
N=8
Price[] = {3, 5, 8, 9, 10, 17, 17, 20}
Output: 24
Explanation: 
The maximum obtainable value is 
24 by cutting the rod into 8 pieces 
of length 1, i.e, 8*3=24. 

Your Task:  
You don't need to read input or print anything. Your task is to complete the function cutRod() which takes the array A[] and its size N as inputs and returns the maximum price obtainable.


Expected Time Complexity: O(N2)
Expected Auxiliary Space: O(N)


Constraints:
1 ≤ N ≤ 1000
1 ≤ Ai ≤ 105
 */
public class CuttingRod {
    public int cutRod(int price[], int n) {
        //code here
        int R = n+1;
        
        int[] dp = new int [R];
        dp[0] = 0;
        dp[1] = price[0];
        for (int i = 2; i < R; i++) {
            dp[i] = price[i-1];
            int left = 1;
            int right = i-1;
            while (left <= right) {
                if (dp[left] + dp[right] > dp[i]) {
                    dp[i] = dp[left] + dp[right];
                }
                left++;
                
                
                right--;
            }
        }

        return dp[R-1];
    }
    
    public static void main(String args[]){
        CuttingRod cr =new CuttingRod();
        int[] price = {3,5,8,9,10,20,22,25};
        long t1 = System.currentTimeMillis();
        int r = cr.cutRod(price, price.length);
        long t2 = System.currentTimeMillis();
        System.out.println(r);
        System.out.println(t2 - t1);
    }
}
