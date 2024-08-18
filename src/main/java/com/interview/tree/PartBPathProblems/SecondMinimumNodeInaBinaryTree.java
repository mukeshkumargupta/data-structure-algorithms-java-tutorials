package com.interview.tree.PartBPathProblems;

import com.interview.tree.TreeNode;

/*
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 * Category: Easy, Tricky
 * Related: Try to find 3rd smallest or kth smallest
 * https://leetcode.com/problems/cracking-the-safe/ Hard
 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/ Medium
 * https://leetcode.com/problems/depth-of-bst-given-insertion-order/ Medium
 */
public class SecondMinimumNodeInaBinaryTree {
    public void dfs(TreeNode root, Set<Integer> uniques) {
        if (root != null) {
            uniques.add(root.val);
            dfs(root.left, uniques);
            dfs(root.right, uniques);
        }
    }
    public int findSecondMinimumValue(TreeNode root) {
        Set<Integer> uniques = new HashSet<Integer>();
        dfs(root, uniques);

        int min1 = root.val;
        long ans = Long.MAX_VALUE;
        for (int v : uniques) {
            if (min1 < v && v < ans) ans = v;
        }
        return ans < Long.MAX_VALUE ? (int) ans : -1;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
