package com.interview.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode.com/problems/jump-game-iii/
 * https://www.youtube.com/watch?v=7Cz91Uj0VCU
 * Category: Medium, Must Do, Fundamental, all its variant important to make complete all patterns of ds&algo
 *
 * Related:
 * - https://leetcode.com/problems/jump-game-vii/ (Jump Game VII - Medium)
 * - https://leetcode.com/problems/find-permutation/ (Medium, Locked)
 * - https://leetcode.com/problems/minimum-absolute-sum-difference/ (Medium, Very Important)
 * - https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/ (Medium, Very Very Important)
 * - https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/ (Medium, Bad Question)
 * - https://leetcode.com/problems/delete-leaves-with-a-given-value/ (Medium)
 * - https://leetcode.com/problems/maximum-good-people-based-on-statements/ (Hard)
 *
 * Other Related Tip:
 * - Find minimum number of jumps required to reach destination → just count levels in BFS traversal, you will get the answer.
 * - This question can be enhanced for any number of jumps by giving a formula.
 *   In this question, only left or right jump (by arr[i]) is allowed.
 *
 * Problem:
 * Given an array of non-negative integers arr, and a start index.
 * You can move:
 * - to index i + arr[i], or
 * - to index i - arr[i],
 * but you cannot move outside of the array bounds.
 *
 * Goal:
 * - Check if you can reach any index with value 0.
 *
 * Example 1:
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 *  Paths to reach index 3 with value 0:
 *    - index 5 -> index 4 -> index 1 -> index 3
 *    - index 5 -> index 6 -> index 4 -> index 1 -> index 3
 *
 * Example 2:
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 *  Path to reach index 3:
 *    - index 0 -> index 4 -> index 1 -> index 3
 *
 * Example 3:
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation:
 *  - No way to reach a 0 value.
 *
 * Constraints:
 * - 1 <= arr.length <= 5 * 10^4
 * - 0 <= arr[i] < arr.length
 * - 0 <= start < arr.length
 */
public class A_E_JumpGameIII {
    public static class CanReachBFSBruitforce {
        public boolean canReach(int[] arr, int start) {
            int n = arr.length;
            boolean[] visited = new boolean[n]; // Tracks visited indices
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);

            while (!queue.isEmpty()) {
                int curr = queue.poll();

                // If the current position holds 0, we can reach the goal
                if (arr[curr] == 0) {
                    return true;
                }

                // Jump forward and backward
                int forward = curr + arr[curr];
                int backward = curr - arr[curr];

                // Add valid and unvisited positions to the queue
                if (forward < n && !visited[forward]) {
                    // Mark the current position as visited
                    visited[curr] = true;
                    queue.offer(forward);
                }
                if (backward >= 0 && !visited[backward]) {
                    // Mark the current position as visited
                    visited[curr] = true;
                    queue.offer(backward);
                }
            }

            return false; // If we exhaust all possibilities and can't find a zero
        }

        public static void main(String[] args) {
            CanReachBFSBruitforce solution = new CanReachBFSBruitforce();
            int[] arr = {4, 2, 3, 0, 3, 1, 2};
            int start = 5;
            System.out.println(solution.canReach(arr, start)); // Output: true
        }
    }

    public static class CanReachBetterApproach {
        //Using bfs
        public boolean canReachBfs(int[] arr, int start) {
                    /*
                     * T.C : O(n)
            S.C : O(n)
            Runtime: 6 ms, faster than 51.99% of Java online submissions for Jump Game III.
            Memory Usage: 49.8 MB, less than 88.54% of Java online submissions for Jump Game III.
                     */
            int l = arr.length;
            Queue<Integer> q = new LinkedList<>();
            q.add(start);

            while (!q.isEmpty()) {
                int curr = q.remove();

                if (arr[curr] == 0) return true;   // reached the target index

                // reached this index again, so not possible from this index but might be possible to reach from other direction, so check again in the queue
                if (arr[curr]<0) continue;

                if (curr + arr[curr] < l)
                    q.add(curr + arr[curr]);
                if (curr - arr[curr] >= 0)
                    q.add(curr - arr[curr]);

                arr[curr] = -arr[curr];   // to distinguish between index coming first time or again
            }

            return false;
        }
    }

    public static class CanReachDFSBruitForce {
        private boolean canReach(int[] arr, int start) {
            return dfs(arr, start, new boolean[arr.length]);
        }

        private boolean dfs(int[] arr, int curr, boolean[] visited) {
            // If current position is out of bounds or already visited, return false
            if (curr < 0 || curr >= arr.length || visited[curr]) {
                return false;
            }

            // If the current position holds 0, we can reach the goal
            if (arr[curr] == 0) {
                return true;
            }

            // Mark the current position as visited
            visited[curr] = true;

            // Explore forward and backward jumps
            return dfs(arr, curr + arr[curr], visited) || dfs(arr, curr - arr[curr], visited);
        }

        public static void main(String[] args) {
            CanReachDFSBruitForce solution = new CanReachDFSBruitForce();
            int[] arr = {4, 2, 3, 0, 3, 1, 2};
            int start = 5;
            System.out.println(solution.canReach(arr, start)); // Output: true
        }
    }


    public static class canReachDfSBetter {


        private boolean canReach(int[] arr, int start) {//Not easy to understand

            if(start<0 || start>=arr.length || arr[start]<0) return false;   // terminating conditions

            if(arr[start]==0){   //reached the target
                return true;
            }

            arr[start] = -arr[start];  //visited

            return canReach(arr,start+arr[start])||canReach(arr,start-arr[start]);   //checking in both direction
        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }
    }
}
