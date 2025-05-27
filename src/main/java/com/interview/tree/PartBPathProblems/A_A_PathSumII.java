package com.interview.tree.PartBPathProblems;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 10/06/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/path-sum/ done
 * https://leetcode.com/problems/path-sum-ii/ done
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/ Difficult Category  Reference: https://www.youtube.com/watch?v=TO5zsKtc1Ic
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/ done
 * https://leetcode.com/problems/smallest-string-starting-from-leaf/
 * 
 * This is better explanation: https://www.youtube.com/watch?v=MwLDG-WNOjM
 * Reference: https://www.youtube.com/watch?v=Jg4E4KZstFE
 * Derived question: Find max in path, min in path, avg in path, sum in path, print sum if sum equal, or avg equal, all element in path, in case of number make number and return maximum of it etc
 * Category: Must Do, VVImp
 * 
 */
public class A_A_PathSumII {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }

    }
    /*
         * Reference: https://leetcode.com/problems/path-sum-ii/
     * Category: Medium
     * Derived question: print all list of path, there is no criteria of sum
     */
    /*
    💡 Approach:
    Traverse the tree using Depth-First Search (DFS).

    At each node, add its value to the path.

    If it's a leaf node and the remaining target equals the node's value, add the current path to the result.

     Backtrack to explore other paths.
    🕒 Time & Space Complexity:
        Time: O(N), where N is the number of nodes.

        Space: O(H) for the recursion stack (H = height of the tree), and O(N) if storing all paths in result.
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int remainingSum, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.val);
        remainingSum -= node.val;

        if (node.left == null && node.right == null && remainingSum == 0) {
            result.add(new ArrayList<>(path)); // Found valid path
        }

        dfs(node.left, remainingSum, path, result);
        dfs(node.right, remainingSum, path, result);

        path.remove(path.size() - 1); // Backtrack
    }
    




}
