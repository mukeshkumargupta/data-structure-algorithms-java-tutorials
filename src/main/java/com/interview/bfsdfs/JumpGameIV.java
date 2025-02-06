package com.interview.bfsdfs;

import java.util.*;

/*
 * https://leetcode.com/problems/jump-game-iv/
 * Category: Hard, Must Do, Fundamental
 * https://www.youtube.com/watch?v=JYbU8RH1OSQ
 * Related: https://leetcode.com/problems/jump-game-vii/
 * https://leetcode.com/problems/longest-uncommon-subsequence-ii/ Medium Bad
 * https://leetcode.com/problems/search-suggestions-system/ VVImp
 * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/ Medium VVImp
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * Related question: It can asked min jump required to reach at given index, however in this question it is asked for last index

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You do not need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 

Constraints:

1 <= arr.length <= 5 * 104
-108 <= arr[i] <= 108
 */
public class JumpGameIV {
    /*
        Optimized BFS Approach
        Explanation:
        Since the problem asks for the minimum number of jumps, BFS is a natural fit. BFS explores level by level and guarantees the shortest path is found first.
        For each index, you can jump to:
        The previous index (i - 1).
        The next index (i + 1).
        All indices with the same value as arr[i].
        To optimize:
        Use a Map to group indices by their values. This allows you to jump to all indices with the same value efficiently.
        Once a value is processed, remove its indices from the map to avoid revisiting.

        You're correct—DFS in its naive form often does not work properly for the "Jump Game IV" problem because it can revisit indices excessively or get stuck in recursion depth issues. Additionally, finding the minimum number of jumps is naturally a BFS-type problem since BFS explores all nodes at the current "level" before moving to the next, ensuring the shortest path is found.
     */

    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;

        // Map to store indices for each value
        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueToIndices.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        // BFS setup
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0); // Start at index 0
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                // If we reach the last index
                if (curr == n - 1) return steps;

                // Jump to the previous index
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    queue.offer(curr - 1);
                    visited[curr - 1] = true;
                }

                // Jump to the next index
                if (curr + 1 < n && !visited[curr + 1]) {
                    queue.offer(curr + 1);
                    visited[curr + 1] = true;
                }

                // Jump to all indices with the same value
                if (valueToIndices.containsKey(arr[curr])) {
                    for (int next : valueToIndices.get(arr[curr])) {
                        if (!visited[next]) {
                            queue.offer(next);
                            visited[next] = true;
                        }
                    }
                    // Remove the value from the map to avoid revisiting
                    valueToIndices.remove(arr[curr]);
                }
            }
            steps++;
        }

        return -1; // If no path is found (shouldn't happen)
    }
    
}
