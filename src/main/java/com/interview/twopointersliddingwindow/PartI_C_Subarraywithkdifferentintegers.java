package com.interview.twopointersliddingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PartI_C_Subarraywithkdifferentintegers {
    /*
        🔗 Problem Link:
        https://leetcode.com/problems/subarrays-with-k-different-integers/description/

        🎥 Video Explanation:
        https://www.youtube.com/watch?v=7wYGbV_LsX4&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=11

        📌 Category: Hard

        🔗 Related Problems:
        - https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/description/ (Medium) Locked it is same as k distinc but here not to try goal and goal -1
        - https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/ (Medium) it is also exactly same as above
        - https://leetcode.com/problems/count-vowel-substrings-of-a-string/description/ (Easy)

        🛠️ Brute Force Approach (O(n²)):
        1️⃣ Generate all possible subarrays.
        2️⃣ For each subarray, count the number of distinct integers.
        3️⃣ If the count of distinct integers equals k, increment the result.

        📝 Explanation:
        - Iterate over all possible subarrays (nested loops).
        - Use a HashSet to track distinct integers.
        - If the size of the set equals k, increment the count.

        ⏳ Time Complexity: O(n²)
    */
    public int subarraysWithKDistinctBruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        // Generate all subarrays
        for (int i = 0; i < n; i++) {
            Set<Integer> distinct = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinct.add(nums[j]);
                // If the number of distinct integers equals k, increment the count
                if (distinct.size() == k) {
                    count++;
                }
            }
        }

        return count;
    }


    public int subarraysWithKDistinctBetter(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        // Generate all subarrays
        for (int i = 0; i < n; i++) {
            Set<Integer> distinct = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinct.add(nums[j]);
                // If the number of distinct integers equals k, increment the count
                if (distinct.size() == k) {
                    count++;
                } else if (distinct.size() > k) {
                    break;
                }
            }
        }

        return count;
    }

    /*
        🔥 Optimal Approach (O(n)): Sliding Window + HashMap

        📌 Key Idea:
        - Use a sliding window with two helper functions:
          1️⃣ `atMostKDistinct(nums, k)`: Counts subarrays with at most `k` distinct integers.
          2️⃣ `atMostKDistinct(nums, k - 1)`: Counts subarrays with at most `k - 1` distinct integers.
        - The result is:
          `exactlyKDistinct = atMostKDistinct(nums, k) - atMostKDistinct(nums, k-1)`

        🛠️ Explanation:
        1️⃣ Maintain a sliding window with a frequency map (`Map<Integer, Integer> freq`).
        2️⃣ Expand the right boundary (`right`), adding elements to the frequency map.
        3️⃣ If distinct elements exceed `k`, shrink the window from the left (`left`).
        4️⃣ Count valid subarrays in each step as `(right - left + 1)`.
        5️⃣ Use the difference of two `atMostKDistinct` calls to get exactly `k` distinct elements.

        ⏳ Time Complexity: O(n)
        - Each element is processed at most twice (once when expanding, once when shrinking).

        ✅ Why Not Use a `Set<Integer>`?
        - A `Set` only stores unique values but does NOT track frequency.
        - When shrinking the window, we need to decrement the count of elements.
        - If we remove an element prematurely (without checking its frequency), we may break the logic.

        📝 Example Where Set Fails:
        ```plaintext
        nums = [1, 2, 1, 2, 3], k = 2
        ```
        | Step | Left | Right | Window    | Unique Elements |
        |------|------|-------|-----------|----------------|
        | 1    | 0    | 0     | [1]       | {1}            |
        | 2    | 0    | 1     | [1,2]     | {1,2} ✅ (size = k) |
        | 3    | 0    | 2     | [1,2,1]   | {1,2} ✅ (size = k) |
        | 4    | 0    | 3     | [1,2,1,2] | {1,2} ✅ (size = k) |
        | 5    | 0    | 4     | [1,2,1,2,3] | {1,2,3} ❌ (size > k) |

        - Now, if we remove `1` from the `Set`, it disappears completely, even though there's another `1` at index 2.
        - This breaks the logic because `Set` cannot track element frequencies.

        ✅ Why Use a `Map<Integer, Integer>`?
        - A `Map` tracks occurrences of each number.
        - When shrinking, it decrements the count instead of removing elements immediately.
        - We remove an element from the map **only when its count becomes 0**.

        📝 Example With `Map`:
        ```plaintext
        nums = [1, 2, 1, 2, 3], k = 2
        ```
        | Step | Left | Right | Window     | Frequency Map |
        |------|------|-------|------------|--------------|
        | 1    | 0    | 0     | [1]        | {1 → 1}      |
        | 2    | 0    | 1     | [1,2]      | {1 → 1, 2 → 1} ✅ |
        | 3    | 0    | 2     | [1,2,1]    | {1 → 2, 2 → 1} ✅ |
        | 4    | 0    | 3     | [1,2,1,2]  | {1 → 2, 2 → 2} ✅ |
        | 5    | 0    | 4     | [1,2,1,2,3] | {1 → 2, 2 → 2, 3 → 1} ❌ (size > k) |
        | 6    | 1    | 4     | [2,1,2,3]  | {1 → 1, 2 → 2, 3 → 1} ❌ (size > k) |
        | 7    | 2    | 4     | [1,2,3]    | {1 → 1, 2 → 1, 3 → 1} ✅ (size = k) |

        🚀 **Final Conclusion:**
        | ✅ Use `Map<Integer, Integer>` | ❌ Do NOT Use `Set<Integer>` |
        |--------------------------------|------------------------------|
        | Tracks exact frequency of elements | Only stores presence, not count |
        | Allows gradual decrement when shrinking | Removes elements prematurely |
        | Ensures correct window size handling | Fails when duplicate numbers exist |

        🔥 **Thus, a `Map` is required for correct frequency tracking, while a `Set` is NOT suitable for this problem!**
    */
    public int subarraysWithKDistinctOptimal(int[] nums, int k) {
        // Find the number of subarrays with exactly k distinct integers
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
    }

    // Helper function to find the number of subarrays with at most k distinct integers
    private int atMostKDistinct(int[] nums, int k) {
        int left = 0;
        int count = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int right = 0; right < nums.length; right++) {
            // Add the current element to the window
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

            // If we have more than k distinct elements, shrink the window
            while (freqMap.size() > k) {
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                if (freqMap.get(nums[left]) == 0) {
                    freqMap.remove(nums[left]);
                }
                left++;
            }

            // All subarrays between left and right are valid
            count += (right - left + 1);
        }

        return count;
    }
}
