package com.interview.tree;

/*
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 * Category: Medium, Must Do, dfs
 * Hint: Here solved as dfs, try to solve post order traversal like how size of tree calculated
 * https://www.youtube.com/watch?v=rbqbmCABEhU
 * Related: 
 * https://leetcode.com/problems/trapping-rain-water-ii/ Hard, VVImp
 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/ Medium
 * https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/ Medium
 * 1448. Count Good Nodes in Binary Tree
Medium

2230

74

Add to List

Share
Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

 

Example 1:



Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.
Example 2:



Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
Example 3:

Input: root = [1]
Output: 1
Explanation: Root is considered as good.
 

Constraints:

The number of nodes in the binary tree is in the range [1, 10^5].
Each node's value is between [-10^4, 10^4].
Accepted
156,973
Submissions
215,081
 */
public class CountGoodNodesinBinaryTree {
    public void goodNodesUtil(TreeNode root, TreeNode maxNode,int[] count) {
        if (root == null) {
            return;
        }
        
        if (maxNode == null) {//root node is always good node
            count[0] += 1;
        } else {
            if (root.val >= maxNode.val) {
                count[0] += 1;
            }
        }
        
        
        TreeNode findMaxNode = null;
        if (maxNode == null) {
            findMaxNode = root;
        } else {
            findMaxNode = root.val > maxNode.val ? root : maxNode;
        }
        //DFS
        goodNodesUtil(root.left, findMaxNode, count);
        goodNodesUtil(root.right, findMaxNode, count);
        
    }
    public int goodNodes(TreeNode root) {
        /*
         * Runtime: 3 ms, faster than 65.70% of Java online submissions for Count Good Nodes in Binary Tree.
Memory Usage: 59.5 MB, less than 70.66% of Java online submissions for Count Good Nodes in Binary Tree.
         */
                
        if (root == null) {
            return 0;
        }
        int[] count = new int[1];
        goodNodesUtil(root, null, count);
        return count[0]; 
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
