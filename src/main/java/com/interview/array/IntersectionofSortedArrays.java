package com.interview.array;

import java.util.ArrayList;

/*
https://www.naukri.com/code360/problems/intersection-of-2-arrays_1082149?leftPanelTabValue=SUBMISSION
https://www.youtube.com/watch?v=wvcQg43_V8U&list=PLgUwDviBIf0rENwdL0nEH0uGom9no0nyB&index=2
Category: Easy

Problem Statement:
You are given two arrays 'A' and 'B' of size 'N' and 'M' respectively. Both these arrays are sorted in non-decreasing order.
You have to find the intersection of these two arrays.

Intersection of two arrays is an array that consists of all the common elements occurring in both arrays.

Note:
1. The length of each array is greater than zero.
2. Both the arrays are sorted in non-decreasing order.
3. The output should be in the order of elements that occur in the original arrays.
4. If there is no intersection present then return an empty array.

Constraints:
1 <= T <= 100
1 <= N, M <= 10^4
0 <= A[i] <= 10^5
0 <= B[i] <= 10^5

Time Limit: 1 sec

Sample Input 1:
2
6 4
1 2 2 2 3 4
2 2 3 3
3 2
1 2 3
3 4

Sample Output 1:
2 2 3
3

Explanation for Sample Input 1:
For the first test case, the common elements are 2 2 3 in both the arrays, so we print it.
For the second test case, only 3 is common so we print 3.

Sample Input 2:
2
3 3
1 4 5
3 4 5
1 1
3
6

Sample Output 2:
4 5
-1
*/
public class IntersectionofSortedArrays {
    private static class BruitForce {
        /*
        🔹 Brute Force Approach
        Idea:
        Iterate through each element of A and check if it exists in B using linear search.
        If found, add it to the result and mark it as visited in B (using a boolean array or setting the value to a special marker).
        This approach takes O(N × M) time.
         */
        public static ArrayList<Integer> findArrayIntersection(ArrayList<Integer> A, int n, ArrayList<Integer> B, int m)
        {
            // Write Your Code Here.
            ArrayList<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[B.size()]; // To avoid duplicate counting

            for (int i = 0; i < A.size(); i++) {
                for (int j = 0; j < B.size(); j++) {
                    if (A.get(i).equals(B.get(j)) && !visited[j]) { // Match found
                        result.add(A.get(i));
                        visited[j] = true; // Mark as visited to avoid multiple counting
                        break; // Move to next element in A
                    }
                }
            }
            return result;
        }
    }

    /*
    ✅ Time Complexity: O(N + M)
    ✅ Space Complexity: O(1)

    🔹 Idea:
    Since both arrays are sorted, we use two pointers to traverse them efficiently.
    Move i if A[i] < B[j], move j if A[i] > B[j], else add A[i] to result and increment both.
     */
    private static class Optimized {
        public static ArrayList<Integer> findArrayIntersection(ArrayList<Integer> A, int n, ArrayList<Integer> B, int m)
        {
            // Write Your Code Here.
            ArrayList<Integer> result = new ArrayList<>();
            int i = 0, j = 0;

            while (i < A.size() && j < B.size()) {
                if (A.get(i) < B.get(j)) {
                    i++;
                } else if (A.get(i) > B.get(j)) {
                    j++;
                } else {
                    result.add(A.get(i)); // Add common element
                    i++;
                    j++;
                }
            }
            return result;
        }
    }
}
