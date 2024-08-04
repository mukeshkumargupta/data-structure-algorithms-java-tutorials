package com.interview.slidingwindow;

import java.util.*;
/*
 * https://leetcode.com/problems/insert-interval/
 * Category: Tricky, Medium , Google
 * 
 * Related: If second input is having  ore than one array asize then think how to merge it
 * 
 * Related: https://leetcode.com/problems/merge-intervals/ Medium
 * https://leetcode.com/problems/range-module/ Hard
 * 
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
Example 3:

Input: intervals = [], newInterval = [5,7]
Output: [[5,7]]
Example 4:

Input: intervals = [[1,5]], newInterval = [2,3]
Output: [[1,5]]
Example 5:

Input: intervals = [[1,5]], newInterval = [2,7]
Output: [[1,7]]
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105
 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        /*
         * Runtime: 2 ms, faster than 58.75% of Java online submissions for Insert Interval.
Memory Usage: 44.5 MB, less than 29.72% of Java online submissions for Insert Interval
         */
        List<int[]> result = new ArrayList();//imp
        int i = 0, n = intervals.length;
        while(i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }
        
        int[] mI = newInterval;
        while(i < n && intervals[i][0] <= newInterval[1]){
            mI[0] = Math.min(mI[0], intervals[i][0]);
            mI[1] = Math.max(mI[1], intervals[i][1]);
            i++;
        }
        result.add(mI);
        
        while(i < n) result.add(intervals[i++]);
        
        return result.toArray(new int[result.size()][2]);//imp
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
