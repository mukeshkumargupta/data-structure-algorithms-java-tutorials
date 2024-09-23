package com.interview.dynamic;

import java.util.Arrays;

/*
 * https://www.codingninjas.com/codestudio/problems/frog-jump_3621012
 * https://www.youtube.com/watch?v=EgG3jsGoPvQ&t=120s
 * Category: Easy, Fundamental
 * Problem Statement
There is a frog on the 1st step of an N stairs long staircase. The frog wants to reach the Nth stair. HEIGHT[i] is the height of the (i+1)th stair.If Frog jumps from ith to jth stair, the energy lost in the jump is given by |HEIGHT[i-1] - HEIGHT[j-1] |.In the Frog is on ith staircase, he can jump either to (i+1)th stair or to (i+2)th stair. Your task is to find the minimum total energy used by the frog to reach from 1st stair to Nth stair.
For Example
If the given �HEIGHT� array is [10,20,30,10], the answer 20 as the frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost) and then a jump from 2nd stair to last stair (|10-20| = 10 energy lost). So, the total energy lost is 20.
Input Format:
The first line of the input contains an integer, 'T,� denoting the number of test cases.

The first line of each test case contains a single integer,' N�, denoting the number of stairs in the staircase,

The next line contains �HEIGHT� array.
Output Format:
For each test case, return an integer corresponding to the minimum energy lost to reach the last stair.
Note:
You do not need to print anything. It has already been taken care of. Just implement the given function.
Constraints:
1 <= T <= 10
1 <= N <= 100000.
1 <= HEIGHTS[i] <= 1000 .

Time limit: 1 sec
Sample Input 1:
2
4

10 20 30 10
3
10 50 10
Sample Output 1:
20
0
Explanation Of Sample Input 1:
For the first test case,
The frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost).
Then a jump from the 2nd stair to the last stair (|10-20| = 10 energy lost).
So, the total energy lost is 20 which is the minimum.
Hence, the answer is 20.

For the second test case:
The frog can jump from 1st stair to 3rd stair (|10-10| = 0 energy lost).
So, the total energy lost is 0 which is the minimum.
Hence, the answer is 0.
Sample Input 2:
2
8
7 4 4 2 6 6 3 4
6
4 8 3 10 4 4
Sample Output 2:
7
2
 */
public class PartBFrogJump {
    private static int frogJumpRecursive(int ind, int heights[], int[] dp) {
        if ( ind == 0) {
            return 0;
        }

        if(dp[ind] != -1) {
            return dp[ind] ;
        }

        int fStep = frogJumpRecursive(ind-1, heights, dp) + Math.abs(heights[ind] - heights[ind-1]);
        int sStep = Integer.MAX_VALUE;
        if (ind-2 >= 0) {
            sStep = frogJumpRecursive(ind-2, heights, dp) + Math.abs(heights[ind] - heights[ind-2]);
        }
        return dp[ind] = Math.min(fStep, sStep);
        // Write your code here..
    }
    public static int frogJumpTopDownApproach(int n, int heights[]) {
        /*TC: O(N), since we are using memoization so rest branching will be computed in O1
         * SC: O(N) for dp and O(N) for recursiontree
         */
        // Write your code here..
        int[] dp = new int[n];
        return frogJumpRecursive(n-1, heights, dp);//
    }

    public static int frogJumpBottomUpApproach(int n, int heights[]) {
        /*
         * TC: O(N)
         * SC: O(N)
         */
        // Write your code here..
        int[] dp = new int[n];
        dp[0] = 0;
        for (int ind = 1; ind < n; ind++) {//total loop sud run n-1 time, u can see in recursion
            int fStep = dp[ind-1] + Math.abs(heights[ind] - heights[ind-1]);
            int sStep = Integer.MAX_VALUE;
            if (ind-2 >= 0) {
                sStep = dp[ind-2] + Math.abs(heights[ind] - heights[ind-2]);
            }
            dp[ind] = Math.min(fStep, sStep);
        }
        return dp[n-1];

    }

    public static int frogJumpSpaceOptimized(int n, int heights[]) {
        /*
         * TC: O(N)
         * SC O(1)
         */
        // Write your code here..
        //int[] dp = new int[n];

        //dp[0] = 0;
        int prevIndex1 = 0;
        int prevIndex2 = 0;
        int currIndex = 0;
        for (int ind = 1; ind < n; ind++) {//total loop sud run n-1 time, u can see in recursion
            //int fStep = dp[ind-1] + Math.abs(heights[ind] - heights[ind-1]);
            int fStep = prevIndex1 + Math.abs(heights[ind] - heights[ind-1]);
            int sStep = Integer.MAX_VALUE;
            if (ind-2 >= 0) {
                //sStep = dp[ind-2] + Math.abs(heights[ind] - heights[ind-2]);
                sStep = prevIndex2 + Math.abs(heights[ind] - heights[ind-2]);
            }
            //dp[ind] = Math.min(fStep, sStep);
            currIndex = Math.min(fStep, sStep);
            prevIndex2 = prevIndex1;
            prevIndex1 = currIndex;
        }
        //return dp[n-1];
        return currIndex;

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /*
    Video:  https://www.youtube.com/watch?v=Kmh3rhyEtB8
        Time Complexity: O(N *K)

    Reason: The overlapping subproblems will return the answer in constant time. Therefore the total number of new subproblems we solve is ‘n’. At every new subproblem, we are running another loop for K times. Hence total time complexity is O(N * K).

    Space Complexity: O(N)

    Reason: We are using a recursion stack space(O(N)) and an array (again O(N)). Therefore total space complexity will be O(N) + O(N) ≈ O(N)
     */
    public static class FrogJumpwithKDistance {
        // Recursive function to calculate the minimum cost to reach the end
        // from a given index with at most 'k' jumps.
        static int frogJumpRecursive(int ind, int[] height, int[] dp, int k) {
            // Base case: If we are at the beginning (index 0), no cost is needed.
            if (ind == 0)
                return 0;

            // If the result for this index has been previously calculated, return it.
            if (dp[ind] != -1)
                return dp[ind];

            int mmSteps = Integer.MAX_VALUE;

            // Loop to try all possible jumps from '1' to 'k'
            for (int j = 1; j <= k; j++) {
                // Ensure that we do not jump beyond the beginning of the array
                if (ind - j >= 0) {
                    // Calculate the cost for this jump and update mmSteps with the minimum cost
                    int jump = frogJumpRecursive(ind - j, height, dp, k) + Math.abs(height[ind] - height[ind - j]);
                    mmSteps = Math.min(jump, mmSteps);
                }
            }

            // Store the minimum cost for this index in the dp array and return it.
            return dp[ind] = mmSteps;
        }

        // Function to find the minimum cost to reach the end of the array
        static int frogJumpRecursive(int n, int[] height, int k) {
            int[] dp = new int[n];
            Arrays.fill(dp, -1); // Initialize a memoization array to store calculated results
            return frogJumpRecursive(n - 1, height, dp, k); // Start the recursion from the last index
        }

        // Function to find the minimum cost to reach the end using at most 'k' jumps
        static int frogJumpBottomUp(int n, int[] height, int[] dp, int k) {
            dp[0] = 0;

            // Loop through the array to fill in the dp array
            for (int i = 1; i < n; i++) {
                int mmSteps = Integer.MAX_VALUE;

                // Loop to try all possible jumps from '1' to 'k'
                for (int j = 1; j <= k; j++) {
                    if (i - j >= 0) {
                        int jump = dp[i - j] + Math.abs(height[i] - height[i - j]);
                        mmSteps = Math.min(jump, mmSteps);
                    }
                }
                dp[i] = mmSteps;
            }
            return dp[n - 1]; // The result is stored in the last element of dp
        }

        static int frogJumpBottomUp(int n, int[] height, int k) {
            int[] dp = new int[n]; // Initialize a memoization array to store calculated results
            Arrays.fill(dp, -1);
            return frogJumpBottomUp(n, height, dp, k);
        }

        public static void main(String args[]) {
            int height[] = { 30, 10, 60, 10, 60, 50 };
            int n = height.length;
            int k = 2;
            System.out.println(frogJumpRecursive(n, height, k)); // Print the result of the solve function

            System.out.println(frogJumpBottomUp(n, height, k)); // Print the result of the solve function
        }

    }

}
