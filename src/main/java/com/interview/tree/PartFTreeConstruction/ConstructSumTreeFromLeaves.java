package com.interview.tree.PartFTreeConstruction;

import com.interview.tree.TreeNode;

/*
 * https://stackoverflow.com/questions/44212271/building-a-sum-tree-from-leaves
 * Category: medium, Tricky, Must Do
 */
public class ConstructSumTreeFromLeaves {
    public TreeNode sumTreeFromLeaves(int[] array) {
        return sumTreeFromLeavesUtil(array, 0, array.length-1);
    }
        

    private TreeNode sumTreeFromLeavesUtil(int[] array, int start, int end) {
        if (start == end) {
          //return new TreeNode();
            return TreeNode.newTreeNode(array[start]);
        }

            
        int mid = start + (end - start)/2;
        TreeNode l = sumTreeFromLeavesUtil(array, start, mid);
        TreeNode r = sumTreeFromLeavesUtil(array, mid+1, end);
        TreeNode root = TreeNode.newTreeNode(l.val + r.val);
        root.left = l;
        root.right = r;
        return root;
                
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ConstructSumTreeFromLeaves instance = new ConstructSumTreeFromLeaves();
        //int[] input = {1,5,6,8,9,3,7};
        //int[] input = {1,5,6,8,9,3,7, 8};
        int[] input = {1,5, 6};
        TreeNode root = instance.sumTreeFromLeaves(input);
        System.out.println(root.val);
        
    }
    
}
