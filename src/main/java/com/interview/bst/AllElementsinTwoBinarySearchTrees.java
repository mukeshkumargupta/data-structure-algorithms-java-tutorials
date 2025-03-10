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
    /*
    This problem requires retrieving all elements from two binary search trees (BSTs) and returning them in sorted order. We will solve this using an inorder traversal approach, which is both efficient and readable.

Approach: Inorder Traversal + Merging Two Sorted Lists
Idea:
Inorder Traversal of BSTs:
Since BSTs give sorted elements when traversed in inorder, we traverse both trees separately and store their elements in two lists.
Merge Two Sorted Lists:
We then merge these two sorted lists into a single sorted list (like in Merge Sort).
Step-by-Step Implementation
Perform inorder traversal on both BSTs and store results in list1 and list2.
Merge the two sorted lists into a single sorted list.
Return the merged list as the final answer.
Time and Space Complexity
Step	Time Complexity	Space Complexity
Inorder traversal (Tree 1)	O(M)	O(M)
Inorder traversal (Tree 2)	O(N)	O(N)
Merge two sorted lists	O(M + N)	O(M + N)
Total Complexity	O(M + N)	O(M + N)
Here, M and N are the number of nodes in the two BSTs.

Why is this the Best Approach?
✅ Readable and structured (easy to understand).
✅ Efficient O(M + N) time complexity (optimal for merging two BSTs).
✅ Preserves BST properties using inorder traversal.
✅ Uses only extra space for the lists, no additional data structures needed.
     */
    private static class Better {
        private static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
        }
        public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();

            // Step 1: Get sorted elements from both BSTs
            inorder(root1, list1);
            inorder(root2, list2);

            // Step 2: Merge both sorted lists
            return mergeSortedLists(list1, list2);
        }

        // Inorder traversal to get sorted elements
        private void inorder(TreeNode root, List<Integer> list) {
            if (root == null) return;
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }

        // Merging two sorted lists
        private List<Integer> mergeSortedLists(List<Integer> list1, List<Integer> list2) {
            List<Integer> result = new ArrayList<>();
            int i = 0, j = 0;

            while (i < list1.size() && j < list2.size()) {
                if (list1.get(i) < list2.get(j)) {
                    result.add(list1.get(i++));
                } else {
                    result.add(list2.get(j++));
                }
            }

            // Add remaining elements (if any)
            while (i < list1.size()) result.add(list1.get(i++));
            while (j < list2.size()) result.add(list2.get(j++));

            return result;
        }
    }
/*
    Approach: Iterative Inorder Traversal + Merge Two BSTs

    - Use two stacks (stack1 and stack2) to perform simultaneous inorder traversal of both BSTs.
    - Compare the top elements of both stacks and add the smaller one to the result.
    - Move forward in the BST whose element was added.
    - Repeat this process until both stacks are empty.

    ---------------------------------------------------------------
    Time and Space Complexity:
    ---------------------------------------------------------------
    Step                           Time Complexity   Space Complexity
    ---------------------------------------------------------------
    Inorder traversal (Tree 1)     O(M)             O(H1) (worst O(M))
    Inorder traversal (Tree 2)     O(N)             O(H2) (worst O(N))
    Merge while traversing         O(M + N)         O(H1 + H2) (worst O(M + N))
    ---------------------------------------------------------------
    Total Complexity               O(M + N)         O(H1 + H2)

    - Here, H1 and H2 are the heights of the trees.
    - Best case: If trees are balanced, O(logM + logN).
    - Worst case: If trees are skewed, O(M + N) (same as storing in lists).

    ---------------------------------------------------------------
    Why is this Better?
    ---------------------------------------------------------------
    ✅ Optimized Space: Uses only O(H1 + H2) space instead of O(M + N).
    ✅ No extra lists: We merge while traversing the trees, saving memory.
    ✅ Time Complexity Remains O(M + N) (Same as previous approach).
    ✅ Faster Execution: No need to merge two lists separately.

    ---------------------------------------------------------------
    Comparison with Previous Approach:
    ---------------------------------------------------------------
    Approach                     Time Complexity   Space Complexity   Notes
    ---------------------------------------------------------------------------
    Inorder + Merge (List-Based)  O(M + N)        O(M + N)          Simple but uses extra lists
    Iterative Inorder (Stack)     O(M + N)        O(H1 + H2)        Saves space by avoiding extra lists

    ---------------------------------------------------------------
    Final Thoughts:
    ---------------------------------------------------------------
    - If you don't care about space, the list-based approach is easier to implement.
    - If you want optimal space usage, use this iterative stack-based approach.

    ---------------------------------------------------------------
    Example Input:
    ---------------------------------------------------------------
    Tree 1:
        2
       / \
      1   4

    Tree 2:
        3
       / \
      0   5

    ---------------------------------------------------------------
    Step-by-Step Execution:
    ---------------------------------------------------------------

    Initialization:
    stack1 = []
    stack2 = []
    result = []
    curr1 = root1 (2)
    curr2 = root2 (3)

    Step 1: Push Leftmost Nodes
    - Tree 1: Push 2 → 1 to stack1, curr1 = null
    - Tree 2: Push 3 → 0 to stack2, curr2 = null

    Stacks:
    stack1 = [2, 1]
    stack2 = [3, 0]

    Step 2: Process Smallest Element
    - Top elements: stack1.peek() = 1, stack2.peek() = 0
    - 0 is smaller → pop 0 from stack2, add 0 to result, move to 0.right (null)

    result = [0]
    stack1 = [2, 1]
    stack2 = [3]
    curr2 = null

    Step 3: Process Smallest Element
    - Top elements: stack1.peek() = 1, stack2.peek() = 3
    - 1 is smaller → pop 1 from stack1, add 1 to result, move to 1.right (null)

    result = [0, 1]
    stack1 = [2]
    stack2 = [3]
    curr1 = null

    Step 4: Process Smallest Element
    - Top elements: stack1.peek() = 2, stack2.peek() = 3
    - 2 is smaller → pop 2 from stack1, add 2 to result, move to 2.right (4)

    result = [0, 1, 2]
    stack1 = []
    stack2 = [3]
    curr1 = 4

    Step 5: Push Leftmost Nodes
    - Tree 1: Push 4 to stack1, curr1 = null

    stack1 = [4]
    stack2 = [3]

    Step 6: Process Smallest Element
    - Top elements: stack1.peek() = 4, stack2.peek() = 3
    - 3 is smaller → pop 3 from stack2, add 3 to result, move to 3.right (5)

    result = [0, 1, 2, 3]
    stack1 = [4]
    stack2 = []
    curr2 = 5

    Step 7: Push Leftmost Nodes
    - Tree 2: Push 5 to stack2, curr2 = null

    stack1 = [4]
    stack2 = [5]

    Step 8: Process Smallest Element
    - Top elements: stack1.peek() = 4, stack2.peek() = 5
    - 4 is smaller → pop 4 from stack1, add 4 to result, move to 4.right (null)

    result = [0, 1, 2, 3, 4]
    stack1 = []
    stack2 = [5]
    curr1 = null

    Step 9: Process Smallest Element
    - stack1 is empty → pop 5 from stack2, add 5 to result, move to 5.right (null)

    result = [0, 1, 2, 3, 4, 5]
    stack1 = []
    stack2 = []
    curr2 = null

    Step 10: Termination
    - Both stacks are empty, curr1 == null, curr2 == null

    ---------------------------------------------------------------
    Final Output:
    ---------------------------------------------------------------
    [0, 1, 2, 3, 4, 5] // Merged sorted order of elements from both BSTs

    ---------------------------------------------------------------
    Time & Space Complexity Analysis:
    ---------------------------------------------------------------
    - Time Complexity: O(M + N) (Each node is processed once)
    - Space Complexity: O(H1 + H2) (Stack size depends on tree height)
*/

    private static class Optimal {

        private static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

        }
        public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();

            TreeNode curr1 = root1, curr2 = root2;

            while (curr1 != null || curr2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
                // Push leftmost nodes of both trees onto their respective stacks
                while (curr1 != null) {
                    stack1.push(curr1);
                    curr1 = curr1.left;
                }
                while (curr2 != null) {
                    stack2.push(curr2);
                    curr2 = curr2.left;
                }

                // Decide which stack to pop from (pick smaller element)
                if (stack2.isEmpty() || (!stack1.isEmpty() && stack1.peek().val <= stack2.peek().val)) {
                    curr1 = stack1.pop();
                    result.add(curr1.val);
                    curr1 = curr1.right;  // Move to right subtree
                } else {
                    curr2 = stack2.pop();
                    result.add(curr2.val);
                    curr2 = curr2.right;  // Move to right subtree
                }
            }

            return result;
        }
    }
}
