package com.interview.tree.PartFTreeConstruction;

import com.interview.tree.PartATreeTraversal.TreeTraversals;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

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
    /**
     * Understanding Serialization and Deserialization
     *
     * Serialization (Converting a tree into a string):
     * - Traverse the tree in level order (BFS) using a queue.
     * - Store null for missing (leaf) children.
     * - Convert the sequence into a comma-separated string.
     *
     * Deserialization (Reconstructing the tree from the string):
     * - Read the string and split it into an array.
     * - Use a queue to process nodes level by level.
     * - Attach children nodes to their parents accordingly.
     *
     * Dry Run
     * Step 1: Serialize the Tree
     *
     * Tree structure:
     *
     *        1
     *       / \
     *      2   3
     *         / \
     *        4   5
     *
     * BFS Level-Order Serialization:
     * - Start with root (1), queue = [1]
     * - Process 1, add 2, 3 → "1,"
     * - Queue = [2, 3]
     * - Process 2, add null, null → "1,2,"
     * - Queue = [3, null, null]
     * - Process 3, add 4, 5 → "1,2,3,"
     * - Queue = [null, null, 4, 5]
     * - Process null, "1,2,3,null,"
     * - Process null, "1,2,3,null,null,"
     * - Process 4, add null, null → "1,2,3,null,null,4,"
     * - Queue = [5, null, null]
     * - Process 5, add null, null → "1,2,3,null,null,4,5,"
     * - Queue = [null, null, null, null]
     * - Process remaining nulls
     *
     * Final serialized string: "1,2,3,null,null,4,5,null,null,null,null,"
     *
     * Step 2: Deserialize the Tree
     * - Split "1,2,3,null,null,4,5,null,null,null,null," → ["1", "2", "3", "null", "null", "4", "5", "null", "null", "null", "null"]
     * - Start with root = TreeNode(1), queue = [1]
     * - Process 1, add 2 and 3 → queue = [2, 3]
     * - Process 2, add null, null → queue = [3]
     * - Process 3, add 4, 5 → queue = [4, 5]
     * - Process 4, add null, null → queue = [5]
     * - Process 5, add null, null → queue = []
     *
     * Final tree is reconstructed.
     *
     * Time & Space Complexity Analysis
     *
     * Serialization (BFS):
     * - Time Complexity: O(N), where N is the number of nodes.
     * - Space Complexity: O(N) (queue for BFS traversal).
     *
     * Deserialization (BFS):
     * - Time Complexity: O(N) (processing each node).
     * - Space Complexity: O(N) (storing nodes in the queue).
     */
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    // Serialize a binary tree to a string using BFS
    public String serialize(TreeNode root) {
        if (root == null) return "null"; // Edge case

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                sb.append("null,");
                continue;
            }

            sb.append(node.val).append(",");
            queue.offer(node.left);
            queue.offer(node.right);
        }

        return sb.toString();
    }

    // Deserialize a string back to a binary tree using BFS
    public TreeNode deserialize(String data) {
        if (data.equals("null")) return null; // Edge case

        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();

            if (!values[i].equals("null")) {
                parent.left = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(parent.left);
            }
            i++;

            if (!values[i].equals("null")) {
                parent.right = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(parent.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        SerializeDeserializeBinaryTree codec = new SerializeDeserializeBinaryTree();

        // Construct the example tree:
        //         1
        //        / \
        //       2   3
        //          / \
        //         4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // Serialize
        String serialized = codec.serialize(root);
        System.out.println("Serialized: " + serialized);

        // Deserialize
        TreeNode newRoot = codec.deserialize(serialized);
        System.out.println("Deserialized (Root): " + newRoot.val);
    }



}

