package com.interview.bst;

import java.util.*;
/*
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/ 
 * https://www.youtube.com/watch?v=ssL3sHwPeb4
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/ Easy
 * https://leetcode.com/problems/two-sum-iii-val-structure-design/ Easy
 * https://leetcode.com/problems/two-sum-bsts/ Medium
 * 
 * Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.

 

Example 1:


Input: root = [5,3,6,2,4,null,7], k = 9
Output: true
Example 2:


Input: root = [5,3,6,2,4,null,7], k = 28
Output: false
Example 3:

Input: root = [2,1,3], k = 4
Output: true
Example 4:

Input: root = [2,1,3], k = 1
Output: false
Example 5:

Input: root = [2,1,3], k = 3
Output: true
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-104 <= Node.val <= 104
root is guaranteed to be a valid binary search tree.
-105 <= k <= 105
 */
public class TwoSumIVInputisaBST {
    Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {//97.57 runtime
        if (root == null) {
            return false;
        }
        
        if (!set.contains(k-root.val)) {
            set.add(root.val);
        } else {
            return true;
        }
        
        if (findTarget (root.left, k) || findTarget (root.right, k))  {
            return true;
        } else {
            return false;
        }
    }
    
    
    //Method 2
    boolean found = false;
    public void findTargetUtilM2(TreeNode root, int k) {
        /*
         * Runtime: 8 ms, faster than 17.48% of Java online submissions for Two Sum IV - Input is a BST.
Memory Usage: 49.2 MB, less than 19.77% of Java online submissions for Two Sum IV - Input is a BST
         */
        if (root == null) {
            return;
        }
        
        if (!found) {
            findTarget (root.left, k);
        }
        
        if (!set.contains(k-root.val)) {
            set.add(root.val);
        } else {
            found = true;
        }
        
        if (!found) {
            findTarget (root.right, k);
        }
        
    }
    public boolean findTargetM2(TreeNode root, int k) {
        findTargetUtil(root, k);
        return found;
        
    }
    
    
    public void findTargetUtilM3(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        
        
        if (!set.contains(k-root.val)) {
            set.add(root.val);
        } else {
            found = true;
        }
        
        
        if (!found) {
            findTarget (root.left, k);
            findTarget (root.right, k);
        }
        
    }
    public boolean findTargetM3(TreeNode root, int k) {
        /*
         * Note: any order you can use it, but first one is more elegant way to write code
         * Runtime: 2 ms, faster than 97.67% of Java online submissions for Two Sum IV - Input is a BST.
Memory Usage: 39.3 MB, less than 99.30% of Java online submissions for Two Sum IV - Input is a BST.
         */
        findTargetUtil(root, k);
        return found;
        
    }
}
