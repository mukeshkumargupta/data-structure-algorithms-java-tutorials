package com.interview.twopointer;

import java.util.Arrays;

/*
 * Problem: https://leetcode.com/problems/boats-to-save-people/
 * Category: Medium, Tricky, Greedy, Two Pointers
 *
 * Related Problems:
 * - https://leetcode.com/problems/evaluate-reverse-polish-notation/ (Medium)
 * - https://leetcode.com/problems/push-dominoes/ (Medium)
 * - https://leetcode.com/problems/ways-to-make-a-fair-array/ (Medium)
 *
 * Description:
 * You are given an array `people` where `people[i]` is the weight of the ith person,
 * and an infinite number of boats where each boat can carry a maximum weight of `limit`.
 * Each boat carries at most two people at the same time, provided the sum of the weights
 * of those people does not exceed the `limit`.
 *
 * Return the minimum number of boats required to carry every person.
 *
 * Example 1:
 * Input: people = [1, 2], limit = 3
 * Output: 1
 * Explanation: 1 boat can carry (1, 2)
 *
 * Example 2:
 * Input: people = [3, 2, 2, 1], limit = 3
 * Output: 3
 * Explanation: 3 boats are needed: (1, 2), (2), and (3)
 *
 * Example 3:
 * Input: people = [3, 5, 3, 4], limit = 5
 * Output: 4
 * Explanation: 4 boats are required: (3), (3), (4), and (5)
 *
 * Constraints:
 * - 1 <= people.length <= 5 * 10^4
 * - 1 <= people[i] <= limit <= 3 * 10^4
 */
public class BoatstoSavePeople {
    /*
     * Approach 1: Brute Force
     *
     * The brute force approach involves checking all possible combinations of pairs to count how many boats are needed.
     * This is inefficient and not feasible for larger inputs.
     *
     * Explanation:
     * 1. Loop through each person in the array.
     * 2. For each person, if they haven't been used, allocate a new boat.
     * 3. Attempt to pair them with another person who hasn't been used and whose combined weight is within the limit.
     * 4. If paired, mark the second person as used.
     * 5. Continue until all people have been processed.
     *
     * Time Complexity:
     * - O(n^2) due to nested loops.
     *
     * Space Complexity:
     * - O(n) for the used array.
     */

    public static class BoatsToSavePeopleBruteForce {
        public int numRescueBoats(int[] people, int limit) {
            int n = people.length;
            int boats = 0;
            boolean[] used = new boolean[n]; // Track used people

            for (int i = 0; i < n; i++) {
                if (!used[i]) {
                    boats++; // A new boat is needed
                    used[i] = true; // Mark this person as used
                    for (int j = i + 1; j < n; j++) {
                        if (!used[j] && people[i] + people[j] <= limit) {
                            used[j] = true; // Pair found, mark as used
                            break; // Move to the next person
                        }
                    }
                }
            }

            return boats;
        }
    }

    /*
     * Approach 2: Sorting with Two-Pointer Technique (Optimal)
     *
     * This approach leverages sorting and uses two pointers to efficiently find the minimum number of boats.
     *
     * Explanation:
     * 1. Sort the Array: The weights are sorted in ascending order.
     * 2. Two Pointers: Use two pointers (left for the lightest person and right for the heaviest).
     * 3. Pairing Logic:
     *    - Check if the sum of the weights at left and right is less than or equal to the limit.
     *    - If yes, move both pointers inward (indicating they can share a boat).
     *    - If no, just decrement the right pointer (the heavier person must go alone).
     * 4. Count Boats: Each loop iteration corresponds to one boat used.
     *
     * Time Complexity:
     * - O(n log n) for sorting plus O(n) for the two-pointer traversal, resulting in an overall complexity of O(n log n).
     *
     * Space Complexity:
     * - O(1) as sorting is done in-place.
     */
    public static class BoatsToSavePeopleGreedy {
        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            int boats = 0;
            int left = 0, right = people.length - 1;

            while (left <= right) {
                boats++; // A new boat is needed
                if (people[left] + people[right] <= limit) {
                    left++; // Pair found, move the left pointer
                }
                right--; // Move the right pointer regardless
            }

            return boats;
        }

        public static void main(String[] args) {
            BoatsToSavePeopleGreedy solution = new BoatsToSavePeopleGreedy();
            int[] people = {3, 2, 2, 1};
            int limit = 3;
            System.out.println(solution.numRescueBoats(people, limit)); // Output: 3
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
