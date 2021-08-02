package com.interview.tree;

/**
 * Date 04/11/2017
 * @author Mukesh Kumar Gupta
 * 
 * Youtube link - https://youtu.be/ySDDslG8wws
 * 
 * Reference: https://leetcode.com/problems/same-tree/
 * Category: Easy
 * Given roots of two tree, return true if they have same val and same structure
 * or return false.
 * 
 * Solution
 * Keep comparing root of both val and then recursively check left and right
 * Related: https://leetcode.com/problems/leaf-similar-trees/.
 * 
 * Time complexity is O(n)
 * 
 * Status: Done
 * Category: Easy, Must Do
 */
public class SameTree {

    public boolean sameTree(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null){
            return true;
        }
        if(root1 == null || root2 == null){
            return false;
        }
        
        return root1.val == root2.val && 
                sameTree(root1.left, root2.left) &&
                sameTree(root1.right, root2.right);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode root1 = null;
        root1 = bt.addTreeNode(10, root1);
        root1 = bt.addTreeNode(20, root1);
        root1 = bt.addTreeNode(15, root1);
        root1 = bt.addTreeNode(2, root1);

        TreeNode root2 = null;
        root2 = bt.addTreeNode(10, root2);
        root2 = bt.addTreeNode(20, root2);
        root2 = bt.addTreeNode(15, root2);
        root2 = bt.addTreeNode(2, root2);
        
        SameTree st = new SameTree();
        assert st.sameTree(root1, root2);
   }
}
