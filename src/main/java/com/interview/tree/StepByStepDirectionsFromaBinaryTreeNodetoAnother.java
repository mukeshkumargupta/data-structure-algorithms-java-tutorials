package com.interview.tree;

import java.util.*;
/*
 * Problem: https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
 * Category: Medium
 *
 * Related Concepts:
 * - Similar to the Knight's Tour problem.
 * - Can be solved using BFS after building a parent lookup.
 * - Can also be solved using DFS + Backtracking (same pattern as "All Paths" problems).
 *
 * Problem Statement:
 * ------------------
 * Given the root of a binary tree with `n` nodes, each uniquely assigned a value from `1` to `n`,
 * and two integers `startValue` (source node `s`) and `destValue` (destination node `t`),
 * find the shortest path from node `s` to node `t`.
 *
 * Directions:
 * - 'L' → Move to the left child.
 * - 'R' → Move to the right child.
 * - 'U' → Move to the parent node.
 *
 * Return the step-by-step directions as a string representing the shortest path.
 *
 * Example 1:
 * ----------
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is:
 *   3 → 1 → 5 → 2 → 6
 *
 * Example 2:
 * ----------
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is:
 *   2 → 1
 *
 * Constraints:
 * ------------
 * - The number of nodes in the tree is `n`.
 * - 2 <= n <= 10^5
 * - 1 <= Node.val <= n
 * - All the values in the tree are unique.
 * - 1 <= startValue, destValue <= n
 * - startValue != destValue
 */
public class StepByStepDirectionsFromaBinaryTreeNodetoAnother {
    class NodeDetails {
        TreeNode node;
        String path;
        NodeDetails(TreeNode node , String path ) {
            this.node = node;
            this.path = path;
        }
    }
    public String getDirections(TreeNode root, int start, int end) {
        //startVal = start;
        Map<TreeNode, TreeNode> parentsLookup = new HashMap<>();
        TreeNode[] startNode = new TreeNode[1];
        buildParentLookup(parentsLookup, root, null, startNode, start);
        Queue<NodeDetails> q = new LinkedList<>();
        q.add(new NodeDetails(startNode[0], ""));
        Set<Integer> visited = new HashSet<>();
        StringBuilder result = new StringBuilder();
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i=0;i<size;i++) {
                
                NodeDetails curNodeNetails = q.remove();
                TreeNode curNode = curNodeNetails.node;
                String curPath = curNodeNetails.path;

                visited.add(curNode.val);
                if (curNode.val == end) {
                    //return curStr;
                    return curPath;
                }
                if (parentsLookup.get(curNode) != null && !visited.contains(parentsLookup.get(curNode).val)) {
                    q.add(new NodeDetails(parentsLookup.get(curNode), curPath + "U"));
                }
                if (curNode.left != null && !visited.contains(curNode.left.val)) {
                    q.add(new NodeDetails(curNode.left, curPath + "L"));
                }
                if (curNode.right != null && !visited.contains(curNode.right.val)) {
                     q.add(new NodeDetails(curNode.right, curPath + "R"));
                }
            }
        }
        return "";
    }
    
    public void buildParentLookup(Map<TreeNode, TreeNode> parentsLookup, TreeNode root, TreeNode par, TreeNode[] startNode, int startVal) {
        if (root.val == startVal) {
            startNode[0] = root;
        }
        parentsLookup.put(root, par);
        //DFS
        if (root.left != null) {
            buildParentLookup(parentsLookup, root.left, root, startNode, startVal);
        }
        if (root.right != null) {
            buildParentLookup(parentsLookup, root.right, root, startNode, startVal);
        }        
    }       
}

