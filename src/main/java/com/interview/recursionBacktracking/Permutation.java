package com.interview.recursionBacktracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Date 01/29/2017
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/permutations/
 * https://www.youtube.com/watch?v=f2ic2Rsc9pU&t=6s
 * Category: Medium, Must Do, Tricky, Top150
 * Related:
 * https://leetcode.com/problems/permutations-ii/ Medium
 * https://leetcode.com/problems/combinations/ Medium
 * 
 * 
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]
 */
public class Permutation {
    /*
        ✅ Permutations (Distinct Elements)

        🔗 Problem: https://leetcode.com/problems/permutations/
        🎥 Video: https://www.youtube.com/watch?v=f2ic2Rsc9pU

        📌 Idea:
        - Generate all permutations of the input array using backtracking and swapping.
        - At each recursive call, swap the current element with the rest and recurse on the remaining array.
        - Restore the array back to its previous state after recursion (backtrack step).

        🔄 Total Number of Permutations for n distinct elements = n!

        ⏱ Time Complexity: O(n * n!)
        🧮 Why?
        - There are n! permutations of an array of size n.
        - For each permutation, a copy of the array is added to the result using `new ArrayList<>()`, which takes O(n) time.

        👉 So:
        Time = O(n!) [for generating permutations] * O(n) [for copying each] = O(n * n!)

        📦 Space Complexity: O(n) (excluding output)
        🧮 Why?
        - No extra data structures are used apart from the recursion stack.
        - The recursion goes n levels deep → O(n) call stack.
        - `result` holds the output, not part of auxiliary space.

        📝 Output Space:
        - result contains n! permutations.
        - Each permutation is a list of n elements.
        - So output space = O(n * n!)

        ✅ Summary:
        +------------------+------------------+---------------------------------------------+
        |     Metric       |   Complexity     |                  Reasoning                  |
        +------------------+------------------+---------------------------------------------+
        | Time             |   O(n * n!)      | n! permutations, each copied in O(n) time   |
        | Space            |   O(n)           | Recursion stack depth                       |
        | Output Space     |   O(n * n!)      | Final result list with all permutations     |
        +------------------+------------------+---------------------------------------------+
    */
    List<List<Integer>> result = new ArrayList<>();
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        
    }
    private void permuteUtil(int[] nums, int start, int end) {
        if (start == end) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(nums, start, i);
            permuteUtil(nums,start+1, end);
            swap(nums, start, i);
        }
        
    }
    public List<List<Integer>> permute(int[] nums) {//100% runtime
        permuteUtil(nums, 0, nums.length-1);
        return result;
    }
}
