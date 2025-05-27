package com.interview.tree.PartBPathProblems;

import com.interview.tree.TreeNode;

//Derived of question sum path:
//Reference: Reference: https://www.youtube.com/watch?v=Gi0202QawRQ&list=PLIA-9QRQ0RqG6CNfSJSzT0h5Pc_HvwZG5&index=112&t=0s
public class A_C_FindMaxNumberInAllPath {
    
    long ans =0;
    void dfs(TreeNode root, long val)
    {
        if(root == null)
            return;
        
        val = 10*val + root.val;

        //Leaf TreeNode
        if(root.left == null && root.right == null)
        {
            if (ans < val) {
                //Here logic will change
                ans = val;
            }
            return;
        }
        dfs(root.left,val);
        dfs(root.right,val);
    }
public long maximumnSumOfPath(TreeNode root) {
        if(root == null)
            return 0;
        
        dfs(root,0);
        return ans;
    }

}
