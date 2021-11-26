package com.interview.twopointer;

/*
 * https://leetcode.com/problems/container-with-most-water/
 * https://www.youtube.com/watch?v=2DjZ8AHcofE
 * Category: Medium, Tricky, Google
 * Retalted: https://leetcode.com/problems/trapping-rain-water/ Hard
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

Notice that you may not slant the container.

 

Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1
Example 3:

Input: height = [4,3,2,1,4]
Output: 16
Example 4:

Input: height = [1,2,1]
Output: 2
 

Constraints:

n == height.length
2 <= n <= 105
0 <= height[i] <= 104
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        /*
         * Runtime: 5 ms, faster than 31.30% of Java online submissions for Container With Most Water.
Memory Usage: 78.7 MB, less than 16.17% of Java online submissions for Container With Most Water.
         */
        int l = height.length;
        int minHeightIndex = 0;
        int minHeight = height[0];
        int maxHeightIndex = l-1;
        int maxHeight = height[l-1];
        if (minHeight > maxHeight) {
            maxHeight = minHeight;
            maxHeightIndex = minHeightIndex;
            minHeight = height[l-1];
            minHeightIndex = l-1;
        }
        int width = l-1;
        int maxArea = minHeight*(width);

        while(width > 0) {
            int idx = minHeightIndex;
            while(height[idx] <= minHeight) {//keep doing loop wntil you get value greater than min
                if(minHeightIndex < maxHeightIndex) {//check min idex is in left lide or right side, if left side then increment otherwise decrement
                    idx++;
                } else {
                    idx--;
                }
                width--;
                if (width < 1) {//break if less than 1
                    break;
                }


                //Now check that idx is has maxHeight then update correct maxHeight
                if (height[idx] > height[maxHeightIndex]) {
                    //now update min befroe updating
                    minHeight = maxHeight;
                    minHeightIndex = maxHeightIndex;
                    maxHeight= height[idx];
                    maxHeightIndex = idx;
                } else {
                    minHeight = height[idx];
                    minHeightIndex = idx;
                }
                maxArea = Math.max(maxArea, width*minHeight);
            }
        }
        return maxArea;
        
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
