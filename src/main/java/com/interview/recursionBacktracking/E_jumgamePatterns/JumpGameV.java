package com.interview.recursionBacktracking.E_jumgamePatterns;

/*
 * https://leetcode.com/problems/jump-game-v/
 * https://www.youtube.com/watch?v=pQXbujZLTv0
 * Category: Hard, Fundamental, Must Do
 * Related: https://leetcode.com/problems/jump-game-vii/ Medium
 * Find from which position it will start to cover max jump, find all list of positions from where he can make this much jump.
 * https://leetcode.com/problems/employee-free-time/ Locked
 * https://leetcode.com/problems/minimize-malware-spread/ Imp
 * https://leetcode.com/problems/matrix-cells-in-distance-order/ Imp
 * https://leetcode.com/problems/jump-game-viii/ Medium Locked
 *
 * Given an array of integers arr and an integer d. In one step you can jump from index i to index:
 * i + x where: i + x < arr.length and 0 < x <= d.
 * i - x where: i - x >= 0 and 0 < x <= d.
 * In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j
 * (More formally min(i, j) < k < max(i, j)).
 * You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 *
 * Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
 * Output: 4
 * Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
 * Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9.
 * You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
 * Similarly You cannot jump from index 3 to index 2 or index 1.
 *
 * Example 2:
 *
 * Input: arr = [3,3,3,3,3], d = 3
 * Output: 1
 * Explanation: You can start at any index. You always cannot jump to any index.
 *
 * Example 3:
 *
 * Input: arr = [7,6,5,4,3,2,1], d = 1
 * Output: 7
 * Explanation: Start at index 0. You can visit all the indices.
 *
 * Constraints:
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 105
 * 1 <= d <= arr.length
 */
public class JumpGameV {
    private static class Optimal {
        int[] dp;
        private int helper(int[] arr, int d, int index) {
            if (dp[index] > 0) {//No need to fill by -1 like we do for memoization so we can avoid initialization call so this check is sufficient
                return dp[index];
            }
            int result = 1;// 1 is for if left or right is not able to jump
            for(int i = index-1; i >= Math.max(index-d, 0) && arr[index] > arr[i]; i--) {//left side
                result = Math.max(result, 1 + helper(arr, d, i));
            }

            for (int i = index+1; i <= Math.min(index+d, arr.length-1) && arr[index] > arr[i]; i++ ) {//right side
                result = Math.max(result, 1 + helper(arr, d, i));
            }
            //store it
            return dp[index] = result;

        }
        public int maxJumps(int[] arr, int d) {
        /*
         * Runtime: 8 ms, faster than 99.57% of Java online submissions for Jump Game V.
Memory Usage: 39.5 MB, less than 54.89% of Java online submissions for Jump Game V.
TC: O(N*d)
SC: O(N)
         */
            int max = 0;
            int l = arr.length;
            dp = new int[l];
            for (int i = 0; i < l; i++) {
                max = Math.max(max, helper(arr, d, i));
            }
            return max;

        }
    }

    private static class OptimalLittleMoreOptimization {

        //Little optimization
        public int maxJumps(int[] arr, int d, int pos, int[] dp, int l) {
        /*
         * Runtime: 8 ms, faster than 100.00% of Java online submissions for Jump Game V.
Memory Usage: 42.2 MB, less than 71.76% of Java online submissions for Jump Game V.
         */
            //if already computed then return it
            if (dp[pos] != 0) {
                return dp[pos];
            }

            //Iterate based on criteria
            int max = 1;// 1 is for if left or right is not able to jump
            //Try left side and try right side
            //left side
            for (int i = pos-1; i >= pos-d && i >= 0; i--) {
                if (arr[i] < arr[pos]) {
                    max = Math.max(max, 1 + maxJumps(arr, d, i, dp, l));
                } else {
                    break;// here optimization done
                }

            }

            //right side
            for (int i = pos+1; i <= pos +d && i < l; i++) {
                if (arr[i] < arr[pos]) {
                    max = Math.max(max, 1 + maxJumps(arr, d, i, dp, l));
                } else {
                    break;// here optimization done
                }

            }


            return dp[pos] = max;
        }

        public int maxJumps(int[] arr, int d) {
            int l = arr.length;
            int[] dp = new int[l];
            int max = 0;
            //try from all position and fill in dp
            for (int i = 0; i < l; i++) {
                max = Math.max(max, maxJumps(arr, d, i, dp, l));
            }
            return max;

        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }
    }
}
