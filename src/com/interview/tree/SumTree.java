package com.interview.tree;


class Count{
    int size;
}

/**
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree/
 * A SumTree is a Binary Tree where the value of a node is equal to sum of the nodes present 
 * in its left subtree and right subtree
 * Reference: //https://www.youtube.com/watch?v=zEIWqb8nWDk
 * Implemented here: isSumTree_V1()
 * Category: VVImp, Medium
 */
public class SumTree {

    public boolean isSumTree(Node root){
        Count count = new Count();
        return isSumTree(root,count);
    }
    
    private boolean isSumTree(Node root,Count count){// #Ignore see below solution
        if(root == null){
            return true;
        }
        if(root.left == null && root.right == null){
            count.size = root.data;
            return true;
        }
        Count leftCount = new Count();
        Count rightCount = new Count();
        boolean isLeft = isSumTree(root.left,leftCount);
        boolean isRight = isSumTree(root.right,rightCount);
        count.size = root.data + leftCount.size + rightCount.size;
        return isLeft && isRight && root.data == (leftCount.size + rightCount.size);
    }
    
    private int sum(Node root) {
        if (root == null) {
            return 0;
        }
        
        return root.data + sum(root.left) + sum(root.right); 
    }
    
    //https://www.youtube.com/watch?v=zEIWqb8nWDk
    private boolean isSumTree_V1(Node root){//Mine approach
        if(root == null){
            return true;
        }
        
        //If node is child node then return 1
        if (root.left == null && root.right == null) {
            return true;
        }

        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        //Is having any left or right then only compare
        return (leftSum + rightSum == root.data) && isSumTree_V1(root.left) && isSumTree_V1(root.right);

    }
    
    public static void main(String args[]){
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        int inorder[] = {4,10,6,46,11,13,2};
        int preorder[] = {46,10,4,6,13,11,2};
		//int inorder[] = { 4,4};
		//int preorder[] = { 4,4};
        Node root = ctf.createTree(inorder, preorder);
        SumTree st = new SumTree();
        System.out.println(st.isSumTree(root));
        System.out.println(st.isSumTree_V1(root));
    }
}
