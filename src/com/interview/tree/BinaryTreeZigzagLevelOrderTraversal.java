package com.interview.tree;

/*
 * Reference: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * Category: Medium
 * Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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
                Collections.reverse(rowList);
                result.add(rowList);
                isReverse = !isReverse; 
                //System.out.println("Reverse List " + isReverse);
            }
            
        }
   
        return result;
        
    }
}
