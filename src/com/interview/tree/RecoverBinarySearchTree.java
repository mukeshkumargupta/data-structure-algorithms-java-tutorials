package com.interview.tree;

/*
 * https://leetcode.com/problems/recover-binary-search-tree/submissions/
 * Category: Medium
 * https://leetcode.com/problems/concatenated-words/ Hard
 * https://leetcode.com/problems/split-bst/ Medium
 * https://leetcode.com/problems/sum-of-distances-in-tree/ Hard
 */
public class RecoverBinarySearchTree {
    private void inorderTraversal(TreeNode root, List<TreeNode> inorder) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, inorder);
        inorder.add(root);
        inorderTraversal(root.right, inorder);
        
    }
    public void recoverTree(TreeNode root) {//runtime 86.23
        List<TreeNode> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);
        
        class Point{
            int i1;
            int i2;
        }
        
        Point p1 = null;
        Point p2 = null;
            
        int size = inorder.size();
        for (int i = 0; i < size; i++) {
            if (i != size-1) {
               if (inorder.get(i).val > inorder.get(i+1).val) {
                   if (p1 == null) {
                        p1 = new Point();
                        p1.i1 = i;
                        p1.i2 = i+1; 
                       //System.out.println(p1.i1 + " -> " + p1.i2);
                   } else {
                        p2 = new Point();
                        p2.i1 = i;
                        p2.i2 = i+1;
                       //System.out.println(p2.i1 + " -> " + p2.i2);
                   }

               } 
            }
        }
         if (p1 != null && p2 != null) {
             TreeNode temp1 = inorder.get(p1.i1);
             TreeNode temp2 = inorder.get(p2.i2);
             int temp = temp1.val;
             temp1.val = temp2.val;
             temp2.val = temp;
             

         } else {
             TreeNode temp1 = inorder.get(p1.i1);
             TreeNode temp2 = inorder.get(p1.i2);
             int temp = temp1.val;
             temp1.val = temp2.val;
             temp2.val = temp; 
         }

        
        
    }
}
