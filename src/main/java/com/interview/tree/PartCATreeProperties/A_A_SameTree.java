package com.interview.tree.PartCATreeProperties;

import com.interview.tree.TreeNode;

/**
 * Date 04/11/2017
 * @author Mukesh Kumar Gupta
 * 
 * Youtube link - https://youtu.be/ySDDslG8wws
 * 
 * Reference: https://leetcode.com/problems/same-tree/
 * 
 * Category: Easy, Google
 * Related:
 * https://leetcode.com/problems/boundary-of-binary-tree/ Medium
 * https://leetcode.com/problems/operations-on-tree/ Medium
 * https://leetcode.com/problems/find-if-path-exists-in-graph/ Easy
 * Given roots of two tree, return true if they have same val and same structure
 * or return false.
 * 
 * Solution
 * Keep comparing root of both val and then recursively check left and right
 * Related: https://leetcode.com/problems/leaf-similar-trees/.
 * 
 * Time complexity is O(n)
 * 
 * Status: Done
 * Category: Easy, Must Do
 */
public class A_A_SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Same Tree.
Memory Usage: 38.5 MB, less than 12.99% of Java online submissions for Same Tree.
         */
        if (p == null && q == null) return true;
        
        if ( p == null || q == null) return false;
        
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);  
    }
    
}
