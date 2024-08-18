package com.interview.tree.PartCATreeProperties;

import com.interview.tree.TreeNode;
import com.interview.tree.PartFTreeConstruction.ConstructTreeFromInOrderPreOrder;

/**
 * http://www.geeksforgeeks.org/tree-isomorphism-problem/
 * https://leetcode.com/problems/flip-equivalent-binary-trees/
 * Category: Medium
 * Test cases:
 * Same tree
 * Exact mirror
 * Some TreeNodes flipped
 * Reference: https://www.youtube.com/watch?v=9Eo42meRcrY
 * Reference: Check two tree is mirror of each other: https://www.youtube.com/watch?v=9jH2L2Ysxko
 * Make same tree to mirror of own tree(I think we need to swap the pointer), or print mirror of given tree(I think just reverse left and right call
 * Category: Must Do
 */
public class TreeIsomorphism {

    public boolean areIsomorphicTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        
        if(root1 == null || root2 == null){
            return false;
        }

        return root1.val == root2.val
                && ((areIsomorphicTrees(root1.left, root2.left) && areIsomorphicTrees(
                        root1.right, root2.right)) || (areIsomorphicTrees(
                        root1.left, root2.right) && areIsomorphicTrees(
                        root1.right, root2.left)));

    }
    
    public static void main(String args[]){
        int in1[] = {8,5,6,10,11,9,12};
        int pre1[] = {10,5,8,6,9,11,12};
        int in2[] = {11,9,12,10,6,5,15};
        int pre2[] = {10,9,11,12,5,6,15};
        ConstructTreeFromInOrderPreOrder ct = new ConstructTreeFromInOrderPreOrder();
        TreeNode root1 = ct.createTree(in1, pre1);
        TreeNode root2 = ct.createTree(in2, pre2);
        TreeIsomorphism ti = new TreeIsomorphism();
        System.out.println(ti.areIsomorphicTrees(root1, root2));
    }
}
