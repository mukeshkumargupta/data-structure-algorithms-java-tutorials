package com.interview.tree.PartATreeTraversal;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/construct-quad-tree/description/?envType=study-plan-v2&envId=top-interview-150
Category: Medium. top150, Must Do

Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.

Return the root of the Quad-Tree representing grid.

A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:

val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}
We can construct a Quad-Tree from a two-dimensional area using the following steps:

If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
Recurse for each of the children with the proper sub-grid.

If you want to know more about the Quad-Tree, you can refer to the wiki.

Quad-Tree format:

You don't need to read this section for solving the problem. This is only if you want to understand the output format here. The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.

It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].

If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.



Example 1:


Input: grid = [[0,1],[1,0]]
Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
Explanation: The explanation of this example is shown below:
Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.

Example 2:



Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
The topLeft, bottomLeft and bottomRight each has the same value.
The topRight have different values so we divide it into 4 sub-grids where each has the same value.
Explanation is shown in the photo below:



Constraints:

n == grid.length == grid[i].length
n == 2x where 0 <= x <= 6
 */
public class ConstructQuadTree {
    /*
        ✅ Problem Summary:
        You are given a 2D grid of size n x n containing only 0s and 1s.
        You need to construct a QuadTree for this grid.

        Each node of the QuadTree has:
        - val (true for 1, false for 0)
        - isLeaf (true if it's a leaf node)
        - topLeft, topRight, bottomLeft, bottomRight (children nodes)

        📌 Conditions:
        - If all the values in the current grid are the same (either all 0s or all 1s), it's a leaf node.
        - Otherwise, recursively divide the grid into 4 quadrants and construct each quadrant.

        ✅ Approach:
        We'll use divide and conquer (recursion):
        1. Check if the current subgrid has all same values.
        2. If yes → Return a leaf node.
        3. Else → Divide grid into 4 equal parts (topLeft, topRight, bottomLeft, bottomRight).
        4. Recursively build each quadrant and return the parent node.

        🧠 Explanation with Example:
        Example 1:
        grid = [
          [1, 1],
          [1, 1]
        ]
        → Entire grid is 1 → return Node(true, true)

        Example 2:
        grid = [
          [1, 1],
          [1, 0]
        ]
        → Not uniform → divide into four 1x1 grids → create leaf nodes accordingly.

        ⏱ Time Complexity:
        - T(n) = 4T(n/2) + O(n²) for checking uniformity.
        - Using Master Theorem:
            a = 4, b = 2, d = 2
            Since a = b^d → This is Case 2
            → Time: O(n² log n)

        🗂 Space Complexity:
        - Recursion depth = log n
        - At each level we store 4 nodes per subgrid
        - Total space = O(n²) (because each leaf/internal node represents a part of the grid)
    */
    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}
        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
        }
        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
    public Node construct(int[][] grid) {
        return build(grid, 0, 0, grid.length);
    }

    private Node build(int[][] grid, int row, int col, int size) {
        // Step 1: Check if all values are same in current subgrid
        if (isUniform(grid, row, col, size)) {
            return new Node(grid[row][col] == 1, true);
        }

        // Step 2: Divide into 4 quadrants
        int newSize = size / 2;
        Node topLeft = build(grid, row, col, newSize);
        Node topRight = build(grid, row, col + newSize, newSize);
        Node bottomLeft = build(grid, row + newSize, col, newSize);
        Node bottomRight = build(grid, row + newSize, col + newSize, newSize);

        // Step 3: Return internal node with all 4 children
        return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    private boolean isUniform(int[][] grid, int row, int col, int size) {
        int val = grid[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (grid[i][j] != val) return false;
            }
        }
        return true;
    }

    /*
        📂 Category: Hard, Tree, 2D Matrix, Divide & Conquer

        ✅ Original Problem:
        1. **Construct Quad Tree** (Leetcode 427)
           ➤ Given a grid of 0s and 1s, return the root of the corresponding quad tree.

           Input:
           grid = [
             [1, 1],
             [1, 1]
           ]

           Output: Node(val=true, isLeaf=true)

        *******************************************

        ✅ Derived and Related Problems:

        2. **Flatten Quad Tree to List**
           ➤ Flatten the quad tree into a list using pre-order traversal.

           Input:
           Quad tree built from:
           grid = [
             [1, 0],
             [0, 1]
           ]

           Output: ["[1,0]", "[1,1]", "[0,1]", "[0,1]", "[1,1]"]

        ---------------------------------------------------

        3. **Reconstruct Grid from Quad Tree**
           ➤ Given a quad tree, reconstruct the original 2D grid.

           Input:
           A quad tree with root:
           Node(false, false,
             topLeft=Node(true, true),
             topRight=Node(true, true),
             bottomLeft=Node(true, true),
             bottomRight=Node(false, true))

           Output:
           grid = [
             [1, 1],
             [1, 0]
           ]

        ---------------------------------------------------

        4. **Count Leaf Nodes in a Quad Tree**
           ➤ Return the total number of leaf nodes in a quad tree.

           Input:
           Quad tree built from grid:
           [[1,1],[1,0]]

           Output: 4

        ---------------------------------------------------

        5. **Compress Grid Using Quad Tree**
           ➤ Return number of nodes in the quad tree. Helps understand compression.

           Input:
           grid = [
             [1, 1],
             [1, 1]
           ]

           Output: 1 (fully compressed)

           Input:
           grid = [
             [1, 1],
             [1, 0]
           ]

           Output: 5 (1 root + 4 leafs)

        ---------------------------------------------------

        6. **Compare Two Quad Trees**
           ➤ Given two quad trees, return true if both represent the same grid.

           Input: quadTree1 and quadTree2

           Output: true or false

        ---------------------------------------------------

        7. **Count All 1s in Quad Tree**
           ➤ Count how many cells are set to 1 in the grid represented by a quad tree.

           Input: quad tree from grid:
           [[1, 1],
            [0, 1]]

           Output: 3

        ---------------------------------------------------

        8. **Largest All-1 Square in Quad Tree**
           ➤ Return size of the largest square subgrid consisting entirely of 1s in a quad tree.

           Input:
           grid = [
             [1, 1, 0],
             [1, 1, 0],
             [0, 0, 1]
           ]

           Output: 2 (The 2x2 top-left square)

        ---------------------------------------------------

        9. **Check Symmetry of Quad Tree**
           ➤ Return true if the quad tree represents a symmetric matrix.

           Input:
           grid = [
             [1, 0],
             [0, 1]
           ]

           Output: true

        ---------------------------------------------------

        10. **Merge Two Quad Trees**
           ➤ Merge two quad trees where 1 dominates 0. Return the merged quad tree.

           Input:
           QuadTree1: grid = [[1, 1], [0, 0]]
           QuadTree2: grid = [[0, 1], [1, 0]]

           Output:
           grid = [[1, 1], [1, 0]]

        ---------------------------------------------------

        11. **Validate Quad Tree Structure**
           ➤ Check if a given quad tree follows correct structure: internal nodes must have 4 children, leaves must not.

           Input:
           A node having isLeaf = false but missing some children.

           Output:
           false

        ---------------------------------------------------

        12. **Convert Quad Tree to String and Parse Back**
           ➤ Serialize and deserialize a quad tree.

           Input:
           Quad tree from grid = [[1,0],[0,1]]

           Serialized Output: "[0,[1,1],[0,1],[0,1],[1,1]]"

           Parse back to tree and validate correctness.

        ---------------------------------------------------

        13. **Balanced Quad Tree Checker**
           ➤ Check if all four children of any internal node are leaf or non-leaf nodes of the same height.

           Input:
           Custom quad tree with irregular subtree sizes

           Output:
           true / false

        ---------------------------------------------------

        14. **Count Levels in Quad Tree**
           ➤ Return the maximum depth (height) of the quad tree.

           Input:
           grid = [
             [1, 1],
             [1, 0]
           ]

           Output: 2

        ---------------------------------------------------

        15. **Print Grid from Quad Tree**
           ➤ Similar to reconstruction, but instead print the grid in formatted string form.

           Input: Quad tree

           Output:
           [
             [1, 1],
             [1, 0]
           ]

        *******************************************

        🧠 These questions test a variety of skills:
        - Recursion
        - Tree traversal
        - Tree to matrix and matrix to tree conversions
        - Structural validations
        - Data compression insights

        Would you like solutions for any of the above questions?
*/

    // 1. Construct Quad Tree (Leetcode 427)
    //Already solve above

// Time: O(n^2 log n), Space: O(n^2)

    // 2. Flatten Quad Tree to List (Preorder)
    public List<String> flatten(Node root) {
        List<String> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(Node node, List<String> result) {
        if (node == null) return;
        result.add("[" + (node.val ? 1 : 0) + "," + (node.isLeaf ? 1 : 0) + "]");
        if (!node.isLeaf) {
            preorder(node.topLeft, result);
            preorder(node.topRight, result);
            preorder(node.bottomLeft, result);
            preorder(node.bottomRight, result);
        }
    }

// Time: O(n), Space: O(n)

    // 3. Reconstruct Grid from Quad Tree
    public int[][] reconstruct(Node root, int size) {
        int[][] grid = new int[size][size];
        fill(grid, root, 0, 0, size);
        return grid;
    }

    private void fill(int[][] grid, Node node, int row, int col, int size) {
        if (node.isLeaf) {
            for (int i = row; i < row + size; i++) {
                for (int j = col; j < col + size; j++) {
                    grid[i][j] = node.val ? 1 : 0;
                }
            }
        } else {
            int half = size / 2;
            fill(grid, node.topLeft, row, col, half);
            fill(grid, node.topRight, row, col + half, half);
            fill(grid, node.bottomLeft, row + half, col, half);
            fill(grid, node.bottomRight, row + half, col + half, half);
        }
    }

// Time: O(n^2), Space: O(n^2)

    // 4. Count Leaf Nodes
    public int countLeaves(Node root) {
        if (root == null) return 0;
        if (root.isLeaf) return 1;
        return countLeaves(root.topLeft) + countLeaves(root.topRight) +
                countLeaves(root.bottomLeft) + countLeaves(root.bottomRight);
    }

// Time: O(n), Space: O(log n)

    // 5. Compress Grid Using Quad Tree → Total Nodes
    public int countTotalNodes(Node root) {
        if (root == null) return 0;
        if (root.isLeaf) return 1;
        return 1 + countTotalNodes(root.topLeft) + countTotalNodes(root.topRight) +
                countTotalNodes(root.bottomLeft) + countTotalNodes(root.bottomRight);
    }

// Time: O(n), Space: O(log n)

    // 6. Compare Two Quad Trees
    public boolean isSame(Node a, Node b) {
        if (a == null || b == null) return a == b;
        if (a.isLeaf && b.isLeaf) return a.val == b.val;
        if (a.isLeaf || b.isLeaf) return false;
        return isSame(a.topLeft, b.topLeft) && isSame(a.topRight, b.topRight)
                && isSame(a.bottomLeft, b.bottomLeft) && isSame(a.bottomRight, b.bottomRight);
    }

// Time: O(n), Space: O(log n)

    // 7. Count All 1s in Quad Tree
    public int countOnes(Node root, int size) {
        if (root.isLeaf) return root.val ? size * size : 0;
        int half = size / 2;
        return countOnes(root.topLeft, half) + countOnes(root.topRight, half) +
                countOnes(root.bottomLeft, half) + countOnes(root.bottomRight, half);
    }

// Time: O(n^2), Space: O(log n)

    // 8. Largest All-1 Square in Grid (Pre-QuadTree)
    public int largest1Square(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n + 1][n + 1];
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i+1][j], Math.min(dp[i][j+1], dp[i+1][j+1]));
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

// Time: O(n^2), Space: O(n^2)

    // 9. Check Symmetry of Grid (Pre-QuadTree)
    public boolean isSymmetric(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != grid[j][i]) return false;
            }
        }
        return true;
    }

// Time: O(n^2), Space: O(1)

    // 10. Merge Two Quad Trees
    public Node merge(Node t1, Node t2) {
        if (t1.isLeaf) return t1.val ? t1 : t2;
        if (t2.isLeaf) return t2.val ? t2 : t1;
        Node topLeft = merge(t1.topLeft, t2.topLeft);
        Node topRight = merge(t1.topRight, t2.topRight);
        Node bottomLeft = merge(t1.bottomLeft, t2.bottomLeft);
        Node bottomRight = merge(t1.bottomRight, t2.bottomRight);
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf &&
                topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true);
        }
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

// Time: O(n), Space: O(log n)

    // 11. Validate Quad Tree Structure
    public boolean isValid(Node node) {
        if (node == null) return true;
        if (!node.isLeaf) {
            if (node.topLeft == null || node.topRight == null || node.bottomLeft == null || node.bottomRight == null)
                return false;
        }
        return (node.isLeaf || isValid(node.topLeft) && isValid(node.topRight) &&
                isValid(node.bottomLeft) && isValid(node.bottomRight));
    }

// Time: O(n), Space: O(log n)

    // 12. Serialize & Deserialize Quad Tree
    public String serialize(Node node) {
        if (node == null) return "null";
        if (node.isLeaf) return "[" + (node.val ? 1 : 0) + ",1]";
        return "[" + (node.val ? 1 : 0) + ",0," + serialize(node.topLeft) + "," + serialize(node.topRight) + "," +
                serialize(node.bottomLeft) + "," + serialize(node.bottomRight) + "]";
    }

// Deserialization would be based on parsing string recursively (skipped for brevity)

    // 13. Balanced Quad Tree Checker
    public int height(Node node) {
        if (node == null || node.isLeaf) return 1;
        int[] heights = new int[] {
                height(node.topLeft), height(node.topRight),
                height(node.bottomLeft), height(node.bottomRight)
        };
        for (int h : heights) if (h != heights[0]) return -1;
        return heights[0] + 1;
    }

    public boolean isBalanced(Node node) {
        return height(node) != -1;
    }

// Time: O(n), Space: O(log n)

    // 14. Count Levels in Quad Tree
    public int countLevels(Node node) {
        if (node == null || node.isLeaf) return 1;
        return 1 + Math.max(
                Math.max(countLevels(node.topLeft), countLevels(node.topRight)),
                Math.max(countLevels(node.bottomLeft), countLevels(node.bottomRight))
        );
    }

// Time: O(n), Space: O(log n)

// 15. Print Grid from Quad Tree
// Same as Reconstruct Grid (see #3)
}
