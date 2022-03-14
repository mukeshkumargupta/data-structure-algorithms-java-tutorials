package com.interview.tree;

public class PathSumIII {
    
    /*
     * 
     * https://www.youtube.com/watch?v=yyZA4v0x16w
     * https://leetcode.com/problems/path-sum-iii/submissions/
     * Category: Medium, Must Do
     * Related: https://leetcode.com/problems/path-sum-iv/ 
     * https://leetcode.com/problems/longest-univalue-path/ Medium
     * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

 

Example 1:


Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.
Example 2:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3
 

Constraints:

The number of nodes in the tree is in the range [0, 1000].
-109 <= Node.val <= 109
-1000 <= targetSum <= 1000
     */
    int totalCount = 0;
    
    public void findPathSum(TreeNode root, int targetSum, int currentSum, Map<Integer, Integer> lookup ) {
        //O(N)
        if (root == null) {
            return;
        }
        currentSum += root.val;
        if (lookup.containsKey(currentSum - targetSum)) {
            totalCount += lookup.get(currentSum - targetSum);
        }
        
        if (currentSum == targetSum) {
            totalCount +=1;
        }
        lookup.put(currentSum, lookup.getOrDefault(currentSum, 0) + 1);
        //System.out.println(currentSum);

        findPathSum(root.left, targetSum, currentSum, lookup);
        findPathSum(root.right, targetSum, currentSum, lookup);
        lookup.put(currentSum, lookup.get(currentSum)-1);
        
        
    }
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        
        Map<Integer, Integer> lookup = new HashMap<>();
        findPathSum(root, targetSum, 0, lookup);
        return totalCount;
        
        
    }
    public void findPathSumSlow(TreeNode root, int targetSum, int currentSum) {
        /*
         * Runtime: 23 ms, faster than 20.25% of Java online submissions for Path Sum III.
Memory Usage: 38.9 MB, less than 72.28% of Java online submissions for Path Sum III.
            TC: O(N2)
         */
        //Bruitforce think like array
        if (root == null) {
            return;
        }
        currentSum += root.val;
        //System.out.println(currentSum);
        if (currentSum == targetSum) {
            totalCount += 1;
        }
        findPathSum(root.left, targetSum, currentSum);
        findPathSum(root.right, targetSum, currentSum);
        
        
    }
    public int pathSum(TreeNode root, int targetSum) {
        //Bruitforce think like array
        if (root == null) {
            return 0;
        }
        
        findPathSum(root, targetSum, 0);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return totalCount;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
