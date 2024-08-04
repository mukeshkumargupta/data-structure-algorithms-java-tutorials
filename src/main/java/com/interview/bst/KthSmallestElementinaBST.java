package com.interview.bst;

/*
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * Category: medium, Must Do, Facebook
 * Related: find kth smallest, largest(reverse inorder) Goolge, 
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/ Easy
 * Related: https://leetcode.com/problems/maximum-average-subtree/ Medium
 * https://leetcode.com/problems/validate-binary-tree-nodes/ Medium
 * https://leetcode.com/problems/count-nodes-with-the-highest-score/ Medium
 * 
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

 

Example 1:


Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:


Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
 

Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104
 

Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
 */
public class KthSmallestElementinaBST {
    //100 fast
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
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Kth Smallest Element in a BST.
Memory Usage: 42.1 MB, less than 27.09% of Java online submissions for Kth Smallest Element in a BST.
         */
        kthSmallestUtil(root, k); 
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
