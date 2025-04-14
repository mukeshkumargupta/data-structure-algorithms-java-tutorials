package com.interview.hash;

/*
 * https://leetcode.com/problems/find-the-town-judge/
 * Category: Easy, tricky
 * Related:
 * https://leetcode.com/problems/find-the-celebrity/ Medium
 * 
 * In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

 

Example 1:

Input: n = 2, trust = [[1,2]]
Output: 2
Example 2:

Input: n = 3, trust = [[1,3],[2,3]]
Output: 3
Example 3:

Input: n = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1
Example 4:

Input: n = 3, trust = [[1,2],[2,3]]
Output: -1
 */
public class FindtheTownJudge {
    private static class BruitForce {
    /*
        🔹 Approach 1: Brute Force (Check Each Person)

        🚀 Idea:
        - Iterate through each person from 1 to N.
        - Check if they are trusted by N-1 people.
        - Ensure they do not trust anyone.

        ✅ Optimizations Possible
        Checking trustsSomeone requires iterating over the trust list multiple times.

        Checking trustCount also iterates over the list multiple times.

        We can improve this using trust counters.

        ⏳ Complexity Analysis:
        - Time Complexity: O(N²) due to nested loops.
        - Space Complexity: O(1) as we use only a few integer variables.
    */

        public int findJudge(int n, int[][] trust) {
            for (int i = 1; i <= n; i++) {
                boolean trustsSomeone = false;
                int trustCount = 0;

                for (int[] relation : trust) {
                    if (relation[0] == i) {
                        trustsSomeone = true;
                        break;
                    }
                }

                if (trustsSomeone) continue;

                for (int[] relation : trust) {
                    if (relation[1] == i) {
                        trustCount++;
                    }
                }

                if (trustCount == n - 1) {
                    return i;
                }
            }

            return -1;
        }
    }

    private static class Better {
    /*
        🔹 Approach: Optimized Using In-Degree & Out-Degree (0-based Index)

        🚀 Idea:
        - Use two arrays: inDegree[] and outDegree[].
        - If someone trusts person `i`, increase inDegree[i].
        - If person `i` trusts someone, increase outDegree[i].
        - Judge must have inDegree[i] == n-1 and outDegree[i] == 0.

        ⏳ Complexity Analysis:
        - Time Complexity: O(N), single pass over trust list.
        - Space Complexity: O(N), storing inDegree & outDegree.
    */

        public int findJudge(int n, int[][] trust) {
            if (n == 1) return 1; // If only one person exists, they are the judge (0-based index).

            int[] inDegree = new int[n]; // Trust counts for people 0 to n-1
            int[] outDegree = new int[n];

            for (int[] relation : trust) {
                int a = relation[0] - 1; // Convert 1-based to 0-based
                int b = relation[1] - 1;

                outDegree[a]++; // `a` trusts someone, so can't be the judge
                inDegree[b]++; // `b` is trusted
            }

            for (int i = 0; i < n; i++) { // Iterate from 0 to n-1
                if (inDegree[i] == n - 1 && outDegree[i] == 0) {
                    return i+1; // Since it is 0 based index
                }
            }

            return -1; // No judge found
        }
    }

    private static class Optimised {
    /*
        🔹 Approach 3: Optimized Using Trust Count Array (0-Based Index)

        🚀 Idea:
        - Maintain a single trustCount[] array.
        - If A trusts B: Decrease trustCount[A], Increase trustCount[B].
        - The judge should have trustCount[i] == n-1.

        ⏳ Complexity Analysis:
        - Time Complexity: O(N), since we traverse the trust array once.
        - Space Complexity: O(N), storing trustCount[].
    */

        public int findJudge(int n, int[][] trust) {
            if (n == 1) return 1; // If only one person exists, they are the judge.

            int[] trustCount = new int[n];

            for (int[] relation : trust) {
                int a = relation[0] -1 ; // Person who trusts
                int b = relation[1] -1; // Person being trusted

                trustCount[a]--; // A trusts someone, so can't be a judge
                trustCount[b]++; // B gains trust
            }

            for (int i = 0; i < n; i++) {
                if (trustCount[i] == n - 1) {
                    return i + 1; // Found the judge
                }
            }

            return -1; // No judge found
        }
    }
    
}
