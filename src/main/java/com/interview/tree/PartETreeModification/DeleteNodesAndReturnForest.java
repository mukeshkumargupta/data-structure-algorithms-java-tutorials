package com.interview.tree.PartETreeModification;

import com.interview.tree.TreeNode;

/*
 * 
 * https://leetcode.com/problems/delete-nodes-and-return-forest/submissions/
 * Category: Medium, Tricky, Similar to delete node in bst
 * Related: https://leetcode.com/problems/count-nodes-with-the-highest-score/ Medium
 * Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.

 

Example 1:


Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
Example 2:

Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]
 

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */
public class DeleteNodesAndReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        /*
         * Post order traversal
         * Runtime: 2 ms, faster than 80.88% of Java online submissions for Delete Nodes And Return Forest.
Memory Usage: 48.8 MB, less than 6.09% of Java online submissions for Delete Nodes And Return Forest.
         */
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i : to_delete) {
            set.add(i);
        }
        delNodesUtils(root, set, res);
        if(!set.contains(root.val)) {//Don't forget to add root!
            res.add(root);
        }
        return res;
    }
    
    private TreeNode delNodesUtils(TreeNode root, Set<Integer> set, List<TreeNode> res) {
        if(root == null) {
            return root;
        }
        
        root.left = delNodesUtils(root.left, set, res);//deal with the left subtree
        root.right = delNodesUtils(root.right, set, res);//deal with the right subtree
        
        if(set.contains(root.val)) {//when current root need to be deleted, non-null children will become newhead
            if(root.left != null) {
                res.add(root.left);
            }
            if(root.right != null) {
                res.add(root.right);
            }
            return null;//delete current root at the end
        } 
        return root;
    }   
    
    /*
     * Using dfs
     * 
     */
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        /*
         * Runtime: 3 ms, faster than 54.25% of Java online submissions for Delete Nodes And Return Forest.
Memory Usage: 47.9 MB, less than 29.94% of Java online submissions for Delete Nodes And Return Forest.
         */
        List<TreeNode> rst = new ArrayList<>();
        if (root == null) {
            return rst;
        }
        
        Set<Integer> deletes = new HashSet<>();
        for (int d : to_delete) {
            deletes.add(d);
        }
        
        dfs(root, true, deletes, rst);
        return rst;
    }
    
    private boolean dfs(TreeNode root, boolean isARoot,
                     Set<Integer> deletes, List<TreeNode> rst) {
        if (root == null) {
            return false;
        }
        
        boolean deleteRoot = deletes.contains(root.val);
        if (!deleteRoot) {
            if (isARoot) {
                rst.add(root);
            }
        } 
        
        boolean leftDeleted = dfs(root.left, deleteRoot, deletes, rst);
        boolean rightDeleted = dfs(root.right, deleteRoot, deletes, rst);
        
        if (leftDeleted) {
            root.left = null;
        }
        if (rightDeleted) {
            root.right = null;
        }
        return deleteRoot;
    } 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
