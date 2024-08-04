package com.interview.tree;

/**
 * Find sum of TreeNode
 * https://www.youtube.com/watch?v=glaVznehaCk&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=20&t=13s
 * Here u can solver using all type of traversal what you know even in bult stack and queue you can solve the problem
 */
public class SumTreeNode {
    private int sum(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        return root.val + leftSum + rightSum; //Kind of post order  VVImp This is also good way to visualize tree. Using this concept many problems are solved.
    }
    
    private void sumPostOrder(TreeNode root, int[]sum){
        if(root == null){
            return;
        }
        sumPostOrder(root.left, sum);
        sumPostOrder(root.right, sum);
        sum[0] += root.val;  //post order traversal
    }
    
    private void sumPreOrder(TreeNode root, int[]sum){
        if(root == null){
            return;
        }
        sum[0] += root.val;  //pre order traversal
        sumPostOrder(root.left, sum);
        sumPostOrder(root.right, sum);

    }
    
    private void sumInOrder(TreeNode root, int[]sum){
        if(root == null){
            return;
        }
        sumPostOrder(root.left, sum);
        sum[0] += root.val;  //in order traversal
        sumPostOrder(root.right, sum);
    }
    
 
    
    public static void main(String args[]){
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        int inorder[] = {4,10,6,46,11,13,2};
        int preorder[] = {46,10,4,6,13,11,2};
        TreeNode root = ctf.createTree(inorder, preorder);
        SumTreeNode sn = new SumTreeNode();
        System.out.println(sn.sum(root));
        //Other method
        int[] result = new int[1];
        sn.sumPostOrder(root, result);
        System.out.println(result[0]);
        
        //Other method using preorder
        int[] resultPreOrder = new int[1];
        sn.sumPreOrder(root, resultPreOrder);
        System.out.println(resultPreOrder[0]);
        
        //Other method using inorder
        int[] resultInOrder = new int[1];
        sn.sumPreOrder(root, resultInOrder);
        System.out.println(resultInOrder[0]);
    }
}
