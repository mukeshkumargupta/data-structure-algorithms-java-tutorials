package com.interview.tree;

/*
 * https://leetcode.com/problems/merge-two-binary-trees/
 * Category: Easy
 * Related: 
 * https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree/ Medium
 * 
 * https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/ Medium
 * https://leetcode.com/problems/binary-search-tree-iterator-ii/ Medium
 */
public class MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) { //65% runtime
        if (root1 == null && root2 == null) { //if both are null
            return null;
        }
        
        int sum = 0;
        TreeNode root = null;
        if (root1 != null && root2 != null) {
            sum = root1.val + root2.val;
            root = new TreeNode(sum);
            root.left = mergeTrees(root1.left, root2.left);
            root.right = mergeTrees(root1.right, root2.right);
        } else if (root1 != null) {
            sum = root1.val;
            root = new TreeNode(sum);
            root.left = mergeTrees(root1.left, null);
            root.right = mergeTrees(root1.right, null);
        } else {
            sum = root2.val;
            root = new TreeNode(sum);
            root.left = mergeTrees(null, root2.left);
            root.right = mergeTrees(null, root2.right);
        }
        return root;
    }
}
}
