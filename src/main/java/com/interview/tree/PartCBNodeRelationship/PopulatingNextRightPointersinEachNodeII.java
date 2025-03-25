package com.interview.tree.PartCBNodeRelationship;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
https://www.youtube.com/watch?v=yl-fdkyQD8A&t=313s
Category: Medium, Facebook, FAANG
Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.



Example 1:


Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
Example 2:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 6000].
-100 <= Node.val <= 100


Follow-up:

You may only use constant extra space.
The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class PopulatingNextRightPointersinEachNodeII {

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

    private static class Node {
        int val;
        Node left;
        Node right;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }
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
    (Using Next Pointers, O(1) Space)
💡 Idea:

Unlike 116. Populating Next Right Pointers in Each Node, this tree is not perfect, so we need extra logic to handle missing children.
Instead of a queue, use the next pointers to traverse the tree level by level.
Maintain a dummy head to track the start of the next level.
🔵 Time Complexity: O(n)

Each node is processed once.
🔵 Space Complexity: O(1)

No extra space apart from a few variables.
✅ Why This is Better?

Eliminates the need for a queue (O(n) → O(1) space).
Traverses level by level efficiently using the next pointers.
     */
    private static class Optimal {
        public Node connect(Node root) {
            if (root == null) return null;

            Node head = root; // Start from the root

            while (head != null) {
                Node dummy = new Node(0); // Dummy head for the next level
                Node temp = dummy; // Pointer to build next level connections

                // Traverse the current level
                while (head != null) {
                    if (head.left != null) {
                        temp.next = head.left;
                        temp = temp.next;
                    }
                    if (head.right != null) {
                        temp.next = head.right;
                        temp = temp.next;
                    }
                    head = head.next; // Move to the next node in the current level
                }

                head = dummy.next; // Move to the next level
            }

            return root;
        }
    }
}
