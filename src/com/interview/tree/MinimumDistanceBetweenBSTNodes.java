package com.interview.tree;

/*
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 * Category: Easy
 * Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.

 

Example 1:


Input: root = [4,2,6,1,3]
Output: 1
Example 2:


Input: root = [1,0,48,null,null,12,49]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 100].
0 <= Node.val <= 105
 

Note: This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */
public class MinimumDistanceBetweenBSTNodes {
    int previousNodeValue = -1;
    public void minDiffInBSTUtil(TreeNode root, int[] min) {
    if (root == null) {
        return;
    }
    
    minDiffInBSTUtil(root.left, min);
        
    if (previousNodeValue != -1) {
        int diff = root.val - previousNodeValue;
        //System.out.println("diff " + diff + " root " + root.val + " previousNode " + previousNodeValue);
        if (diff < min[0]) {
            min[0] = diff;
        }
    }
    
    previousNodeValue = root.val;
    //System.out.println(" root print " + previousNodeValue);
        
    minDiffInBSTUtil(root.right, min);      
}


public int minDiffInBST(TreeNode root) {
    int previousNodeValue = -1;
    int[] min = new int [1];
    min[0] = Integer.MAX_VALUE;
    
    minDiffInBSTUtil(root, min);
    return min[0];
    
}
}
