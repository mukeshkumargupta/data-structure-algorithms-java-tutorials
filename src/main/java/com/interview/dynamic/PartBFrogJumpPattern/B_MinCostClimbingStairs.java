package com.interview.dynamic.PartBFrogJumpPattern;

import java.util.Arrays;

/*
https://leetcode.com/problems/min-cost-climbing-stairs/description/
Category: Easy, Must Do
Related:
https://leetcode.com/problems/climbing-stairs/ Easy
https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair/ Hard
You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.



Example 1:

Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.
Example 2:

Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.


Constraints:

2 <= cost.length <= 1000
0 <= cost[i] <= 999
 */
public class B_MinCostClimbingStairs {
private static class Bruitforce {
    /*
    Time Complexity: Exponential
Space Complexity: O(N) due to recursion stack
     */
    public int minCostClimbingStairsRecursive(int[] cost, int i) {
        if (i < 0) return 0;
        if (i == 0 || i == 1) return cost[i];
        return cost[i] + Math.min(minCostClimbingStairsRecursive(cost, i - 1), minCostClimbingStairsRecursive(cost, i - 2));
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        return Math.min(minCostClimbingStairsRecursive(cost, n - 1), minCostClimbingStairsRecursive(cost, n - 2));
    }
}
    /*
    ✅ Time and Space Complexity:
Time: O(n)

Space: O(n) (due to memoization array + recursion stack)
     */
    private static class BetterMemo {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int[] dp = new int[n];
            Arrays.fill(dp, -1);
            return Math.min(minCostClimbingStairsRecursive(cost, n - 1, dp), minCostClimbingStairsRecursive(cost, n - 2, dp));
        }

        private int minCostClimbingStairsRecursive(int[] cost, int i, int[] dp) {
            if (i < 0) return 0;
            if (i == 0 || i == 1) return cost[i];
            if (dp[i] != -1) return dp[i];
            return dp[i] = cost[i] + Math.min(minCostClimbingStairsRecursive(cost, i - 1, dp), minCostClimbingStairsRecursive(cost, i - 2, dp));
        }
    }

    /*
    💡 Idea:
We build an array dp[i] where each index represents the min cost to reach step i.

You can come to step i either from i - 1 or i - 2.
    ✅ Time and Space Complexity:
Time: O(n)

Space: O(n) for the dp array
     */
    private static class Optimized {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int[] dp = new int[n];
            dp[0] = cost[0];
            dp[1] = cost[1];

            for (int i = 2; i < n; i++) {
                dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
            }
            //    return dp[n - 1];not this Tricky
            return Math.min(dp[n - 1], dp[n - 2]);
        }
    }

/*
🧠 3. Optimized DP (Constant Space)
💡 Idea:
Only the last two values are needed at any time. We can track prev1 and prev2.
✅ Time and Space Complexity:
Time: O(n)

Space: O(1)
 */
    private static class OptimizedSpace {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int prev1 = cost[1];
        int prev2 = cost[0];

        for (int i = 2; i < n; i++) {
            int curr = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = curr;
        }

        return Math.min(prev1, prev2);
    }
    }
}
