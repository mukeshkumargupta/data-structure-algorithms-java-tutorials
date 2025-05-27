package com.interview.graph.PartGGraphColoring;

import java.util.*;

/*
 * https://leetcode.com/problems/possible-bipartition/
 * Category: Medium, Must Do
 * https://www.youtube.com/watch?v=0ACfAqs8mm0&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=34
 * Related:
 * https://leetcode.com/problems/serialize-and-deserialize-bst/ Medium
 * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/ Medium
 * https://leetcode.com/problems/find-center-of-star-graph/ Easy
 * https://leetcode.com/problems/mini-parser/ Medium Bad
 * https://leetcode.com/problems/loud-and-rich/ Medium Imp
 * https://leetcode.com/problems/time-needed-to-inform-all-employees/ Medium

 */
public class B_PossibleBipartition {
    /*Explanation:
DFS Approach: The graph is represented with 0-based indexing. For each node (person), we try to color it and its connected nodes using two colors. If a conflict occurs, the graph is not bipartite, and the function returns false.

BFS Approach: This approach also uses 0-based indexing. We traverse the graph level by level, coloring nodes alternately. If a conflict is found where two connected nodes have the same color, the function returns false.

These solutions ensure consistency with 0-based indexing, making it easier to understand and apply within problems that use this convention.
*/
    public boolean possibleBipartitionDfs(int n, int[][] dislikes) {
        int[] colors = new int[n];  // 0: uncolored, 1: color A, -1: color B
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] dislike : dislikes) {
            adjacencyList.get(dislike[0] - 1).add(dislike[1] - 1);
            adjacencyList.get(dislike[1] - 1).add(dislike[0] - 1);
        }

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !dfs(adjacencyList, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> adjacencyList, int[] colors, int node, int color) {
        colors[node] = color;

        for (int neighbor : adjacencyList.get(node)) {
            if (colors[neighbor] == 0) {  // If the neighbor is uncolored
                if (!dfs(adjacencyList, colors, neighbor, -color)) {
                    return false;
                }
            } else if (colors[neighbor] == color) {  // If the neighbor has the same color
                return false;
            }
        }
        return true;
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] colors = new int[n];  // 0: uncolored, 1: color A, -1: color B
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] dislike : dislikes) {
            adjacencyList.get(dislike[0] - 1).add(dislike[1] - 1);
            adjacencyList.get(dislike[1] - 1).add(dislike[0] - 1);
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {  // Not yet visited
                queue.offer(i);
                colors[i] = 1;  // Start coloring with color A

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    for (int neighbor : adjacencyList.get(node)) {
                        if (colors[neighbor] == 0) {  // If the neighbor is uncolored
                            queue.offer(neighbor);
                            colors[neighbor] = -colors[node];  // Color with the opposite color
                        } else if (colors[neighbor] == colors[node]) {  // If the neighbor has the same color
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] input = {{1, 2}, {1, 3}, {2, 3}};
        B_PossibleBipartition BPossibleBipartition = new B_PossibleBipartition();
        BPossibleBipartition.possibleBipartition(3, input);
    }

}
