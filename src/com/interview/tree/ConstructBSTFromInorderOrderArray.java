package com.interview.tree;

/*
 * https://www.youtube.com/watch?v=UAsLKuEMhsQ
 * Category: Medium
 */
public class ConstructBSTFromInorderOrderArray {
    int preIdx=0;
    public TreeNode bstFromInorder(int[] inorder) {
        return formBST(preorder,0,inorder.length-1);
    }
    public TreeNode formBST(int[] inorder, int start, int end){
        if(start > end){
            return null;
        }
        int mid = start + (end - start)/2;
        TreeNode root=new TreeNode(preorder[mid]);
        root.left=formBST(inorder,start,mid -1);
        root.right=formBST(inorder,mid +1 ,end);
        return root;
    }
}
