package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * Category: medium, Must Do
 * Related: find kth smallest, largest(reverse inorder) Goolge, 
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/ Easy
 */
public class KthSmallestElementinaBST {
    int result = -1;
    int processNodeCounter = 0;
    void kthSmallestUtil(TreeNode root, int k) {
        if (root == null) {
           return ; 
        }
        
        
        if ( processNodeCounter < k) {
            //System.out.println("k- Left>" + k);
            kthSmallestUtil(root.left, k); 
        }
        
        processNodeCounter++;
        //System.out.println("processNodeCounter" + processNodeCounter);
        if (processNodeCounter == k) {
            result = root.val;
            //System.out.println("Visited node value" + root.val);
            return;
        }
        
        if (processNodeCounter < k) {
            //System.out.println("k- Right>" + k);
            kthSmallestUtil(root.right, k);
        }
        
        
    }
    public int kthSmallest(TreeNode root, int k) {
        kthSmallestUtil(root, k); 
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
