package com.interview.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * http://www.glassdoor.com/Interview/Create-an-iterator-to-traverse-a-binary-tree-When-the-next-function-is-called-on-the-binary-tree-return-the-value-at-the-QTN_674695.htm
 * null tree.
 */
public class NextInorderSuccessorIterator {

    private TreeNode root = null;
    Stack<TreeNode> stack = new Stack<TreeNode>();
    Set<TreeNode> visited = new HashSet<TreeNode>();
    NextInorderSuccessorIterator(TreeNode root){
        this.root = root;
    }
    
    public int next(){
        TreeNode TreeNode = null;
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        TreeNode = root;
        root = root.right;
        return TreeNode.val;
    }
    
    public boolean hasNext(){
        if(root != null || stack.size() > 0){
            return true;
        }
        return false;
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
        NextInorderSuccessorIterator nis = new NextInorderSuccessorIterator(TreeNode);
        while(nis.hasNext()){
            System.out.println(nis.next());
        }
    }
}
