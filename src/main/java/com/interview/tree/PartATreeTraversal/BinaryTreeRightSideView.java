package com.interview.tree.PartATreeTraversal;

import com.interview.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Reference:
 * https://leetcode.com/problems/binary-tree-right-side-view
 * Category: Medium, Must Do, top75, Facebook, FAANG
 * Related:
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/ Medium
 * https://leetcode.com/problems/boundary-of-binary-tree/ Medium
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.



Example 1:

Input: root = [1,2,3,null,5,null,4]

Output: [1,3,4]

Explanation:



Example 2:

Input: root = [1,2,3,4,null,null,null,5]

Output: [1,3,4,5]

Explanation:



Example 3:

Input: root = [1,null,3]

Output: [1,3]

Example 4:

Input: root = []

Output: []



Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */
public class BinaryTreeRightSideView {
    List<Integer> result = new ArrayList<>();
    void rightSideViewUtil(TreeNode root, int[] height, int level) {
        if (root == null) {
           return; 
        }
        
        if (level > height[0]) {
            height[0] = level; 
            result.add(root.val);
        }
        rightSideViewUtil(root.right, height, level+1);
        rightSideViewUtil(root.left, height, level+1);
        
    }
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
           return result; 
        }
        int[] height = new int[1];
        height[0] = -1;
        

        rightSideViewUtil(root, height, 0);
        return result;
        
    }
}
