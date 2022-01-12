package com.interview.slidingwindow;

import java.util.*;

/*
 * https://leetcode.com/problems/sliding-window-maximum/
 * Category: Hard, Tricky, Must Do, Sliding window
 * Related: https://leetcode.com/problems/minimum-window-substring/ Hard
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/ Medium
 * https://leetcode.com/problems/paint-house-ii/ Hard
 * https://leetcode.com/problems/jump-game-vi/ Medium
 * 
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]
Example 3:

Input: nums = [1,-1], k = 1
Output: [1,-1]
Example 4:

Input: nums = [9,11], k = 2
Output: [11]
Example 5:

Input: nums = [4,-2], k = 2
Output: [4]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
Accepted
487,516
Submissions
1,063,336
 * 
 */

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] array, int k) {
        LinkedList<Integer> list = new LinkedList();
        int[] result = new int[array.length-k+1];
         
        for (int i = 0; i < k; i++)
        {
            // remove all useless elements present at the front of the list
            while (!list.isEmpty() && array[list.getLast()] < array[i])
            {
                list.removeLast();
            }
             
            // add index of current element at the back
            list.addLast(i);
        }
        int resultIndex = -1;
        // first element present in the list is the greatest element for the last 'k' sized sub-array
        result[++resultIndex] = array[list.getFirst()];
        
        for (int i = k; i < array.length; i++)
        {
            // now remove all indices of elements from the list which do not belong to current window
            while (!list.isEmpty() && (list.getFirst() < (i-k+1))) //Tricky
            {
                list.removeFirst();
            }
             
            // now again remove all useless elements present at the front of the list
            // remove all useless elements present at the front of the list
            while (!list.isEmpty() && array[list.getLast()] < array[i])
            {
                list.removeLast();
            }
             
            // and finally insert this new element at the back of the list
            list.add(i);
            result[++resultIndex] = array[list.getFirst()];
        }
         
        // now print the largest element from the last sub-array(window)
        //System.out.println(array[list.getFirst()]);
         //result[++resultIndex] = array[list.getFirst()];
        return result;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] input = { 1, -1 };
        SlidingWindowMaximum sm = new SlidingWindowMaximum();
        /*[1,3,1,2,0,5]
                3*/
        sm.maxSlidingWindow(input, 1);
        
    }
    
}
