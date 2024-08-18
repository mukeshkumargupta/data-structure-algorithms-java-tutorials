package com.interview.tree.PartFTreeConstruction;

import com.interview.tree.TreeNode;
import com.interview.tree.PartATreeTraversal.TreeTraversals;

/**
 * http://www.geeksforgeeks.org/full-and-complete-binary-tree-from-given-preorder-and-postorder-traversals/
 * Full tree is a tree with all TreeNodes with either 0 or 2 child. Never has 1 child.
 * Test cases
 * Empty tree
 * Tree with big on left side
 * Tree with big on right side
 */
public class ConstructFullTreeFromPreOrderPostOrder {

    public TreeNode constructTree(int preorder[], int postorder[]){
    
        return constructTree(preorder, postorder, 0, postorder.length-2, 0);
        
    }
    
    private TreeNode constructTree(int preorder[],int postorder[],int low,int high,int index){

        if(low > high || index >= preorder.length-1){
            TreeNode TreeNode = new TreeNode();
            TreeNode.val = preorder[index];
            return TreeNode;
        }
        
        TreeNode TreeNode = new TreeNode();
        TreeNode.val = preorder[index];
        int i=0;
        for(i=low; i <= high; i++){
            if(preorder[index+1] == postorder[i]){
                break;
            }
        }
        TreeNode.left = constructTree(preorder, postorder, low, i-1, index+1);
        TreeNode.right = constructTree(preorder, postorder, i+1, high-1, index + i-low+2);
        return TreeNode;
    }
    
    public static void main(String args[]){
        ConstructFullTreeFromPreOrderPostOrder cft = new ConstructFullTreeFromPreOrderPostOrder();
        int preorder[] = {1,2,3,6,7,8,9};
        int postorder[] = {2,6,8,9,7,3,1};
        TreeNode root = cft.constructTree(preorder, postorder);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        tt.preOrder(root);
        tt.postOrder(root);
    }
}
