package com.interview.bst.PartABasicOperations;

import com.interview.tree.TreeNode;

/**
 * Regular binary search
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 * Category: Easy, Fundamental
 */
public class D_SearchInBST {
    /*
        Thought Process:

        - Start from the root and traverse recursively.
        - If the node’s value matches `val`, return the node.
        - If `val` is smaller, search in the left subtree.
        - If `val` is greater, search in the right subtree.
        - If the node is `null`, return `null`.

        Time Complexity: O(H), where H is the height of the BST.
            - Worst case: O(N) (skewed tree).
            - Best case: O(log N) (balanced tree).

        Space Complexity: O(H) due to recursive call stack.

        ✅ Pros: Simple and intuitive.
        ❌ Cons: Uses extra space for recursion.
    */
    public TreeNode searchRecursive(TreeNode root, int key){
        if(root == null){
            return null;
        }
        if(root.val == key){
            return root;
        }else if(root.val < key){
            return searchRecursive(root.right, key);
        }else{
            return searchRecursive(root.left, key);
        }
    }

    /*
        Thought Process:

        - Instead of recursion, use a while loop for iteration.
        - Traverse the BST by comparing val with root.val.
        - If val is smaller, move to the left; otherwise, move to the right.
        - Stop when we find the node or reach null.

        Time Complexity: O(H), where H is the height of the tree.
            - Worst case: O(N).
            - Best case: O(log N).

        Space Complexity: O(1) since no recursive calls are made.

        ✅ Pros: Space-efficient (O(1)). Avoids function call overhead.
        ❌ Cons: Slightly less intuitive than recursion.

        Final Thoughts:

        | Approach  | Time Complexity | Space Complexity | Pros                  | Cons                         |
        |-----------|---------------|----------------|----------------------|-----------------------------|
        | Recursive | O(H)          | O(H) (stack space) | Clean and intuitive | Uses extra memory          |
        | Iterative | O(H)          | O(1)            | Space-efficient      | Slightly less readable     |
    */
    public TreeNode searchIterative(TreeNode root, int val) {
        TreeNode current = root;
        while (current != null) {
            if (current.val == val) {
                return current;
            } else if (val < current.val ) {
                current = current.left;
            } else  {
               current = current.right; 
            }
            
        }
        return current;
  
    }
}
