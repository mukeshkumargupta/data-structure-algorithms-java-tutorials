package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/vertex-cover-problem-set-2-dynamic-programming-solution-tree/
 * http://en.wikipedia.org/wiki/Vertex_cover
 * Using lis to store the cover val
 * Test cases:
 * null root
 * Only left child
 * Only right child
 * Tree with only one child at every TreeNode
 */
public class VertexCoverBinaryTreeDP {

    public int cover(TreeNode root){
        if(root == null){
            return 0;
        }
        //no need to include leaf TreeNode ever
        if(root.left == null && root.right == null){
            return 0;
        }
        //store result
        if(root.lis != -1){
            return root.lis;
        }
        //if root is included
        int inclRoot = 1 + cover(root.left) + cover(root.right);
        int exclRoot = 0;
        //if root is not included
        if(root.left!=null){
            exclRoot += (1 + cover(root.left.left) + cover(root.left.right));
        }
        if(root.right!=null){
            exclRoot += (1 + cover(root.right.left) + cover(root.right.right));
        }
        root.lis = Math.min(inclRoot, exclRoot);
        return root.lis;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode root = null;
        root = bt.addTreeNode(10, root);
        root = bt.addTreeNode(0, root);
        root = bt.addTreeNode(-5, root);
        root = bt.addTreeNode(5, root);
        root = bt.addTreeNode(7, root);
        root = bt.addTreeNode(3, root);
        root = bt.addTreeNode(30, root);
        root = bt.addTreeNode(40, root);
        root = bt.addTreeNode(25, root);
        root = bt.addTreeNode(46, root);
        root = bt.addTreeNode(-8, root);
        root = bt.addTreeNode(-2, root);
        root = bt.addTreeNode(-1, root);
        root = bt.addTreeNode(28, root);
        VertexCoverBinaryTreeDP vc = new VertexCoverBinaryTreeDP();
        System.out.println(vc.cover(root));
    }
}
