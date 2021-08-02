package com.interview.dynamic;

/**
 * Date 07/31/2017
 * @author Mukesh Kumar Gupta
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/jump-game/
 * Category: Tricky
 * https://www.youtube.com/watch?v=muDPTDrpS28
 *
 *  Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/jump-game-ii/ Medium
 * https://leetcode.com/problems/jump-game-iii/ Medium
 * https://leetcode.com/problems/jump-game-vii/ Medium
 */
public class JumpGame {

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

}
