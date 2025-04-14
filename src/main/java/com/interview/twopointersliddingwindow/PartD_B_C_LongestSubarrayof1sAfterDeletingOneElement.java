package com.interview.twopointersliddingwindow;

/*
https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/?envType=study-plan-v2&envId=leetcode-75
Category: Medium, top75
Related:
https://leetcode.com/problems/max-consecutive-ones-iii/
Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.



Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class PartD_B_C_LongestSubarrayof1sAfterDeletingOneElement {
/*
    ⏱ Time Complexity
    - We iterate through the array once → O(n)
    - Sliding window expands and shrinks linearly

    ✅ Final Time Complexity: O(n)

    🧠 Space Complexity
    - We use only constant extra space

    ✅ Final Space Complexity: O(1)

    🧪 Example Dry Run
    Input:
    nums = [1, 1, 0, 1, 1, 1, 0, 1]

    Steps:

    right	nums[right]	zeroCount	left	maxLen	Window
    0	    1	            0	        0	    0	    [1]
    1	    1	            0	        0	    1	    [1, 1]
    2	    0	            1	        0	    2	    [1, 1, 0]
    3	    1	            1	        0	    3	    [1, 1, 0, 1]
    4	    1	            1	        0	    4	    [1, 1, 0, 1, 1]
    5	    1	            1	        0	    5	    [1, 1, 0, 1, 1, 1]
    6	    0	            2	        0→3	5	    [1, 1, 1, 0]
    7	    1	            1	        3	    5	    [1, 1, 1, 0, 1]

    Output: 5

    Explanation:
    We are allowed to delete one element (usually a zero),
    so the longest subarray of only 1s after deleting one element is of length 5.
    Example: [1, 1, 0, 1, 1, 1] → remove the 0 → length = 5
*/

    public int longestSubarray(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeroCount++;

            // shrink window until there's at most 1 zero
            while (zeroCount > 1) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }

            // length of window minus one (for deleting one element)
            maxLen = Math.max(maxLen, right - left);
        }

        return maxLen;
    }

    /*
📚 Concept: Sliding Window + Conditional Count + At Most K Operations

These questions revolve around:
- Finding longest/shortest subarrays
- With at most K modifications (e.g., flipping 0 to 1)
- Or deleting exactly one element
*/

    /*
🔁 Sliding Window – With At Most K Zeros (or One Deletion)
    These focus on maintaining a valid window with constraints.

1. ✅ Longest Subarray of 1's After Deleting One Element
    https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/

            2. Flip String to Monotone Increasing
    https://leetcode.com/problems/flip-string-to-monotone-increasing/

            3. Max Consecutive Ones III
    https://leetcode.com/problems/max-consecutive-ones-iii/

            4. Max Consecutive Ones II
    https://leetcode.com/problems/max-consecutive-ones-ii/

            5. Replace the Substring for Balanced String
    https://leetcode.com/problems/replace-the-substring-for-balanced-string/

            6. Longest Substring with At Most K Distinct Characters
    https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

            7. Longest Repeating Character Replacement
    https://leetcode.com/problems/longest-repeating-character-replacement/

            8. Minimum Replacements to Make String Balanced (Custom Variant)

---

        🎯 Deletion-Based Logic (Exactly One/Multiple Deletions)
    These focus on modifying or deleting a fixed number of elements.

9. Minimum Deletions to Make Array Beautiful
    https://leetcode.com/problems/minimum-deletions-to-make-array-beautiful/

            10. Longest Subarray with Sum at Most K (Custom Variation)

11. Delete and Earn
    https://leetcode.com/problems/delete-and-earn/

            ---

            🧠 Count & Conditional Maintenance Variants
    Where maintaining counters (like zeroCount) is critical.

            12. Subarrays with K Different Integers
    https://leetcode.com/problems/subarrays-with-k-different-integers/

            13. Number of Subarrays with Bounded Maximum
    https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/

            14. Count Number of Nice Subarrays (Exactly K odd numbers)
    https://leetcode.com/problems/count-number-of-nice-subarrays/

            ---

            ✂️ Split / Grouping Based Variants
    Where you need to break arrays or strings based on similar logic.

15. Split Array into Consecutive Subsequences
    https://leetcode.com/problems/split-array-into-consecutive-subsequences/

            16. Split Array Largest Sum
    https://leetcode.com/problems/split-array-largest-sum/

            ---

            🧩 Bonus: Pattern Matching and Binary Variants
    Works with binary arrays/strings or similar transformation logic.

17. Minimum Flips to Make a Binary String Alternating
    https://leetcode.com/problems/minimum-flips-to-make-a-binary-string-alternating/

            18. Maximum Binary String After Change
    https://leetcode.com/problems/maximum-binary-string-after-change/

            19. Minimum Swaps to Group All 1's Together
    https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/
    */
}
