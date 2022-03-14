package com.interview.bst;

import java.util.*;

/*
 * https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 * Category: medium, Tricky, Facebook
 * Related: https://leetcode.com/problems/binary-tree-upside-down/ Medium
 * https://leetcode.com/problems/closest-binary-search-tree-value-ii/ Hard
 * https://leetcode.com/problems/orderly-queue/ Hard
 * 
 */
public class AllElementsinTwoBinarySearchTrees {
    public List<Integer> getAllElementsSol1(TreeNode root1, TreeNode root2) { //66.81 runtime, memory 29.35 Trick: lon n memory space height of tree max grow
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        List<Integer> result = new ArrayList<>();
        
        TreeNode c1 = root1;
        TreeNode c2 = root2;
        
        
        while (!s1.isEmpty() || c1 != null  || !s2.isEmpty() || c2 != null  ) {
            
            while (c1 != null) {
                s1.push(c1);
                c1 = c1.left;  
            }
            
            while (c2 != null) {
                s2.push(c2);
                c2 = c2.left; 
            }
            
            if (s2.isEmpty()  || (!s1.isEmpty() && s1.peek().val <= s2.peek().val)) {
                c1 = s1.pop();
                result.add(c1.val);
                c1 = c1.right;
                
            } else {
                c2 = s2.pop();
                result.add(c2.val);
                c2 = c2.right;
            }
            
        }
        return result;
        
    }
    
    
    List<Integer> l1;
    List<Integer> l2;
    
    void inorder(TreeNode root, List<Integer> l) {
        if (root == null) {
            return;
        }
        inorder(root.left, l);
        l.add(root.val);
        inorder(root.right, l);
        
    }
    public List<Integer> getAllElementsSol2(TreeNode root1, TreeNode root2) { //66.81 runtime //memory 29.35 not sure why both same while it sud be more  order n1 + n2
        l1 = new ArrayList<>();
        l2 = new ArrayList<>();
        inorder(root1, l1);
        
        inorder(root2, l2);
        
        int s1 = l1.size();
        int s2 = l2.size();
        
        int p1 = 0; int p2 = 0;
        
        List<Integer> result = new ArrayList<>();
        while (p1 < s1 && p2 < s2) {
            if (l1.get(p1) < l2.get(p2)) {
                result.add(l1.get(p1));
                p1++;
            } else if (l1.get(p1) > l2.get(p2)) {
                result.add(l2.get(p2));
                p2++;
            } else {
                result.add(l1.get(p1));
                result.add(l2.get(p2));
                p1++;
                p2++;
            }
        }
        
        while (p1 < s1) {
            result.add(l1.get(p1));
            p1++;
        }
        
        while (p2 < s2) {
            result.add(l2.get(p2));
            p2++;
        }
        
        return result;
        
        
    }
}
