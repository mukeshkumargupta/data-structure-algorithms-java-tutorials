package com.interview.tree;
/*
 * https://leetcode.com/problems/invert-binary-tree/
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/number-of-distinct-islands/ Medium apply dfs or bfs , Locked
 * https://leetcode.com/problems/maximum-average-subtree/ Medium, VImp,  Locked, I think using postorder + prefix sum + size we can get in O(N)
 * https://leetcode.com/problems/move-sub-tree-of-n-ary-tree/ Hard
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
Memory Usage: 39.7 MB, less than 57.56% of Java online submissions for Invert Binary Tree.
         */
        if (root == null) return null;
        
        //Swap
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
