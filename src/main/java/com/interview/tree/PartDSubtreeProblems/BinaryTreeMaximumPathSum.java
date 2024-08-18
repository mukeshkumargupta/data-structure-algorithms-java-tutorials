package com.interview.tree.PartDSubtreeProblems;


import com.interview.tree.TreeNode;

/**
 * Date 03/22/2017
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * Category: Hard, Tricky, Top150, postorder, Must Do
 * Derived question tell which root, you are getting max path so make dp array to save root.
 * Related:
 * https://leetcode.com/problems/path-sum-iv/ Medium Locked
 * https://leetcode.com/problems/longest-univalue-path/ Medium VVImp
 * Given a binary tree, find the maximum path sum. For this problem, a path is defined as any sequence of TreeNodes
 * from some starting TreeNode to any TreeNode in the tree along the parent-child connections.
 * 
 * Time complexity O(n)
 * Space complexity depends on depth of tree.
 * https://www.youtube.com/watch?v=WszrfSwMz58 Best explanation
 * Reference: https://www.youtube.com/watch?v=TO5zsKtc1Ic
 *
 *
 *
 * 
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

 

Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
 */
public class BinaryTreeMaximumPathSum {
    public int maxPathSumUtil(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int leftSum = Math.max(0, maxPathSumUtil(root.left, max));
        int rightSum = Math.max(0, maxPathSumUtil(root.right, max));//To consider this case [2,-1]
        //int leftSum = maxPathSumUtil(root.left, max);
        //int rightSum = maxPathSumUtil(root.right, max);
        max[0] = Math.max(max[0], root.val + leftSum + rightSum);
        return root.val + Math.max(leftSum, rightSum);
        
    }
    
    public int maxPathSum(TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 86.10% of Java online submissions for Binary Tree Maximum Path Sum.
Memory Usage: 43.9 MB, less than 85.73% of Java online submissions for Binary Tree Maximum Path Sum.
         */
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPathSumUtil(root, max);
        return max[0]; 
    }
}
