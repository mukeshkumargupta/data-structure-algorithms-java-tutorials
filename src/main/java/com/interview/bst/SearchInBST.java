package com.interview.bst;

import com.interview.tree.TreeNode;

/**
 * Regular binary search
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 * Category: Easy, Fundamental
 */
public class SearchInBST {

    public TreeNode searchRecursive(TreeNode root, int key){
        if(root == null){
            return null;
        }
        if(root.val == key){
            return root;
        }else if(root.val < key){
            return searchRecursive(root.right, key);
        }else{
            return searchRecursive(root.left, key);
        }
    }
    
    public TreeNode searchIterative(TreeNode root, int val) {
        TreeNode current = root;
        while (current != null) {
            if (current.val == val) {
                return current;
            } else if (val < current.val ) {
                current = current.left;
            } else  {
               current = current.right; 
            }
            
        }
        return current;
  
    }
}
