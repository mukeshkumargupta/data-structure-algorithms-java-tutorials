package com.interview.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 10/06/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/path-sum/ done
 * https://leetcode.com/problems/path-sum-ii/ done
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/ Difficult Category  Reference: https://www.youtube.com/watch?v=TO5zsKtc1Ic
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/ done
 * https://leetcode.com/problems/smallest-string-starting-from-leaf/
 * 
 * This is better explanation: https://www.youtube.com/watch?v=MwLDG-WNOjM
 * Reference: https://www.youtube.com/watch?v=Jg4E4KZstFE
 * Derived question: Find max in path, min in path, avg in path, sum in path, print sum if sum equal, or avg equal, all element in path, in case of number make number and return maximum of it etc
 * Category: Must Do, VVImp
 * 
 */
public class PathSum {
    /*
     * Reference: https://leetcode.com/problems/path-sum-ii/
     * Category: Medium
     * Derived question: print all list of path, there is no criteria of sum
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        pathSumUtil(root, sum, result, current);
        return result;
    }

    private void pathSumUtil(TreeNode root, int sum, List<List<Integer>> result, List<Integer> currentPath) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                currentPath.add(root.val);
                result.add(new ArrayList<>(currentPath));
                currentPath.remove(currentPath.size() - 1);
            }
            return;
        }
        currentPath.add(root.val);
        pathSumUtil(root.left, sum - root.val, result, currentPath);
        pathSumUtil(root.right, sum - root.val, result, currentPath);
        currentPath.remove(currentPath.size() - 1);
    }
    
    private void sumNumbersUtil(TreeNode root, int number, int[] sum) {
        if (root == null) {
            return;
        }
        number = number*10 + root.val;
        if (root.left == null && root.right == null) {
            sum[0]+= number; 
            return;
        }
        sumNumbersUtil(root.left, number, sum);
        sumNumbersUtil(root.right, number, sum);
    }
    //Reference: https://leetcode.com/problems/sum-root-to-leaf-numbers
    /*
     * Category: Medium, Must Do
     * Derived, Minum sum , maximum sum, average out of all
     */
    private int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] sum = new int[1];
        sumNumbersUtil(root, 0, sum);
        return sum[0];
 
    }
    /*
     * Reference: https://leetcode.com/problems/path-sum/
     * Category; Easy
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
    //Related: https://leetcode.com/problems/smallest-string-starting-from-leaf/submissions/
    //Related: https://leetcode.com/problems/reverse-only-letters/ Easy
    
    //https://leetcode.com/problems/kth-ancestor-of-a-tree-node/ Hard
    //https://leetcode.com/problems/number-of-ways-to-reconstruct-a-tree/ Hard
    String smallestString = "";//If you want to pass as argumement then you need to take string builder to keep as memory

    void smallestFromLeafUtilMethod1(TreeNode root, String currentString) {//faster than 100% of Java online submissions for Smallest String Starting From Leaf.
        if (root == null) {
            return;
        }
        
        currentString = "" + (char)('a' + root.val) + currentString;

        if (root.left == null && root.right == null) {
            if (smallestString.isEmpty() || currentString.compareTo(smallestString) < 0) {
                smallestString = currentString;
            }
            return;
        }
        smallestFromLeafUtilMethod1(root.left, currentString);
        smallestFromLeafUtilMethod1(root.right, currentString);
    }
    public String smallestFromLeafMethod1(TreeNode root) {
        //runtime 12.13 %, since sting manupulation is used so solow, so use String.valueOf, that is method 2.
        smallestFromLeafUtilMethod1(root, "");
        return smallestString;
        
    }
    
    public String smallestFromLeafMethod2(TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 99.72% of Java online submissions for Smallest String Starting From Leaf.
Memory Usage: 38.9 MB, less than 62.20% of Java online submissions for Smallest String Starting From Leaf.
Note: Verfast because String.valueOf is used
         */
        getSmallestLeafMethod2(root, "");
        return smallestString;
    }
    
    public void getSmallestLeafMethod2(TreeNode root, String currentString) {
        
        if(root == null) return;
        currentString = getChar(root.val)+currentString;
        
        if(root.left == null && root.right == null) {
            if(smallestString.isEmpty() || currentString.compareTo(smallestString) < 0) {
                smallestString = currentString;
            }
            return;
        }
        
        getSmallestLeafMethod2(root.left, currentString);
        getSmallestLeafMethod2(root.right, currentString);
        
    }
    
    public String getChar(int alphabetVal) {
        return String.valueOf((char)(alphabetVal+'a'));
    }
    
    public String smallestFromLeafMethod3(TreeNode root) {
        /*
         * Runtime: 4 ms, faster than 53.74% of Java online submissions for Smallest String Starting From Leaf.
Memory Usage: 41.7 MB, less than 13.68% of Java online submissions for Smallest String Starting From Leaf.
         */
        
        StringBuilder ds = new StringBuilder();
        getSmallestLeafMethod3(root, ds);
        return smallestString;
    }
    
    public void getSmallestLeafMethod3(TreeNode root, StringBuilder ds) {
        
        if(root == null) return;
        
        
        if(root.left == null && root.right == null) {
            ds.append((char)(root.val + 'a'));
            StringBuilder sb = new StringBuilder(ds.toString());
            String revString = sb.reverse().toString();
            if(smallestString.isEmpty() || revString.compareTo(smallestString) < 0) {
                smallestString = revString;
            }
            //System.out.println("leaf-> " + ds.toString());
            ds.deleteCharAt(ds.length()-1);
            return;
        }
        ds.append((char)(root.val + 'a'));
        getSmallestLeafMethod3(root.left, ds);
        getSmallestLeafMethod3(root.right, ds);
        //System.out.println("End-> " + ds.toString());
        ds.deleteCharAt(ds.length()-1);
        
        
    }
    
    /*https://leetcode.com/problems/binary-tree-paths
     * Category: Easy
Related: https://leetcode.com/problems/prefix-and-suffix-search/ Hard
https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/ Medium
https://leetcode.com/problems/verbal-arithmetic-puzzle/ Hard
Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.

 

Example 1:


Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]
Example 2:

Input: root = [1]
Output: ["1"]
     * 
     */
    
    void binaryTreePathsUtil(TreeNode root, List<Integer> path, List<String> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {//leaf node
            path.add(root.val);
            int size = path.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size -1; i++) {
                sb.append(path.get(i));
                sb.append("->");
            }
            sb.append(root.val);
            result.add(sb.toString());
             path.remove(size-1);
            return;
        }
        
        
        path.add(root.val);
        binaryTreePathsUtil(root.left, path, result);
        binaryTreePathsUtil(root.right, path, result);
        path.remove(path.size() -1);
        
        
    }
    public List<String> binaryTreePaths(TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 99.87% of Java online submissions for Binary Tree Paths.
Memory Usage: 39.1 MB, less than 85.86% of Java online submissions for Binary Tree Paths.
TC: O(N)

         */
        List<Integer> path = new ArrayList<>();
        List<String> result  = new ArrayList<>();
        binaryTreePathsUtil(root, path, result);
        return result;
        
    }
}
