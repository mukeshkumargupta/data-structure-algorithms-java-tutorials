package com.interview.dynamic;

/**
 * Date 04/04/2017
 * @author Mukesh Kumar Gupta
 *
 * 0/1 Knapsack Problem - Given items of certain weights/values and maximum allowed weight
 * how to pick items to pick items from this set to maximize sum of value of items such that
 * sum of weights is less than or equal to maximum allowed weight.
 *
 * Time complexity - O(W*total items)
 *
 * Youtube link
 * Topdown DP - https://youtu.be/149WSzQ4E1g
 * Category: Medium, Must Do
 * Bottomup DP - https://youtu.be/8LusJS5-AGo (This method selected)
 * 
    int val[] = { 60, 100, 120 };
    int wt[] = { 10, 20, 30 };
    int W = 50;

 *
 * References -
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 * https://en.wikipedia.org/wiki/Knapsack_problem
 */
public class Knapsack01 {

    /**
     * Solves 0/1 knapsack in bottom up dynamic programming
     */
    
    public int bottomUpDP(int val[], int wt[], int W){
        int R = val.length+1;
        int C = W+1;
        int T[][] = new int[R][C];
        for(int i=0; i < R; i++){
            for(int j=0; j < C; j++){
                if(i == 0 || j == 0){
                    T[i][j] = 0;
                    continue;
                }
                if(wt[i-1] > j){
                    T[i][j] = T[i-1][j];
                }else{
                    T[i][j] = Math.max(T[i-1][j], T[i-1][j-wt[i-1]] + val[i-1]);
                }
            }
        }
        return T[R-1][C-1];
    }


    public static void main(String args[]){
        Knapsack01 k = new Knapsack01();
        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};

        System.out.println(k.bottomUpDP(val, wt, 30));
    }
}
