package com.interview.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/ipo/description/?envType=study-plan-v2&envId=top-interview-150
Category: Hard, top150,
Related:
https://leetcode.com/problems/maximum-subsequence-score/ Medium
https://leetcode.com/problems/maximum-elegance-of-a-k-length-subsequence/ Hard

Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.

You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.

Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.

Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.

The answer is guaranteed to fit in a 32-bit signed integer.



Example 1:

Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
Output: 4
Explanation: Since your initial capital is 0, you can only start the project indexed 0.
After finishing it you will obtain profit 1 and your capital becomes 1.
With capital 1, you can either start the project indexed 1 or the project indexed 2.
Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
Example 2:

Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
Output: 6


Constraints:

1 <= k <= 105
0 <= w <= 109
n == profits.length
n == capital.length
1 <= n <= 105
0 <= profits[i] <= 104
0 <= capital[i] <= 109
 */
public class IPO {
    /*
    🧠 Time & Space Complexity:
    Time: O(n log n + k log n)

    O(n log n) to sort by capital

    O(k log n) for pushing/popping from the heap

    Space: O(n) for storing the heap
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        // Step 1: Store all projects as (capital, profit) pairs
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        // Step 2: Sort projects by capital required
        Arrays.sort(projects, (a, b) -> a[0] - b[0]);

        // Step 3: Max-heap to choose most profitable available project
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (a, b) -> {
            return b - a;
        });

        int i = 0;
        while (k-- > 0) {
            // Add all projects whose capital requirement <= current capital
            while (i < n && projects[i][0] <= w) {
                maxHeap.offer(projects[i][1]); // add profit
                i++;
            }

            if (maxHeap.isEmpty()) break;

            // Pick the most profitable project
            w += maxHeap.poll();
        }

        return w;
    }

/*
    Derived Questions:
    Great! The LeetCode problem IPO (502) combines greedy, priority queue (heap), and sorting.
    Here's a breakdown of the core concepts and a list of variations/derived problems based on this topic.

    🔹 Core Concepts Involved:
    - Greedy selection of the most profitable project at each step
    - Priority Queue / Max-Heap to always pick the highest profit
    - Sorting based on project capital requirements
    - Two-pointer-like scan to push projects into heap as soon as they're affordable
    - Simulation of K steps

    📘 Problems & Variations Derived From This Concept:

    1. IPO (Leetcode 502)
       - Maximize capital after selecting at most k projects.

    2. Maximize Final Capital with Deadline (Variation)
       - Each project has a deadline; can only do one project per day.
       - Pick profitable ones respecting deadline (similar to Job Scheduling Problem).

    3. Job Sequencing with Deadlines
       - Pick jobs with highest profit while meeting individual deadlines.
       🧠 Similar to IPO with time constraints.
       🔗 LeetCode 630 - Course Schedule III is another variation.

    4. Maximum Performance of a Team (Leetcode 1383)
       - Pick k workers to maximize speed * minEfficiency.
       ✅ Similar pattern:
         - Sort by one parameter
         - Maintain a heap of the other
         - Greedy + heap strategy

    5. Find K Most Profitable Projects (No Capital Constraint)
       - From a list of profits, return top K profitable ones.
       💡 Use max-heap directly.

    6. Maximize Score After N Operations (Leetcode 1799)
       - Pick best pair of numbers at each step to maximize score.
       - Brute force + memoization, but similar greedy selection idea.

    7. Sliding Window Maximum (Leetcode 239)
       - Pick max element in a sliding window — uses a max-heap or deque.

    8. Trapping Rain Water II (Leetcode 407)
       - Heap-based water level simulation — greedy + heap pattern again.

    9. Dijkstra's Algorithm
       - Although not directly about profit, uses greedy + priority queue to find the shortest path.

    10. K-th Largest Elements / Streams
        - Constantly add numbers and keep track of k largest — classic max/min heap use.

    ⚙️ Interview Insight:
    This problem and its variations test:
    - Real-world greedy simulation (like startup investments)
    - Understanding of priority queues
    - Efficient heap usage under constraints
    - Sorting and heap combo for optimization problems
    - Handling constraints smartly (capital/deadline/time)

    ✅ Summary Table:

    | Problem Type                         | Techniques Used                          |
    |--------------------------------------|-------------------------------------------|
    | Max Profit from K Projects (IPO)     | Sorting + Max-Heap + Greedy               |
    | Job Scheduling with Deadline         | Greedy + Disjoint Set / Sorting + Heap    |
    | Team Performance Maximization        | Sorting + Min-Heap + Greedy               |
    | K Largest Profits                    | Heap                                      |
    | Course Schedule III                  | Greedy + Min-Heap + Sorting               |
    | Resource Allocation with Constraints | Greedy + Heap + Simulation                |
*/
}
