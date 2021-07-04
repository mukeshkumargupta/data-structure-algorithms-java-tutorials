package com.interview.tree;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Category: Medium, Must Know
 * Related: construct all tree from inorder preorder, inorder post order,   
 */
public class ConstructTreeFromInOrderPreOrder {
    
    int index = 0;
    int search(int[] inorder, int start, int end, int data) {
        int index;
        for (index = start; index <= end; index++) {
            if (data == inorder[index]) {
               break; 
            }
        }
        return index;
        
    }
    public TreeNode buildTreeUtil(int[] preorder, int[] inorder, int start, int end) {
        if (start > end) {
            return null;
        }
        int data = preorder[index++];

        //create new node
        TreeNode root = new TreeNode(data);
        int position = search(inorder, start, end, data);
        root.left = buildTreeUtil(preorder, inorder, start, position-1);
        root.right = buildTreeUtil(preorder, inorder, position+1, end);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int l = preorder.length;
        return buildTreeUtil(preorder, inorder, 0, l-1); 
    }
}
