package com.interview.dynamic;

/*
https://leetcode.com/problems/palindrome-partitioning-iv/description/
Derived: https://leetcode.com/problems/longest-palindromic-substring/ this approach is  used in optimization
Category: Hard, VVImp
Related: all possible palindrom just to recompute
1745. Palindrome Partitioning IV
Hard
Topics
Companies
Hint
Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​

A string is said to be palindrome if it the same string when reversed.



Example 1:

Input: s = "abcbdd"
Output: true
Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.
Example 2:

Input: s = "bcbddxy"
Output: false
Explanation: s cannot be split into 3 palindromes.


Constraints:

3 <= s.length <= 2000
s​​​​​​ consists only of lowercase English letters.
 */
public class PartJ_C_1_PalindromePartitioningIV {

/*
❌ TLE due to slow execution

🔹 **Problem Breakdown**
- We need to check whether we can split a string `s` into **three non-empty palindromic substrings**.
- A **palindrome** is a string that reads the same **forward and backward**.
- We need to **try all possible ways** to partition `s` into three substrings.
- **Return true** if at least one valid split exists.

---

✅ **Approach 1: Brute Force (O(N³))**

🔹 **Idea**
1. Try all possible pairs `(i, j)` to split `s` into three parts:
   - **First part**: `s[0...i]`
   - **Second part**: `s[i+1...j]`
   - **Third part**: `s[j+1...n-1]`
2. Check if **all three substrings** are palindromes.
3. If **any valid split is found**, return `true`.

---

🔹 **Time Complexity**
| Step                           | Complexity |
|--------------------------------|------------|
| Splitting into three parts     | O(N²)      |
| Checking palindromes           | O(N)       |
| **Total Complexity**           | **O(N³)**  |

---

🔹 **Dry Run for `s = "abcbdd"`**

| `i` | `j` | Substrings              | Palindromes? |
|-----|-----|-------------------------|-------------|
| 0   | 3   | `"a", "bcb", "dd"`       | ✅ **Yes** ✅ |

✅ **Valid split found → return `true`**
*/

    private static class Bruitforce {
        public boolean checkPartitioning(String s) {
            int n = s.length();
            for (int i = 0; i < n - 2; i++) {  // First partition point
                for (int j = i + 1; j < n - 1; j++) {  // Second partition point
                    if (isPalindrome(s, 0, i) && isPalindrome(s, i + 1, j) && isPalindrome(s, j + 1, n - 1)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean isPalindrome(String s, int left, int right) {
            while (left < right) {
                if (s.charAt(left++) != s.charAt(right--)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
    ✅ Approach 2: Better Solution Using Memoization (O(N²))

    🔹 Idea:
    Instead of checking palindromes O(N) times in brute force, we can precompute all palindromic substrings in O(N²).

    🔹 Steps:
    1. **Precompute `isPalindrome[][]`**
       - Store whether `s[i...j]` is a palindrome in a 2D DP table.
    2. **Try all (i, j) partitions in O(N²)**.

    🔹 Time Complexity:
    | Step                         | Complexity |
    |------------------------------|------------|
    | Precompute palindrome table  | O(N²)      |
    | Try all (i, j) partitions    | O(N²)      |
    | **Total Complexity**         | **O(N²) ✅** |

    ---

    🔹 Dry Run for `s = "abcbdd"`

    **Step 1: Compute `isPalindrome[][]`**

    | i → | 0    | 1      | 2      | 3       | 4       | 5       |
    |-----|------|--------|--------|---------|---------|---------|
    | 0   | ✅ "a" | ❌    | ❌    | ❌     | ❌     | ❌     |
    | 1   |      | ✅ "b" | ❌    | ✅ "bcb" | ❌     | ❌     |
    | 2   |      |        | ✅ "c" | ❌     | ❌     | ❌     |
    | 3   |      |        |        | ✅ "b" | ❌     | ❌     |
    | 4   |      |        |        |        | ✅ "d" | ✅ "dd" |
    | 5   |      |        |        |        |        | ✅ "d" |

    ---

    **Step 2: Try Cuts**
    - `i = 0, j = 3` → `"a", "bcb", "dd"` ✅ → **Return true**.
    */
    private static class Better {
        public boolean checkPartitioning(String s) {
            int n = s.length();

            // table[i][j] will be false if
            // substring str[i..j] is not palindrome.
            // Else table[i][j] will be true
            boolean dp[][] = new boolean[n][n];

            // All substrings of length 1 are palindromes
            int maxLength = 1;
            for (int i = 0; i < n; ++i)
                dp[i][i] = true;

            // check for sub-string of length 2.
            int start = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    start = i;
                    maxLength = 2;
                }
            }

            // Check for lengths greater than 2.
            // k is length of substring
            for (int k = 3; k <= n; ++k) {

                // Fix the starting index
                for (int i = 0; i < n - (k - 1); ++i) {
                    // Get the ending index of substring from
                    // starting index i and length k
                    int j = i + k - 1;

                    // checking for sub-string from ith index to
                    // jth index iff str.charAt(i+1) to
                    // str.charAt(j-1) is a palindrome
                    if (dp[i + 1][j - 1]
                            && s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;

                        if (k > maxLength) {
                            start = i;
                            maxLength = k;
                        }
                    }
                }
            }

            // Step 2: Try all possible (i, j) partitions in O(N²)
            for (int i = 0; i < n - 2; i++) {
                if (!dp[0][i]) continue;
                for (int j = i + 1; j < n - 1; j++) {
                    if (dp[i + 1][j] && dp[j + 1][n - 1]) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

}
