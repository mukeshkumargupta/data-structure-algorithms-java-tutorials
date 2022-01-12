package com.interview.tree;


import java.util.*;
/**
 * Date 03/22/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a binary tree, find the maximum path sum. For this problem, a path is defined as any sequence of TreeNodes
 * from some starting TreeNode to any TreeNode in the tree along the parent-child connections.
 * 
 * Time complexity O(n)
 * Space complexity depends on depth of tree.
 * https://www.youtube.com/watch?v=WszrfSwMz58 Best explanation
 * Reference: https://www.youtube.com/watch?v=TO5zsKtc1Ic
 *
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * Category: Hard, Tricky
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

 

Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
 */
public class BinaryTreeMaximumPathSum {
    public int maxPathSumUtil(TreeNode root, int[] max) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Maximum Path Sum.
Memory Usage: 41 MB, less than 64.36% of Java online submissions for Binary Tree Maximum Path Sum.
         */
        if (root == null) {
            return 0;
        }
        int leftSum = Math.max(0, maxPathSumUtil(root.left, max));
        int rightSum = Math.max(0, maxPathSumUtil(root.right, max));
        max[0] = Math.max(max[0], root.val + leftSum + rightSum);
        return root.val + Math.max(leftSum, rightSum);
        
    }
    



      class Node {

        int label;
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
        }
        
        //List<Node<T>> nodes;
       }

/*public static void main(String[] args) {

    Integer[] parent = new Integer[] { -1, 0, 0, 1, 1, 3, 5 };
    Integer[] values = new Integer[] { 0, 4, 6, -11, 3, 10, 11 };

    List<Integer> list1 = new ArrayList<>(Arrays.asList(parent));
    List<Integer> list2 = new ArrayList<>(Arrays.asList(values));
    bestPossibleSum(list1, list2);
}

static List<Node<Integer>> tree = new ArrayList<>();

private static void bestPossibleSum(List<Integer> list1, List<Integer> list2) {
    int adj[][] = new int[list1.size()][list1.size()];
    createTree(list1, list2, adj);
    List<Integer> traversedNodes = new ArrayList<>();
    List<Integer> sumOfraversedNodes = new ArrayList<>();

    for (int i = 0; i < adj.length; i++) {
        dfs(tree.get(i), traversedNodes, sumOfraversedNodes);
        traversedNodes.clear();
    }

    System.out.println(sumOfraversedNodes);
}

private static void dfs(Node<Integer> tree, List<Integer> traversedNodes, List<Integer> sums) {
    if (!traversedNodes.contains(tree.label)) {
        traversedNodes.add(tree.label);
        sums.add(getSum(traversedNodes));
        for (Node<Integer> child : tree.nodes) {
            dfs(child, traversedNodes, sums);
        }
    }
}

private static Integer getSum(List<Integer> traversedNodes) {
    System.out.println(traversedNodes);
    return traversedNodes.stream().reduce(0, Integer::sum);
}

private static void createTree(List<Integer> parent, List<Integer> values, int[][] adj) {

    for (int i = 0; i < parent.size(); i++) {
        Node<Integer> node = new Node<>();
        node.label = i;
        node.data = values.get(i);
        node.nodes = new ArrayList<>();
        tree.add(i, node);
    }

    for (int i = 0; i < parent.size(); i++) {
        if (parent.get(i) != -1) {
            adj[parent.get(i)][i] = 1;
            adj[i][parent.get(i)] = 1;
            tree.get(parent.get(i)).nodes.add(tree.get(i));
        }
    }

    tree.forEach(t -> {
        System.out.println(t.label);
        System.out.println(t.nodes.stream().map(m -> m.label).collect(Collectors.toList()));
    });
    // System.out.println(Arrays.deepToString(adj));
}*/

      
      private int maxSum(Node btree, int[] result) {

          if (btree == null)
              return 0;
          
          int l = maxSum(btree.left, result);
          int r = maxSum(btree.right, result);

          int maxSingle = Math.max(Math.max(l, r) + btree.label, btree.label);
          int maxTop = Math.max(l + r + btree.label, maxSingle);

          result[0] = Math.max(maxTop, result[0]);
          return maxSingle;
      }

      private Node createBT(Integer[] parent, Node root) {
          Map<Integer, Node> map = new HashMap<>();

          for (int i = 0; i < parent.length; i++) {
              map.put(i, new Node(i));
          }

          for (int i = 0; i < parent.length; i++) {
              if (parent[i] == -1) {
                  root = map.get(i);
              } else {
                  Node par = map.get(parent[i]);
                  Node child = map.get(i);
                  if (par.left == null) {
                      par.left = child;
                  } else {
                      par.right = child;
                  }
              }
          }
          return root;
      }
    
    public int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPathSumUtil(root, max);
        return max[0]; 
    }
}
