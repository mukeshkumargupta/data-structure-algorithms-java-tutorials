package com.interview.tree.PartCATreeProperties;

import com.interview.tree.TreeNode;

/*
 * Date 04/11/2017
 * @author Mukesh Kumar Gupta
 * 
 * Youtube link - https://youtu.be/MILxfAbIhrE
 * 
 * Given a binary tree, return true if it is binary search tree else return false.
 * https://leetcode.com/problems/validate-binary-search-tree/submissions/ 100% runtime
 * Category: Medium, Must Do, FAANG, Facebook
 * Related
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/ Easy
 * 
 */
public class A_B_IsBST {

    private boolean isValidBSTUtil(TreeNode root, long min, long max) {
        if(root == null){
            return true;
        }
        if(root.val <= min || root.val >= max){
            return false;
        }
        return isValidBSTUtil(root.left, min, root.val) && isValidBSTUtil(root.right, root.val, max);
        
    }
    public boolean isValidBST(TreeNode root) {
        return isValidBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
        
    }
    /*
     * Runtime
    0 ms
    Beats
    100%
     */
    private boolean isValidBSTUtil_V1(TreeNode root, long min, long max) {
        
        if(root == null){
            return true;
        }
        /*if(root.val <= min || root.val >= max){
            return false;
        }*/
        return (root.val > min &&  root.val < max) && isValidBSTUtil(root.left, min, root.val) && isValidBSTUtil(root.right, root.val, max);
        
    }
    public boolean isValidBST_V1(TreeNode root) {
        return isValidBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
        
    }
}
