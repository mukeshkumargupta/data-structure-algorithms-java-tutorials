package com.interview.tree;

import java.util.*;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Category: Medium, Must Do, Fundamental, Top150
 * Note: Since search is taking time so use map here to get in order of 1
 * Related: construct all tree from inorder preorder, inorder post order, 
 * https://leetcode.com/problems/magic-squares-in-grid/ Medium Imp Concept wise
 * https://leetcode.com/problems/number-of-closed-islands/ Medium VImp
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/ Medium  VVImp
 * https://leetcode.com/problems/stone-game/ Medium
 * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/ Medium
 * https://leetcode.com/problems/number-of-unique-flavors-after-sharing-k-candies/ Medium
 */
public class ConstructTreeFromInOrderPreOrder {
    Map<Integer, Integer> lookup = new HashMap<>();
    int index = 0;
    int search(int[] inorder, int start, int end, int val) {
        /*
         * Runtime: 5 ms, faster than 32.23% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
Memory Usage: 43.7 MB, less than 70.85% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
without lookup stats
         */
        int index;
        for (index = start; index <= end; index++) {
            if (val == inorder[index]) {
               break; 
            }
        }
        return index;
        
    }
    
    void buildlookup(int[] inorder ) {
        int length = inorder.length;
        for (int i = 0; i < length; i++) {
            lookup.put(inorder[i], i);
        }
    }
    public TreeNode buildTreeUtil(int[] preorder, int[] inorder, int start, int end) {
        if (start > end) {
            return null;
        }
        int val = preorder[index++];

        //create new TreeNode
        TreeNode root = new TreeNode(val);
        int position = search(inorder, start, end, val);
        //int position =  lookup.get(val);
        root.left = buildTreeUtil(preorder, inorder, start, position-1);
        root.right = buildTreeUtil(preorder, inorder, position+1, end);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*
         * Runtime: 4 ms, faster than 52.97% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
Memory Usage: 44.9 MB, less than 23.54% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
with lookup based search
         */
        int l = preorder.length;
        
        buildlookup(inorder);
        return buildTreeUtil(preorder, inorder, 0, l-1); 
    }
}
