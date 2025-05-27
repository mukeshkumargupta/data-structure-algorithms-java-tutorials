package com.interview.tree.PartATreeTraversal;

import com.interview.tree.TreeNode;

import java.util.*;
/*
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 * https://www.youtube.com/watch?v=ZbybYvcVLks
 * Category: Medium, Must Do
 * Given the root of a binary tree, return the maximum width of the given tree.
 * Related: https://leetcode.com/problems/find-mode-in-binary-search-tree/ Easy
 * https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/ Medium
 * https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island/ Hard

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes are also counted into the length calculation.

It is guaranteed that the answer will in the range of 32-bit signed integer.

 

Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:


Input: root = [1,3,null,5,3]
Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:


Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:


Input: root = [1,3,2,5,null,null,9,6,null,null,7]
Output: 8
Explanation: The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 

Constraints:

The number of nodes in the tree is in the range [1, 3000].
-100 <= Node.val <= 100
 */
public class A_B_D_MaximumWidthofBinaryTree {
    class Pair {
        TreeNode node;
        int num; 
        Pair(TreeNode _node, int _num) {
            num = _num;
            node = _node; 
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 98.93% of Java online submissions for Maximum Width of Binary Tree.
Memory Usage: 38.8 MB, less than 68.61% of Java online submissions for Maximum Width of Binary Tree.
TC: O(N)
SC: O(N)
         */
        int ans = 0;
        Queue<Pair> q = new LinkedList<>(); 
        if (root != null) {
            q.add(new Pair(root, 0));
        }
         
        while(!q.isEmpty()){
            int size = q.size();
            int mmin = q.peek().num;    //to make the id starting from zero
            int first = 0,last = 0;
            for(int i=0; i<size; i++){
                Pair pair = q.remove();
                int cur_id = pair.num-mmin;
                TreeNode node = pair.node;
                if(i==0) first = cur_id;
                if(i==size-1) last = cur_id;
                if(node.left != null)
                    q.offer(new Pair(node.left, cur_id*2+1));
                if(node.right != null) 
                    q.offer(new Pair(node.right, cur_id*2+2));
            }
            ans = Math.max(ans, last-first+1);
        }
        return ans;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
