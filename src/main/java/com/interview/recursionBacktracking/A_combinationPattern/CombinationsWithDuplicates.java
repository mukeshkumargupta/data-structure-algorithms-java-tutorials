package com.interview.recursionBacktracking.A_combinationPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
📂 Category: Medium, Must Do, Derived Question from Combinations where array contains duplicates

*****************************************
🧩 Related Problems on Combinations:

1. ✅ Combinations (Leetcode 77)
   Input: n = 4, k = 2
   Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]

2. ✅ Combination Sum (Leetcode 39)
   Input: candidates = [2,3,6,7], target = 7
   Output: [[2,2,3],[7]]

3. ✅ Combination Sum II (Leetcode 40)
   Input: candidates = [10,1,2,7,6,1,5], target = 8
   Output: [[1,1,6],[1,2,5],[1,7],[2,6]]

4. ✅ Combination Sum III (Leetcode 216)
   Input: k = 3, n = 7
   Output: [[1,2,4]]

5. ✅ Letter Combinations of a Phone Number (Leetcode 17)
   Input: digits = "23"
   Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

6. ✅ Combinations from Array with Duplicates
   Input: nums = [1,2,2], k = 2
   Output: [[1,2],[2,2]]

7. ✅ Subsets of Size K
   Input: nums = [1,2,3], k = 2
   Output: [[1,2],[1,3],[2,3]]

8. ✅ Subsets II (Leetcode 90, with duplicates)
   Input: nums = [1,2,2]
   Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

9. ✅ Count Combinations of Size K
   Input: nums = [1,2,3,4], k = 2
   Output: 6

10. ✅ Combinations with Sum Divisible by X
    Input: nums = [1,2,3,4,5], k = 2, X = 3
    Output: [[1,2],[1,5],[2,4],[3,6]]
    (Only combinations where sum % 3 == 0)

11. ✅ Combinations with No Adjacent Elements
    Input: nums = [1,2,3,4,5], k = 2
    Output: [[1,3],[1,4],[1,5],[2,4],[2,5],[3,5]]

12. ✅ Combinations Where Repetition is Allowed up to R Times
    Input: nums = [1,2], k = 2, R = 2
    Output: [[1,1],[1,2],[2,2]]

13. ✅ Combinations of Strings
    Input: strs = ["a", "b", "c"], k = 2
    Output: [["a","b"],["a","c"],["b","c"]]

14. ✅ K-th Combination in Lexicographic Order
    Input: n = 4, k = 3, pos = 2
    Output: [1,2,4]
    (2nd lexicographic combination of 3 elements from [1,2,3,4])

15. ✅ Generate Power Set (All Subsets)
    Input: nums = [1,2]
    Output: [[],[1],[2],[1,2]]

16. ✅ Generate Parentheses (Leetcode 22)
    Input: n = 3
    Output: ["((()))","(()())","(())()","()(())","()()()"]

17. ✅ Palindrome Partitioning (Leetcode 131)
    Input: s = "aab"
    Output: [["a","a","b"],["aa","b"]]

18. ✅ Word Break II (Leetcode 140)
    Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
    Output: ["cats and dog","cat sand dog"]

*****************************************

🎯 Variation in Focus:
You're referring to a twist of the Combinations problem where:

- Instead of a number `n`, you are given an array of integers (possibly with duplicates).
- You want to generate **all unique combinations** of length `k`.

✅ Problem:
Given an array `int[] nums` that may contain duplicates, return all **unique combinations** of length `k`.

🔍 Key Differences from Original:
- We must **skip duplicate combinations**.
- To handle duplicates, we **sort the array** and **skip adjacent duplicates** during backtracking.

⏱️ Time Complexity:
Let:
- `n` = length of the array
- `k` = size of each combination
- `C` = number of **unique combinations** of size `k`

Steps:
1. Sorting the array: O(n log n)
2. Backtracking:
   - In the worst case (no duplicates): we explore all combinations of size `k` → O(C)
   - For each combination, we copy a list of size `k` → O(k)

✅ Total Time Complexity:
O(n log n) for sorting + O(C × k) for generating combinations

🧠 Space Complexity:
1. Call stack depth: O(k)
2. Current list: O(k)
3. Result list: O(C × k) for storing final combinations

✅ Total Space Complexity:
O(k) auxiliary + O(C × k) for result list

---
Let me know if you'd like the same version for:
- Duplicates allowed *within* combinations (like `Combination Sum`)
- Or generating **permutations** instead!
*/
public class CombinationsWithDuplicates {
    public List<List<Integer>> combineUnique(int[] nums, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // ✅ Step 1: Sort to group duplicates
        backtrack(0, nums, k, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, int k, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // ✅ Skip duplicates at the same level
            if (i > start && nums[i] == nums[i - 1]) continue;

            current.add(nums[i]);
            backtrack(i + 1, nums, k, current, result); // move forward
            current.remove(current.size() - 1); // backtrack
        }
    }
}
