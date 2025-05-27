package com.interview.tree.PartATreeTraversal;

import com.interview.tree.BinaryTree;
import com.interview.tree.TreeNode;

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
public class A_Iterative_TreeTraversals {

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
    
    /*
     * https://www.youtube.com/watch?v=2YBhNLodD8Q
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 44.45% of Java online submissions for Binary Tree Postorder Traversal.
Memory Usage: 42.7 MB, less than 7.44% of Java online submissions for Binary Tree Postorder Traversal.
            Category: Easy
            Related: https://leetcode.com/problems/binary-search-tree-iterator/ Medium VImp
            https://leetcode.com/problems/closest-binary-search-tree-value-ii/ Hard Locked
            https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/ Medium Locked 2 and 3rd version is aslo locked
         */
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        if (root != null) {
            st1.push(root);
        }
        
        while(!st1.isEmpty()) {
            TreeNode current = st1.pop();
            st2.push(current);
            if (current.left != null) {
                st1.push(current.left);
            }
            
            if (current.right != null) {
                st1.push(current.right);
            }
        }
        List<Integer> result = new ArrayList<>();
        while(!st2.isEmpty()) {
            result.add(st2.pop().val);
        }
        return result;
        
    }
    //VVImp
    public List<Integer> postorderTraversalOneStack(TreeNode root) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Postorder Traversal.
Memory Usage: 40 MB, less than 47.18% of Java online submissions for Binary Tree Postorder Traversal.
TC: 2N, here N + N( when while n is equal to right then print)
SC: N
         */
        Stack<TreeNode> st1 = new Stack<>();
        TreeNode current = root;
        List<Integer> result = new ArrayList<>();
        while(current != null || !st1.isEmpty()) {
            while (current != null) {//keep trying left then 
                st1.push(current);
                current = current.left;
            }
            
            TreeNode temp = st1.peek().right;
            if (temp == null) {//it means left and right both null then print
                temp = st1.pop();
                result.add(temp.val);


                while(!st1.isEmpty() && st1.peek().right == temp) {
                    temp = st1.pop();
                    result.add(temp.val);
                }

            } else {
                current = temp;
            }
            

            
        }

        return result;
        
    }
    
    public List<Integer> postorderTraversalSlow(TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 44.45% of Java online submissions for Binary Tree Postorder Traversal.
Memory Usage: 42.2 MB, less than 27.33% of Java online submissions for Binary Tree Postorder Traversal
Related:
https://leetcode.com/problems/equal-tree-partition/ Medium Locked
https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/ Hard VVImp
https://leetcode.com/problems/correct-a-binary-tree/ Medium Locked
         */
        Stack<TreeNode> st1 = new Stack<>();
        TreeNode current = root;
        List<Integer> result = new ArrayList<>();
        while(current != null || !st1.isEmpty()) {
            if (current != null) {
                st1.push(current);
                current = current.left;
            } else {
                TreeNode temp = st1.peek().right;
                if (temp == null) {//it means left and right both null then print
                    temp = st1.pop();
                    result.add(temp.val);


                    while(!st1.isEmpty() && st1.peek().right == temp) {
                        temp = st1.pop();
                        result.add(temp.val);
                    }

                } else {
                    current = temp;
                }
            }
            

            
        }

        return result;
        
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


        A_Iterative_TreeTraversals tt = new A_Iterative_TreeTraversals();
        tt.postOrder(head);
        System.out.println();
        tt.postorderTraversal(head);
        System.out.println();
        tt.postorderTraversalOneStack(head);
    }
}
