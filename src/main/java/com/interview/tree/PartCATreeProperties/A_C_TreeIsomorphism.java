package com.interview.tree.PartCATreeProperties;

import com.interview.tree.TreeNode;

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
public class A_C_TreeIsomorphism {

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

}
