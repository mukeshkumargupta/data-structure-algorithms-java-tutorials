package com.interview.sort.wigglesort;

import java.util.*;
/*
 * LeetCode Problem: Wiggle Sort II
 * URL: https://leetcode.com/problems/wiggle-sort-ii/
 *
 * Category: Medium, Tricky, Top150
 * Related Problems:
 * - Wiggle Sort: https://leetcode.com/problems/wiggle-sort/ (Medium)
 * - Array With Elements Not Equal to Average of Neighbors:
 *   https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/ (Medium)
 *
 * Problem Statement:
 * Given an integer array `nums`, reorder it such that:
 *     nums[0] < nums[1] > nums[2] < nums[3] > nums[4] ...
 *
 * Assumptions:
 * - The input array always has a valid solution.
 *
 * Example 1:
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also a valid solution.
 *
 * Example 2:
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 *
 * Constraints:
 * - 1 <= nums.length <= 50,000
 * - 0 <= nums[i] <= 5000
 * - It is guaranteed that a valid answer exists.
 *
 * Follow-Up:
 * - Can you achieve an O(n) time complexity?
 * - Can you solve it in-place with O(1) extra space?
 *
 * Video Explanation:
 * https://www.youtube.com/watch?v=mwsjU6CHOb4
 */
public class A_B_WiggleSorII {

/*
    Wiggle Sort II (Leetcode 324)
    🔗 Problem Link: https://leetcode.com/problems/wiggle-sort-ii/

    Problem Statement
    Given an unsorted array nums, rearrange it so that:
    nums[0] < nums[1] > nums[2] < nums[3] > nums[4] …

    Example:
    Input: [1, 5, 1, 1, 6, 4]
    Output: [1, 6, 1, 5, 1, 4]

    Key Differences from Wiggle Sort I
    ---------------------------------
    Feature                 | Wiggle Sort I         | Wiggle Sort II
    ------------------------|----------------------|----------------------
    Pattern                | nums[0] ≤ nums[1] ≥ nums[2] ≤ nums[3] ... | nums[0] < nums[1] > nums[2] < nums[3] ...
    Duplicates Allowed?     | ✅ Yes               | ✅ Yes
    Sorting Needed?         | ❌ No                | ✅ Yes (or Median Partitioning)
    Best Approach           | Greedy Swap (O(N))   | QuickSelect + Partition (O(N))

    Why Greedy Swap Doesn't Work for Wiggle Sort II?
    - Unlike Wiggle Sort I, we cannot just swap adjacent elements.
    - If we do, it does not guarantee the strict < and > pattern.
    - Sorting and reordering carefully (or using QuickSelect) is needed.

    1. Brute Force Approach (Generate All Permutations)
    ---------------------------------------------------
    Idea:
    - Generate all permutations of nums.
    - Check each permutation if it satisfies the wiggle pattern.
    - Return the first valid one.

    Time Complexity:
    - Generating all permutations → O(N!)
    - Checking each permutation → O(N)
    - Overall Complexity → O(N! × N) ❌ (Not feasible)
*/


    private static class BruitForce {
        public void wiggleSort(int[] nums) {
            List<List<Integer>> permutations = new ArrayList<>();
            backtrack(nums, 0, permutations);

            for (List<Integer> perm : permutations) {
                if (isValidWiggle(perm)) {
                    for (int i = 0; i < nums.length; i++) {
                        nums[i] = perm.get(i);
                    }
                    return;
                }
            }
        }

        private void backtrack(int[] nums, int index, List<List<Integer>> result) {
            if (index == nums.length) {
                List<Integer> temp = new ArrayList<>();
                for (int num : nums) temp.add(num);
                result.add(new ArrayList<>(temp));
                return;
            }
            for (int i = index; i < nums.length; i++) {
                swap(nums, i, index);
                backtrack(nums, index + 1, result);
                swap(nums, i, index);
            }
        }

        private boolean isValidWiggle(List<Integer> nums) {
            for (int i = 1; i < nums.size(); i++) {
                if ((i % 2 == 1 && nums.get(i) <= nums.get(i - 1)) ||
                        (i % 2 == 0 && nums.get(i) >= nums.get(i - 1))) {
                    return false;
                }
            }
            return true;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

/*
    Better Approach Explanation (Sorting + Placement)
    - Sort the array → Ensures elements are ordered. (O(N log N))
    - Fill odd indices first (peaks) with largest values. (O(N))
    - Fill even indices (valleys) with remaining smaller values. (O(N))
    - Copy result back to nums to maintain in-place modification. (O(N))

    Complexity Analysis
    - Time Complexity: O(N log N) (due to sorting)
    - Space Complexity: O(N) (extra array result)

    Pros: Simple and effective.
    Cons: Uses extra space, not truly in-place.
*/

    private static class Better {
        public void wiggleSort(int[] nums) {
        /*
         * Runtime: 2 ms, faster than 98.66% of Java online submissions for Wiggle Sort II.
Memory Usage: 41.8 MB, less than 70.63% of Java online submissions for Wiggle Sort II.
         * TC: NLogN
         * SC: O(N)
         */
            Arrays.sort(nums);

            int l = nums.length;
            int i = 1; int j = l-1;
            int[] result = new int[l];
            while (i < l ) {
                result[i] = nums[j];
                i +=2;
                j--;
            }
            i = 0;
            while (i < l) {
                result[i] = nums[j];
                i +=2;
                j--;
            }

            for (i = 0; i < l ; i++) {
                nums[i] = result[i];
            }


        }
    }

    /*
     * 3. Optimal Approach (O(N) Time & O(1) Space)
     *
     * Idea:
     * - Find the median using QuickSelect (O(N)).
     * - Three-way partitioning to place elements:
     *   - Numbers larger than the median in odd indices.
     *   - Numbers smaller than the median in even indices.
     *   - Numbers equal to the median in the remaining positions.
     * - Use a Virtual Indexing Trick to avoid extra space.
     *
     * Key Concept: Virtual Indexing
     * - We map indices using a formula to ensure elements are placed correctly:
     *   newIndex(i) = (1 + 2 * i) % (n | 1)
     * - This avoids using an extra array while ensuring in-place swaps.
     *
     * Time Complexity:
     * - QuickSelect (Finding Median) → O(N)
     * - Three-way partitioning → O(N)
     * - Overall Complexity → O(N) ✅
     *
     * Final Comparison:
     * -------------------------------------------------------------------
     * | Approach                     | Time Complexity | Space Complexity | Feasibility |
     * |------------------------------|----------------|------------------|-------------|
     * | Brute Force                  | O(N! × N)      | O(N!)            | ❌ Not feasible |
     * | Sorting + Swap               | O(N log N)     | O(N)             | ✅ Decent |
     * | QuickSelect + Partitioning    | O(N)          | O(1)             | ✅ Best |
     * -------------------------------------------------------------------
     *
     * Conclusion:
     * - Use the optimal approach (QuickSelect + Partitioning) for O(N) time & O(1) space.
     * - Sorting + swapping is an easy-to-implement alternative but has O(N log N) complexity.
     * - Brute force is impractical for large arrays.
     */
    private static class Optimal {
        public void wiggleSort(int[] nums) {
            int n = nums.length;
            int median = findKthLargest(nums, (n + 1) / 2); // O(N)

            int left = 0, i = 0, right = n - 1;

            while (i <= right) {
                if (nums[newIndex(i, n)] > median) {
                    swap(nums, newIndex(left++, n), newIndex(i++, n));
                } else if (nums[newIndex(i, n)] < median) {
                    swap(nums, newIndex(right--, n), newIndex(i, n));
                } else {
                    i++;
                }
            }
        }

        private int newIndex(int index, int n) {
            return (1 + 2 * index) % (n | 1); // Virtual Indexing Trick
        }

        private int findKthLargest(int[] nums, int k) {
            int left = 0, right = nums.length - 1;
            Random random = new Random();

            while (left <= right) {
                int pivotIndex = left + random.nextInt(right - left + 1);
                int newPivot = partition(nums, left, right, pivotIndex);
                if (newPivot == k - 1) {
                    return nums[newPivot];
                } else if (newPivot > k - 1) {
                    right = newPivot - 1;
                } else {
                    left = newPivot + 1;
                }
            }
            return -1;
        }

        private int partition(int[] nums, int left, int right, int pivotIndex) {
            int pivotValue = nums[pivotIndex];
            swap(nums, pivotIndex, right);
            int storeIndex = left;

            for (int i = left; i < right; i++) {
                if (nums[i] > pivotValue) {
                    swap(nums, storeIndex++, i);
                }
            }
            swap(nums, storeIndex, right);
            return storeIndex;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
}
