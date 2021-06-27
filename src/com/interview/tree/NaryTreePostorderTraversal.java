package com.interview.tree;

/*
 * https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 * Category: Easy
 */
public class NaryTreePostorderTraversal {
    List<Integer> result = new LinkedList<>();
    void postorderUtil(Node root) {
        if (root == null) {
            return;
        }
        
        for (Node node: root.children) {
            postorderUtil(node);
        }
        result.add(root.val);
        
    }
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return result;
        }
        postorderUtil(root);
        return result;
        
    }
}
