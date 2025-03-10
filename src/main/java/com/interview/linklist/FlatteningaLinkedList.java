package com.interview.linklist;

import java.util.*;

/*
 * Problem Link:
 * https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1
 * Video Explanation:
 * https://www.youtube.com/watch?v=ysytSSXpAI0&t=1088s
 *
 * Category: Medium, Must Do, Fundamental
 * Related Problem:
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 *
 * Problem Statement:
 * Given a linked list of size N, where each node contains:
 * (i) A `next` pointer to the next node in the main list.
 * (ii) A `bottom` pointer to a sorted sub-linked-list where this node is the head.
 *
 * The objective is to flatten the linked list into a single sorted list while preserving the order.
 * The flattened list should use the `bottom` pointer instead of `next`.
 *
 * Approaches:
 * 1. **Brute Force (Iterative Merge)**:
 *    - Merge two vertical lists iteratively.
 *    - This can be extended to a K-sorted merge list problem.
 *    - Using a **priority queue (min-heap)** optimizes merging multiple sorted lists efficiently.
 *
 * 2. **Recursive Solution**:
 *    - Flatten the right sublist first.
 *    - Merge the flattened right list with the current node.
 *    - Uses divide-and-conquer approach.
 *
 * Example 1:
 * Input:
 *   5 -> 10 -> 19 -> 28
 *   |     |     |     |
 *   7     20    22    35
 *   |           |      |
 *   8          50     40
 *   |                 |
 *   30               45
 *
 * Output:
 *   5 -> 7 -> 8 -> 10 -> 19 -> 20 -> 22 -> 28 -> 30 -> 35 -> 40 -> 45 -> 50
 *
 * Explanation:
 *   The entire linked list is flattened into a single sorted list.
 *   (Here, `|` represents the `bottom` pointer).
 *
 * Constraints:
 * - `0 <= N <= 50`
 * - `1 <= Mi <= 20`
 * - `1 <= Node Value <= 1000`
 *
 * Expected Complexity:
 * - **Time Complexity**: `O(N * M)`
 * - **Auxiliary Space**: `O(1)`
 */
public class FlatteningaLinkedList {

    private static class Node {
        int data;
        Node next, bottom;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.bottom = null;
        }
    }
    /*
     * Flattening a Linked List
     *
     * Given a linked list where each node has two pointers:
     * - next (points to the next node in the main list).
     * - bottom (points to the head of a sorted sub-linked-list).
     *
     * Goal:
     * Flatten this linked list into a single-level sorted linked list.
     *
     * Approaches to Solve the Problem:
     *
     * 1. Brute Force (Collect and Sort) - O(N*M log(N*M))
     *
     * Approach:
     * - Traverse the linked list and store all nodes in an array.
     * - Sort the array.
     * - Reconstruct a single-level linked list from the sorted array.
     *
     * Time Complexity:
     * - Collecting nodes: O(N * M)
     * - Sorting: O(N * M log(N * M))
     * - Reconstructing: O(N * M)
     * - Overall Complexity: O(N*M log(N*M))
     */
    private static class Bruitforce {
        public Node flatten(Node head) {
            if (head == null) return null;

            List<Node> nodeList = new ArrayList<>();

            // Collect all nodes
            Node temp = head;
            while (temp != null) {
                Node bottomNode = temp;
                while (bottomNode != null) {
                    nodeList.add(bottomNode);
                    bottomNode = bottomNode.bottom;
                }
                temp = temp.next;
            }

            // Sort nodes by data
            Collections.sort(nodeList, (a, b) -> a.data - b.data);

            // Reconstruct flattened list
            for (int i = 0; i < nodeList.size() - 1; i++) {
                nodeList.get(i).bottom = nodeList.get(i + 1);
                nodeList.get(i).next = null;
            }
            nodeList.get(nodeList.size() - 1).bottom = null;
            nodeList.get(nodeList.size() - 1).next = null;

            return nodeList.get(0);
        }
    }

    /*
     * 2. Better Approach (Merge Two Lists at a Time) - O(N*M)
     *
     * Approach:
     * - Use the "merge two sorted lists" approach.
     * - Recursively flatten the next lists.
     * - Merge the current bottom list with the recursively flattened list.
     *
     * Time Complexity:
     * - Merging takes O(M) per call.
     * - We merge for each of N nodes.
     * - Total Complexity: O(N * M).
     */

    private static class Better {

        public Node flatten(Node head) {
            if (head == null || head.next == null) return head;

            // Recursively flatten the next sublist
            head.next = flatten(head.next);

            // Merge current list with next flattened list
            head = merge(head, head.next);

            return head;
        }

        private Node merge(Node a, Node b) {
            if (a == null) return b;
            if (b == null) return a;

            Node result;

            if (a.data < b.data) {
                result = a;
                result.bottom = merge(a.bottom, b);
            } else {
                result = b;
                result.bottom = merge(a, b.bottom);
            }

            result.next = null;
            return result;
        }

    }

    /*
     * 3. Optimal Approach (Using Min-Heap / Priority Queue) - O(N*M log N)
     *
     * Approach:
     * - Use a Min Heap (PriorityQueue) to always fetch the smallest element.
     * - Insert the head nodes of each vertical list into the heap.
     * - Extract the minimum node, add its bottom node to the heap, and maintain a sorted order.
     *
     * Time Complexity:
     * - Insertion into Min-Heap: O(log N) per insertion.
     * - Each node (N*M) is inserted once: O(N*M log N).
     * - Overall Complexity: O(N*M log N).
     *
     * Comparison of Approaches:
     * --------------------------------------------------------------
     * | Approach                   | Time Complexity | Space Complexity | Method                     |
     * --------------------------------------------------------------
     * | Brute Force                | O(N*M log(N*M)) | O(N*M)           | Store and sort nodes       |
     * | Merge Two Lists Recursively | O(N*M)         | O(1)             | Recursively flatten        |
     * | Priority Queue (Min Heap)   | O(N*M log N)   | O(N)             | Use Min Heap for merging   |
     * --------------------------------------------------------------
     *
     * Conclusion:
     * - If N and M are small, the brute force method works fine.
     * - The recursive merge approach is better with O(N*M).
     * - The optimal approach using a priority queue is ideal when N is large.
     *
     * 🚀 Best Choice: Priority Queue (Min Heap) O(N*M log N)
     */

    public static class Optimal {
        public Node flatten(Node head) {
            if (head == null) return null;

            PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.data - b.data);

            // Insert all head nodes of vertical lists into heap
            while (head != null) {
                minHeap.offer(head);
                head = head.next;
            }

            Node dummy = new Node(0);
            Node current = dummy;

            // Extract the minimum node and push its bottom nodes into the heap
            while (!minHeap.isEmpty()) {
                Node minNode = minHeap.poll();
                current.bottom = minNode;
                current = current.bottom;

                if (minNode.bottom != null) {
                    minHeap.offer(minNode.bottom);
                }
            }

            return dummy.bottom;
        }
    }
    
}
