package com.interview.tree;
/*
 * https://leetcode.com/problems/house-robber-iii/
 * https://www.youtube.com/watch?v=FYho45iq68Y 
 * Category: Medium
 * Related: https://leetcode.com/problems/house-robber-ii/ Medium
 * 
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.

Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.

Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.

 

Example 1:


Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:


Input: root = [3,4,5,1,3,null,1]
Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 104
 */
public class HouseRobberIII {
    Map<TreeNode, Integer> map = new HashMap<>();
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }
        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        
        if (root.right != null) {
            val += rob( root.right.left) + rob(root.right.right);
        }
        
        map.put(root, Math.max(root.val + val, rob(root.left) + rob(root.right)));
        return map.get(root);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
