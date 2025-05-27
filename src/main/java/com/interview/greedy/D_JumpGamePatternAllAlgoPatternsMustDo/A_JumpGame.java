package com.interview.greedy.D_JumpGamePatternAllAlgoPatternsMustDo;

public class A_JumpGame {
    /*
     * Problem: https://leetcode.com/problems/jump-game/
     * Video Explanation: https://www.youtube.com/watch?v=tZAa_jJ3SwQ
     * Related Video: https://www.youtube.com/watch?v=muDPTDrpS28
     * Category: Medium, Tricky, Top150, all its variant(1st to 8 however 8th is locked) important to make complete in one shot to cover all patterns of ds&algo
     * Author: Mukesh Kumar Gupta
     * Date: 07/31/2017
     *
     *  Related:
     * - https://leetcode.com/problems/jump-game-ii/ (Medium)
     * - https://leetcode.com/problems/jump-game-vii/ (Medium)
     * - https://leetcode.com/problems/intersection-of-three-sorted-arrays/ (Easy, locked)
     * - https://leetcode.com/problems/reduce-array-size-to-the-half/ (Medium, Very Important)
     * - Priority Queue Hint: To reduce array size to half, use a map to count frequency and prioritize removal by frequency.
     * Example Solution: https://leetcode.com/problems/reduce-array-size-to-the-half/discuss/1731840/Java-easy-Solution-oror-HashMap-oror-PriorityQueue
     * - https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/ (Medium)
     *
     * Note: There are multiple versions of the Jump Game problem. Try solving all variations, as each offers valuable problem-solving experience.
     *
     * Problem Statement:
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to determine if you can reach the last index.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Examples:
     *
     * Example 1:
     * Input: nums = [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     *
     * Example 2:
     * Input: nums = [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, making it impossible to reach the last index.
     *
     * Constraints:
     * - 1 <= nums.length <= 10^4
     * - 0 <= nums[i] <= 10^5
     *

     */
    public static class JumpGame {

        public boolean canJump(int[] nums) { // 2 ms, faster than 75.12% of Java online submissions for Jump Game.
            int l = nums.length;
            int reachable = 0;

            for (int i = 0; i < l; i++) {
                if (i > reachable) {
                    return false;
                }

                reachable = Math.max(i + nums[i], reachable);
            }
            return true;


        }

        /*
         * Little Optimized
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Jump Game.
    Memory Usage: 42.3 MB, less than 73.05% of Java online submissions for Jump Game.
         */
        public boolean canJumpOptimized(int[] nums) {
            int reachable = 0;
            int l = nums.length;
            for (int i = 0; i < l; i++) {
                if (i > reachable) {
                    return false;
                }


                reachable = Math.max(i + nums[i], reachable);
                if (reachable >= l - 1) { //early break
                    return true;
                }
            }
            return true;

        }
    }
}
