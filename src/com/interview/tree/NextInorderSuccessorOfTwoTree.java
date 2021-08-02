package com.interview.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Next inorder iterator of two trees.
 * Merging tree to print one result
 */
public class NextInorderSuccessorOfTwoTree {

    private TreeNode root1 = null;
    private TreeNode root2 = null;
    Stack<TreeNode> stack1 = new Stack<TreeNode>();
    Stack<TreeNode> stack2 = new Stack<TreeNode>();
    Set<TreeNode> visited = new HashSet<TreeNode>();

    NextInorderSuccessorOfTwoTree(TreeNode root1, TreeNode root2) {
        this.root1 = root1;
        this.root2 = root2;
    }

    public boolean hasNext() {
        if (root1 != null || stack1.size() > 0 || root2 != null
                || stack2.size() > 0) {
            return true;
        }
        return false;
    }

    public TreeNode next() {
        TreeNode TreeNode = null;
        while (root1 != null) {
            stack1.push(root1);
            root1 = root1.left;
        }
        while (root2 != null) {
            stack2.push(root2);
            root2 = root2.left;
        }
        if (!stack1.isEmpty() && !stack2.isEmpty()) {
            if (stack1.peek().val <= stack2.peek().val) {
                TreeNode = stack1.pop();
                root1 = TreeNode.right;
            } else {
                TreeNode = stack2.pop();
                root2 = TreeNode.right;
            }
        } else if (stack1.isEmpty()) {
            TreeNode = stack2.pop();
            root2 = TreeNode.right;
        } else {
            TreeNode = stack1.pop();
            root1 = TreeNode.right;
        }
        return TreeNode;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode TreeNode = null;
        TreeNode = bt.addTreeNode(10, TreeNode);
        TreeNode = bt.addTreeNode(-5, TreeNode);
        TreeNode = bt.addTreeNode(7, TreeNode);
        TreeNode = bt.addTreeNode(20, TreeNode);
        TreeNode = bt.addTreeNode(3, TreeNode);
        TreeNode = bt.addTreeNode(14, TreeNode);
     
        TreeNode TreeNode1 = null;
        TreeNode1 = bt.addTreeNode(8, TreeNode1);
        TreeNode1 = bt.addTreeNode(-10, TreeNode1);
        TreeNode1 = bt.addTreeNode(18, TreeNode1);
        TreeNode1 = bt.addTreeNode(2, TreeNode1);
        TreeNode1 = bt.addTreeNode(11, TreeNode1);
        
        NextInorderSuccessorOfTwoTree nis = new NextInorderSuccessorOfTwoTree(TreeNode, TreeNode1);
        while(nis.hasNext()){
            System.out.println(nis.next().val);
        }
        
    }

}
