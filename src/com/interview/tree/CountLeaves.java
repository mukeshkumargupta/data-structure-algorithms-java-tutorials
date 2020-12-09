package com.interview.tree;

/*
 * 
 * //Reference: https://practice.geeksforgeeks.org/problems/count-leaves-in-binary-tree/1
 * //Category: Must Do
 * //Note: Print All Leaves, print all left leaves sums or right leaves sum (done) or true, sum all leave whose parent have only one leave either left or right(Logic: check both left and right not null if yes return false, if any one null return true, pass this argument),  if two tree have same leaves pattern
 * example https://leetcode.com/problems/leaf-similar-trees/
 * It is not similar question but must solve it https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 * Category: Easy, All leaves related questions
*/
public class CountLeaves {
    void countLeavesUtil(Node root, int[] result) {
        // Your code
        if (root == null) {
            return;
        }
        // Check leave node
        if (root.left == null && root.right == null) {
            result[0] = result[0] + 1;
        }
        countLeavesUtil(root.left, result);
        countLeavesUtil(root.right, result);
    }
    
    int countLeaves(Node node) {
        // Your code
        int[] result = new int[1];
        countLeavesUtil(node, result);
        return result[0];
    }
    
    // Reference: https://www.youtube.com/watch?v=gPj3Fhekys8
    // https://leetcode.com/problems/sum-of-left-leaves/submissions/ Similarly u can
    // get right leave sum or count of leave
    int leftSum = 0;
    
    private void sumOfLeftLeavesUtil(Node root, boolean isLeft) {
        if (root == null)
            return;
        
        if (isLeft && root.left == null && root.right == null) {
            leftSum += root.data;
            return;
        }
        
        sumOfLeftLeavesUtil(root.left, true);
        sumOfLeftLeavesUtil(root.right, false);
        
    }
    
    public int sumOfLeftLeaves(Node root) {
        if (root == null)
            return 0;
        
        sumOfLeftLeavesUtil(root, false);
        return leftSum;
    }
}
