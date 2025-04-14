package com.interview.tree.PartBPathProblems;
import com.interview.tree.TreeNode;

import java.util.*;
/*
 * Reference:https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * Category: Medium, Must Do, Facebook, FAANG
 * https://www.youtube.com/watch?v=i9ORlEy6EsI&t=1s
 * Video: https://www.youtube.com/watch?v=nPtARJ2cYrg
 * Related: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/ Medium, treeconstruction, VVImp
 * https://leetcode.com/problems/check-completeness-of-a-binary-tree/ Medium, VVImp
 * https://leetcode.com/problems/two-sum-bsts/ Medium, Locked
 */

/**
 * Definition for a binary tree TreeNode.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class AllNodesDistanceKInBinaryTree {
    /*
     * Runtime: 19 ms, faster than 46.35% of Java online submissions for All Nodes Distance K in Binary Tree.
Memory Usage: 43.3 MB, less than 25.69% of Java online submissions for All Nodes Distance K in Binary Tree.
Time Complexity: O(N)O(N), where NN is the number of nodes in the given tree.

Space Complexity: O(N)O(N).
     */
    Map<TreeNode, TreeNode> lookup = new HashMap<>();
    int level = 0;
    
    private void buidLookUP(TreeNode root) {
        if (root == null) {
            return;
        }
        
        if (root.left != null) {
            lookup.put(root.left, root);
            buidLookUP(root.left);
        }
        
        if (root.right != null) {
            lookup.put(root.right, root);
            buidLookUP(root.right);
        }
    }
    

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();

        buidLookUP(root);
        //Now do bfs
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        q.add(target);
        visited.add(target);
        
        while (!q.isEmpty()) {
            int size = q.size();
            if (level == k) {
                while(!q.isEmpty()) {
                    result.add(q.remove().val);
                }
                return result;
                
            }
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = q.remove();
                if (currentNode.left != null && !visited.contains(currentNode.left)) {
                    q.add(currentNode.left);
                    visited.add(currentNode.left);
                }
                if (currentNode.right != null && !visited.contains(currentNode.right)) {
                    q.add(currentNode.right);
                    visited.add(currentNode.right);
                }
                if (lookup.containsKey(currentNode) && !visited.contains(lookup.get(currentNode))) {
                    q.add(lookup.get(currentNode));
                    visited.add(lookup.get(currentNode));
                }
            }
            level++;
        }
        return result;
    }
}
