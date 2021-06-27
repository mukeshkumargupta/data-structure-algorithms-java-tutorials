package com.interview.binarysearch;

/**
 * Regular binary search
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 * Category: Easy
 */
public class BinarySearch {

    public TreeNode searchBST(TreeNode root, int val) {
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
