package com.interview.tree;

/*
 * 
 * //Reference: https://practice.geeksforgeeks.org/problems/count-leaves-in-binary-tree/1
 * //Category: Must Do
 * //Note: Print All Leaves, print all left leaves sums or right leaves sum (done) or true, sum all leave whose parent have only one leave either left or right(Logic: check both left and right not null if yes return false, if any one null return true, pass this argument),  if two tree have same leaves pattern
 * example https://leetcode.com/problems/leaf-similar-trees/
 * It is not similar question but must solve it https://leetcode.com/problems/count-good-TreeNodes-in-binary-tree/
 * Category: Easy, All leaves related questions
*/
public class CountLeaves {
    void countLeavesUtil(TreeNode root, int[] result) {
        // Your code
        if (root == null) {
            return;
        }
        // Check leave TreeNode
        if (root.left == null && root.right == null) {
            
            
            result[0] = result[0] + 1;
        }
        countLeavesUtil(root.left, result);
        countLeavesUtil(root.right, result);
    }
    
    int countLeaves(TreeNode TreeNode) {
        // Your code
        int[] result = new int[1];
        countLeavesUtil(TreeNode, result);
        return result[0];
    }
    
    // Reference: https://www.youtube.com/watch?v=gPj3Fhekys8
    // https://leetcode.com/problems/sum-of-left-leaves/submissions/ Similarly u can
    // get right leave sum or count of leave
    int leftSum = 0;
    
    private void sumOfLeftLeavesUtil(TreeNode root, boolean isLeft) {
        if (root == null)
            return;
        
        if (isLeft && root.left == null && root.right == null) {
            leftSum += root.val;
            return;
        }
        
        sumOfLeftLeavesUtil(root.left, true);
        sumOfLeftLeavesUtil(root.right, false);
        
    }
    
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        
        sumOfLeftLeavesUtil(root, false);
        return leftSum;
    }
    
    // Reference: https://www.youtube.com/watch?v=gPj3Fhekys8
    // https://leetcode.com/problems/sum-of-left-leaves/submissions/
    //Second variant, Solve by passing one argument like for left, true and right false and add check, that is other way to solve
    int sum = 0;
    public void sumOfLeftLeavesUtil_V1(TreeNode root) {
        if (root == null) {
            return;
        }
        
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {//Leaf TreeNode
                sum += root.left.val;
            } 
        }
        sumOfLeftLeavesUtil(root.left);
        sumOfLeftLeavesUtil(root.right);
    }
    public int sumOfLeftLeaves_V1(TreeNode root) {
        sumOfLeftLeavesUtil(root);
        return sum;
    }
    
    //sum all leave whose parent have only one leave either left or right
    // Need to run on leetcode paltform, not run yet
    //Second variant
    int sumofLeftOrRightRootOnly = 0;
    public void sumOfLeftOrRightLeavesOnlyUtil(TreeNode root) {
        if (root == null) {
            return;
        }
        
        if (!(root.left != null && root.right != null) ) {
            //Check which child is leaf TreeNode
            if (root.left != null) {
                if (root.left.left == null && root.left.right == null) {//Leaf TreeNode
                    sum += root.left.val;
                }
            } else {
                if (root.right.left == null && root.right.right == null) {//Leaf TreeNode
                    sum += root.right.val;
                }
            }
 
        }
        sumOfLeftLeavesUtil(root.left);
        sumOfLeftLeavesUtil(root.right);
    }
    public int sumOfLeftOrRightOnlyLeaves(TreeNode root) {
        sumOfLeftOrRightLeavesOnlyUtil_V1(root);
        return sum;
    }
}
