package com.interview.sort.PartCPriorityQueuePattern;

import java.util.*;

/*
 * Problem: Task Scheduler
 * Link: https://leetcode.com/problems/task-scheduler/
 *
 * Difficulty: Medium | Asked in: Facebook | Tricky Problem
 * Related Problems:
 * - Rearrange String K Distance Apart (Hard) - https://leetcode.com/problems/rearrange-string-k-distance-apart/
 * - Reorganize String (Medium) - https://leetcode.com/problems/reorganize-string/
 * - Maximum Number of Weeks for Which You Can Work (Medium) - https://leetcode.com/problems/maximum-number-of-weeks-for-which-you-can-work/
 * https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs-ii/description/ Medium
 * https://leetcode.com/problems/task-scheduler-ii/description/ Medium
 *
 * 1. Task Scheduling & CPU Scheduling
621. Task Scheduler 🔥 (This problem itself)

358. Rearrange String k Distance Apart

767. Reorganize String

1054. Distant Barcodes

1405. Longest Happy String

1668. Maximum Repeating Substring

2. Greedy + Priority Queue (Max Heap)
253. Meeting Rooms II

759. Employee Free Time

621. Task Scheduler (same problem)

1353. Maximum Number of Events That Can Be Attended

1481. Least Number of Unique Integers after k Removals

1834. Single-Threaded CPU 🔥 (CPU scheduling problem using Priority Queue)

846. Hand of Straights (Heap + Frequency Map)

3. Job Scheduling & Sequencing
630. Course Schedule III

502. IPO

1235. Maximum Profit in Job Scheduling

1005. Maximize Sum Of Array After K Negations

1801. Number of Orders in the Backlog

4. Interval Scheduling & Merging
56. Merge Intervals

435. Non-overlapping Intervals

452. Minimum Number of Arrows to Burst Balloons

981. Time Based Key-Value Store (Heap-based scheduling)
 *
 *
 * Description:
 * Given an array of tasks where each character represents a different task, the CPU must execute them in any order.
 * Each task requires exactly one unit of time to complete.
 * The CPU can either execute a task or remain idle for a unit of time.
 *
 * A non-negative integer `n` represents the cooldown period between two identical tasks.
 * This means that there must be at least `n` units of time between two occurrences of the same task.
 *
 * Return the minimum number of time units required to complete all tasks.
 *
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A possible sequence is:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There must be at least 2 units of time between any two identical tasks.
 *
 * Example 2:
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: Since `n = 0`, any permutation of the tasks will work, such as:
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 *
 * Example 3:
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation: A possible sequence is:
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 * Constraints:
 * - 1 <= tasks.length <= 10^4
 * - tasks[i] is an uppercase English letter.
 * - 0 <= n <= 100
 */
public class A_A_TaskScheduler {
    /*
     * Brute Force Approach (Simulation using Priority Queue/Sorting)
     *
     * Approach:
     * 1. Count the frequency of each task.
     * 2. Process tasks in descending order of frequency.
     * 3. Simulate the scheduling using a PriorityQueue (max-heap) or Sorting.
     * 4. Insert tasks into a queue with cooldown tracking.
     *
     * Steps:
     * - Create a frequency map of tasks.
     * - Push all tasks into a max heap.
     * - Pop the most frequent tasks and schedule them, keeping track of cooldown.
     * - If a task is still in cooldown, wait (idle) until it can be scheduled.
     * - Repeat until all tasks are scheduled.
     *
     * Time Complexity:
     * - Building frequency map: O(N)
     * - Sorting (Heap operations): O(N log N)
     * - Processing tasks: O(N)
     * - Overall: O(N log N) due to heap operations.
     *
     * Space Complexity:
     * - Heap storage: O(26) (since max 26 tasks)
     * - Auxiliary space (cooldown tracking): O(N)
     * - Worst-case: O(N)
     *
     * Dry Run of the Brute Force Approach (Using Priority Queue)
     *
     * Example 1:
     * Input: tasks = ["A", "A", "A", "B", "B", "B"], n = 2
     *
     * Step 1: Frequency Count
     * Task  Frequency
     * A     3
     * B     3
     *
     * Step 2: Use Max Heap (Priority Queue)
     * Max Heap: [3, 3]  // A and B, both with frequency 3
     *
     * Step 3: Scheduling (Cycle = n + 1 = 3 slots)
     * Cycle No.  Selected Tasks from Heap   Remaining Frequencies   Time
     * 1 (0-2)    A, B, Idle                 (A=2, B=2)             3
     * 2 (3-5)    A, B, Idle                 (A=1, B=1)             6
     * 3 (6-7)    A, B                        (A=0, B=0)             8
     *
     * Final Output:
     * Total intervals taken = 8
     *
     * Example 2:
     * Input: tasks = ["A", "A", "A", "B", "B"], n = 3
     *
     * Step 1: Frequency Count
     * Task  Frequency
     * A     3
     * B     2
     *
     * Step 2: Use Max Heap (Priority Queue)
     * Max Heap: [A:3, B:2]
     * Cycle length = n + 1 = 4 slots
     *
     * Step 3: Scheduling Execution (Cycle-based execution)
     * Cycle No.  Selected Tasks from Heap   Remaining Frequencies   Scheduled Tasks   Time
     * 1 (0-3)    A, B, Idle, Idle           (A=2, B=1)             A B _ _           4
     * 2 (4-7)    A, B, Idle, Idle           (A=1, B=0)             A B _ _ A B _ _   8
     * 3 (8)      A                           (A=0, B=0)             A B _ _ A B _ _ A 9
     *
     * Final Output:
     * Total time intervals taken = 9
     *
     * Final Observations:
     * - Every cycle consists of n+1 slots (tasks + idle).
     * - When tasks run out, we remove the idle slots.
     * - The total time required is calculated based on heap scheduling.
     *
     * Brute Force Complexity Recap:
     * - Time Complexity: O(N log N) due to heap operations.
     * - Space Complexity: O(N) due to task storage.
     */

    private static class BruitAndBetter {
        public int leastInterval(char[] tasks, int n) {
            Map<Character, Integer> freqMap = new HashMap<>();
            for (char task : tasks) {
                freqMap.put(task, freqMap.getOrDefault(task, 0) + 1);
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            maxHeap.addAll(freqMap.values());

            int time = 0;
            while (!maxHeap.isEmpty()) {
                List<Integer> tempList = new ArrayList<>();
                int cycle = n + 1; // A full cycle of execution

                // Process the most frequent tasks first
                for (int i = 0; i < cycle; i++) {
                    if (!maxHeap.isEmpty()) {
                        tempList.add(maxHeap.poll() - 1);
                    }
                }

                // Add back remaining tasks to the heap
                for (int count : tempList) {
                    if (count > 0) maxHeap.offer(count);
                }

                time += maxHeap.isEmpty() ? tempList.size() : cycle;
            }

            return time;
        }

        public static void main(String[] args) {
            BruitAndBetter ts = new BruitAndBetter();
            System.out.println(ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2)); // Output: 8
        }
    }

    /*
         Optimized Mathematical Approach (Greedy Formula-Based Solution)

    Key Observations:
    - The most frequent task determines the minimum execution time.
    - If there are `maxFreq` instances of the most frequent task, they must be spaced apart by `n` intervals.
    - The total minimum time is driven by the formula:

        (maxFreq - 1) × (n + 1) + count of maxFreq tasks

    - If there are `maxFreq` "A"s, we place them first, leaving gaps for other tasks or idle slots.

    Time Complexity:
    - Frequency Calculation: O(N)
    - Sorting: O(26 log 26) = O(1)
    - Final Computation: O(1)
    - Overall: O(N)

    Space Complexity:
    - Only `int[26]` array: O(1)

    🔥 Comparison of Approaches:

    | Approach                     | Time Complexity  | Space Complexity | Notes                                      |
    |------------------------------|-----------------|------------------|--------------------------------------------|
    | Brute Force (Priority Queue) | O(N log N)      | O(N)             | Simulates scheduling, uses a heap         |
    | Optimized Greedy (Formula)   | O(N)            | O(1)             | Directly computes the answer using a formula |

    */
    private static class Optimal {
        public int leastInterval(char[] tasks, int n) {
            int[] freq = new int[26];
            for (char task : tasks) {
                freq[task - 'A']++;
            }

            Arrays.sort(freq);
            int maxFreq = freq[25];
            int idleSlots = (maxFreq - 1) * (n + 1);

            int maxCount = 1;
            for (int i = 24; i >= 0; i--) {
                if (freq[i] == maxFreq) maxCount++;
                else break;
            }

            return Math.max(tasks.length, idleSlots + maxCount);
        }

        public static void main(String[] args) {
            Optimal ts = new Optimal();
            System.out.println(ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2)); // Output: 8
        }
    }
    
}
