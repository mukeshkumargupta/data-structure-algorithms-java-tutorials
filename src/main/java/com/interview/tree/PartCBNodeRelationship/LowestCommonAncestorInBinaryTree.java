package com.interview.tree.PartCBNodeRelationship;

import com.interview.tree.TreeNode;

/*
 * Date 04/27/2017
 * @author Mukesh Kumar Gupta
 *
 * Find lowest common ancestor in binary tree.
 *
 * Time complexity O(n)
 * Space complexity O(h)
 * Reference: https://www.youtube.com/watch?v=13m9ZCB8gjw
 * Leetcode: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Category: Medium, Tricky, Must Do, VVImp, postorder, Facebook, FAANG
 * Related:
 * https://leetcode.com/problems/smallest-common-region/description/ Medium Locked
 * https://leetcode.com/problems/find-players-with-zero-or-one-losses/description/ Medium
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/description/ Medium Locked
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/ Medium
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/ Medium
 * https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/ Medium
 * https://leetcode.com/problems/cycle-length-queries-in-a-tree/ Hard
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: �The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).�

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.
 */
public class LowestCommonAncestorInBinaryTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;

        }
    }
    public TreeNode lca(TreeNode root, TreeNode n1, TreeNode n2){
        if(root == null){
            return null;
        }
        if(root == n1 || root == n2){
            return root;
        }
        
        TreeNode left = lca(root.left, n1, n2);
        TreeNode right = lca(root.right, n1, n2);

        if(left != null && right != null){
            return root;
        }
        if(left == null && right == null){
            return null;
        }
        return left != null ? left : right;
    }
}
