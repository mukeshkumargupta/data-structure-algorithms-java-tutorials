package com.interview.tree;

/*
 * Reference: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * Category: Medium
 * https://www.youtube.com/watch?v=3OXWEdlIGl4&t=7s
 * Related: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/ Medium VImp
 * https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/ Medium VImp
 * https://leetcode.com/problems/build-binary-expression-tree-from-infix-expression/ Hard Locked
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/ Medium Locked
 * https://leetcode.com/problems/operations-on-tree/ Medium Imp
 * Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
Memory Usage: 40.8 MB, less than 62.92% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
         */
        Queue<TreeNode> q = new LinkedList<>();
        
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) return result;
        
        q.add(root);
        boolean isReverse = false;
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> rowList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode currentTreeNode = q.remove();
                rowList.add(currentTreeNode.val);
                if (currentTreeNode.left != null) {
                    q.add(currentTreeNode.left);
                }
                if (currentTreeNode.right != null) {
                    q.add(currentTreeNode.right);
                }
                
            }
            if (!isReverse) {
                result.add(rowList);
                isReverse = !isReverse;
                //System.out.println(isReverse);
            } else {
                //Revese list
                //Collections.reverse(rowList);
                List<Integer> reverseList = new ArrayList<>();
                int rowlistSize = rowList.size();
                for (int i = rowlistSize-1; i >= 0; i--) {
                    reverseList.add(rowList.get(i));
                }
                result.add(reverseList);
                isReverse = !isReverse; 
                //System.out.println("Reverse List " + isReverse);
            }
            
        }
   
        return result;
        
    }
}
