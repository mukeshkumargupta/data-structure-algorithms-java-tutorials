package com.interview.bst.PartBInorderTraversalProperties;

import java.util.ArrayList;
import java.util.List;

/*
    Convert BST to Sorted Doubly Linked List (Leetcode #426)
    https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/description/

    Category: Medium

    Problem:
    Given a Binary Search Tree (BST), convert it into a sorted doubly linked list in-place (left as previous, right as next).
    The head of the list should be the smallest element, and the tail should be the largest.
 */
public class B_ConvertBSTtoSortedDoublyLinkedList {

    /*
        Approach 1: Brute Force (Using Extra Space)

        Thought Process:
        - Perform an inorder traversal to extract elements in sorted order.
        - Store the elements in a list or array.
        - Create a doubly linked list using the stored values.

        Time & Space Complexity:
        - Time Complexity: O(N) (inorder traversal + list creation)
        - Space Complexity: O(N) (extra storage for list)

        ✅ Pros: Simple and easy to implement
        ❌ Cons: Extra O(N) space is used
    */
    public static class Node {
        int val;
        Node left;
        Node right;
        Node (int val) {
            this.val = val;
        }
    }
    private static class Bruitforce {
        public Node treeToDoublyList(Node root) {
            if (root == null) return null;

            List<Node> nodes = new ArrayList<>();
            inorder(root, nodes);

            // Convert list to doubly linked list
            for (int i = 0; i < nodes.size(); i++) {
                Node curr = nodes.get(i);
                Node prev = (i == 0) ? nodes.get(nodes.size() - 1) : nodes.get(i - 1);
                Node next = (i == nodes.size() - 1) ? nodes.get(0) : nodes.get(i + 1);

                curr.left = prev;
                curr.right = next;
            }
            return nodes.get(0);
        }

        private void inorder(Node root, List<Node> nodes) {
            if (root == null) return;
            inorder(root.left, nodes);
            nodes.add(root);
            inorder(root.right, nodes);
        }
    }

/*
    Approach 2: Better (Using Recursion, In-Place), Prefer this approach easy to write

    Thought Process:
    - Use inorder traversal to process the BST in sorted order.
    - Maintain previous and head pointers to build the doubly linked list in-place.

    Time & Space Complexity:
    - Time Complexity: O(N) (single traversal)
    - Space Complexity: O(H) (recursive stack, H = height of BST)

    ✅ Pros: In-place, efficient (O(N) time, O(H) space)
    ❌ Cons: Uses recursion stack space
*/

    private static class Better {
        Node head = null, prev = null;

        public Node treeToDoublyList(Node root) {
            if (root == null) return null;
            inorder(root);

            // Connect head and tail to make it circular
            prev.right = head;
            head.left = prev;
            return head;
        }

        private void inorder(Node node) {
            if (node == null) return;

            inorder(node.left);

            if (prev == null) {
                head = node; // First node (smallest element)
            } else {
                prev.right = node;
                node.left = prev;
            }
            prev = node; // Move prev forward

            inorder(node.right);
        }
    }

    /*
        Approach 3: Best (Using Iterative Morris Traversal, Constant Space)

        Thought Process:
        - Use Morris Traversal to traverse BST without recursion.
        - Modify pointers in-place to construct the doubly linked list.
        - Use threaded BST technique to avoid using extra space.

        Time & Space Complexity:
        - Time Complexity: O(N)
        - Space Complexity: O(1) (no extra space)

        ✅ Pros: O(1) space, fully in-place conversion
        ❌ Cons: Slightly more complex

        Final Thoughts:
        | Approach                  | Time Complexity | Space Complexity | Pros             | Cons               |
        |---------------------------|----------------|------------------|------------------|--------------------|
        | Brute Force (Inorder + List) | O(N)         | O(N)             | Simple          | Uses extra space  |
        | Recursive In-Place        | O(N)         | O(H)             | Clean, easy     | Uses recursion stack |
        | Iterative Morris (Best)   | O(N)         | O(1)             | Space-efficient | More complex       |

        🚀 Recommendation:
        - For interviews, use the recursive approach (better readability).
        - For best efficiency, use the Morris traversal approach (O(1) space).
    */
    private static class Optimized {
        public Node treeToDoublyList(Node root) {
            if (root == null) return null;

            Node head = null, prev = null, curr = root;

            while (curr != null) {
                if (curr.left == null) {
                    // Process current node
                    if (prev == null) head = curr;
                    else {
                        prev.right = curr;
                        curr.left = prev;
                    }
                    prev = curr;
                    curr = curr.right;
                } else {
                    Node pred = curr.left;
                    while (pred.right != null && pred.right != curr) {
                        pred = pred.right;
                    }

                    if (pred.right == null) {
                        pred.right = curr; // Create a thread
                        curr = curr.left;
                    } else {
                        pred.right = null; // Remove thread
                        if (prev == null) head = curr;
                        else {
                            prev.right = curr;
                            curr.left = prev;
                        }
                        prev = curr;
                        curr = curr.right;
                    }
                }
            }

            // Connect head and tail to form a circular list
            if (prev != null) {
                prev.right = head;
                head.left = prev;
            }

            return head;
        }
    }
}
