package com.interview.dynamic;

import java.util.*;

/*
 * LeetCode: https://leetcode.com/problems/word-break/
 * Video Explanation: https://www.youtube.com/watch?v=th4OnoGasMU
 * Category: Medium, Tricky, Very Important (VVImp)
 * Related Problem: https://leetcode.com/problems/word-break-ii/ (Hard)
 *
 * Problem Statement:
 * Given a string `s` and a dictionary of strings `wordDict`, return true if `s` can be segmented
 * into a space-separated sequence of one or more dictionary words.
 *
 * Note: The same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 * Input:  s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input:  s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: "applepenapple" can be segmented as "apple pen apple".
 * Note: Reusing a dictionary word is allowed.
 *
 * Example 3:
 * Input:  s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 * Constraints:
 * - 1 <= s.length <= 300
 * - 1 <= wordDict.length <= 1000
 * - 1 <= wordDict[i].length <= 20
 * - `s` and `wordDict[i]` consist of only lowercase English letters.
 * - All words in `wordDict` are unique.
 *
 * Statistics:
 * - Accepted: 992,361
 * - Submissions: 2,251,710
 */
        
public class PartK_A_WordBreak {
    /*
     * Problem Understanding:
     * Given a string s and a dictionary of words wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
     *
     * 💡 Key Points to Consider:
     * - A word can be reused multiple times.
     * - The order must be maintained.
     * - We need to check if we can completely segment s.
     *
     * Approach 1: Brute Force (Recursion)
     *
     * Idea:
     * - Try every possible partition of s, checking if the prefix is in wordDict and recursively checking the remaining substring.
     * - If we find a valid partition, return true.
     *
     * Steps:
     * 1. Try all partitions of s from index i = 1 to n.
     * 2. If s[0:i] exists in wordDict, recursively check s[i:n].
     * 3. If we reach the end of s with all valid partitions, return true; otherwise, return false.
     *
     * Complexity Analysis:
     * - Time Complexity: O(2ⁿ) (Exponential, since each partition leads to 2 choices).
     * - Space Complexity: O(n) (Recursive stack depth).
     *
     * Dry Run (Brute Force):
     *
     * For s = "leetcode" and wordDict = ["leet", "code"]
     *
     * Step   Substring Checked   Exists in Dict?   Remaining Substring   Recursive Call
     * ---------------------------------------------------------------------------------
     * 1      "l"                ❌                 —                      —
     * 2      "le"               ❌                 —                      —
     * 3      "lee"              ❌                 —                      —
     * 4      "leet"             ✅                 "code"                 Recurse
     * 5      "c"                ❌                 —                      —
     * 6      "co"               ❌                 —                      —
     * 7      "cod"              ❌                 —                      —
     * 8      "code"             ✅                 "" (empty)             ✅ Base Case
     *
     * Since we reached an empty substring, return true.
     */
    private static class Bruitforce {
        public static boolean wordBreak(String s, List<String> wordDict) {
            return helper(s, new HashSet<>(wordDict));
        }

        private static boolean helper(String s, Set<String> wordDict) {
            if (s.isEmpty()) return true;

            for (int i = 1; i <= s.length(); i++) {
                if (wordDict.contains(s.substring(0, i)) && helper(s.substring(i), wordDict)) {
                    return true;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code"))); // true
        }
    }

    /*
     * Approach 2: Recursion with Memoization
     *
     * Idea:
     * Store results of subproblems to avoid redundant calculations.
     *
     * Complexity Analysis:
     * Time Complexity: O(n²) (Each substring is checked once and stored in memo).
     * Space Complexity: O(n) (Memoization storage).
     *
     * Dry Run (Memoization)
     * For s = "applepenapple" and wordDict = ["apple", "pen"]
     *
     * Step    Substring Checked    Exists in Dict?    Remaining Substring    Memo Used?
     * ---------------------------------------------------------------------------------
     * 1       "apple"             ✅                 "penapple"             No
     * 2       "pen"               ✅                 "apple"                No
     * 3       "apple"             ✅                 "" (empty)             ✅
     * 4       —                   —                  —                      Return true
     *
     * How Memoization Helps
     *
     * With Memoization: Reduced Recursion Tree
     * With memoization, we cache results for computed substrings to avoid redundant work.
     *
     *              "catsanddog"
     *             /            \
     *       "sanddog"       "anddog" ❌ (Memoized)
     *       /        \
     *   "dog" ✅   "anddog" ❌ (Memoized)
     *
     * Memoization Table:
     * --------------------------------
     * Substring    Computed Result    Stored in Memo?
     * "dog"        ✅ true            ✅ Yes
     * "sanddog"    ✅ true            ✅ Yes
     * "anddog"     ❌ false           ✅ Yes
     * --------------------------------
     *
     * How Memoization Helps?
     * - When we reach "anddog" again, we instantly return false instead of recomputing.
     * - We only compute "dog" once and reuse the result whenever needed.
     * - The recursion tree shrinks significantly, improving performance from O(2ⁿ) → O(n²).
     */

    private static class Better {
        public static boolean wordBreak(String s, List<String> wordDict) {
            return helper(s, new HashSet<>(wordDict), new HashMap<>());
        }

        private static boolean helper(String s, Set<String> wordDict, Map<String, Boolean> memo) {
            if (s.isEmpty()) return true;
            if (memo.containsKey(s)) return memo.get(s);

            for (int i = 1; i <= s.length(); i++) {
                if (wordDict.contains(s.substring(0, i)) && helper(s.substring(i), wordDict, memo)) {
                    memo.put(s, true);
                    return true;
                }
            }
            memo.put(s, false);
            return false;
        }

        public static void main(String[] args) {
            System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code"))); // true
        }
    }

/*
    Optimizing from Memoization to DP Approach

    1️⃣ Observing the Recursion Tree (Memoization)
    - When using recursion with memoization, we store the results of subproblems to avoid recomputation.
      However, the recursion tree still explores all possible partitions, leading to repeated function calls.
    - Even with memoization, the recursive approach has an overhead of recursive function calls and stack usage.
    - Instead of a top-down approach (where we break the problem recursively), we can switch to a bottom-up approach using Dynamic Programming (DP).

    2️⃣ Transition to DP Approach
    🔹 Key Observation:
      - Instead of solving subproblems recursively and storing results, we can use a DP table (dp[])
        where dp[i] stores whether s[0:i] can be segmented using words from wordDict.

    🔹 How DP Helps?
      ✅ Avoids recursion overhead: No function call stack, reducing memory usage.
      ✅ Iterative processing: Linear traversal of s ensures we solve each subproblem once.
      ✅ Faster execution: Uses nested loops but avoids recomputation.

    3️⃣ How DP Works?
    🔹 Define DP State:
      - dp[i] = true if s[0:i] can be segmented using words from wordDict.

    🔹 Transition:
      - For each index i, check every possible partition j (0 ≤ j < i).
      - If dp[j] is true and s[j:i] exists in wordDict, set dp[i] = true.

    4️⃣ Dry Run of DP Approach
    Example: s = "leetcode", wordDict = ["leet", "code"]

    | i  | s[0:i]   | dp[i] | Explanation |
    |----|----------|-------|-------------|
    |  0 | ""       | ✅    | Base case (empty string is always segmentable) |
    |  1 | "l"      | ❌    | "l" not in wordDict |
    |  2 | "le"     | ❌    | "le" not in wordDict |
    |  3 | "lee"    | ❌    | "lee" not in wordDict |
    |  4 | "leet"   | ✅    | "leet" in wordDict, so dp[4] = true |
    |  5 | "leetc"  | ❌    | No valid split where dp[j] is true |
    |  6 | "leetco" | ❌    | No valid split where dp[j] is true |
    |  7 | "leetcod"| ❌    | No valid split where dp[j] is true |
    |  8 | "leetcode" | ✅  | "code" in wordDict, and dp[4] is true |

    ✅ Final Answer: dp[8] = true, so "leetcode" can be segmented.

    5️⃣ Complexity Analysis
    🔹 Time Complexity: O(n²), where n is the length of s (nested loop for i and j).
    🔹 Space Complexity: O(n), for storing the dp[] table.

    6️⃣ Why DP is Better than Memoization?

    | Approach               | Time Complexity | Space Complexity                 | Function Call Overhead? |
    |------------------------|----------------|---------------------------------|--------------------------|
    | Recursion + Memoization | O(n²)          | O(n) (memo table + recursion stack) | ✅ Yes (Recursive calls) |
    | Bottom-Up DP           | O(n²)          | O(n) (DP table only)            | ❌ No (Iterative approach) |

    - DP avoids the function call overhead and stack usage.
    - DP processes the string iteratively, making it more efficient and scalable.
*/

    private static class Optimize {
        public static boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordSet = new HashSet<>(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true; // Base case

            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }

        public static void main(String[] args) {
            System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code"))); // true
        }
    }
    
}
