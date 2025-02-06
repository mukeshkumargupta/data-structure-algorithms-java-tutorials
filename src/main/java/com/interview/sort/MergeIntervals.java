package com.interview.sort;

import java.util.*;
/*
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 https://leetcode.com/problems/merge-intervals/submissions/
 Category: Medium, Tricky, Top150
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
    private static class Interval {
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;

        }
    }
    public int[][] merge(int[][] intervals) {
        List<Interval> result = new ArrayList<>();

        Arrays.sort(intervals, (arr1, arr2) -> {
            return arr1[0] - arr2[0];
        });

        int start = intervals[0][0];
        int end = intervals[0][1];
        int len = intervals.length;
        for (int i = 1; i < len; i++) {
            if (end >= intervals[i][0]) {
                end = Math.max(end, intervals[i][1]);

            } else {
                result.add(new Interval(start, end));
                start = intervals[i][0];
                end = intervals[i][1];
            }

        }
        result.add(new Interval(start, end));
        int size = result.size();
        int[][] finalResult = new int[size][2];
        for (int i = 0; i < size; i++) {
            finalResult[i][0] = result.get(i).start;
            finalResult[i][1] = result.get(i).end;
        }
        return finalResult;



    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
