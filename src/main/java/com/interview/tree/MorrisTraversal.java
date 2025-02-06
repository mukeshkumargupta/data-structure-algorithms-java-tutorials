package com.interview.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 03/08/2017
 * @author Mukesh Kumar Gupta
 *Category: Medium, Tricky
 * Morris inorder/preorder traversals
 * Related: 
 * https://leetcode.com/problems/binary-search-tree-iterator/ Medium VVImp
 * https://leetcode.com/problems/closest-binary-search-tree-value-ii/ Hard Locked
 * https://leetcode.com/problems/inorder-successor-in-bst/ Medium Locked
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/ Medium, Locked
 *https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/ Medium Locked
 * Time complexity O(n)
 * Space complexity O(1)  Important, here O(1) space is there
 * Reference: https://www.youtube.com/watch?v=80Zug6D1_r4&t=5s
 * Must Do (VImp)
 */
public class MorrisTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        
        while (curr != null) {
            if (curr.left == null) {
                result.add(curr.val);
                curr = curr.right;
            } else {//if there is left, then make connection by going right most, then go left
                TreeNode prev = curr.left;
                
                while(prev.right != null && prev.right != curr) {//there will be two casse if current right exist then make connection, make sure, it soes not reach to own, if point to own it means print that and break the connection,
                    prev = prev.right;
                }
                
                if (prev.right == null) {
                   prev.right =  curr;
                    curr= curr.left;
                } else {
                    result.add(curr.val);
                    prev.right = null;//break the connection
                    curr = curr.right;
                }
                
                
            }
            
        }
        return result;
        
    }

    public void preorder(TreeNode root) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Preorder Traversal.
Memory Usage: 42.6 MB, less than 14.93% of Java online submissions for Binary Tree Preorder Traversal.
         */
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        
        while (curr != null) {
            if (curr.left == null) {
                result.add(curr.val);
                curr = curr.right;
            } else {//if there is left, then make connection by going right most, then go left
                TreeNode prev = curr.left;
                
                while(prev.right != null && prev.right != curr) {//there will be two casse if current right exist then make connection, make sure, it soes not reach to own, if point to own it means print that and break the connection,
                    prev = prev.right;
                }
                
                if (prev.right == null) {
                   result.add(curr.val);//This si one line change from inorder
                   prev.right =  curr;
                    curr= curr.left;
                } else {
                    
                    prev.right = null;//break the connection
                    curr = curr.right;
                }
                
                
            }
            
        }
        return result;
    }

    public static void main(String args[]) {
        BinaryTree bt = new BinaryTree();
        TreeNode root = null;
        root = bt.addTreeNode(10, root);
        root = bt.addTreeNode(50, root);
        root = bt.addTreeNode(-10, root);
        root = bt.addTreeNode(7, root);
        root = bt.addTreeNode(9, root);
        root = bt.addTreeNode(-20, root);
        root = bt.addTreeNode(30, root);
        MorrisTraversal mt = new MorrisTraversal();
        mt.inorder(root);
        System.out.println();
        mt.preorder(root);
    }
}
