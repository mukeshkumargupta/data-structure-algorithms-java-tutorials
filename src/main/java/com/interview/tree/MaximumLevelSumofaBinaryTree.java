package com.interview.tree;

/*
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
 * Category: Medium, VImp, bfs
 * Related:https://leetcode.com/problems/cheapest-flights-within-k-stops/ Medium VVImp
 * https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/ Medium, VVImp, Hint: Not slved bit thinking, find all path and then in each map find max size of palindrom if size of each path equal to max palindrom then include in ans, Application of path and max size of palindrome
 * https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips-ii/ Medium, Locked
 * https://leetcode.com/problems/closest-leaf-in-a-binary-tree/ Medium Locked
 * https://leetcode.com/problems/find-if-path-exists-in-graph/ Easy
 * https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/ Hard, VImp
 */
public class MaximumLevelSumofaBinaryTree {
    public int maxLevelSum(TreeNode root) {
        /*
         * Runtime: 6 ms, faster than 97.46% of Java online submissions for Maximum Level Sum of a Binary Tree.
Memory Usage: 45.5 MB, less than 82.95% of Java online submissions for Maximum Level Sum of a Binary Tree.
         */
        if(root==null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxSum = root.val;
        int level = 1, maxLevel=1;
        while(!queue.isEmpty()){
            int size = queue.size();
            int nodeValSum = 0;
            for(int i=0; i<size; i++){
                TreeNode node = queue.remove();
                nodeValSum += node.val;
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            if(nodeValSum>maxSum){
                maxLevel = Math.max(level, maxLevel);
                maxSum=nodeValSum;
            }
            level++;
        }
        return maxLevel;
        
    }
    public int maxLevelSum(TreeNode root) {
        /*
         * Runtime: 19 ms, faster than 17.67% of Java online submissions for Maximum Level Sum of a Binary Tree.
Memory Usage: 71.7 MB, less than 39.02% of Java online submissions for Maximum Level Sum of a Binary Tree.
         * Find better solution
         */
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int maxSum = root.val;
        int result = 1;
        int level = 1;

        while(!q.isEmpty()) {
            int size = q.size();
            int tempSum = 0;
            boolean childFound = false;
            for (int i = 0; i < size; i++) {
                TreeNode currentTreeNode = q.remove();
 
                if (currentTreeNode.left != null) {
                    q.add(currentTreeNode.left);
                    tempSum += currentTreeNode.left.val;
                    if (!childFound) {
                        childFound = true;
                    }
                }
                
                if (currentTreeNode.right != null) {
                    q.add(currentTreeNode.right);
                    tempSum += currentTreeNode.right.val;
                     if (!childFound) {
                        childFound = true;
                    }
                } 
            }
            level++;
            if (childFound && tempSum > maxSum) {
                maxSum = tempSum;
                result = level;
            }
            

            
        } 

        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
