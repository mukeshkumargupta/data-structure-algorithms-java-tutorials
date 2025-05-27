package com.interview.heap.ipoPatternHeapAndGreedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/maximum-subsequence-score/description/?envType=study-plan-v2&envId=leetcode-75
https://www.youtube.com/watch?v=z9oUzKhEYJU
Category: Medium, top75, Tricky, Facebook
https://leetcode.com/problems/ipo/ Hard
https://leetcode.com/problems/minimum-cost-to-hire-k-workers/ Hard
You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k.
 You must choose a subsequence of indices from nums1 of length k.

For chosen indices i0, i1, ..., ik - 1, your score is defined as:

The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
Return the maximum possible score.

A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.



Example 1:

Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
Output: 12
Explanation:
The four possible subsequence scores are:
- We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
- We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
- We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
- We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
Therefore, we return the max score, which is 12.
Example 2:

Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
Output: 30
Explanation:
Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.


Constraints:

n == nums1.length == nums2.length
1 <= n <= 105
0 <= nums1[i], nums2[j] <= 105
1 <= k <= n
 */
public class B_MaximumSubsequenceScore {
/*
    ✅ Approach 2: Sorting + Min-Heap (Optimal Greedy)

    💡 Idea:
    - Create a pair of (nums2[i], nums1[i])
    - Sort by nums2[i] in descending order
    - Iterate:
        - Maintain a min-heap of size k for nums1[i]
        - Keep track of the sum of nums1 from top k
        - At each iteration:
            - If heap has k elements:
                - Compute score = sum * current nums2[i]
                - Update result
    - This works because:
        - Fixing nums2[i] as the minimum multiplier,
        - We maximize the sum of top k nums1[i] values.

    ⏱ Time & Space Complexity
    Operation       Time        Space
    Sorting         O(n log n)  O(n)
    Heap ops        O(n log k)  O(k)
    Total           O(n log n)  O(n)
*/

    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] pairs = new int[n][2];

        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums2[i];
            pairs[i][1] = nums1[i];
        }

        Arrays.sort(pairs, (a, b) -> b[0] - a[0]); // Sort descending by nums2

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long sum = 0;
        long result = 0;

        for (int i = 0; i < n; i++) {
            sum += pairs[i][1];
            minHeap.offer(pairs[i][1]);

            if (minHeap.size() > k) {
                sum -= minHeap.poll();
            }

            if (minHeap.size() == k) {
                result = Math.max(result, sum * (long) pairs[i][0]);
            }
        }

        return result;
    }
}
