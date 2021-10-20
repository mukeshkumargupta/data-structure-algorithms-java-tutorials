package com.interview.dynamic;

/**
 * https://practice.geeksforgeeks.org/problems/rod-cutting0840/1
 * Related Question: https://leetcode.com/problems/minimum-cost-to-cut-a-stick/ Hard
 * Category: Must Do
 * https://www.youtube.com/watch?v=eQuJb5gBkkc at 1:5:52 Best explanation after it
 */
public class CuttingRod {
    public int cutRod(int prices[], int n){
        int R = n+1;
        int[] np = new int [R];
        for (int i = 0; i < n; i++) {
            np[i+1] = prices[i];
        }
        
        int[] dp = new int [R];
        dp[0] = 0;
        dp[1] = np[1];
        for (int i = 2; i < R; i++) {
            dp[i] = np[i];
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
