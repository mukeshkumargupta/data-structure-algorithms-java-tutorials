package com.interview.bst.PartCRangeQueries;

import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/range-sum-of-bst/submissions/
 * Category: Easy
 * Related: 
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/ Medium
 * https://leetcode.com/problems/find-leaves-of-binary-tree/ Medium
 * https://leetcode.com/problems/count-nodes-equal-to-sum-of-descendants/ Medium
 */
public class A_RangeSumofBST {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int data) {
            this.val = data;
        }
    }
    /*
        Thought Process:

        - Perform an inorder traversal to get all the values in sorted order.
        - Iterate through the list and sum the values in the range [low, high].

        Time Complexity: O(N) → Since we traverse all nodes.
        Space Complexity: O(N) → Extra space for storing inorder traversal.

        ✅ Pros: Simple and easy to understand.
        ❌ Cons: Extra space required. Unnecessary traversal of nodes outside the range.
    */
    private static class Bruitforce {
        public int rangeSumBST(TreeNode root, int low, int high) {
            List<Integer> values = new ArrayList<>();
            inorder(root, values);
            int sum = 0;
            for (int val : values) {
                if (val >= low && val <= high) {
                    sum += val;
                }
            }
            return sum;
        }

        private void inorder(TreeNode root, List<Integer> values) {
            if (root == null) return;
            inorder(root.left, values);
            values.add(root.val);
            inorder(root.right, values);
        }

        /*
    Thought Process:

    - Traverse the BST using DFS (Preorder).
    - If `root.val < low`, search only in the right subtree.
    - If `root.val > high`, search only in the left subtree.
    - If `low <= root.val <= high`, add root.val to sum and search both subtrees.

    Time Complexity: O(H) on average (H = height of tree).
        - Worst case: O(N) (skewed tree).
        - Best case: O(log N) (balanced BST).

    Space Complexity: O(H) due to recursion stack.

    ✅ Pros: Avoids unnecessary traversal. Efficient for large BSTs.
    ❌ Cons: Uses recursive stack space.
*/
        private static class Better {
            public int rangeSumBST(TreeNode root, int low, int high) {
                if (root == null) return 0;

                if (root.val < low) return rangeSumBST(root.right, low, high);
                if (root.val > high) return rangeSumBST(root.left, low, high);

                return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
            }
        }
    }

    /*
        Thought Process:

        - Use an explicit stack for DFS traversal to avoid recursion overhead.
        - Push only relevant nodes onto the stack (prune unnecessary branches).
        - If `root.val < low`, search only in the right subtree.
        - If `root.val > high`, search only in the left subtree.
        - If `low <= root.val <= high`, add root.val to sum and check both subtrees.

        Time Complexity: O(H) on average.
        Space Complexity: O(H) (stack size in worst case).

        ✅ Pros: Space-efficient (O(H)). No recursion overhead.
        ❌ Cons: Slightly more complex than recursion.

        Final Thoughts:
        Approach                        Time Complexity       Space Complexity   Pros                                      Cons
        Brute Force (Inorder Traversal) O(N)                 O(N)               Simple and intuitive                      Extra space, unnecessary traversal
        Recursive DFS with Pruning      O(H) (Best: O(logN), Worst: O(N))  O(H)  Avoids unnecessary nodes                  Uses recursion stack space
        Iterative DFS with Stack        O(H)                 O(H)               No recursion overhead, space-efficient    Slightly more complex
    */
    private static class Optimized {
        public int rangeSumBST(TreeNode root, int low, int high) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            int sum = 0;

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node == null) continue;

                if (node.val >= low && node.val <= high) sum += node.val;
                if (node.val > low) stack.push(node.left);
                if (node.val < high) stack.push(node.right);
            }

            return sum;
        }
    }
}
