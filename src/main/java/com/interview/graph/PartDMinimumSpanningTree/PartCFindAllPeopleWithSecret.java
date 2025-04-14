package com.interview.graph.PartDMinimumSpanningTree;

import java.util.*;

/*
https://leetcode.com/problems/find-all-people-with-secret/description/
https://www.youtube.com/watch?v=zOeI9i8KUPw
Category: Hard, Facebook, FAANG

Related:
https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/description/ Hard

You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.

Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.

The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.

Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.



Example 1:

Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
Output: [0,1,2,3,5]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 5, person 1 shares the secret with person 2.
At time 8, person 2 shares the secret with person 3.
At time 10, person 1 shares the secret with person 5.​​​​
Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
Example 2:

Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
Output: [0,1,3]
Explanation:
At time 0, person 0 shares the secret with person 3.
At time 2, neither person 1 nor person 2 know the secret.
At time 3, person 3 shares the secret with person 0 and person 1.
Thus, people 0, 1, and 3 know the secret after all the meetings.
Example 3:

Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
Output: [0,1,2,3,4]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
Note that person 2 can share the secret at the same time as receiving it.
At time 2, person 3 shares the secret with person 4.
Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.


Constraints:

2 <= n <= 105
1 <= meetings.length <= 105
meetings[i].length == 3
0 <= xi, yi <= n - 1
xi != yi
1 <= timei <= 105
1 <= firstPerson <= n - 1
 */
public class PartCFindAllPeopleWithSecret {
    /*
        ✅ Problem: 2092. Find All People With Secret
        🔗 Link: https://leetcode.com/problems/find-all-people-with-secret/
        📂 Category: Graph, Union-Find, Medium

        🚨 Problem Summary:
        - You are given `n` people labeled from 0 to n-1.
        - Person 0 knows a secret initially, and person `firstPerson` learns it at time 0.
        - You're given a list of meetings as [x, y, time].
        - If either person knows the secret during a meeting, the other person learns it too.
        - Goal: Return all people who will eventually know the secret.

        ⚙️ Approach: Union-Find (Disjoint Set) with Path Compression and Union by Rank

        1️⃣ Sort Meetings by Time:
           - Organize meetings in chronological order using Arrays.sort and a lambda comparator.

        2️⃣ Initialize Union-Find:
           - Create a Disjoint Set for `n` people.
           - Union person `0` and `firstPerson` immediately since both know the secret.

        3️⃣ Process Meetings Grouped by Time:
           - For each unique timestamp, gather all meetings that happen at that time.
           - Temporarily union the people involved in these meetings.

        4️⃣ Validate Knowledge of Secret:
           - After processing a time group, check who is connected to person 0.
           - Disconnect people not connected to 0 to avoid wrong propagation in future meetings.

        5️⃣ Final Result:
           - After all meetings, people still connected to person 0 are those who know the secret.

        🧠 Why This Works:
        - ⏱ Efficient: Processes only necessary connections per time group.
        - 🔗 Accurate: Resets connections after each timestamp to prevent leakage of information.
        - 📦 Space: O(n) for parent and rank arrays.
        - ⌛ Time: O(M log M + M * α(N)) where M = number of meetings, α is inverse Ackermann function (nearly constant).

        💡 Key Insight:
        - Each time group is processed independently with temporary unions.
        - Only people in the same component as person 0 retain the secret after each time group.

        🧪 Example:
        Input: n = 6, firstPerson = 1,
               meetings = [[1,2,5],[2,3,8],[1,5,10]]
        Output: [0,1,2,3,5]
     */
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // Sort meetings based on the time they occur
        Arrays.sort(meetings, (a, b) -> {
            return a[2] - b[2];
        });

        // Initialize Union-Find structure
        UnionFind uf = new UnionFind(n);
        uf.union(0, firstPerson);

        int i = 0;
        while (i < meetings.length) {
            int currentTime = meetings[i][2];
            List<int[]> currentMeetings = new ArrayList<>();

            // Gather all meetings happening at the current time
            while (i < meetings.length && meetings[i][2] == currentTime) {
                currentMeetings.add(meetings[i]);
                i++;
            }

            // Union all people involved in the current time's meetings
            for (int[] meeting : currentMeetings) {
                uf.union(meeting[0], meeting[1]);
            }

            // Check which people are connected to person 0
            Set<Integer> peopleInvolved = new HashSet<>();
            for (int[] meeting : currentMeetings) {
                peopleInvolved.add(meeting[0]);
                peopleInvolved.add(meeting[1]);
            }

            // Disconnect people not connected to person 0
            for (int person : peopleInvolved) {
                if (!uf.connected(person, 0)) {
                    uf.reset(person);
                }
            }
        }

        // Collect all people who are connected to person 0
        List<Integer> result = new ArrayList<>();
        for (int person = 0; person < n; person++) {
            if (uf.connected(person, 0)) {
                result.add(person);
            }
        }

        return result;
    }

    // Union-Find with path compression and union by rank
    private static class UnionFind {
        private int[] parent;
        private int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]); // Path compression
            }
            return parent[u];
        }

        void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                // Union by rank
                if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }

        boolean connected(int u, int v) {
            return find(u) == find(v);
        }

        void reset(int u) {
            parent[u] = u;
            rank[u] = 0;
        }
    }
}
