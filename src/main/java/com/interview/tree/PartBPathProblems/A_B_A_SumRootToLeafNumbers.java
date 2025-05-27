package com.interview.tree.PartBPathProblems;

public class A_B_A_SumRootToLeafNumbers {
    private void sumNumbersUtil(A_A_PathSumII.TreeNode root, int number, int[] sum) {
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
     * Derived, Minium sum , maximum sum, average out of all
     */
    private int sumNumbers(A_A_PathSumII.TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] sum = new int[1];
        sumNumbersUtil(root, 0, sum);
        return sum[0];

    }
}
