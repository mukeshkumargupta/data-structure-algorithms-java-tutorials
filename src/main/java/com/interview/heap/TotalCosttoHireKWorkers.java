package com.interview.heap;
import java.util.*;
/*
https://leetcode.com/problems/total-cost-to-hire-k-workers/description/?envType=study-plan-v2&envId=leetcode-75
Category: Medium, top75, tricky
Related:
https://leetcode.com/problems/meeting-rooms-ii/ Medium Locked
https://leetcode.com/problems/time-to-cross-a-bridge/ Hard
You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.

You are also given two integers k and candidates. We want to hire exactly k workers according to the following rules:

You will run k sessions and hire exactly one worker in each session.
In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the last candidates workers. Break the tie by the smallest index.
For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will choose the 4th worker because they have the lowest cost [3,2,7,7,1,2].
In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but they have the smallest index [3,2,7,7,2]. Please note that the indexing may be changed in the process.
If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.
A worker can only be chosen once.
Return the total cost to hire exactly k workers.



Example 1:

Input: costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
Output: 11
Explanation: We hire 3 workers in total. The total cost is initially 0.
- In the first hiring round we choose the worker from [17,12,10,2,7,2,11,20,8]. The lowest cost is 2, and we break the tie by the smallest index, which is 3. The total cost = 0 + 2 = 2.
- In the second hiring round we choose the worker from [17,12,10,7,2,11,20,8]. The lowest cost is 2 (index 4). The total cost = 2 + 2 = 4.
- In the third hiring round we choose the worker from [17,12,10,7,11,20,8]. The lowest cost is 7 (index 3). The total cost = 4 + 7 = 11. Notice that the worker with index 3 was common in the first and last four workers.
The total hiring cost is 11.
Example 2:

Input: costs = [1,2,4,1], k = 3, candidates = 3
Output: 4
Explanation: We hire 3 workers in total. The total cost is initially 0.
- In the first hiring round we choose the worker from [1,2,4,1]. The lowest cost is 1, and we break the tie by the smallest index, which is 0. The total cost = 0 + 1 = 1. Notice that workers with index 1 and 2 are common in the first and last 3 workers.
- In the second hiring round we choose the worker from [2,4,1]. The lowest cost is 1 (index 2). The total cost = 1 + 1 = 2.
- In the third hiring round there are less than three candidates. We choose the worker from the remaining workers [2,4]. The lowest cost is 2 (index 0). The total cost = 2 + 2 = 4.
The total hiring cost is 4.


Constraints:

1 <= costs.length <= 105
1 <= costs[i] <= 105
1 <= k, candidates <= costs.length
 */
public class TotalCosttoHireKWorkers {
    /*
        🔍 Explanation
        You maintain two min-heaps to always get the cheapest available worker from either side of the array.

        You only add up to 'candidates' elements from each end into the heaps to keep the scope limited.

        Each round, you:
        - Ensure both heaps are filled.
        - Pick the minimum from either heap.
        - Update total cost and continue hiring until 'k' workers are hired.

        ⏱ Time Complexity
        Operation                        Time
        --------------------------------------
        Initial fill of heaps           O(candidates * log candidates) at most
        For each of k hires             O(log candidates) for insertion and poll
        Total                           O(k * log candidates + n), where n is the number of workers in costs array
        Note: Worst-case, every element is touched at most once.

        📦 Space Complexity
        Resource                        Space
        --------------------------------------
        Heaps (minHeap1 & minHeap2)     O(candidates) each, total O(candidates)
        Total                           O(candidates)
    */
    public long totalCost(int[] costs, int k, int candidates) {

        // Min-heaps to pick minimum cost from both ends
        PriorityQueue<Integer> minHeap1 = new PriorityQueue<>();
        PriorityQueue<Integer> minHeap2 = new PriorityQueue<>();

        int i = 0; // Left pointer
        int j = costs.length - 1; // Right pointer
        long totalCost = 0; // Final result
        int hired = 0;

        while (hired < k) {
            // Fill left heap (minHeap1) from the left end
            while (minHeap1.size() < candidates && i <= j) {//i can go till j including j
                minHeap1.offer(costs[i++]);//here i increase so j  will be including i so below condition is j>=i
            }

            // Fill right heap (minHeap2) from the right end
            while (minHeap2.size() < candidates && j >= i) {
                minHeap2.offer(costs[j--]);
            }

            // Choose the cheaper cost between the two heaps
            int cost1 = minHeap1.isEmpty() ? Integer.MAX_VALUE : minHeap1.peek();//u mentioned int max so during addition it can bounce so take long result
            int cost2 = minHeap2.isEmpty() ? Integer.MAX_VALUE : minHeap2.peek();

            if (cost1 <= cost2) {
                totalCost += minHeap1.poll();
            } else {
                totalCost += minHeap2.poll();
            }

            hired++;
        }

        return totalCost;
    }
}
