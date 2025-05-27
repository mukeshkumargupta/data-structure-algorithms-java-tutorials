package com.interview.recursionBacktracking.B_subsetPatterns;

import java.util.*;
/*
 * https://leetcode.com/problems/subsets/
 * Category: Medium, Must Do, Top150
 * Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.
Related:
https://leetcode.com/problems/generalized-abbreviation/ Medium Locked
https://leetcode.com/problems/letter-case-permutation/ Medium VVImp
https://leetcode.com/problems/find-array-given-subset-sums/ VImp
https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/ VImp

 

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */
public class Subsets {
    /*
        ✅ Total Number of Subsets:
        For an array of size n, the number of possible subsets = 2^n.

        ⏱ Time Complexity: O(2^n * n)
        🧮 Why?
        - There are 2^n subsets for a set of n elements.
        - For each subset, you’re copying the current 'ds' into the result: new ArrayList<>(ds) → which takes O(k) time,
          where k is the size of the current subset.
        - In the worst case, a subset can be of size n. So copying each subset into the result costs up to O(n).

        👉 Hence:
        Time = O(2^n) [for generating subsets] * O(n) [to copy each subset] = O(2^n * n)

        📦 Space Complexity: O(n) (excluding output)
        🧮 Why?
        - Auxiliary Space includes Recursion Stack + temp list 'ds':
            • 'ds' holds up to n elements at any point → O(n)
            • Recursion call stack depth is also O(n)

        So, overall auxiliary space used at any given time (excluding result list) is O(n).

        📝 Note on Result List:
        - If you include the output:
            • The result contains 2^n subsets
            • Each subset can have up to n elements
        - So total space used = O(2^n * n) for the result list

        ✅ Summary:
        +------------------+------------------+--------------------------------------------+
        |     Metric       |   Complexity     |                 Reasoning                  |
        +------------------+------------------+--------------------------------------------+
        | Time             |   O(2^n * n)     | 2^n subsets, each copied in O(n)           |
        | Space            |   O(n)           | Recursion + temp list (ds)                 |
        | Output Space     |   O(2^n * n)     | If you include result list in calculation  |
        +------------------+------------------+--------------------------------------------+
    */
    private void subsets(int[] nums, int start, int end, List<Integer> ds, List<List<Integer>> result) {
        result.add(new ArrayList<>(ds));
        
        for (int i = start; i <= end; i++) {
            ds.add(nums[i]);
            subsets(nums, i+1, end, ds, result);
            ds.remove(ds.size()-1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsets(nums, 0, nums.length-1, new ArrayList<>(), result);
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
