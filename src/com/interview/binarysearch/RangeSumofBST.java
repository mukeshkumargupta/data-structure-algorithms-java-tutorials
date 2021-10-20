package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/range-sum-of-bst/submissions/
 * Category: Easy
 * Related: 
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/ Medium
 * https://leetcode.com/problems/find-leaves-of-binary-tree/ Medium
 * https://leetcode.com/problems/count-nodes-equal-to-sum-of-descendants/ Medium
 */
public class RangeSumofBST {
    public void rangeSumBSTUtil(TreeNode root, int low, int high, int[] sum) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Range Sum of BST.
        if (root == null) {
            return;
        }
        //System.out.println(root.val);
        if (root.val >= low && root.val <= high) {
            sum[0] += root.val;
        }
        
        if (root.val > low) {
            rangeSumBSTUtil(root.left, low, high, sum);
        }
        
        if (root.val < high) {
            rangeSumBSTUtil(root.right, low, high, sum);
        }
        
    }
    public int rangeSumBST(TreeNode root, int low, int high) {
        int[] sum = new int[1];
        rangeSumBSTUtil(root, low, high, sum);
        return sum[0];
    }
}
