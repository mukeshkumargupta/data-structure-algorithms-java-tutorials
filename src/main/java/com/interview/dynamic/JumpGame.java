package com.interview.dynamic;

/**
 * Date 07/31/2017
 * @author Mukesh Kumar Gupta
 *
 * https://leetcode.com/problems/jump-game/
 * Category: Tricky, Top150
 * https://www.youtube.com/watch?v=muDPTDrpS28
 *
 *  Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Time complexity O(n)
 * Space complexity O(1)
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105
 *
 * https://leetcode.com/problems/jump-game-ii/ Medium
 * https://leetcode.com/problems/jump-game-iii/ Medium
 * https://leetcode.com/problems/jump-game-vii/ Medium
 * Related:
 * https://leetcode.com/problems/intersection-of-three-sorted-arrays/ Easy locked
 * https://leetcode.com/problems/reduce-array-size-to-the-half/ Medium  VImp
 * Hint: Take map to count frequency then take put value and fre in priority queue then reduce one by one and check criteria if criterial fulpul then add in result set
 * Like this: https://leetcode.com/problems/reduce-array-size-to-the-half/discuss/1731840/Java-easy-Solution-oror-HashMap-oror-PriorityQueue
 * https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/ Medium
 * 
 * Other version is not mentioned related but try to solve all version, All are good question
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
    /*
     * Little Optimized
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Jump Game.
Memory Usage: 42.3 MB, less than 73.05% of Java online submissions for Jump Game.
     */
    public boolean canJump(int[] nums) {
        int reachable = 0;
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            if (i > reachable) {
               return false;  
            }
            
            
            reachable = Math.max(i+ nums[i], reachable);
            if (reachable >= l -1) { //early break
                return true;
            }
        }
        return true;
        
    }

}
