package com.interview.tree.PartBPathProblems;
/*
 * Reference: https://leetcode.com/problems/path-sum/
 * Category; Easy
 * Related:
 * https://leetcode.com/problems/path-sum-ii/ Medium
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/ Medium
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/ Medium
 * https://leetcode.com/problems/path-sum-iii/ Medium
 * https://leetcode.com/problems/path-sum-iv/ Medium Locked
 */
public class A_A_PathSum {

    public boolean hasPathSum(A_A_PathSumII.TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        //Try in left part first if not found then try in right part
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
