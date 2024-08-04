package com.interview.tree;

/*
 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
 * Category: Medium, dfs, VVImp, 
 *  Related:
 *  https://leetcode.com/problems/convert-bst-to-greater-tree/ Medium, VVImp
 *  https://leetcode.com/problems/sentence-similarity-ii/ Locked
 *  https://leetcode.com/problems/correct-a-binary-tree/ Locked
 *  
 * 1315. Sum of Nodes with Even-Valued Grandparent
Medium

1686

56

Add to List

Share
Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are no nodes with an even-valued grandparent, return 0.

A grandparent of a node is the parent of its parent if it exists.

 

Example 1:


Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
Example 2:


Input: root = [1]
Output: 0
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 100
Accepted
97,786
Submissions
114,813
 */
public class SumofNodeswithEvenValuedGrandparent {
    public void sumEvenGrandparentUtil(TreeNode root, TreeNode parent, TreeNode grandParent, int[] sum) {
        if (root == null) {
            return;
        }
        
        if (grandParent != null && grandParent.val % 2 == 0) {//Node having grand parent and even will add into answer
            sum[0] += root.val;
        }
        
        //DFS
        sumEvenGrandparentUtil(root.left, root, parent, sum);
        sumEvenGrandparentUtil(root.right, root, parent, sum);
        
    }
    public int sumEvenGrandparent(TreeNode root) {
        /*
         * Runtime: 2 ms, faster than 79.17% of Java online submissions for Sum of Nodes with Even-Valued Grandparent.
Memory Usage: 52.5 MB, less than 39.31% of Java online submissions for Sum of Nodes with Even-Valued Grandparent.
         */
        if (root == null) {
            return 0;
        }
        int[] sum = new int[1];
        sumEvenGrandparentUtil(root, null, null, sum);
        return sum[0];
        
    }
}
