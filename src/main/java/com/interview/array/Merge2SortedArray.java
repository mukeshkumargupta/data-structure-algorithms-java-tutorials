package com.interview.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
    Problem: Union of Two Sorted Arrays
    Category: Easy
    Source:
    - Problem Link: https://www.naukri.com/code360/problems/sorted-array_6613259
    - Video Explanation: https://www.youtube.com/watch?v=wvcQg43_V8U&list=PLgUwDviBIf0rENwdL0nEH0uGom9no0nyB&index=2

    Problem Statement:
    Given two sorted arrays, ‘a’ and ‘b’, of size ‘n’ and ‘m’, respectively,
    return the union of the arrays.

    Definition of Union:
    - The union of two sorted arrays consists of all distinct elements from both arrays.
    - The final output should be sorted in ascending order.
    - The input arrays may contain duplicates, but the output array must have only unique elements.

    Example 1:
    Input:
        n = 5, m = 3
        a = [1, 2, 3, 4, 6]
        b = [2, 3, 5]
    Output:
        [1, 2, 3, 4, 5, 6]
    Explanation:
        - Common elements: [2, 3]
        - Distinct elements in ‘a’: [1, 4, 6]
        - Distinct elements in ‘b’: [5]
        - Union: [1, 2, 3, 4, 5, 6]

    Example 2:
    Input:
        n = 4, m = 3
        a = [1, 2, 3, 3]
        b = [2, 2, 4]
    Output:
        [1, 2, 3, 4]
    Explanation:
        - Common elements: [2]
        - Distinct elements in ‘a’: [1, 3]
        - Distinct elements in ‘b’: [4]
        - Union: [1, 2, 3, 4]

    Expected Time Complexity:
        O(N + M), where N and M are the sizes of arrays A and B.

    Constraints:
        1 <= n, m <= 10^5
        -10^9 <= a[i], b[i] <= 10^9

    Time Limit: 1 sec
 */
public class Merge2SortedArray {
    /*
        Approach 1: Brute Force (Using Set + Sorting)

        ✅ Time Complexity: O((M+N)log(M+N))
        ✅ Space Complexity: O(M+N)

        Idea:
        - Insert all elements from both arrays into a list.
        - Convert the list into a set to remove duplicates.
        - Convert the set back into a sorted list.
    */
    private static class BruitFoce {
        /*
            Pros:
            - Simple and easy to implement.
            - Handles duplicates automatically.

            Cons:
            - TreeSet insertion takes O(logN) time per element.
            - Overall complexity is O((M+N)log(M+N)) due to sorting.
        */
        public static List<Integer> sortedArray(int[] a, int[] b) {
            Set<Integer> set = new TreeSet<>(); // TreeSet stores unique elements in sorted order

            for (int num : a) set.add(num);
            for (int num : b) set.add(num);

            return new ArrayList<>(set); // Convert set to list
        }
    }


    /*
        Approach 2: Two-Pointer Merge (Better)

        ✅ Time Complexity: O(M + N)
        ✅ Space Complexity: O(M + N)

        Idea:
        - Use two pointers (one for each array).
        - Compare elements, insert the smaller one into the result list.
        - Skip duplicates while merging.

        Pros:
        - More efficient than the brute force approach.
        - Handles duplicates while merging.
        - Achieves linear time complexity O(M + N).

        Cons:
        - Uses extra space for the result list.

        Approach 3: Optimal (In-Place Merge - Not Possible)
        - Since we must return a new merged list and the arrays are given separately,
          an in-place merge is not possible.
        - Thus, Approach 2 (Two-Pointer Merge) is the best possible solution in O(M + N) time.

        -----------------------------
        Final Comparison:
        -----------------------------
        | Approach                | Time Complexity       | Space Complexity | Notes                         |
        |-------------------------|----------------------|-----------------|-------------------------------|
        | Brute Force (Sorting + Set) | O((M+N)log(M+N))  | O(M+N)          | Simple but uses sorting       |
        | Two-Pointer Merge (Best) | O(M+N)              | O(M+N)          | Most efficient & handles duplicates |

        ✅ Final Verdict:
        Use the Two-Pointer Merge Approach for best performance. 🚀
    */
    private static class Optimized {
        public static List<Integer> sortedArray(int[] a, int[] b) {
            List<Integer> result = new ArrayList<>();
            int i = 0, j = 0;
            int m = a.length, n = b.length;

            while (i < m && j < n) {
                if (a[i] < b[j]) {
                    if (result.isEmpty() || result.get(result.size() - 1) != a[i]) {
                        result.add(a[i]);
                    }
                    i++;
                } else if (a[i] > b[j]) {
                    if (result.isEmpty() || result.get(result.size() - 1) != b[j]) {
                        result.add(b[j]);
                    }
                    j++;
                } else { // a[i] == b[j]
                    if (result.isEmpty() || result.get(result.size() - 1) != a[i]) {
                        result.add(a[i]);
                    }
                    i++;
                    j++;
                }
            }

            while (i < m) { // Add remaining elements from a
                if (result.isEmpty() || result.get(result.size() - 1) != a[i]) {
                    result.add(a[i]);
                }
                i++;
            }

            while (j < n) { // Add remaining elements from b
                if (result.isEmpty() || result.get(result.size() - 1) != b[j]) {
                    result.add(b[j]);
                }
                j++;
            }

            return result;
        }
    }
}
