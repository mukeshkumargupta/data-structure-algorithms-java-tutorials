package com.interview.tree;

import javax.swing.tree.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Date 03/12/2017
 * @author Mukesh Kumar Gupta
 *
 * Serialize/Deserialize a binary tree whose val is a number.
 *
 *  Time complexity O(n)
 *  Space complexity O(n)
 *
 * Reference
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * VImp
 */
public class SerializeDeserializeBinaryTree {

    /**
     * Serialize Tree using preorder DFS
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        StringBuffer buff = new StringBuffer();
        serializeUtil(root, buff);
        return buff.toString();
    }

    private void serializeUtil(TreeNode root, StringBuffer buff) {
        if (root == null) {
            buff.append("%,");
            return;
        }

        buff.append(root.val).append(",");
        if (root.left != null || root.right != null) {
            buff.append("$,");
            serializeUtil(root.left, buff);
            serializeUtil(root.right, buff);
        } else {
            return;
        }

    }
    int index = 0;

    /**
     * Deserialize Tree using preorder DFS
     * @param val
     * @return
     */
    public TreeNode deserialize(String val) {
        String[] input = val.split(",");
        index = 0;
        return deserializeUtil(input);
    }

    private TreeNode deserializeUtil(String input[]) {
        if (index == input.length) {
            return null;
        }

        if (input[index].equals("%")) {
            index++;
            return null;
        }
        TreeNode n = new TreeNode();
        n.val = Integer.parseInt(input[index]);
        if (index < input.length - 1) {
            if (input[index + 1].equals("$")) {
                index += 2;
                n.left = deserializeUtil(input);
                n.right = deserializeUtil(input);
            } else {
                index++;
            }
        }
        return n;
    }

    /**
     * Serialize tree using level order traversal.
     */
    public String serializeLevelOrder(TreeNode root) {
        if (root == null) {
            return "";
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root);
        StringBuffer buff = new StringBuffer();
        while (!queue.isEmpty()) {
            root = queue.pollFirst();
            if (root == null) {
                buff.append("%,");
            } else {
                buff.append(root.val).append(",");
                queue.offer(root.left);
                queue.offer(root.right);
            }
        }
        for (int i = buff.length() - 1; i >= 0; i--) {
            if (buff.charAt(i) == '%' || buff.charAt(i) == ',') {
                buff.deleteCharAt(i);
            } else {
                break;
            }
        }
        return buff.toString();
    }

    /**
     * Deserialize Tree using level order traversal.
     */
    public TreeNode deserializeLevelOrder(String val) {
        if (val == null || val.length() == 0) {
            return null;
        }
        String[] input = val.split(",");
        Deque<TreeNode> queue = new LinkedList<>();
        int index = 0;
        queue.offerFirst(TreeNode.newTreeNode(Integer.parseInt(input[index])));
        TreeNode root = queue.peekFirst();
        index++;
        while (!queue.isEmpty()) {
            TreeNode current = queue.pollFirst();
            if (index < input.length && !input[index].equals("%")) {
                current.left = TreeNode.newTreeNode(Integer.parseInt(input[index]));
                queue.offerLast(current.left);
            }
            index++;
            if (index < input.length && !input[index].equals("%")) {
                current.right = TreeNode.newTreeNode(Integer.parseInt(input[index]));
                queue.offerLast(current.right);
            }
            index++;
        }
        return root;
    }


    public static void main(String args[]) {
        SerializeDeserializeBinaryTree sd = new SerializeDeserializeBinaryTree();
        TreeNode TreeNode = sd.deserialize("10,$,30,15,$,%,20,$,21,16,$,%,18");
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(TreeNode);
        String serializedTree= sd.serializeLevelOrder(TreeNode);
        TreeNode root = sd.deserializeLevelOrder("1,2");
        tt.inOrder(root);
    }
}

