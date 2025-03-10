package com.interview.tree.PartETreeModification;

import com.interview.tree.TreeNode;

/*
 * Problem: https://leetcode.com/problems/delete-leaves-with-a-given-value/
 * Category: Medium, Tricky
 *
 * Related Problems:
 * - https://leetcode.com/problems/lexicographical-numbers/ (Medium, Important) - Solve using DFS and Trie.
 * - https://leetcode.com/problems/before-and-after-puzzle/ (Medium, Locked).
 * - https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/ (Medium, Very Important).
 *
 * Given a binary tree `root` and an integer `target`, delete all the leaf nodes with value `target`.
 *
 * Note:
 * - Once a leaf node with value `target` is deleted, if its parent becomes a leaf and has the same value,
 *   it should also be deleted. This process continues until no such leaf nodes remain.
 *
 * Example 1:
 * ----------
 * Input: root = [1,2,3,2,null,2,4], target = 2
 * Output: [1,null,3,null,4]
 * Explanation:
 *   - Leaf nodes (marked in green) with value `2` are removed.
 *   - After removal, new leaf nodes with value `2` are also removed.
 *
 * Example 2:
 * ----------
 * Input: root = [1,3,3,3,2], target = 3
 * Output: [1,3,null,null,2]
 *
 * Example 3:
 * ----------
 * Input: root = [1,2,null,2,null,2], target = 2
 * Output: [1]
 * Explanation:
 *   - Leaf nodes with value `2` are removed step by step.
 *
 * Constraints:
 * ------------
 * - The number of nodes in the tree is in the range [1, 3000].
 * - 1 <= Node.val, target <= 1000.
 */
public class DeleteLeavesWithaGivenValue {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        /*
         * Runtime: 1 ms, faster than 39.27% of Java online submissions for Delete Leaves With a Given Value.
Memory Usage: 45.4 MB, less than 6.34% of Java online submissions for Delete Leaves With a Given Value
         */
        if(root == null) {
            return null;
        }
        
        //Post order traversal for cleanup
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        
        if(root.left == null && root.right == null && root.val == target) {
            root = null;
        }
        
        return root;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
