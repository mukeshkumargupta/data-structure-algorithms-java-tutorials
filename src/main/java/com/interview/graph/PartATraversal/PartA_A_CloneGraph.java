package com.interview.graph.PartATraversal;

import java.util.*;

/*
 * Clone an undirected graph. Each TreeNode in the graph contains a label and a list of its neighbors.
 * https://www.youtube.com/watch?v=f2EfGComRKM&t=622s
 * https://leetcode.com/problems/clone-graph/
 * Category: Medium, Must Do, Facebook, Fundamental, FAANG
 * Related: https://leetcode.com/problems/clone-binary-tree-with-random-pointer/ Medium
 * https://leetcode.com/problems/clone-n-ary-tree/ Medium
 * 
 * Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

 

Example 1:


Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
Example 2:


Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.
Example 4:


Input: adjList = [[2],[1]]
Output: [[2],[1]]
 

Constraints:

The number of nodes in the graph is in the range [0, 100].
1 <= Node.val <= 100
Node.val is unique for each node.
There are no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node
 */
/*
    https://chatgpt.com/c/f94c7d35-afd1-407b-b782-e24b251cc83c reference:
    To clone an undirected graph, you can use Depth-First Search (DFS) or Breadth-First Search (BFS). The key is to keep track of visited nodes to avoid infinite loops and ensure that each node is cloned only once. Here, I'll provide a solution using DFS along with a detailed explanation.

    Steps to Clone the Graph
    Initialize Data Structures:

    Use a map (visited) to store the cloned nodes. This helps in checking if a node has already been cloned and in accessing the cloned node directly.
    DFS Helper Function:

    This function clones a node and all its neighbors recursively.
    If the node has already been cloned (exists in visited), return the cloned node to avoid re-processing.
    Otherwise, create a new node, add it to the visited map, and recursively clone its neighbors.
    Handle Edge Cases:

    If the input node is null, return null.
    Explanation of the Code
    Class Definition:

    Node class represents a graph node with an integer value and a list of neighbors.
    Solution Class:

    The Solution class contains the cloneGraph method which performs the graph cloning.
    visited is a map used to keep track of the original node to its cloned counterpart to avoid cycles and redundant cloning.
    cloneGraph Method:

    Base Case: If the input node is null, return null.
    Check Visited Map: If the node is already cloned (exists in visited), return the cloned node.
    Clone the Node: Create a new node with the same value as the input node.
    Store the Clone: Add the cloned node to the visited map.
    Clone Neighbors: Iterate through the neighbors of the node, recursively clone them, and add them to the neighbors list of the cloned node.
    Finally, return the cloned node.
    Edge Cases
    Empty Graph: If the input node is null, the method returns null.
    Single Node without Neighbors: The method correctly handles the case where the graph consists of a single node with no neighbors.
    Connected Graph: Since the graph is connected, the method will clone all nodes starting from the given node.
    This approach ensures that the entire graph is cloned correctly, with each node and its relationships preserved. The use of a visited map prevents infinite recursion and ensures that each node is cloned only once.
 */
public class PartA_A_CloneGraph {
    public static class DFSApproach {
        // Definition for a TreeNode.
        private static class Node {
            public int val;
            public List<Node> neighbors;
            public Node() {
                val = 0;
                neighbors = new ArrayList<Node>();
            }
            public Node(int _val) {
                val = _val;
                neighbors = new ArrayList<Node>();
            }
            public Node(int _val, ArrayList<Node> _neighbors) {
                val = _val;
                neighbors = _neighbors;
            }
        }
        // Map to store cloned nodes
        private Map<Node, Node> visited = new HashMap<>();

        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }

            // If the node was already cloned, return the cloned node
            if (visited.containsKey(node)) {
                return visited.get(node);
            }

            // Clone the node
            Node cloneNode = new Node(node.val, new ArrayList<>());
            // Save the clone to the visited map
            visited.put(node, cloneNode);

            // Iterate through the neighbors to clone them recursively
            for (Node neighbor : node.neighbors) {
                cloneNode.neighbors.add(cloneGraph(neighbor));
            }

            return cloneNode;
        }
    }


    public static class BFSApproach {

    /*
    BFS Approach
     */
    // Definition for a TreeNode.
    private static class Node {
        public int val;
        public List<DFSApproach.Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<DFSApproach.Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<DFSApproach.Node>();
        }
        public Node(int _val, ArrayList<DFSApproach.Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }

            // Map to store cloned nodes
            Map<Node, Node> visited = new HashMap<>();
            // Initialize the queue for BFS
            Queue<Node> queue = new LinkedList<>();

            // Clone the root node
            Node cloneNode = new Node(node.val, new ArrayList<>());
            // Put the cloned node in the visited map
            visited.put(node, cloneNode);
            // Enqueue the original node
            queue.add(node);

            while (!queue.isEmpty()) {
                // Dequeue a node
                Node currentNode = queue.poll();

                // Iterate through its neighbors
                for (Node neighbor : currentNode.neighbors) {
                    if (!visited.containsKey(neighbor)) {
                        // Clone the neighbor
                        Node neighborClone = new Node(neighbor.val, new ArrayList<>());
                        // Put the cloned neighbor in the visited map
                        visited.put(neighbor, neighborClone);
                        // Enqueue the original neighbor
                        queue.add(neighbor);
                    }
                    // Link the cloned current node to the cloned neighbor
                    visited.get(currentNode).neighbors.add(visited.get(neighbor));
                }
            }

            return cloneNode;
        }
    }


}
