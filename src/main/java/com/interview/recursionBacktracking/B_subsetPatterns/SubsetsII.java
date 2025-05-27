package com.interview.recursionBacktracking.B_subsetPatterns;

import java.util.*;

/*
 * https://leetcode.com/problems/subsets-ii/
 * https://www.youtube.com/watch?v=RIn3gOkbhQE&t=1526s
 * Category: Must Do, Medium
 * Derived: This problem CombinationSumII is just extension of this problem
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.
Related: https://leetcode.com/problems/subsets/ Medium
https://leetcode.com/problems/find-array-given-subset-sums/ Hard

 

Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */
public class SubsetsII {
    /*
    ✅ Subsets II (With Duplicates)
    This generates all **unique** subsets from an array that may contain duplicates.

    🔍 Key Point:
    - The input array is sorted to ensure duplicates are adjacent.
    - Skips duplicate elements during recursive calls using:
        if (i > start && nums[i] == nums[i-1]) continue;

    ⏱ Time Complexity: O(2^n * n)
    🧮 Why?
    - In the worst case (no duplicates), the number of subsets = 2^n.
    - Even with duplicates, we could have close to 2^n unique subsets (if all elements are distinct).
    - For each subset, a copy of the current list `ds` is made using `new ArrayList<>(ds)` which takes O(k) time.
    - In the worst case, `k` = n, so copying each subset takes up to O(n).

    👉 Hence:
    Time = O(2^n) [generate subsets] * O(n) [copy subset] = O(2^n * n)

    📦 Space Complexity: O(n) (excluding output)
    🧮 Why?
    - Auxiliary space includes:
        • Recursion call stack: can go up to depth n → O(n)
        • Temporary list `ds`: at most n elements → O(n)
    - So at any given time, space used is O(n)

    📝 Output Space:
    - Result list contains up to 2^n unique subsets
    - Each subset may contain up to n elements
    - Total space = O(2^n * n) if including result

    ✅ Summary:
    +------------------+------------------+--------------------------------------------+
    |     Metric       |   Complexity     |                 Reasoning                  |
    +------------------+------------------+--------------------------------------------+
    | Time             |   O(2^n * n)     | 2^n unique subsets, each copied in O(n)    |
    | Space            |   O(n)           | Recursion stack + temp list (ds)           |
    | Output Space     |   O(2^n * n)     | For storing all subsets in result list     |
    +------------------+------------------+--------------------------------------------+
*/
    private void subsets(int[] nums, int start, int end, List<Integer> ds, List<List<Integer>> result) {
        result.add(new ArrayList<>(ds));
        
        for (int i = start; i <= end; i++) {
            if (i > start && nums[i] == nums[i-1]) {
                continue;
            }
            ds.add(nums[i]);
            subsets(nums, i+1, end, ds, result);
            ds.remove(ds.size()-1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsets(nums, 0, nums.length-1, new ArrayList<>(), result);
        return result;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
