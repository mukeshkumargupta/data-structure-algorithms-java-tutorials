package com.interview.greedy;

import java.util.*;
/*
 * https://leetcode.com/problems/non-overlapping-intervals/
 * Category:
 * Medium, Tricky, Facebook
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 Related: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/ Medium
 

Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 

Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
 */
public class NonoverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        /*
         * Runtime: 130 ms, faster than 7.84% of Java online submissions for Non-overlapping Intervals.
Memory Usage: 105.8 MB, less than 46.66% of Java online submissions for Non-overlapping Intervals.
Note: Find better solution
         */
        int intervalCount = intervals.length;
        
        if (intervalCount <= 1) {
            return 0;
        }
        
        Arrays.sort(intervals, (aar1, arr2)-> {
            //sort by start time
            //sort by end time also work but not tried here
            return aar1[0] - arr2[0];
        });
        
        int last_included = 0;
        int count = 0;
        for (int i = 1; i < intervalCount; i++) {
            
            if (intervals[i][0] < intervals[last_included][1]) {//overlap, current interval start less than last included then overlap  
                count++;
               //if partial overlap then include last included if complete then take smaller one (Tricky)
                //Summary: update last included if current interval is smaller
                if (intervals[i][1] < intervals[last_included][1]) {
                    last_included = i;
                }
                
            } else {//non-overlap
                last_included = i;
                
            }
            
        }
        return count;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
