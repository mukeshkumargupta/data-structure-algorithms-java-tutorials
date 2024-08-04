package com.interview.bst;

import com.interview.tree.TreeNode;
import com.interview.tree.TreeTraversals;

class Index{
    int index;
}
/**
 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
 * Test case
 * empty array
 * 1,2 or more elements in the array
 * https://www.youtube.com/watch?v=Bexswo4pqZQ
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * Category: Medium, Tricky
 */
public class ConstructBSTFromPreOrderArray {

   
   int preIdx=0;
   public TreeNode bstFromPreorder(int[] preorder) {
       return formBST(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);
   }
   public TreeNode formBST(int[] preorder,Integer min,Integer max){
       if(preIdx>=preorder.length || preorder[preIdx]<=min || preorder[preIdx]>=max){
           return null;
       }
       TreeNode root=new TreeNode(preorder[preIdx++]);
       root.left=formBST(preorder,min,root.val);
       root.right=formBST(preorder,root.val,max);
       return root;
   }
    
    public static void main(String args[]){
        int preorder[] = {10,5,1,7,40,50};
        ConstructBSTFromPreOrderArray poa = new ConstructBSTFromPreOrderArray();
        TreeNode root = poa.toBST(preorder);
        TreeTraversals tt = new TreeTraversals();
        tt.preOrder(root);
        System.out.println();
        tt.inOrder(root);
    }
}
