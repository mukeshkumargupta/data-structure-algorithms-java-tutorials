package com.interview.hash;

import java.util.*;

/*
 * https://leetcode.com/problems/check-array-formation-through-concatenation/
 * Category: Easy, Tricky
 * Related:
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/ Medium
 * https://leetcode.com/problems/course-schedule-iii/ Hard
 * https://leetcode.com/problems/find-the-town-judge/ Easy
 * You are given an array of distinct integers arr and an array of integer arrays pieces, where the integers in pieces are distinct. Your goal is to form arr by concatenating the arrays in pieces in any order. However, you are not allowed to reorder the integers in each array pieces[i].

Return true if it is possible to form the array arr from pieces. Otherwise, return false.

 

Example 1:

Input: arr = [85], pieces = [[85]]
Output: true
Example 2:

Input: arr = [15,88], pieces = [[88],[15]]
Output: true
Explanation: Concatenate [15] then [88]
Example 3:

Input: arr = [49,18,16], pieces = [[16,18,49]]
Output: false
Explanation: Even though the numbers match, we cannot reorder pieces[0].
Example 4:

Input: arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
Output: true
Explanation: Concatenate [91] then [4,64] then [78]
Example 5:

Input: arr = [1,3,5,7], pieces = [[2,4,6,8]]
Output: false
 

Constraints:

1 <= pieces.length <= arr.length <= 100
sum(pieces[i].length) == arr.length
1 <= pieces[i].length <= arr.length
1 <= arr[i], pieces[i][j] <= 100
The integers in arr are distinct.
The integers in pieces are distinct (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).
 */

public class CheckArrayFormationThroughConcatenation {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        /*
         * Runtime: 1 ms, faster than 71.60% of Java online submissions for Check Array Formation Through Concatenation.
Memory Usage: 37.9 MB, less than 95.80% of Java online submissions for Check Array Formation Through Concatenation.
         */

        Map<Integer, int[]> lookup = new HashMap<>();
        for (int[] piece : pieces) {
            lookup.put(piece[0], piece);
        }
        int[] result = new int[arr.length];
        int i = 0;
        for (int elm: arr) {
            if (lookup.containsKey(elm)) {
                for (int p : lookup.get(elm)) {
                    result[i++] = p;
                }
            }
        }
        return Arrays.equals(arr, result);
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
