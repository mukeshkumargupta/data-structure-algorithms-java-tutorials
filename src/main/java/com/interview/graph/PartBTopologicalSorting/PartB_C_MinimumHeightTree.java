package com.interview.graph.PartBTopologicalSorting;

import java.util.*;

/**
 * Date 03/08/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a graph representing a tree. Find all minimum height trees.
 *
 * Time complexity O(n)
 * Space complexity O(n)
 *
 * https://leetcode.com/problems/minimum-height-trees/
 */
public class PartB_C_MinimumHeightTree {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());
        }
        for (int[] edge: edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                int TreeNode = adj.get(leaf).iterator().next();
                adj.get(TreeNode).remove(leaf);
                if (adj.get(TreeNode).size() == 1) {
                    newLeaves.add(TreeNode);
                }
            }
            leaves = newLeaves;
        }

        return leaves;
    }

    public static void main(String args[]) {
        PartB_C_MinimumHeightTree mht = new PartB_C_MinimumHeightTree();
        int input[][] = {{1,0},{1,2},{1,3}};
        List<Integer> result = mht.findMinHeightTrees(4, input);
        result.forEach(r -> System.out.print(r + " "));
    }
}
