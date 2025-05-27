package com.interview.tree.PartBPathProblems;

import java.util.ArrayList;
import java.util.List;

public class A_C_BinaryTreePaths {

    /*https://leetcode.com/problems/binary-tree-paths
     * Category: Easy
Related: https://leetcode.com/problems/prefix-and-suffix-search/ Hard
https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/ Medium
https://leetcode.com/problems/verbal-arithmetic-puzzle/ Hard
https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/ Medium
Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.



Example 1:


Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]
Example 2:

Input: root = [1]
Output: ["1"]
     *
     */
    void dfs(A_A_PathSumII.TreeNode root, List<Integer> path, List<String> result) {
        /*
         * Runtime: 1 ms, faster than 99.98% of Java online submissions for Binary Tree Paths.
        Memory Usage: 42.5 MB, less than 90.31% of Java online submissions for Binary Tree Paths.
         */
        if (root.left == null && root.right == null) {//leaf node
            int size = path.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size -1; i++) {
                sb.append(path.get(i));
                sb.append("->");
            }
            sb.append(root.val);
            result.add(sb.toString());
            return;
        }


        //
        if (root.left != null) {
            path.add(root.left.val);
            dfs(root.left, path, result);
            path.remove(path.size() -1);
        }

        if (root.right != null) {
            path.add(root.right.val);
            dfs(root.right, path, result);
            path.remove(path.size() -1);
        }
    }
    public List<String> binaryTreePathsApproach(A_A_PathSumII.TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 99.87% of Java online submissions for Binary Tree Paths.
Memory Usage: 39.1 MB, less than 85.86% of Java online submissions for Binary Tree Paths.
TC: O(N)

         */
        List<Integer> path = new ArrayList<>();
        List<String> result  = new ArrayList<>();
        path.add(root.val);
        dfs(root, path, result);
        return result;

    }



    void binaryTreePathsUtil(A_A_PathSumII.TreeNode root, List<Integer> path, List<String> result) {
        /*
         * This is older solution, but does not look typical dfs
         */
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {//leaf node
            path.add(root.val);
            int size = path.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size -1; i++) {
                sb.append(path.get(i));
                sb.append("->");
            }
            sb.append(root.val);
            result.add(sb.toString());
            path.remove(size-1);
            return;
        }


        path.add(root.val);
        binaryTreePathsUtil(root.left, path, result);
        binaryTreePathsUtil(root.right, path, result);
        path.remove(path.size() -1);


    }
    public List<String> binaryTreePaths(A_A_PathSumII.TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 99.87% of Java online submissions for Binary Tree Paths.
Memory Usage: 39.1 MB, less than 85.86% of Java online submissions for Binary Tree Paths.
TC: O(N)

         */
        List<Integer> path = new ArrayList<>();
        List<String> result  = new ArrayList<>();
        binaryTreePathsUtil(root, path, result);
        return result;

    }
}
