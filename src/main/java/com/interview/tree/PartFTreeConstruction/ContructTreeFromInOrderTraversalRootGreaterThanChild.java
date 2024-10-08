package com.interview.tree.PartFTreeConstruction;

import com.interview.tree.TreeNode;
import com.interview.tree.PartATreeTraversal.TreeTraversals;

/**
 * http://www.geeksforgeeks.org/construct-binary-tree-from-inorder-traversal/
 * Given inorder traversal of binary tree where every TreeNode is greater than 
 * its left and right child.
 * Test cases:
 * One two or more TreeNodes in the tree
 */
public class ContructTreeFromInOrderTraversalRootGreaterThanChild {

    public TreeNode createTree(int inorder[]){
        return createTree(inorder,0,inorder.length-1);
    }
    private TreeNode createTree(int inorder[],int low,int high)
    {
        if(low > high){
            return null;
        }
        int i;
        int maxIndex = low;
        for(i=low ; i <= high; i++){
            if(inorder[maxIndex] > inorder[i]){
                maxIndex = i;
            }
        }
        TreeNode root = TreeNode.newTreeNode(inorder[maxIndex]);
        root.left = createTree(inorder,low,maxIndex-1);
        root.right = createTree(inorder,maxIndex+1,high);
        return root;
    }
    
    public static void main(String args[]){
        int inorder[] = {9,15,25,6,18,-1,3,-3};
        ContructTreeFromInOrderTraversalRootGreaterThanChild tf = new ContructTreeFromInOrderTraversalRootGreaterThanChild();
        TreeNode root = tf.createTree(inorder);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
    }
}
