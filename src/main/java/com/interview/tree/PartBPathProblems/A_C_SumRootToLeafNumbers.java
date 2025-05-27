package com.interview.tree.PartBPathProblems;

import com.interview.tree.TreeNode;

//Reference: https://www.youtube.com/watch?v=Gi0202QawRQ&list=PLIA-9QRQ0RqG6CNfSJSzT0h5Pc_HvwZG5&index=112&t=0s
//Derived question: find max number in all path(done) during sum make one variable maximum and keep update it. also find min number in all path(almost same as max sum path).
//Return path which has minimum path sum of all TreeNode in path(done), print all path(done), Root to leaf path sum equal to a given number(done) reference: https://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/,
//,Find all duplicate path, find unique path in tree if TreeNode is represented by character like A, B etc, check tree is sum tree where each TreeNode is sum of all its children sum Reference: http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree/,
//find all parent TreeNode which are parent of any child, find all cousin TreeNode, find all TreeNodes which parent are same, 
//find all root-to-leaf paths where each path's sum equals the given sum, 
public class A_C_SumRootToLeafNumbers {
    void dfs(TreeNode root, long val, long[] ans)
    {
        if(root == null)
            return;
        
        val = 10*val + root.val;
        //Here logic will change

        //Leaf TreeNode
        if(root.left == null && root.right == null)
        {
            ans[0]+=val; //Here logic will change
            return;
        }
        dfs(root.left,val,ans);
        dfs(root.right,val, ans);
    }
public long sumNumbers(TreeNode root) {
        if(root == null)
            return 0;
        
        long[] ans = new long[1];
        dfs(root,0, ans);
        return ans[0];
    }

}
