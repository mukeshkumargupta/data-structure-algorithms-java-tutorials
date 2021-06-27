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
 * https://leetcode.com/problems/path-sum/
 * https://leetcode.com/problems/path-sum-ii/ 
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/ Dificult Category  Reference: https://www.youtube.com/watch?v=TO5zsKtc1Ic
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * https://leetcode.com/problems/smallest-string-starting-from-leaf/
 * 
 * This is better explanation: https://www.youtube.com/watch?v=MwLDG-WNOjM
 * Reference: https://www.youtube.com/watch?v=Jg4E4KZstFE
 * Derived question: Find max in paht, min in path, avg in path, sum in path, print sum if sum equal, or avg equal, all element in path, in case of number make number and return maximum of it etc
 * Category: VVImp
 * 
 */
public class PathSum {
    /*
     * Reference: https://leetcode.com/problems/path-sum-ii/
     * Category: Medium
     */
    public List<List<Integer>> pathSum(Node root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        pathSumUtil(root, sum, result, current);
        return result;
    }

    private void pathSumUtil(Node root, int sum, List<List<Integer>> result, List<Integer> currentPath) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (root.data == sum) {
                currentPath.add(root.data);
                result.add(new ArrayList<>(currentPath));
                currentPath.remove(currentPath.size() - 1);
            }
            return;
        }
        currentPath.add(root.data);
        pathSumUtil(root.left, sum - root.data, result, currentPath);
        pathSumUtil(root.right, sum - root.data, result, currentPath);
        currentPath.remove(currentPath.size() - 1);
    }
    
    private void sumNumbersUtil(Node root, int number, int[] sum) {
        if (root == null) {
            return;
        }
        number = number*10 + root.data;
        if (root.left == null && root.right == null) {
            sum[0]+= number; 
            return;
        }
        sumNumbersUtil(root.left, number, sum);
        sumNumbersUtil(root.right, number, sum);
    }
    //Reference: https://leetcode.com/problems/sum-root-to-leaf-numbers
    /*
     * Category: Medium, Must Know
     * Derived, Minum sum , maximum sum, average out of all
     */
    private int sumNumbers(Node root) {
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
    public boolean hasPathSum(Node root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.data == sum;
        }
        return hasPathSum(root.left, sum - root.data) || hasPathSum(root.right, sum - root.data);
    }
}
