package com.interview.tree;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Category: Medium, Must Do, Fundamental
 * Note: Since search is taking time so use map here to get in order of 1
 * Related: construct all tree from inorder preorder, inorder post order, 
 * https://leetcode.com/problems/magic-squares-in-grid/ Medium
 * https://leetcode.com/problems/number-of-closed-islands/ Medium
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/ Medium  
 */
public class ConstructTreeFromInOrderPreOrder {
    
    int index = 0;
    int search(int[] inorder, int start, int end, int val) {
        int index;
        for (index = start; index <= end; index++) {
            if (val == inorder[index]) {
               break; 
            }
        }
        return index;
        
    }
    public TreeNode buildTreeUtil(int[] preorder, int[] inorder, int start, int end) {
        if (start > end) {
            return null;
        }
        int val = preorder[index++];

        //create new TreeNode
        TreeNode root = new TreeNode(val);
        int position = search(inorder, start, end, val);
        root.left = buildTreeUtil(preorder, inorder, start, position-1);
        root.right = buildTreeUtil(preorder, inorder, position+1, end);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int l = preorder.length;
        return buildTreeUtil(preorder, inorder, 0, l-1); 
    }
}
