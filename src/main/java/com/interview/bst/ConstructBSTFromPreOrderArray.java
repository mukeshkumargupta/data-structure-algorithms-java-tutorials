package com.interview.bst;

import com.interview.tree.TreeNode;
import com.interview.tree.PartATreeTraversal.TreeTraversals;

class Index{
    int index;
}
/*
 * Test case
 * empty array
 * 1,2 or more elements in the array
 * https://www.youtube.com/watch?v=Bexswo4pqZQ
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * Category: Medium, Tricky
 *
 * Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
 *
 * It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
 *
 * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
 *
 * A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 * Example 2:
 *
 * Input: preorder = [1,3]
 * Output: [1,null,3]
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 100
 * 1 <= preorder[i] <= 1000
 * All the values of preorder are unique.
 */
public class ConstructBSTFromPreOrderArray {

    /*
     * 🔹 Approach 1: Brute Force (Insert Elements One by One)
     *
     * ------------------------------------------------------------------
     * 🔹 Idea
     * ------------------------------------------------------------------
     * ✅ The simplest approach is to construct the BST by inserting elements
     *    one by one following BST insertion rules.
     * ✅ Start with an **empty tree** and insert each element **sequentially**
     *    using the BST property.
     *
     * ------------------------------------------------------------------
     * 🔹 Algorithm
     * ------------------------------------------------------------------
     *  1. **Create an empty root node**.
     *  2. **Iterate through the preorder array** and insert each element
     *     into the BST using standard **BST insertion logic**.
     *  3. **Return the constructed BST**.
     *
     * ------------------------------------------------------------------
     * 🔹 Time & Space Complexity Analysis
     * ------------------------------------------------------------------
     * ✅ **Time Complexity:**
     *    - **O(N²)** (Inserting each node takes **O(N)** in the worst case,
     *      leading to an overall **O(N²)** complexity).
     *
     * ✅ **Space Complexity:**
     *    - **O(N)** (For recursive calls in the worst case).
     *
     * ------------------------------------------------------------------
     * 🔹 Summary
     * ------------------------------------------------------------------
     * ✅ **Brute Force Approach**
     * ✅ **Time Complexity:** **O(N²)** (due to worst-case insertion)
     * ✅ **Space Complexity:** **O(N)** (due to recursion depth)
     * ✅ **Simple but inefficient for large inputs** 🚀
     *
     *
     */

    /*
        📌 Dry Run Breakdown (Detailed Recursive Insertion Calls)

        1️⃣ Insert 10 (First Node)
           - root = null → Create TreeNode(10).
           - BST after step 1:
             ```
             10
             ```

        2️⃣ Insert 5
           - 5 < 10 → Insert 5 as left of 10.
           - BST after step 2:
             ```
             10
            /
           5
             ```

        3️⃣ Insert 1
           - 1 < 10 → Go left to 5.
           - 1 < 5 → Insert 1 as left of 5.
           - BST after step 3:
             ```
             10
            /
           5
          /
         1
             ```

        4️⃣ Insert 7
           - 7 < 10 → Go left to 5.
           - 7 > 5 → Insert 7 as right of 5.
           - BST after step 4:
             ```
             10
            /
           5
          / \
         1   7
             ```

        5️⃣ Insert 40
           - 40 > 10 → Insert 40 as right of 10.
           - BST after step 5:
             ```
             10
            /   \
           5     40
          / \
         1   7
             ```

        6️⃣ Insert 50
           - 50 > 10 → Go right to 40.
           - 50 > 40 → Insert 50 as right of 40.
           - Final BST:
             ```
             10
            /   \
           5     40
          / \      \
         1   7      50
             ```

        🔹 Preorder Traversal Output:
           ```
           10 5 1 7 40 50
           ```
           ✅ Matches the given input!

        🔹 Inorder Traversal Output (Sorted Order):
           ```
           1 5 7 10 40 50
           ```
           ✅ Confirms correct BST structure!

        🔹 Time & Space Complexity Analysis:
           | Complexity        | Explanation                                      |
           |------------------|--------------------------------------------------|
           | Time Complexity  | O(N²) (Worst case: skewed tree, inserting each node takes O(N)) |
           | Space Complexity | O(N) (Recursion stack for unbalanced trees)      |

        🔹 Summary:
           ✅ Brute Force Approach (One-by-One Insertion)
           ✅ Correctly constructs a BST
           ✅ Time Complexity: O(N²) (Worst case: skewed tree)
           ✅ Space Complexity: O(N) (Recursion depth in worst case)
    */
    private static class BruitForce {
        private static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode (int val) {
                this.val = val;

            }
        }
        public TreeNode bstFromPreorder(int[] preorder) {
            TreeNode root = null;
            for (int val : preorder) {
                root = insert(root, val);
            }
            return root;
        }

        private TreeNode insert(TreeNode root, int val) {
            if (root == null) return new TreeNode(val);
            if (val < root.val) root.left = insert(root.left, val);
            else root.right = insert(root.right, val);
            return root;
        }
    }
    /*
     * 🔹 Optimized Approach (Using Upper and Lower Bound)
     *
     * This approach leverages recursion and bounding constraints (`min` and `max`)
     * to efficiently construct the BST from the given preorder traversal.
     *
     * ------------------------------------------------------------------
     * 🔹 How the Algorithm Works
     * ------------------------------------------------------------------
     * ✅ Step-by-Step Explanation:
     *  1. Use `preIdx` as a global index to track the position in the `preorder` array.
     *  2. Recursively build the BST by:
     *     - Creating a root node from `preorder[preIdx]`.
     *     - Setting bounds (`min` and `max`) to ensure correct placement of nodes.
     *     - Recursively constructing the **left** and **right** subtrees with updated bounds.
     *  3. **Termination conditions:**
     *     - If `preIdx` exceeds the array length.
     *     - If `preorder[preIdx]` is **out of bounds** (i.e., violates BST properties).
     *
     * ------------------------------------------------------------------
     * 🔹 Dry Run (Step-by-Step Execution)
     * ------------------------------------------------------------------
     * Given input:
     *      preorder[] = {10, 5, 1, 7, 40, 50}
     *
     * | Step | preIdx | Value | Left Bound (`min`) | Right Bound (`max`) | Action |
     * |------|--------|-------|--------------------|----------------------|--------|
     * | 1    | 0      | 10    | -∞                 | ∞                    | Create root node (10) |
     * | 2    | 1      | 5     | -∞                 | 10                   | Insert 5 as left child of 10 |
     * | 3    | 2      | 1     | -∞                 | 5                    | Insert 1 as left child of 5 |
     * | 4    | 3      | 7     | 5                  | 10                   | Insert 7 as right child of 5 |
     * | 5    | 4      | 40    | 10                 | ∞                    | Insert 40 as right child of 10 |
     * | 6    | 5      | 50    | 40                 | ∞                    | Insert 50 as right child of 40 |
     *
     * 🔹 Final BST Structure:
     *
     *         10
     *        /   \
     *       5     40
     *      / \      \
     *     1   7      50
     *
     * ------------------------------------------------------------------
     * 🔹 Preorder Traversal Output:
     * ------------------------------------------------------------------
     *      10 5 1 7 40 50
     * ✅ Matches the given input, confirming correct BST construction!
     *
     * ------------------------------------------------------------------
     * 🔹 Inorder Traversal Output (Sorted Order for BST Validation):
     * ------------------------------------------------------------------
     *      1 5 7 10 40 50
     * ✅ Correct BST structure!
     *
     * ------------------------------------------------------------------
     * 🔹 Time & Space Complexity Analysis
     * ------------------------------------------------------------------
     * ✅ **Time Complexity:**
     *    - Each node is processed **once**, and each recursive call makes **O(1)** operations.
     *    - Since we **traverse each node once**, **the total time complexity is O(N)**.
     *
     * ✅ **Space Complexity:**
     *    - **O(N)** in the worst case (skewed tree) due to the recursion stack.
     *    - **O(log N)** in the best case (balanced BST).
     *
     * ------------------------------------------------------------------
     * 🔹 Summary
     * ------------------------------------------------------------------
     * ✅ **Optimized Recursive Approach** using **upper and lower bounds**
     * ✅ **Time Complexity:** **O(N)** (each node is visited once)
     * ✅ **Space Complexity:** **O(N)** (worst case for recursion stack)
     * ✅ **Correctly constructs a BST from preorder traversal** 🚀
     */

    private static class Optimized {
        // TreeNode class representing a node in the BST
        private static class TreeNode {
            int val;
            TreeNode left, right;

            TreeNode(int val) {
                this.val = val;
            }
        }

        private int preIdx = 0; // Tracks the current index in the preorder array

        public TreeNode bstFromPreorder(int[] preorder) {
            return constructBST(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private TreeNode constructBST(int[] preorder, int min, int max) {
            // Base Case: If all elements are processed or out of bounds
            if (preIdx >= preorder.length || preorder[preIdx] <= min || preorder[preIdx] >= max) {
                return null;
            }

            // Create the root node with the current preorder value
            TreeNode root = new TreeNode(preorder[preIdx++]);

            // Recursively construct the left subtree with updated bounds
            root.left = constructBST(preorder, min, root.val);

            // Recursively construct the right subtree with updated bounds
            root.right = constructBST(preorder, root.val, max);

            return root;
        }

        // Helper method for testing the BST construction
        public static void main(String[] args) {
            int[] preorder = {10, 5, 1, 7, 40, 50};
            Optimized bstBuilder = new Optimized();
            TreeNode root = bstBuilder.bstFromPreorder(preorder);

            // Tree Traversals to verify the correctness
            System.out.print("Preorder Traversal: ");
            preOrderTraversal(root);
            System.out.println();

            System.out.print("Inorder Traversal: ");
            inOrderTraversal(root);
            System.out.println();
        }

        // Preorder Traversal (Root -> Left -> Right)
        private static void preOrderTraversal(TreeNode root) {
            if (root == null) return;
            System.out.print(root.val + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }

        // Inorder Traversal (Left -> Root -> Right)
        private static void inOrderTraversal(TreeNode root) {
            if (root == null) return;
            inOrderTraversal(root.left);
            System.out.print(root.val + " ");
            inOrderTraversal(root.right);
        }
    }
   

}
