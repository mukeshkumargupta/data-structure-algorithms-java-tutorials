package com.interview.tree.PartDSubtreeProblems;

import com.interview.tree.TreeNode;

/*
 * Reference: https://leetcode.com/problems/subtree-of-another-tree/
 * Category: Easy
 */
public class SubtreeofAnotherTree {
    public boolean isIdentical(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        
        if (s == null || t == null) return false;
        
        if (s.val == t.val && isIdentical(s.left, t.left) && isIdentical(s.right, t.right)) {
            return true;
        }
        return false;   
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true; 
        }
        if (s == null) return false;
        
        if (isIdentical(s, t)) {
            return true;
        } else {
            return (isSubtree(s.left, t) || isSubtree(s.right, t));
        }
        


    }
}
