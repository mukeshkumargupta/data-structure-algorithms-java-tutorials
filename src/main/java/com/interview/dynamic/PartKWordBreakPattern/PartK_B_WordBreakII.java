package com.interview.dynamic.PartKWordBreakPattern;

import java.util.*;

/*
 * https://leetcode.com/problems/word-break-ii/
 * https://www.youtube.com/watch?v=fNVs1J2KCyo
 * Category: Hard, tricky, VImp, MakeMyTrip, Google
 * Related:
 * https://leetcode.com/problems/word-break/ Medium
 * https://leetcode.com/problems/concatenated-words/ Hard
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */
public class PartK_B_WordBreakII {

    /*
     * 1. Brute Force Approach (Nested Recursion)
     *
     * Explanation:
     * - We recursively try to split the string `s` at each possible index.
     * - If the prefix is found in the dictionary, we call the helper on the remaining substring.
     * - This results in an exponential time complexity.
     *
     * Time Complexity: O(2^N) → Worst case, every possible substring is checked.
     * Space Complexity: O(N) → Due to recursion depth and storing intermediate results.
     *
     * Dry Run:
     *
     * Step 1: Initial Call
     * s = "catsanddog", current = ""
     * We try every possible prefix from the dictionary.
     *
     * Step 2: Explore Prefixes
     * "c" → Not in dictionary
     * "ca" → Not in dictionary
     * "cat" → ✅ Found in dictionary
     *
     * Recursive Call 1 (s = "sanddog", current = "cat")
     * Now, we try to break "sanddog".
     *
     * "s" → Not in dictionary
     * "sa" → Not in dictionary
     * "san" → Not in dictionary
     * "sand" → ✅ Found in dictionary
     *
     * Recursive Call 2 (s = "dog", current = "cat sand")
     * Now, we try to break "dog".
     *
     * "d" → Not in dictionary
     * "do" → Not in dictionary
     * "dog" → ✅ Found in dictionary
     *
     * Recursive Call 3 (s = "", current = "cat sand dog") → Base Case
     * Since `s` is empty, we add "cat sand dog" to the result.
     *
     * Step 3: Backtrack to "catsanddog"
     * After "cat → sand → dog", we return and explore other possibilities.
     *
     * "cats" → ✅ Found in dictionary
     *
     * Recursive Call 4 (s = "anddog", current = "cats")
     * Now, we break "anddog".
     *
     * "a" → Not in dictionary
     * "an" → Not in dictionary
     * "and" → ✅ Found in dictionary
     *
     * Recursive Call 5 (s = "dog", current = "cats and")
     * "dog" → ✅ Found in dictionary
     *
     * Recursive Call 6 (s = "", current = "cats and dog") → Base Case
     * Since `s` is empty, we add "cats and dog" to the result.
     *
     * Final Output:
     * ["cat sand dog", "cats and dog"]
     *
     * Observations:
     * - The brute-force approach explores every possible partitioning.
     * - The recursion tree grows exponentially, leading to O(2^N) complexity.
     * - The same substrings are checked multiple times, causing redundant work.
     *
     * Optimization:
     * - Using Dynamic Programming + Memoization can avoid recomputation.
     *
     * Without `trim()`: " cat sand dog" (with extra space)
     * With `trim()`: "cat sand dog" (correct format)
     */
    private static class BruitForce {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Set<String> wordSet = new HashSet<>(wordDict);
            List<String> result = new ArrayList<>();
            helper(s, wordSet, "", result);
            return result;
        }

        private void helper(String s, Set<String> wordSet, String current, List<String> result) {
            if (s.isEmpty()) {
                result.add(current.trim());
                return;
            }

            for (int i = 1; i <= s.length(); i++) {
                String prefix = s.substring(0, i);
                if (wordSet.contains(prefix)) {
                    helper(s.substring(i), wordSet, current + " " + prefix, result);
                }
            }
        }
    }

    /*
     * Better Approach (DP + Backtracking)
     *
     * Explanation:
     * - We use Dynamic Programming (DP) to store whether a substring can be broken into words.
     * - Then, we use Backtracking to generate all valid sentences.
     *
     * Approach:
     * 1. **DP Array (`dp[i]`)**: Stores valid words ending at index `i-1`, helping to avoid redundant calculations.
     * 2. **Backtracking (DFS)**: Traverses `dp` and constructs valid sentences from valid splits.
     *
     * Time Complexity:
     * - **Filling DP Array**: O(N²) → For each index `i`, we check all previous indices `j`.
     * - **Backtracking**: O(N²) → Each valid split is explored but only generates valid sentences.
     * - **Total**: O(N²) for DP filling and backtracking.
     *
     * Space Complexity:
     * - O(N²) → DP table storage + recursion stack during backtracking.
     *
     * Dry Run Example:
     *
     * **Input:**
     * s = "catsanddog"
     * wordDict = ["cat", "cats", "and", "sand", "dog"]
     *
     * **Step 1: Initialize Data Structures**
     * - `dp[i]` stores valid words ending at `i-1`.
     * - `dp[0] = [""]` (Base case).
     * - All `dp[i]` initially empty.
     *
     * **Step 2: Populate DP Table**
     *
     * | i  | Substring `s[0:i]`  | Words Found (from `wordDict`)          | `dp[i]`                   |
     * |----|----------------------|--------------------------------------|----------------------------|
     * | 0  | ""                   | -                                    | `[""]`                     |
     * | 1  | "c"                  | ❌                                  | `[]`                        |
     * | 2  | "ca"                 | ❌                                  | `[]`                        |
     * | 3  | "cat"                | ✅ `"cat"`                         | `["cat"]`                   |
     * | 4  | "cats"               | ✅ `"cats"`                        | `["cats"]`                  |
     * | 5  | "catsa"              | ❌                                  | `[]`                        |
     * | 6  | "catsan"             | ❌                                  | `[]`                        |
     * | 7  | "catsand"            | ✅ `"sand"` (from `dp[3] → "cat"`)  | `["cat sand"]`              |
     * |    |                      | ✅ `"and"` (from `dp[4] → "cats"`)  | `["cats and"]`              |
     * | 8  | "catsandd"           | ❌                                  | `[]`                        |
     * | 9  | "catsanddo"          | ❌                                  | `[]`                        |
     * | 10 | "catsanddog"         | ✅ `"dog"` (from `dp[7] → "cat sand"`, `dp[7] → "cats and"`) | `["cat sand dog", "cats and dog"]` |
     *
     * **Step 3: Backtracking (dfs(10))**
     * - Starting from `dp[10]`, we reconstruct the sentences:
     *   - `"dog"` comes from `dp[7]`, which has:
     *     - `"cat sand"` → `"cat sand dog"`
     *     - `"cats and"` → `"cats and dog"`
     *
     * **Final Output:**
     * ["cat sand dog", "cats and dog"]
     *
     * **Key Optimizations:**
     * ✅ Avoids redundant recalculations using DP.
     * ✅ Uses DP for fast lookups.
     * ✅ Backtracking ensures efficient sentence construction.
     */
    private static class Better {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Set<String> wordSet = new HashSet<>(wordDict);
            List<String> result = new ArrayList<>();
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true; // base case: empty string can always be segmented

            // Fill DP array
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            // If the entire string can be segmented, start backtracking
            if (dp[s.length()]) {
                backtrack(s, dp, wordSet, s.length(), new StringBuilder(), result);
            }

            return result;
        }

        private void backtrack(String s, boolean[] dp, Set<String> wordSet, int end, StringBuilder current, List<String> result) {
            if (end == 0) {
                result.add(current.toString().trim());
                return;
            }

            for (int start = end - 1; start >= 0; start--) {
                if (dp[start] && wordSet.contains(s.substring(start, end))) {
                    int length = current.length();
                    current.insert(0, " " + s.substring(start, end));
                    backtrack(s, dp, wordSet, start, current, result);
                    current.delete(0, s.substring(start, end).length() + 1);
                }
            }
        }
    }

    /*
        Optimal Approach (Memoization + Backtracking)

        We optimize further using Memoization to store the results of substrings and avoid redundant calculations.

        Explanation:
        - Memoization: We use a memo map to store the results of subproblems (i.e., all possible sentences starting from index 'start').
        - If the result is already computed for a substring, it is fetched from the memo, saving computation time.

        Time Complexity:
        - Memoization ensures that each substring is only computed once, making the time complexity O(N²) where N is the length of the string.

        Space Complexity:
        - O(N), where N is the number of unique starting points in the string.

        Summary of Approaches:
        | Approach                          | Time Complexity | Space Complexity |
        |-----------------------------------|----------------|-----------------|
        | Brute Force                      | O(2^N)         | O(N)            |
        | Better (DP + Backtracking)       | O(N²)          | O(N)            |
        | Optimal (Memoization + Backtracking) | O(N²)     | O(N)            |

        - Brute Force is easy to implement but inefficient for larger inputs.
        - The Better Approach (DP + Backtracking) improves performance but still does a lot of redundant work.
        - The Optimal Approach (Memoization) is the most efficient, as it avoids recomputing results for overlapping subproblems.

        Dry Run of the Optimal Approach (Memoization + Backtracking):

        Given Input:
        ----------------------------------
        s = "catsanddog"
        wordDict = ["cat", "cats", "and", "sand", "dog"]

        Step 1: Initialize Data Structures
        - Memoization Map: Map<String, List<String>> memo
        - Recursive function: dfs(s), which returns all possible sentences.

        Step 2: Start dfs("catsanddog")
        - Check memo: Not found
        - Try each prefix:
            "c" ❌ (Not in dictionary)
            "ca" ❌
            "cat" ✅ → Remaining string: "sanddog"
            Call dfs("sanddog")

        Step 3: Start dfs("sanddog")
        - Check memo: Not found
        - Try each prefix:
            "s" ❌
            "sa" ❌
            "san" ❌
            "sand" ✅ → Remaining string: "dog"
            Call dfs("dog")

        Step 4: Start dfs("dog")
        - Check memo: Not found
        - Try each prefix:
            "d" ❌
            "do" ❌
            "dog" ✅ → Remaining string: "" (empty)
        - Base case: Return ["dog"]
        - Memoize: memo["dog"] = ["dog"]
        - Return ["dog"]

        Step 5: Resume dfs("sanddog")
        - Receive result: ["dog"]
        - Form sentences: "sand" + " " + "dog" = "sand dog"
        - Memoize: memo["sanddog"] = ["sand dog"]
        - Return: ["sand dog"]

        Step 6: Resume dfs("catsanddog")
        - Receive result: ["sand dog"]
        - Form sentences: "cat" + " " + "sand dog" = "cat sand dog"
        - Continue checking prefix...
        - "cats" ✅ → Remaining string: "anddog"
          Call dfs("anddog")

        Step 7: Start dfs("anddog")
        - Check memo: Not found
        - Try each prefix:
            "a" ❌
            "an" ❌
            "and" ✅ → Remaining string: "dog"
            Call dfs("dog") (already memoized)
        - Receive memoized result: ["dog"]
        - Form sentences: "and dog"
        - Memoize: memo["anddog"] = ["and dog"]
        - Return: ["and dog"]

        Step 8: Resume dfs("catsanddog")
        - Receive result: ["and dog"]
        - Form sentences: "cats and dog"
        - Memoize: memo["catsanddog"] = ["cat sand dog", "cats and dog"]
        - Return: ["cat sand dog", "cats and dog"]

        Final Result:
        ----------------------------------
        ["cat sand dog", "cats and dog"]

        Memoization Table at End:
        ----------------------------------
        | Substring     | Result                |
        |--------------|----------------------|
        | "dog"        | ["dog"]               |
        | "sanddog"    | ["sand dog"]          |
        | "anddog"     | ["and dog"]           |
        | "catsanddog" | ["cat sand dog", "cats and dog"] |

        Time Complexity Analysis:
        - The function processes each substring once due to memoization.
        - Each substring is broken into words in O(N²) due to recursion.
        - Total Complexity: O(N²) (much better than O(N!) in brute force).

        Space Complexity:
        - O(N²) for memoization and recursion stack.
    */
    private static class Optimal {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Set<String> wordSet = new HashSet<>(wordDict);
            Map<Integer, List<String>> memo = new HashMap<>();
            return backtrack(s, wordSet, 0, memo);
        }

        private List<String> backtrack(String s, Set<String> wordSet, int start, Map<Integer, List<String>> memo) {
            // Return memoized results if they exist
            if (memo.containsKey(start)) {
                return memo.get(start);
            }

            List<String> result = new ArrayList<>();

            if (start == s.length()) {
                result.add("");
                return result;
            }

            for (int end = start + 1; end <= s.length(); end++) {
                String word = s.substring(start, end);
                if (wordSet.contains(word)) {
                    List<String> sublist = backtrack(s, wordSet, end, memo);
                    for (String str : sublist) {
                        result.add(word + (str.isEmpty() ? "" : " " + str));
                    }
                }
            }

            memo.put(start, result);
            return result;
        }
    }
    
}
