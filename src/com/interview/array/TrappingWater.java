package com.interview.array;
/**
 * References
 * https://leetcode.com/problems/trapping-rain-water/
 * Related: https://leetcode.com/problems/trapping-rain-water-ii/ Hard VVImp
 * https://leetcode.com/problems/pour-water/ Medium Locked
 * https://www.youtube.com/watch?v=m18Hntz4go8&t=1s
 * Category: Hard, Must Do, Top150
 * Related: https://leetcode.com/problems/trapping-rain-water-ii/ Hard
 * https://leetcode.com/problems/pour-water/ Medium
 * 
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */
public class TrappingWater {

    public int trap(int[] height) {
        /*
         * Runtime: 1 ms, faster than 90.60% of Java online submissions for Trapping Rain Water.
Memory Usage: 47.3 MB, less than 24.30% of Java online submissions for Trapping Rain Water.
         */
        int len = height.length;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        
        leftMax[0] = height[0];
        for (int i = 1; i < len ; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        rightMax[len-1] = height[len-1];
        for (int i = len -2; i >= 0; i--) {

            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }
        
        int totalSum = 0;
        for (int i = 0; i < len; i++) {
            totalSum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return totalSum;
        

        
    }
    
    public int trap(int[] height) {
        /*
         * Runtime: 1 ms, faster than 91.78% of Java online submissions for Trapping Rain Water.
Memory Usage: 38.9 MB, less than 53.07% of Java online submissions for Trapping Rain Water.
         */
        int len = height.length;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        
        int max = 0;
        for (int i = 0; i < len ; i++) {
            if (height[i] > max) {
                max = height[i];
            }
            leftMax[i] = max;
        }
        max = 0;
        for (int i = len -1; i >= 0; i--) {
            if (height[i] > max) {
                max = height[i];
            }
            rightMax[i] = max;
        }
        
        int totalSum = 0;
        for (int i = 0; i < len; i++) {
            totalSum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return totalSum;
        

        
    }

    public static void main(String args[]){
        int input[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingWater tw = new TrappingWater();
        System.out.println(tw.trap(input));
    }
}
