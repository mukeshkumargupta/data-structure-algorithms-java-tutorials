package com.interview.bst.PartJBSTAndGraphLikeProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Category: Medium
https://leetcode.com/problems/binary-search-tree-iterator/description/
Related:
https://leetcode.com/problems/binary-tree-inorder-traversal/ Easy
https://leetcode.com/problems/flatten-2d-vector/ Medium
https://leetcode.com/problems/zigzag-iterator/ Medium
https://leetcode.com/problems/peeking-iterator/ Medium
https://leetcode.com/problems/inorder-successor-in-bst/ Medium
https://leetcode.com/problems/binary-search-tree-iterator-ii/ Medium
Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.



Example 1:


Input
["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
Output
[null, 3, 7, true, 9, true, 15, true, 20, false]

Explanation
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // return 3
bSTIterator.next();    // return 7
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 9
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 15
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 20
bSTIterator.hasNext(); // return False


Constraints:

The number of nodes in the tree is in the range [1, 105].
0 <= Node.val <= 106
At most 105 calls will be made to hasNext, and next.


Follow up:

Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 */
public class A_BSTIterator {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
/*
    Approach 1: Brute Force (Store All Elements in a List)

    Idea
    - Perform an inorder traversal and store all BST values in a list.
    - Use an index to iterate over the list.

    Time & Space Complexity
    Time Complexity: O(N) (traversal takes O(N))
    Space Complexity: O(N) (stores all nodes)

    Pros & Cons
    ✅ Simple to implement
    ❌ Uses O(N) extra space, not efficient
    ❌ Doesn’t provide true iterator behavior
*/

    private static class BruitForce {
            /*
        Approach 1: Brute Force (Store Inorder Traversal in a List)

        Idea:
        - Perform an inorder traversal and store all elements in a list.
        - Maintain an index to iterate over the list.

        Time & Space Complexity:
        - Time: O(N) (one-time traversal)
        - Space: O(N) (stores all elements)
    */

        private List<Integer> values;
        private int index;

        public BruitForce(TreeNode root) {
            values = new ArrayList<>();
            inorder(root);
            index = 0;
        }

        private void inorder(TreeNode node) {
            if (node == null) return;
            inorder(node.left);
            values.add(node.val);
            inorder(node.right);
        }

        public int next() {
            return values.get(index++);
        }

        public boolean hasNext() {
            return index < values.size();
        }
    }

    /*
        Approach 2: Better (Using Stack for O(H) Space)  [Use this approach to implement]

        Idea
        - Instead of storing all elements, use a stack to track only the leftmost path.
        - `next()`
          - Pop the top element (smallest available).
          - Process the right subtree (if exists).
          - Push left children of the right subtree to stack.
        - `hasNext()` checks if the stack is non-empty.

        Time & Space Complexity
        Time Complexity: O(1) average per `next()`
        Space Complexity: O(H) (height of the tree)

        Pros & Cons
        ✅ Efficient space usage
        ✅ Iterates in true sorted order
        ❌ Slightly more complex implementation
    */
    private static class Better {
    /*
        Approach 2: Better (Using Stack for O(H) Space)

        Idea:
        - Use a stack to track leftmost nodes.
        - When calling `next()`, process right subtree and update stack.

        Time & Space Complexity:
        - Time: O(1) average per `next()`
        - Space: O(H) (height of BST)
    */

        private Stack<TreeNode> stack;

        public Better(TreeNode root) {
            stack = new Stack<>();
            pushLeft(root);
        }

        private void pushLeft(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public int next() {
            TreeNode curr = stack.pop();
            if (curr.right != null) {
                pushLeft(curr.right);
            }
            return curr.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    /*
        Approach 3: Optimized (Morris Traversal - O(1) Space)
        Idea
        - Morris Traversal enables O(1) space inorder traversal.
        - Unlike previous approaches, we don’t use a stack but modify tree links.
        - next() follows Morris Traversal rules:
          - If left is null, visit node.
          - If left is not null, find inorder predecessor to establish/restore the link.

        Time & Space Complexity
        Time Complexity: O(1) amortized per next()
        Space Complexity: O(1) (modifies tree links)

        Pros & Cons
        ✅ O(1) space complexity (most optimized)
        ✅ Efficient traversal
        ❌ Modifies the tree temporarily

        Final Comparison
        Approach                Time Complexity  Space Complexity  Notes
        Brute Force (List Storage)  O(N)           O(N)             Stores all values before iterating
        Better (Stack-based)        O(1) avg       O(H)             True iterator behavior, uses stack
        Optimized (Morris Traversal) O(1) avg       O(1)             Best approach, but modifies tree

        Final Thoughts
        - If you just need an easy implementation, go with Approach 2 (Stack-based).
        - If you want the most optimized solution, use Approach 3 (Morris Traversal).
        🚀 Approach 2 (Stack-based) is the best balance between simplicity and efficiency!
    */
    private static class Optimised {
    /*
        Approach 3: Optimized (Morris Traversal - O(1) Space)

        Idea:
        - Use Morris Traversal for O(1) space.
        - Modify tree temporarily, restoring links when needed.

        Time & Space Complexity:
        - Time: O(1) amortized per `next()`
        - Space: O(1) (no extra data structures)
    */

        private TreeNode curr;

        public Optimised(TreeNode root) {
            curr = root;
        }

        public boolean hasNext() {
            return curr != null;
        }

        public int next() {
            while (curr != null) {
                if (curr.left == null) {
                    int val = curr.val;
                    curr = curr.right;
                    return val;
                } else {
                    TreeNode pred = curr.left;
                    while (pred.right != null && pred.right != curr) {
                        pred = pred.right;
                    }

                    if (pred.right == null) {
                        pred.right = curr;
                        curr = curr.left;
                    } else {
                        pred.right = null;
                        int val = curr.val;
                        curr = curr.right;
                        return val;
                    }
                }
            }
            return -1;
        }
    }
}
