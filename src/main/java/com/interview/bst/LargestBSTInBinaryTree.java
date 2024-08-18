package com.interview.bst;

import com.interview.tree.PartFTreeConstruction.ConstructTreeFromInOrderPreOrder;
import com.interview.tree.TreeNode;

/**
 * Date 07/20/2017
 * @author Mukesh Kumar Gupta
 * 
 * Video link - https://youtu.be/4fiDs7CCxkc
 * 
 * Given a binary tree, find size of largest binary search subtree in this
 * binary tree.
 * 
 * Traverse tree in post order fashion. Left and right TreeNodes return 4 piece
 * of information to root which isBST, size of max BST, min and max in those
 * subtree. 
 * If both left and right subtree are BST and this TreeNode val is greater than max
 * of left and less than min of right then it returns to above level left size +
 * right size + 1 and new min will be min of left side and new max will be max of
 * right side.
 * 
 * References: https://www.youtube.com/watch?v=4fiDs7CCxkc&list=PLIA-9QRQ0RqG6CNfSJSzT0h5Pc_HvwZG5&index=72
 * http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
 * https://leetcode.com/problems/largest-bst-subtree/Locked
 * Category: Must Do, Fundamental
 * Note: This question is same as finding diameter of tree
 */
public class LargestBSTInBinaryTree {
    /**
     * Object of this class holds information which child passes back
     * to parent TreeNode.
     */
    class MinMax{
        int min;
        int max;
        boolean isBST;
        int size ;
        
        MinMax(){
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            isBST = true;
            size = 0;
        }
    }

    public int largestBST(TreeNode root){
        //
        MinMax m = largest(root);
        return m.size;
    }
    
    
    private MinMax largest(TreeNode root){
        //if root is null return min as Integer.MAX and max as Integer.MIN 
        if(root == null){
            return new MinMax();
        }
        
        //postorder traversal of tree. First visit left and right then
        //use information of left and right to calculate largest BST.
        MinMax leftMinMax = largest(root.left);
        MinMax rightMinMax =largest(root.right);
        
        MinMax minMaxNode = new MinMax();
        
        //if either of left or right subtree says its not BST or the val
        //of this TreeNode is not greater/equal than max of left and less than min of right
        //then subtree with this TreeNode as root will not be BST. 
        //Return false and max size of left and right subtree to parent
        if(leftMinMax.isBST == false || rightMinMax.isBST == false || (leftMinMax.max >= root.val || rightMinMax.min <= root.val)){
            minMaxNode.isBST = false;
            minMaxNode.size = Math.max(leftMinMax.size, rightMinMax.size);
            return minMaxNode;
        }
        
        //if we reach this point means subtree with this TreeNode as root is BST.
        //Set isBST as true. Then set size as size of left + size of right + 1.
        //Set min and max to be returned to parent.
        minMaxNode.isBST = true;
        minMaxNode.size = leftMinMax.size + rightMinMax.size + 1;
     
        //if root.left is null then set root.val as min else
        //take min of left side as min
        minMaxNode.min = root.left != null ? leftMinMax.min : root.val;
  
        //if root.right is null then set root.val as max else
        //take max of right side as max.
        minMaxNode.max = root.right != null ? rightMinMax.max : root.val;
   
        return minMaxNode;
    }
    
    public static void main(String args[]){
        LargestBSTInBinaryTree lbi = new LargestBSTInBinaryTree();
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        //this is just to create a binary tree.
        int inorder[]  = {-7,-6,-5,-4,-3,-2,1,2,3,16,6,10,11,12,14};
        int preorder[] = {3,-2,-3,-4,-5,-6,-7,1,2,16,10,6,12,11,14};
        TreeNode root = ctf.buildTree(inorder, preorder);
        int largestBSTSize = lbi.largestBST(root);
        System.out.println("Size of largest BST  is " + largestBSTSize);
        assert largestBSTSize == 8;
    }
}


