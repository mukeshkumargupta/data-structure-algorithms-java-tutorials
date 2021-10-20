package com.interview.tree;

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
public class PathSum {
    /*
     * Reference: https://leetcode.com/problems/path-sum-ii/
     * Category: Medium
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        pathSumUtil(root, sum, result, current);
        return result;
    }

    private void pathSumUtil(TreeNode root, int sum, List<List<Integer>> result, List<Integer> currentPath) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                currentPath.add(root.val);
                result.add(new ArrayList<>(currentPath));
                currentPath.remove(currentPath.size() - 1);
            }
            return;
        }
        currentPath.add(root.val);
        pathSumUtil(root.left, sum - root.val, result, currentPath);
        pathSumUtil(root.right, sum - root.val, result, currentPath);
        currentPath.remove(currentPath.size() - 1);
    }
    
    private void sumNumbersUtil(TreeNode root, int number, int[] sum) {
        if (root == null) {
            return;
        }
        number = number*10 + root.val;
        if (root.left == null && root.right == null) {
            sum[0]+= number; 
            return;
        }
        sumNumbersUtil(root.left, number, sum);
        sumNumbersUtil(root.right, number, sum);
    }
    //Reference: https://leetcode.com/problems/sum-root-to-leaf-numbers
    /*
     * Category: Medium, Must Do
     * Derived, Minum sum , maximum sum, average out of all
     */
    private int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] sum = new int[1];
        sumNumbersUtil(root, 0, sum);
        return sum[0];
 
    }
    /*
     * Reference: https://leetcode.com/problems/path-sum/
     * Category; Easy
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
    //Related: https://leetcode.com/problems/smallest-string-starting-from-leaf/submissions/
    //Related: https://leetcode.com/problems/reverse-only-letters/ Easy\
    //https://leetcode.com/problems/kth-ancestor-of-a-tree-node/ Hard
    //https://leetcode.com/problems/number-of-ways-to-reconstruct-a-tree/ Hard
    String smallestString = "";

    void smallestFromLeafUtil(TreeNode root, String currentString) {//faster than 100% of Java online submissions for Smallest String Starting From Leaf.
        if (root == null) {
            return;
        }
        
        currentString = "" + (char)('a' + root.val) + currentString;

        if (root.left == null && root.right == null) {
            if (smallestString.isEmpty() || currentString.compareTo(smallestString) < 0) {
                smallestString = currentString;
            }
        }
        smallestFromLeafUtil(root.left, currentString);
        smallestFromLeafUtil(root.right, currentString);
    }
    public String smallestFromLeaf(TreeNode root) {
        smallestFromLeafUtil(root, "");
        return smallestString;
        
    }
}
