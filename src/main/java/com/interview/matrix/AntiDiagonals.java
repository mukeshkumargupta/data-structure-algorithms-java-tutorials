package com.interview.matrix;

import java.util.*;
/*
 * 🔗 Problem Link: https://www.interviewbit.com/problems/anti-diagonals/
 * 📌 Category: Easy, Tricky,
 * Related Problems:
 * Print Diagonals of a Matrix
 *
 *
 * 📝 Problem Description:
 * Given an N*N square matrix, return an array of its anti-diagonals.
 *
 * 🔹 Example 1:
 * Input:
 *   1  2  3
 *   4  5  6
 *   7  8  9
 * Output:
 *   [
 *     [1],
 *     [2, 4],
 *     [3, 5, 7],
 *     [6, 8],
 *     [9]
 *   ]
 *
 * 🔹 Example 2:
 * Input:
 *   1  2
 *   3  4
 * Output:
 *   [
 *     [1],
 *     [2, 3],
 *     [4]
 *   ]
 *
 * 🔔 Note:
 * - Implement the given function without reading input.
 * - Do not print the output; return values as specified.
 * - For more clarity, refer to sample codes.
 */
public class AntiDiagonals {
    /*
🔹 Approach
We iterate through the matrix and place each element in its respective anti-diagonal index.

The sum of row index i and column index j gives the index of the anti-diagonal where the element belongs.

We use a list of lists to store the result.

🚀 Optimized Solution (O(N²) Time, O(N²) Space)
🔹 Complexity Analysis
Time Complexity: O(N²) → Since we iterate over each element once.

Space Complexity: O(N²) → We store all elements in the result structure.
     */
    private static class Optimized {
        public ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> A) {
            int N = A.size();
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();

            // Initialize the result list with empty lists for all anti-diagonals
            for (int i = 0; i < 2 * N - 1; i++) {
                result.add(new ArrayList<>());
            }

            // Fill anti-diagonals
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    result.get(i + j).add(A.get(i).get(j));
                }
            }

            return result;
        }
    }

    /*
    These are related questions
    1️⃣ Print Diagonals of a Matrix
🔹 Instead of anti-diagonals, print all the main diagonals of an N x M matrix.
🔹 Example:

makefile
Copy
Edit
Input:
1  2  3
4  5  6
7  8  9

Output:
[ [1], [4, 2], [7, 5, 3], [8, 6], [9] ]
🛠 Key Change: Instead of using i + j, use i - j + (N - 1) to track diagonals.
Transpose the matrix (swap A[i][j] → A[j][i]).

Apply the same pattern as anti-diagonals (use i + j as the key).

     */
    private static class DiagonalPrint {
        public ArrayList<ArrayList<Integer>> mainDiagonals(ArrayList<ArrayList<Integer>> A) {
            int N = A.size();
            int M = A.get(0).size();
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();

            // Initialize lists for diagonals
            for (int i = 0; i < N + M - 1; i++) {
                result.add(new ArrayList<>());
            }

            // Swap row and column positions and use (i + j) logic
            for (int j = 0; j < M; j++) {  // Iterate column first (swap effect)
                for (int i = 0; i < N; i++) {
                    result.get(i + j).add(A.get(i).get(j));
                }
            }

            return result;
        }
    }
    /*
    4️⃣ Diagonal Sum of a Matrix
🔹 Find the sum of elements in all anti-diagonals.
🔹 Example:

makefile
Copy
Edit
Input:
1  2  3
4  5  6
7  8  9

Output:
[1, 6, 15, 14, 9]
🛠 Key Idea: Instead of storing elements, maintain sums.
     */
    private static class AntiDiagonalUsingMap {
        public static List<List<Integer>> diagonal(List<List<Integer>> A) {
            int N = A.size();
            Map<Integer, List<Integer>> map = new HashMap<>();
            List<List<Integer>> result = new ArrayList<>();

            // Iterate through the matrix and group elements by their anti-diagonal index
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int diagonalIdx = i + j; // Key in the map

                    map.putIfAbsent(diagonalIdx, new ArrayList<>());
                    map.get(diagonalIdx).add(A.get(i).get(j));
                }
            }

            // Convert map values to list format
            for (int i = 0; i < 2 * N - 1; i++) {
                result.add(map.get(i));
            }

            return result;
        }

        // Driver Code
        public static void main(String[] args) {
            List<List<Integer>> matrix = Arrays.asList(
                    Arrays.asList(1, 2, 3),
                    Arrays.asList(4, 5, 6),
                    Arrays.asList(7, 8, 9)
            );

            List<List<Integer>> result = diagonal(matrix);
            System.out.println(result);
        }
    }
    
}
