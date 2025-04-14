package com.interview.recursionBacktracking;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/combinations/description/?envType=study-plan-v2&envId=top-interview-150
https://www.youtube.com/watch?v=7IQHYbmuoVU very good recursion tree is drawn here
Category: Medium, Must Do, top150
Related:
https://leetcode.com/problems/combination-sum/ Medium
https://leetcode.com/problems/permutations/ Medium

also i framed one more questions:
Great twist to the original problem!

You're referring to a variation of the Combinations problem where:

Instead of a number n, you are given an array of integers (possibly with duplicates).

You want to generate all unique combinations of size k.

✅ Problem:
Given an array int[] nums that may contain duplicates, return all unique combinations of length k.

🔍 Key Differences from Original:
We must skip duplicate combinations.

To handle duplicates, we sort the array and skip adjacent duplicates during backtracking.



Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

You may return the answer in any order.



Example 1:

Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
Example 2:

Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.


Constraints:

1 <= n <= 20
1 <= k <= n
 */
public class Combinations {
    /*
     ⏱️ Time Complexity

     Step-by-step Analysis:

     1. Number of combinations:
        We're generating all combinations of k out of n.
        This is:
            C = (n choose k) = n! / (k! * (n - k)!)
        Let’s call this value C.

        👉 So we will make C recursive calls that reach the base case and add a list to the result.

     2. Time per combination:
        Each combination has k elements.
        Creating a new ArrayList<>(current) takes O(k) time.

     ✅ Total Time Complexity:
        Total Time = O(C × k) = O((n choose k) × k)

        For example, if n = 20 and k = 10,
        then C = 184,756, and each combination takes O(10) time.

     📦 Space Complexity

     Let’s analyze both explicit (in-memory) and implicit (call stack) space usage:

     1. Recursive Call Stack:
        In the worst case, the recursion goes k levels deep
        (since we add one number at a time until the size is k).

        👉 So the maximum depth of recursion = k
        Stack space = O(k)

     2. Current List (`current`):
        At any time, we store up to k elements in the current list
        because we're building combinations of size k.

        So this also costs O(k) space.

     3. Result List:
        We are storing all combinations in the result list.
        Each combination has size k, and there are C = (n choose k) such combinations.

        So the total result size is:
            O(C × k) = O((n choose k) × k)

     ✅ Total Space Complexity:
        O(k) (call stack + current list) + O((n choose k) × k) (result list)

        We usually report total space complexity as:
            O((n choose k) × k)
        since the result list dominates the memory usage.

     🚀 Summary

     | Complexity Type | Value                                 |
     |------------------|----------------------------------------|
     | Time Complexity  | O(C × k) where C = (n choose k)        |
     | Space Complexity | O(C × k) for result + O(k) auxiliary   |
    */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
        // Base case: If combination size equals k, add to result
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Explore numbers from 'start' to 'n'
        for (int i = start; i <= n; i++) {
            current.add(i);                  // Choose
            backtrack(i + 1, n, k, current, result); // Explore
            current.remove(current.size() - 1);      // Unchoose (backtrack)
        }
    }

    public static void main(String[] args) {
        Combinations solver = new Combinations();
        System.out.println(solver.combine(4, 2));
    }
}
