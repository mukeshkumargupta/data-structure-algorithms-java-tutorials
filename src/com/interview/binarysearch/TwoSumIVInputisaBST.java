package com.interview.binarysearch;

import java.util.*;
/*
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/ 
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/ Easy
 * https://leetcode.com/problems/two-sum-iii-val-structure-design/ Easy
 * https://leetcode.com/problems/two-sum-bsts/ Medium
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
}
