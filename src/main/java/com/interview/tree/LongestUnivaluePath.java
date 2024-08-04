package com.interview.tree;

/*
 * https://leetcode.com/problems/longest-univalue-path/
 * Category: Medium, Must Do, postorder, VVImp
 * Related: 
 * https://leetcode.com/problems/count-univalue-subtrees/ Medium Locked
 * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/ Hard VVimp
 * 
 * 687. Longest Univalue Path
Medium

3078

608

Add to List

Share
Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.

The length of the path between two nodes is represented by the number of edges between them.

 

Example 1:


Input: root = [5,4,5,1,1,5]
Output: 2
Example 2:


Input: root = [1,4,5,4,4,5]
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000
The depth of the tree will not exceed 1000.
Accepted
140.8K
Submissions
357.7K
 */
public class LongestUnivaluePath {
    public int longestUnivaluePath(TreeNode root) {
        /*
         * Runtime: 2 ms, faster than 99.04% of Java online submissions for Longest Univalue Path.
Memory Usage: 46.1 MB, less than 79.21% of Java online submissions for Longest Univalue Path.
         */
        int[] longestPath = new int[1];
        findLongestPathPostOrder(longestPath,root);
        return longestPath[0];
    }
    public int findLongestPathPostOrder(int[] longestPath, TreeNode root){
        if(root == null) return 0;
        int left = findLongestPathPostOrder(longestPath, root.left);
        int right = findLongestPathPostOrder(longestPath, root.right);
        int currLeft = 0, currRight = 0;
        if(left != 0 && root.val == root.left.val) currLeft += left;
        if(right != 0 && root.val == root.right.val) currRight += right;
        if(currLeft + currRight > longestPath[0]) longestPath[0] = currLeft + currRight;
        return Math.max(currLeft, currRight) + 1;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
