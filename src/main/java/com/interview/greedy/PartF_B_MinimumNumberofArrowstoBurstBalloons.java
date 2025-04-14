package com.interview.greedy;

import java.util.Arrays;

/*
https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
Category: Medium, Tricky, Top150

🎥 Reference Video: https://www.youtube.com/watch?v=zdn1i_dDWIk

📌 Related Problems:
- Meeting Rooms II: https://leetcode.com/problems/meeting-rooms-ii/
- Non-overlapping Intervals: https://leetcode.com/problems/non-overlapping-intervals/

📝 Problem Summary:
You're given `points[i] = [xstart, xend]`, which represents balloons on a wall along the X-axis. An arrow shot vertically upward at position `x` will burst all balloons where `xstart <= x <= xend`.

Return the **minimum number of arrows** required to burst all balloons.

📘 Example 1:
Input:  [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation:
- Arrow at x = 6 → bursts [2,8], [1,6]
- Arrow at x = 11 → bursts [10,16], [7,12]

📘 Example 2:
Input:  [[1,2],[3,4],[5,6],[7,8]]
Output: 4

📘 Example 3:
Input:  [[1,2],[2,3],[3,4],[4,5]]
Output: 2

🔒 Constraints:
- 1 <= points.length <= 10^5
- points[i].length == 2
- -2^31 <= xstart < xend <= 2^31 - 1
*/

public class PartF_B_MinimumNumberofArrowstoBurstBalloons {

    /*
    💡 Dry Run Example:
    Input: {{10,16}, {2,8}, {1,6}, {7,12}}

    ✅ Step 1: Sort by end coordinates:
    → Sorted: {{1,6}, {2,8}, {7,12}, {10,16}}

    ✅ Step 2: Initialize
    → arrows = 1
    → first_end = 6

    ✅ Step 3: Iterate through sorted balloons:
    → {2,8} starts at 2 ≤ 6 → overlapping → no new arrow
    → {7,12} starts at 7 > 6 → not overlapping → new arrow, arrows = 2, first_end = 12
    → {10,16} starts at 10 ≤ 12 → overlapping → no new arrow

    ✅ Final Result: 2 arrows
    */

    public int findMinArrowShots(int[][] points) {
        int len = points.length;

        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrow = 1;
        int end = points[0][1];
        for (int i = 1; i < len ;i++) {
            //System.out.println(points[i][0] +" -> " + end);
            if (points[i][0] <= end) {
                continue;
            }

            arrow++;
            end = points[i][1];



        }
        return arrow;

    }
}
