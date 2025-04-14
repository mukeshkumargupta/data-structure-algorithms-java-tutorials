package com.interview.tree.PartCATreeProperties;

/*
 * Category: https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * Category: Easy, Must Do, Top150
 * https://www.youtube.com/watch?v=eD3tmO66aBA&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=15
 * Related: https://leetcode.com/problems/time-needed-to-inform-all-employees/ Medium Imp
 * https://leetcode.com/problems/smallest-common-region/ Medium Locked
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/ Medium Locked, VVImp
 * https://leetcode.com/problems/operations-on-tree/ Medium
 */


public class HeightOfTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class Approach1SubTreeApproach {

    }
    public int maxDepth(TreeNode root) {
        /*
         * Using post order
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of Binary Tree.
Memory Usage: 43.6 MB, less than 9.37% of Java online submissions for Maximum Depth of Binary Tree.
         */
        if (root == null) { 
            return 0;
        }
        
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) +1;
    }

    private static class Approach2DFSApproach {
        /*
         * Other method using dfs
         */
        public void maxDepthUtil(TreeNode root, int height, int[] depth) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of Binary Tree.
Memory Usage: 43.8 MB, less than 6.89% of Java online submissions for Maximum Depth of Binary Tree.
         */
            if (root == null) return;

            if (height > depth[0]) {
                depth[0] = height;
            }

            maxDepthUtil(root.left, height + 1, depth);
            maxDepthUtil(root.right, height + 1, depth);

        }
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int[] depth = new int[1];
            maxDepthUtil(root, 1, depth);
            return depth[0];
        }
    }
    

    
    //other method
    //similar to narray tree height
    static class Approach3NArrayTreeApproach {
        public int dfs(TreeNode root) {
            if (root == null) return 0;
            
            int result = 1;//for root
            int max = 0;
            //Iterate all its adjucent
            if (root.left != null) {
                max = Math.max(max, dfs(root.left));
            }
            
            if (root.right != null) {
                max = Math.max(max, dfs(root.right));
            }
            return result + max;
            
        }
        public int maxDepth(TreeNode root) {
            return dfs(root);
        }
    }

}
