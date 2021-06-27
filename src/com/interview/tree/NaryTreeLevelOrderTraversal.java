package com.interview.tree;

/*
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 * Category: Easy
 */
public class NaryTreeLevelOrderTraversal {
    List<List<Integer>> result = new ArrayList<>();
    Queue<Node> q = new LinkedList<>();
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        q.add(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> currentLevelList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node currentNode = q.remove();
                currentLevelList.add(currentNode.val);
                
                for (Node node: currentNode.children) {
                    q.add(node);
                }
            }
            result.add(currentLevelList);
            
        }
        return result;
        
    }
}
