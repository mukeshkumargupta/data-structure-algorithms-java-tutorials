package com.interview.binarysearch.PartBLowerBoundUpperBound;

import java.util.Arrays;

/*

https://leetcode.com/problems/successful-pairs-of-spells-and-potions/description/?envType=study-plan-v2&envId=leetcode-75
Related:
https://leetcode.com/problems/most-profit-assigning-work/description/ Medium
https://leetcode.com/problems/longest-subsequence-with-limited-sum/ Easy
https://leetcode.com/problems/maximum-matching-of-players-with-trainers/ Medium
2300. Successful Pairs of Spells and Potions
Medium
Topics
Companies
Hint
You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.

You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.

Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.



Example 1:

Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
Output: [4,0,3]
Explanation:
- 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
- 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
- 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
Thus, [4,0,3] is returned.
Example 2:

Input: spells = [3,1,2], potions = [8,5,8], success = 16
Output: [2,0,2]
Explanation:
- 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
- 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
- 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
Thus, [2,0,2] is returned.


Constraints:

n == spells.length
m == potions.length
1 <= n, m <= 105
1 <= spells[i], potions[i] <= 105
1 <= success <= 1010
 */
public class A_BS_2_A_B_Lower_bound_SuccessfulPairsofSpellsandPotions {
    /*
        💡 Intuition:
        The brute force approach would be to try every spell-potion pair which is O(n * m) — too slow for large inputs.

        Instead, we sort potions[] and for each spell, binary search the smallest potion that satisfies spell * potion >= success.

        From that index onward, all potions will form successful pairs with this spell.

        ✅ Approach:
        Sort potions[].

        For each spell, calculate the minimum potion value required:
        minPotion = ceil(success / spell)

        Use binary search on potions[] to find the first index where potion ≥ minPotion.

        All potions from that index to end are valid.

        🧠 Why binary search works:
        Since potions are sorted, the condition spell * potion >= success turns into finding the first potion such that potion >= success / spell.

        Once we find that point, the number of valid potions is potions.length - index.

        🧮 Time Complexity:
        Operation                         Time
        Sorting potions                   O(m log m)
        Binary search for n spells       O(n log m)
        Total                            O(m log m + n log m)
        Where:
        n = spells.length
        m = potions.length

        📦 Space Complexity:
        O(1) extra space (excluding result array and sorting input)
    */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int spell = spells[i];

            // minimum potion needed: ceil(success / spell)
            long minPotion = (success + spell - 1) / spell;//tricky this also way to get ceil value like like added one more spell then -1

            int left = 0, right = m - 1, index = m;

            while (left <= right) {//lower bound code
                int mid = left + (right - left) / 2;
                if (potions[mid] >= minPotion) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            result[i] = m - index;
        }

        return result;
    }
}
