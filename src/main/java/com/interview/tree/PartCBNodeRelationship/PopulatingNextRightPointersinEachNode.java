package com.interview.tree.PartCBNodeRelationship;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * Category: Must Do, Top150
 * https://www.youtube.com/watch?v=o-SxW_0E-o8 Using dfs
 * Related:
 * - https://leetcode.com/problems/binary-tree-right-side-view/ (Medium, Very Important)
 * - https://leetcode.com/problems/populating-next-right-pointers-in-each-node/ (Medium, Very Important)
 *
 * 🔹 116. Populating Next Right Pointers in Each Node
 *
 * 🟢 Problem Statement:
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * };
 *
 * Populate each `next` pointer to point to its next right node.
 * If there is no next right node, the `next` pointer should be set to NULL.
 * Initially, all `next` pointers are set to NULL.
 *
 * 🔹 Example 1:
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree, your function should populate
 * each `next` pointer to point to its next right node, just like in the given figure.
 * The serialized output is in level order as connected by the `next` pointers,
 * with '#' signifying the end of each level.
 *
 * 🔹 Example 2:
 *
 * Input: root = []
 * Output: []
 *
 * 🔹 Constraints:
 * - The number of nodes in the tree is in the range [0, 2^12 - 1].
 * - -1000 <= Node.val <= 1000
 *
 * 🔹 Follow-up:
 * - You may only use constant extra space.
 * - The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class PopulatingNextRightPointersinEachNode {
    private static class Node {
        int val;
        Node left;
        Node right;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }

    /*
    🟥 Approach 1: Brute Force (Using BFS with Queue, O(n) Space)
    💡 Idea:
    - Use Level Order Traversal (BFS): Traverse the tree level by level using a queue.
    - Connect Nodes at the Same Level: For each level, iterate through all nodes and connect the current node’s `next` pointer to the next node in the queue.
    - Handle the Last Node: The last node of each level should have `next = null`.

    🔵 Time Complexity: O(n)
       - Each node is visited once.

    🔵 Space Complexity: O(n)
       - Worst case: The queue contains all nodes at the deepest level (≈ n/2 nodes).

    ❌ Why This is Suboptimal?
    - Uses extra O(n) space due to the queue.
    - Not the most efficient way as we process nodes level by level.
    */
    private static class BruitForce {//Using BFS
        public Node connect(Node root) {
            if (root == null) return null;

            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                Node prev = null;

                for (int i = 0; i < size; i++) {
                    Node curr = queue.poll();

                    // Connect previous node to the current node
                    if (prev != null) prev.next = curr;
                    prev = curr;

                    if (curr.left != null) queue.offer(curr.left);
                    if (curr.right != null) queue.offer(curr.right);
                }

                // Last node in each level should point to null (handled automatically)
                prev.next = null;
            }

            return root;
        }
    }

    /*
    🟡 Approach 2: Better (Using Next Pointers, O(1) Space)
    💡 Idea:
    - Use next pointers directly: Since the tree is perfect, every node at level `i` has its children at level `i+1`.
    - Traverse level-by-level, modifying next pointers:
      - Use a `leftmost` pointer to track the start of each level.
      - Iterate through the current level using the `next` pointer.
      - Connect children of adjacent nodes.

    🔵 Time Complexity: O(n)
       - Each node is processed once.

    🔵 Space Complexity: O(1)
       - No extra space is used apart from variables.

    ✅ Why This is Better?
    - No extra space (only O(1))
    - More efficient traversal using next pointers.
    */
    private static class Better {
        public Node connect(Node root) {
            if (root == null) return null;

            Node leftmost = root;  // Start from the root

            while (leftmost.left != null) {  // Traverse level by level
                Node curr = leftmost;

                while (curr != null) {
                    // Connect left child to right child
                    curr.left.next = curr.right;

                    // Connect right child to next subtree's left child (if exists)
                    if (curr.next != null) {
                        curr.right.next = curr.next.left;
                    }

                    curr = curr.next; // Move to next node in the current level
                }

                leftmost = leftmost.left; // Move to the next level
            }

            return root;
        }
    }

    /*
    🟢 Approach 3: Optimized (Recursive DFS, O(1) Space)
    💡 Idea:
    Instead of iterative traversal, we can use recursion.
    For each node:
    - Connect left child to right child.
    - Connect right child to next node’s left child (if exists).
    - Recursively process left and right subtrees.

    🔵 Time Complexity: O(n)
       - Each node is processed once.

    🔵 Space Complexity: O(1) (excluding recursion stack)

    🚀 Why This is the Best?
    - No extra space (O(1), excluding recursion stack).
    - Elegant & concise using recursion.
    - Fast & efficient with O(n) time complexity.

    🔹 Summary Table:
    | Approach                       | Time Complexity | Space Complexity  | Notes                                      |
    |--------------------------------|---------------|-----------------|-------------------------------------------|
    | Brute Force (BFS + Queue)      | O(n)         | O(n)            | Uses a queue, extra space.                |
    | Iterative (Next Pointer Traversal) | O(n)         | O(1)            | Efficient, uses next pointers.            |
    | Optimized Recursive (DFS)      | O(n)         | O(1) (except recursion stack) | Best approach, clean and elegant.  |

    ✅ Final Recommendation:
    - If recursion is allowed, go with **Approach 3 (DFS)** for clarity and efficiency.
    - If you want an **iterative solution**, go with **Approach 2** for best performance.
    */
    private static class Optimal {//USing DFS
        public Node connect(Node root) {
            if (root == null || root.left  == null || root.right == null) return null;

            root.left.next = root.right; // Connect left to right
            if (root.next != null) {
                root.right.next = root.next.left; // Connect right to next subtree
            }

            connect(root.left);  // Recursively process left subtree
            connect(root.right); // Recursively process right subtree

            return root;
        }
    }
    
}
