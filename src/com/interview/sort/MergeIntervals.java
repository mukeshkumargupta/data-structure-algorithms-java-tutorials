package com.interview.sort;

import java.util.*;
/*
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 https://leetcode.com/problems/merge-intervals/submissions/
 Category: Medium, Tricky
 Related: https://leetcode.com/problems/meeting-rooms/ Easy
 https://leetcode.com/problems/meeting-rooms-ii/ Medium
 https://leetcode.com/problems/teemo-attacking/ Easy
 https://leetcode.com/problems/add-bold-tag-in-string/ Medium
 https://leetcode.com/problems/range-module/ Hard
 https://leetcode.com/problems/employee-free-time/ Hard
 https://leetcode.com/problems/partition-labels/ Medium
 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 

Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
Accepted
1,115,217
Submissions
2,579,697
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        /*
         * Runtime: 5 ms, faster than 96.19% of Java online submissions for Merge Intervals.
Memory Usage: 41.6 MB, less than 75.81% of Java online submissions for Merge Intervals.
            TC: NLOGN + N
            SC: N i worst case if no merge interval
            
         */
        
        Arrays.sort(intervals, (arr1, arr2) -> {//sort by start time
            return arr1[0] - arr2[0];
        });
        List<int[]> result = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int[] interval : intervals) {
            if (interval[0] <= end) {
                end = Math.max(end, interval[1]);
            } else {
                /*int[] temp = new int[2];
                temp[0] = start;
                temp[1] = end;
                result.add(temp);*/
                //Remove boiler plat code
                result.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            }
            
        }
        /*int[] temp = new int[2];
        temp[0] = start;
        temp[1] = end;
        result.add(temp);*/
        result.add(new int[]{start, end});
        return result.toArray(new int[result.size()][]);
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
