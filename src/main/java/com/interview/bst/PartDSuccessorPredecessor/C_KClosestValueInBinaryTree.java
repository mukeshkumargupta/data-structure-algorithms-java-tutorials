package com.interview.bst.PartDSuccessorPredecessor;

import com.interview.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Date 10/08/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 *
 * Time complexity O(log(n) + k)
 *
 * https://leetcode.com/problems/closest-binary-search-tree-value-ii/
 */
public class C_KClosestValueInBinaryTree {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (root == null || k == 0) {
            return new ArrayList<>();
        }

        Stack<TreeNode> predecessor = new Stack();
        Stack<TreeNode> successor = new Stack();

        double closestDiff = Double.MAX_VALUE;
        TreeNode closestDiffTreeNode = null;
        while (root != null) {
            predecessor.push(root);
            successor.push(root);
            if (Math.abs(target - root.val) < closestDiff) {
                closestDiff = Math.abs(target - root.val);
                closestDiffTreeNode = root;
            }
            if (root.val == target) {
                break;
            }

            else if (target > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        while (predecessor.peek() != closestDiffTreeNode) {
            predecessor.pop();
            successor.pop();
        }
        predecessor.pop();
        successor.pop();
        List<Integer> result = new ArrayList<>();
        result.add(closestDiffTreeNode.val);
        TreeNode prec = closestDiffTreeNode;
        TreeNode succ = closestDiffTreeNode;
        k--;
        prec = predecessor(predecessor, prec);
        succ = successor(successor, succ);
        while (k > 0) {
            if (succ == null || (prec != null && Math.abs(target - prec.val) < Math.abs(target - succ.val))) {
                result.add(prec.val);
                prec = predecessor(predecessor, prec);
            } else {
                result.add(succ.val);
                succ = successor(successor, succ);
            }
            k--;
        }
        return result;
    }

    private TreeNode predecessor(Stack<TreeNode> stack, TreeNode current) {
        if (current == null) {
            return null;
        }
        if (current.left != null) {
            stack.push(current);
            current = current.left;
            while (current.right != null) {
                stack.push(current);
                current = current.right;
            }
            return current;
        } else {
            while (!stack.isEmpty() && stack.peek().left == current) {
                current = stack.pop();
            }
            if (stack.isEmpty()) {
                return null;
            } else {
                return stack.pop();
            }
        }
    }

    private TreeNode successor(Stack<TreeNode> stack, TreeNode current) {
        if (current == null) {
            return null;
        }
        if (current.right != null) {
            stack.push(current);
            current = current.right;
            while (current.left != null) {
                stack.push(current);
                current = current.left;
            }
            return current;
        } else {
            while (!stack.isEmpty() && stack.peek().right == current) {
                current = stack.pop();
            }
            if (stack.isEmpty()) {
                return null;
            } else {
                return stack.pop();
            }
        }
    }
}
