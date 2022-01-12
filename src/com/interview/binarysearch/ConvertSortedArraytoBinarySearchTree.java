package com.interview.binarysearch;
/*
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * https://www.youtube.com/watch?v=_Vo3cQ2xtqk
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/swim-in-rising-water/ Hard
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/ Medium
 * https://leetcode.com/problems/minimum-skips-to-arrive-at-meeting-on-time/ Hard
 * 
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

 

Example 1:


Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:


Input: nums = [1,3]
Output: [3,1]
Explanation: [1,3] and [3,1] are both a height-balanced BSTs.
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
 */
public class ConvertSortedArraytoBinarySearchTree {
    public TreeNode bst(int[] nums, int start, int end) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
        if (start > end) {
            return null;
        }
        int mid = start + (end -start)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = bst(nums, start, mid-1);
        root.right = bst(nums, mid+1, end);
        return root;
        
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int start = 0;
        int end = nums.length -1;
        return bst(nums, start, end);
    }
}
