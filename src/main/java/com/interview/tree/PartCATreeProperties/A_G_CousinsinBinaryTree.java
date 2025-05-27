package com.interview.tree.PartCATreeProperties;

/*
 * Reference: https://leetcode.com/problems/cousins-in-binary-tree/
 * Category: Easy
 * Related: Cousin, Brother, Uncle, Grandson
 * Video: https://www.youtube.com/watch?v=UyxnGWMvxwc&t=621s
 */

import com.interview.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree TreeNode.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class A_G_CousinsinBinaryTree {
    class Details {
        int h;
        int parent; 
    }
    Map<Integer, Details> parentLookup = new HashMap<>();

    private void buildParentLookup(TreeNode root, int h) {
        if (root == null) return;
        
        if (root.left != null) {
            Details d = new Details();
            d.h = h +1;
            d.parent = root.val;
            //System.out.println("left " + root.left.val + " " + root.val + " " + d.h);
            parentLookup.put(root.left.val, d);
            buildParentLookup(root.left, h+1);
        }
        
        if (root.right != null) {
            Details d = new Details();
            d.h = h + 1;
            d.parent = root.val;
            //System.out.println("right " + root.right.val + " " + root.val + " " + d.h);
            parentLookup.put(root.right.val, d);
            buildParentLookup(root.right, h+1);
        }
        
    }
    public boolean isCousins(TreeNode root, int x, int y) {
        buildParentLookup(root, 0);
        
        Details d1 = parentLookup.get(x);
        Details d2 = parentLookup.get(y);
        
        if ( root.val == x || root.val == y) {// if any one of eleemnt is equal to root then no posibility of cousin
            return false;
        }

        if (d1.h == d2.h && d1.parent  != d2.parent) {
            return true;
            
        } else {
            return false;
        } 
    }
    
}
