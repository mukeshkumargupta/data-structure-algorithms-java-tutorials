package com.interview.tree.PartBPathProblems;


import com.interview.tree.TreeNode;
import com.interview.tree.PartFTreeConstruction.ConstructTreeFromInOrderPreOrder;

class Count{
    int size;
}

/**
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree/
 * A SumTree is a Binary Tree where the value of a TreeNode is equal to sum of the TreeNodes present 
 * in its left subtree and right subtree
 * Reference: //https://www.youtube.com/watch?v=zEIWqb8nWDk
 * Implemented here: isSumTree_V1()
 * Category: VVImp, Medium
 */
public class SumTree {

    public boolean isSumTree(TreeNode root){
        Count count = new Count();
        return isSumTree(root,count);
    }
    
    private boolean isSumTree(TreeNode root,Count count){// #Ignore see below solution
        if(root == null){
            return true;
        }
        if(root.left == null && root.right == null){
            count.size = root.val;
            return true;
        }
        Count leftCount = new Count();
        Count rightCount = new Count();
        boolean isLeft = isSumTree(root.left,leftCount);
        boolean isRight = isSumTree(root.right,rightCount);
        count.size = root.val + leftCount.size + rightCount.size;
        return isLeft && isRight && root.val == (leftCount.size + rightCount.size);
    }
    
    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return root.val + sum(root.left) + sum(root.right); 
    }
    
    //https://www.youtube.com/watch?v=zEIWqb8nWDk
    private boolean isSumTree_V1(TreeNode root){//Mine approach
        if(root == null){
            return true;
        }
        
        //If TreeNode is child TreeNode then return 1
        if (root.left == null && root.right == null) {
            return true;
        }

        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        //Is having any left or right then only compare
        return (leftSum + rightSum == root.val) && isSumTree_V1(root.left) && isSumTree_V1(root.right);

    }
    
    public static void main(String args[]){
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        int inorder[] = {4,10,6,46,11,13,2};
        int preorder[] = {46,10,4,6,13,11,2};
		//int inorder[] = { 4,4};
		//int preorder[] = { 4,4};
        TreeNode root = ctf.createTree(inorder, preorder);
        SumTree st = new SumTree();
        System.out.println(st.isSumTree(root));
        System.out.println(st.isSumTree_V1(root));
    }
}
