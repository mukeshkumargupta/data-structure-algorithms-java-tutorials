package com.interview.twopointersliddingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/max-number-of-k-sum-pairs/description/?envType=study-plan-v2&envId=leetcode-75:
https://www.youtube.com/watch?v=FevSHchkCeo
Category: Medium
Related:
https://leetcode.com/problems/two-sum/ Easy
https://leetcode.com/problems/count-good-meals/ Medium VImp
https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/ Medium VImp
You are given an integer array nums and an integer k.

In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.

Return the maximum number of operations you can perform on the array.

Agrahhya@123456


Example 1:

Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.
Example 2:

Input: nums = [3,1,3,4,3], k = 6
Output: 1
Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= 109
 */
public class MaxNumberofK_SumPairs {
        /*
        Alternate idea if HashMap isn’t preferred:
        - Sort the array
        - Use two pointers (left, right) to find pairs that sum to k

        ⏱ Time: O(n log n)
        🧠 Space: O(1) if sorting in-place
    */
    private static class Better {
        public int maxOperations(int[] nums, int k) {
            Arrays.sort(nums);
            int left = 0, right = nums.length - 1, count = 0;

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == k) {
                    count++;
                    left++;
                    right--;
                } else if (sum < k) {
                    left++;
                } else {
                    right--;
                }
            }

            return count;
        }
    }

    private static class Optimal {
/*
🔍 Problem:
Find the maximum number of pairs in the array that sum up to a given value `k`.
Each element can be used at most once.

Approach:
- Use a HashMap to track frequency of elements.
- For each number `num`, check if `k - num` exists in the map.
- If it does, form a pair and reduce frequency of both.

⏱ Time Complexity: O(n)
We iterate over the array once.

🧠 Space Complexity: O(n)
HashMap stores frequencies of at most n elements.

🧪 Input:
nums = [3, 1, 3, 4, 3, 2, 1, 2, 4], k = 6

✅ Expected Output:
3 pairs:
(3, 3)
(2, 4)
(2, 4)

🧵 Dry Run Step-by-Step:

Step | num | complement | freqMap before                      | Action Taken                      | freqMap after                        | count
-----|-----|------------|-------------------------------------|------------------------------------|--------------------------------------|------
1    | 3   | 3          | {}                                  | 3 not found → store it            | {3:1}                                 | 0
2    | 1   | 5          | {3:1}                               | 5 not found → store 1             | {3:1, 1:1}                            | 0
3    | 3   | 3          | {3:1, 1:1}                          | 3 found! Pair (3,3) formed        | {3:0, 1:1}                            | 1
4    | 4   | 2          | {3:0, 1:1}                          | 2 not found → store 4             | {3:0, 1:1, 4:1}                       | 1
5    | 3   | 3          | {3:0, 1:1, 4:1}                     | 3 not found → store 3 again       | {3:1, 1:1, 4:1}                       | 1
6    | 2   | 4          | {3:1, 1:1, 4:1}                     | 4 found! Pair (2,4) formed        | {3:1, 1:1, 4:0}                       | 2
7    | 1   | 5          | {3:1, 1:1, 4:0}                     | 5 not found → store 1 again       | {3:1, 1:2, 4:0, 5:0}                  | 2
8    | 2   | 4          | {3:1, 1:2, 4:0, 5:0}                | 4 not found → store 2             | {3:1, 1:2, 4:0, 5:0, 2:1}             | 2
9    | 4   | 2          | {3:1, 1:2, 4:0, 5:0, 2:1}           | 2 found! Pair (4,2) formed        | {3:1, 1:2, 4:0, 5:0, 2:0}             | 3
*/

        public int maxOperations(int[] nums, int k) {
            Map<Integer, Integer> freqMap = new HashMap<>();
            int count = 0;

            for (int num : nums) {
                int complement = k - num;

                if (freqMap.getOrDefault(complement, 0) > 0) {
                    count++;
                    freqMap.put(complement, freqMap.get(complement) - 1);// when found then both need to remove so no need to add num, while k to reduce by 1 this is what question says
                } else {
                    freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
                }
            }

            return count;
        }
    }

    /*
        Derived Questions:

        ➕ Two Sum Variants
        These are based on identifying pairs that sum to a target value.

        Two Sum
        https://leetcode.com/problems/two-sum/

        Two Sum II - Input Array is Sorted
        https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

        Two Sum IV - Input is a BST
        https://leetcode.com/problems/two-sum-iv-input-is-a-bst/

        3Sum (Triplets sum to zero)
        https://leetcode.com/problems/3sum/

        4Sum
        https://leetcode.com/problems/4sum/

        🔁 HashMap + Frequency Based
        These questions test your skill in frequency mapping and counting operations.

        Maximum Number of Balloons
        https://leetcode.com/problems/maximum-number-of-balloons/

        Valid Anagram
        https://leetcode.com/problems/valid-anagram/

        Top K Frequent Elements
        https://leetcode.com/problems/top-k-frequent-elements/

        Minimum Deletions to Make Character Frequencies Unique
        https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/

        👫 Pair and Group Logic
        Problems requiring forming pairs or counting specific pair/group combinations.

        Count Number of Pairs With Absolute Difference K
        https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/

        Count Good Meals (Product-based pairing)
        https://leetcode.com/problems/count-good-meals/

        Number of Pairs of Strings With Concatenation Equal to Target
        https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/

        Count Nice Pairs in an Array
        https://leetcode.com/problems/count-nice-pairs-in-an-array/

        🎯 Greedy + Pair Making
        These problems involve greedily forming valid pairs with constraints.

        Hand of Straights
        https://leetcode.com/problems/hand-of-straights/

        Divide Array in Sets of K Consecutive Numbers
        https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/

        Maximum Number of Pairs in Array
        https://leetcode.com/problems/maximum-number-of-pairs-in-array/

        Reorganize String
        https://leetcode.com/problems/reorganize-string/

        🧠 Advanced Variants / Edge Cases
        Complex pairing/combination logic.

        Longest Harmonious Subsequence
        https://leetcode.com/problems/longest-harmonious-subsequence/

        Longest Substring with At Most K Distinct Characters
        https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

        K-diff Pairs in an Array
        https://leetcode.com/problems/k-diff-pairs-in-an-array/

*/
}
