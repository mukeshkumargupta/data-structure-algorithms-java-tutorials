package com.interview.binarysearch;

import java.util.*;

/*
 * https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/
 * Category: Medium, Tricky
 * https://www.youtube.com/watch?v=8kwPXbTMSnk&list=PL1w8k37X_6L-bCZ3m0FFBZmRv4onE7Zjl&index=24
 * Related: https://leetcode.com/problems/generate-random-point-in-a-circle/ Medium
 * You are given an array of non-overlapping axis-aligned rectangles rects where rects[i] = [ai, bi, xi, yi] indicates that (ai, bi) is the bottom-left corner point of the ith rectangle and (xi, yi) is the top-right corner point of the ith rectangle. Design an algorithm to pick a random integer point inside the space covered by one of the given rectangles. A point on the perimeter of a rectangle is included in the space covered by the rectangle.

Any integer point inside the space covered by one of the given rectangles should be equally likely to be returned.

Note that an integer point is a point that has integer coordinates.

Implement the Solution class:

Solution(int[][] rects) Initializes the object with the given rectangles rects.
int[] pick() Returns a random integer point [u, v] inside the space covered by one of the given rectangles.
 

Example 1:


Input
["Solution", "pick", "pick", "pick", "pick", "pick"]
[[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
Output
[null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]

Explanation
Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
solution.pick(); // return [1, -2]
solution.pick(); // return [1, -1]
solution.pick(); // return [-1, -2]
solution.pick(); // return [-2, -2]
solution.pick(); // return [0, 0]
 

Constraints:

1 <= rects.length <= 100
rects[i].length == 4
-109 <= ai < xi <= 109
-109 <= bi < yi <= 109
xi - ai <= 2000
yi - bi <= 2000
All the rectangles do not overlap.
At most 104 calls will be made to pick.
 */
public class RandomPointinNonoverlappingRectangles {
    int num_pts;
    int[] rect_cum_count;
    int[][] rects;
    Random r;

    public RandomPointinNonoverlappingRectangles(int[][] rects) {
        num_pts = 0;
        this.rects = rects;
        rect_cum_count = new int[rects.length];
        int i = 0;
        for(int[] rect: rects){
            num_pts += (rect[2] - rect[0] + 1)*(rect[3] - rect[1] + 1);
            rect_cum_count[i++] = num_pts;
        }
        r = new Random();
        
        
    }
    public int[] pick() {
        /*
         * Runtime: 107 ms, faster than 21.56% of Java online submissions for Random Point in Non-overlapping Rectangles.
Memory Usage: 72.6 MB, less than 27.55% of Java online submissions for Random Point in Non-overlapping Rectangles.
         */
        int pt_idx = r.nextInt(num_pts);
        int l = 0, r = rects.length-1;
        while(l < r){
            int mid = l + (r-l)/2;
            if(rect_cum_count[mid] <= pt_idx) l = mid+1;
            else r = mid;
        }
        // l = rectangle index
        int[] rect = rects[l];
        int x_pts = rect[2] - rect[0] + 1;
        int y_pts = rect[3] - rect[1] + 1;
        int pts_in_rect = x_pts*y_pts;
        int pt_start = rect_cum_count[l] - pts_in_rect;
        int offset = pt_idx - pt_start;
        int[] result = new int[2];
        result[0] = rect[0] + offset%x_pts;
        result[1] = rect[1] + offset/x_pts;
        return result;
       
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
