package com.interview.tree.PartETreeModification;

import com.interview.tree.BinaryTree;
import com.interview.tree.TreeNode;

/**
 * Date 03/24/2017
 * @author Mukesh Kumar Gupta
 *
 * Populate next pointer for each TreeNode of binary tree.
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-TreeNode-ii/
 */
public class ConnectTreeNodesAtSameLevel {

    public void connect(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode firstTreeNode = root;
        TreeNode prevTreeNode = null;
        while (firstTreeNode != null) {
            root = firstTreeNode;
            firstTreeNode = null;
            prevTreeNode = null;
            while (root != null) {
                if (root.left != null) {
                    if (firstTreeNode == null) {
                        firstTreeNode = root.left;
                    }
                    if (prevTreeNode != null) {
                        prevTreeNode.next = root.left;
                    }
                    prevTreeNode = root.left;
                }
                if (root.right != null) {
                    if (firstTreeNode == null) {
                        firstTreeNode = root.right;
                    }
                    if (prevTreeNode != null) {
                        prevTreeNode.next = root.right;
                    }
                    prevTreeNode = root.right;
                }
                root = root.next;
            }
        }
    }

    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode root = null;
        root = bt.addTreeNode(10, root);
        root = bt.addTreeNode(15, root);
        root = bt.addTreeNode(5, root);
        root = bt.addTreeNode(7, root);
        root = bt.addTreeNode(19, root);
        root = bt.addTreeNode(20, root);
        root = bt.addTreeNode(-1, root);
        root = bt.addTreeNode(21, root);
        ConnectTreeNodesAtSameLevel cns = new ConnectTreeNodesAtSameLevel();

        cns.connect(root);
    }
}
