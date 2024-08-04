package com.interview.tree;

import javax.swing.tree.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Date 03/12/2017
 * @author Mukesh Kumar Gupta
 * https://www.youtube.com/watch?v=-YbXySKJsX8 Using level order
 * https://www.youtube.com/watch?v=jwzo6IsMAFQ&t=701s Using Preorder This can be done other traversal as well
 *
 * Serialize/Deserialize a binary tree whose val is a number.
 *
 *  Time complexity O(n)
 *  Space complexity O(n)
 *  Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

Example 1:


Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [1]
Output: [1]
Example 4:

Input: root = [1,2]
Output: [1,2]
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000
 *
 * Reference
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * Category: Hard, Must Do
 * Related: https://leetcode.com/problems/encode-and-decode-strings/ Medium
 * https://leetcode.com/problems/serialize-and-deserialize-bst/ Medium
 * https://leetcode.com/problems/find-duplicate-subtrees/ Medium
 * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/ Hard
 */
public class SerializeDeserializeBinaryTree {
    //Using level order but any traversal can be used to do this problem
    /*
     * Runtime: 15 ms, faster than 60.94% of Java online submissions for Serialize and Deserialize Binary Tree.
Memory Usage: 41.5 MB, less than 60.46% of Java online submissions for Serialize and Deserialize Binary Tree.
     */
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                res.append("n ");
                continue;
            }
            res.append(node.val + " ");
            q.add(node.left);
            q.add(node.right);
        }
        return res.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == "") return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = q.poll();
            if (!values[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            if (!values[++i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
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

