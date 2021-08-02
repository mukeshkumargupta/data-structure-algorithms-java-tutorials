package com.interview.tree;

import java.util.*;

/**
 * Reference:
 * Youtube link - https://youtu.be/nzmtCFNae9k
 * Youtube link - https://youtu.be/elQcrJrfObg
 * Youtube link - https://youtu.be/qT65HltK2uE
 * Youtube link - https://youtu.be/ZM-sV9zQPEs
 * 
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
 * http://www.geeksforgeeks.org/iterative-preorder-traversal/
 * Category: Must Do, VVImp
 * Related using iteration technics
 * https://leetcode.com/problems/binary-search-tree-iterator/ Medium
 * https://leetcode.com/problems/closest-binary-search-tree-value-ii/ Hard
 * https://leetcode.com/problems/inorder-successor-in-bst/ Medium Solved
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/ Medium
 */
public class TreeTraversals {

    public void inOrder(TreeNode root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }
    
    public void preOrder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    
    public void postOrder(TreeNode root){
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    //Tricky
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        List<Integer> result = new ArrayList<>();
        
        
        TreeNode currentNode = root;
        
        while(!s.isEmpty() || currentNode != null) {
            while (currentNode != null) {
                s.add(currentNode);
                currentNode = currentNode.left;
            }
            TreeNode top = s.pop();
            result.add(top.val);
            currentNode = top.right;
            
        }
        return result;

        
    }
    
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            s.push(root);
        }
        
        while (!s.isEmpty()) {
            TreeNode currentTreeNode = s.pop();
            result.add(currentTreeNode.val);
            
            if (currentTreeNode.right != null) {
                s.add(currentTreeNode.right);
            }
            
            if (currentTreeNode.left != null) {
                s.add(currentTreeNode.left);
            }
            
        }
        return result;
        
    }
    
    public void postOrderItr(TreeNode root){
        Deque<TreeNode> stack1 = new LinkedList<TreeNode>();
        Deque<TreeNode> stack2 = new LinkedList<TreeNode>();
        stack1.addFirst(root);
        while(!stack1.isEmpty()){
            root = stack1.pollFirst();
            if(root.left != null){
                stack1.addFirst(root.left);
            }
            if(root.right != null){
                stack1.addFirst(root.right);
            }
            stack2.addFirst(root);
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.pollFirst().val + " ");
        }
    }
    //VVImp
    public void postOrderItrOneStack(TreeNode root){
        TreeNode current = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while(current != null || !stack.isEmpty()){
            if(current != null){
                stack.addFirst(current);
                current = current.left;
            }else{
                TreeNode temp = stack.peek().right;
                if (temp == null) {
                    temp = stack.poll();
                    System.out.print(temp.val + " ");
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.poll();
                        System.out.print(temp.val + " ");
                    }
                } else {
                    current = temp;
                }
            }
        }
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode head = null;
        head = bt.addTreeNode(10, head);
        head = bt.addTreeNode(15, head);
        head = bt.addTreeNode(19, head);
        head = bt.addTreeNode(17, head);
        head = bt.addTreeNode(11, head);

        head = bt.addTreeNode(-11, head);


        TreeTraversals tt = new TreeTraversals();
        tt.postOrder(head);
        System.out.println();
        tt.postOrderItr(head);
        System.out.println();
        tt.postOrderItrOneStack(head);
    }
}
