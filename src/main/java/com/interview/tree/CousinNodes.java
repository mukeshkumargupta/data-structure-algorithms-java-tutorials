package com.interview.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/check-two-TreeNodes-cousins-binary-tree/ Assumption
 * that both a and b are unique in tree. Test cases: Empty tree Tree with only
 * root Tree and input with a and b as cousin TreeNode Tree and input with a and b
 * not cousin TreeNode Tree with input a and b being siblings(not cousin)
 */
public class CousinTreeNodes {

    /**
     * Little more efficient than other method since this guy does not need
     * another look up to see if parent is same for found TreeNodes.
     */
    public boolean areCousins1(TreeNode root, int val1, int val2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int tempLevel = 1;
        int level = 0;
        boolean found1 = false;
        boolean found2 = false;
        TreeNode parent1 = null;
        TreeNode parent2 = null;
        while (!queue.isEmpty()) {
            while (tempLevel > 0) {
                root = queue.poll();
                if (root.left != null) {
                    if (root.left.val == val1) {
                        found1 = true;
                        parent1 = root;
                    } else if (root.left.val == val2) {
                        found2 = true;
                        parent2 = root;
                    } else {
                        queue.add(root.left);
                        level++;
                    }
                }
                if (root.right != null) {
                    if (root.right.val == val1) {
                        found1 = true;
                        parent1 = root;
                    } else if (root.right.val == val2) {
                        found2 = true;
                        parent2 = root;
                    } else {
                        queue.add(root.right);
                        level++;
                    }
                }
                tempLevel--;
            }
            if (found1 && found2 && parent1 != parent2) {
                return true;
            } else if (found1 || found2) {
                return false;
            }
            tempLevel = level;
            level = 0;
        }
        return false;
    }

    public boolean areCousins(TreeNode root, int a, int b) {
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offerFirst(root);
        int levelSize = 1;
        int tempLevelSize = 1;
        boolean foundFirst = false;
        while (!queue.isEmpty()) {
            levelSize = 0;
            while (tempLevelSize > 0) {
                TreeNode TreeNode = queue.pollLast();
                // this is to make sure a and b are not siblings of each other
                // if they are return false since they cant be cousins
                if (checkSameParent(TreeNode, a, b)) {
                    return false;
                }
                if (TreeNode.val == a || TreeNode.val == b) {
                    if (foundFirst) {
                        return true;
                    }
                    foundFirst = true;
                }
                if (TreeNode.left != null) {
                    queue.offerFirst(TreeNode.left);
                    levelSize++;
                }
                if (TreeNode.right != null) {
                    queue.offerFirst(TreeNode.right);
                    levelSize++;
                }
                tempLevelSize--;
            }
            if (foundFirst) {
                return false;
            }
            tempLevelSize = levelSize;
        }
        return false;
    }

    private boolean checkSameParent(TreeNode root, int a, int b) {
        if (root.left != null && root.right != null) {
            if ((root.left.val == a || root.left.val == b)
                    && (root.right.val == a || root.right.val == b)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        BinaryTree bt = new BinaryTree();
        TreeNode head = null;
        head = bt.addTreeNode(10, head);
        head = bt.addTreeNode(15, head);
        head = bt.addTreeNode(5, head);
        head = bt.addTreeNode(7, head);
        head = bt.addTreeNode(19, head);
        head = bt.addTreeNode(20, head);
        head = bt.addTreeNode(-1, head);
        head = bt.addTreeNode(21, head);
        head = bt.addTreeNode(-6, head);
        CousinTreeNodes cn = new CousinTreeNodes();
        System.out.println(cn.areCousins(head, 10, 19));
        System.out.println(cn.areCousins(head, 19, 7));
        System.out.println(cn.areCousins(head, 19, -1));
        System.out.println(cn.areCousins(head, 19, -6));
        System.out.println(cn.areCousins(head, -1, 7));
        System.out.println(cn.areCousins(head, 7, -1));
    }
}
