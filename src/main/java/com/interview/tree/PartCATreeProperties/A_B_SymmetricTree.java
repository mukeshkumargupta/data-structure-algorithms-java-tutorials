package com.interview.tree.PartCATreeProperties;

import com.interview.tree.TreeNode;

/*
 * https://leetcode.com/problems/symmetric-tree/
 * Category: Easy, Must Do
 * https://www.youtube.com/watch?v=nKggNAiEpBE
 * 
 * Related: https://leetcode.com/problems/most-frequent-subtree-sum/ Medium
 * https://leetcode.com/problems/increasing-order-search-tree/ Easy
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/ Medium, Hint: bfs, level order traversal application
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/ Hard Locked
 * https://leetcode.com/problems/detect-cycles-in-2d-grid/ Medium , VVImp, cycle
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/ Medium, Locked, lca
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 * https://leetcode.com/problems/inorder-successor-in-bst-ii/ Medium
 * https://leetcode.com/problems/find-if-path-exists-in-graph/ Easy
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/ Hard

 

Example 1:


Input: root = [1,2,2,3,4,4,3]
Output: true
Example 2:


Input: root = [1,2,2,null,3,null,3]
Output: false
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
 

Follow up: Could you solve it both recursively and iteratively?
 */
public class A_B_SymmetricTree {

    private static class Approach1 {
        public boolean isIdentical(TreeNode root1, TreeNode root2) {
            if (root1 == null && root2 == null) {//if both are null
                return true;
            }

            //if any one of null
            if (root1 == null || root2 == null) {
                return false;
            }
            return root1.val == root2.val && isIdentical(root1.left, root2.right) && isIdentical(root1.right, root2.left);
        }
        public boolean isSymmetric(TreeNode root) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Symmetric Tree.
Memory Usage: 43.1 MB, less than 7.16% of Java online submissions for Symmetric Tree.
         */
            return isIdentical(root.left, root.right);
        }
    }

    private static class Approach2 {
        //Other solution:
        private boolean isInvertedTree(TreeNode root1, TreeNode root2) {
            if (root1 == null && root2 == null) {
                return true;
            }

            if (root1 == null || root2 == null) {
                return false;
            }

            //Invert first tree
            TreeNode tempNode = root1.left;
            root1.left = root1.right;
            root1.right = tempNode;

            if (root1.val == root2.val && isInvertedTree(root1.left, root2.left) &&  isInvertedTree(root1.right, root2.right)) {
                return true;
            } else {
                return false;
            }

        }
        public boolean isSymmetric(TreeNode root) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Symmetric Tree.
Memory Usage: 43.2 MB, less than 7.16% of Java online submissions for Symmetric Tree.
         */
            return isInvertedTree(root.left, root.right);

        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }
    }
}
