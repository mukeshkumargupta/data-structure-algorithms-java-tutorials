package com.interview.tree.PartBPathProblems;

import com.interview.tree.TreeNode;
import com.interview.tree.PartATreeTraversal.TreeTraversals;
import com.interview.tree.PartFTreeConstruction.ConstructTreeFromInOrderPreOrder;

//Derived of question sum path:
//Reference: Reference: https://www.youtube.com/watch?v=Gi0202QawRQ&list=PLIA-9QRQ0RqG6CNfSJSzT0h5Pc_HvwZG5&index=112&t=0s
public class FindMaxNumberInAllPath {
    
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
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int inorder[] = {4,2,5,1,6,3};
        int preorder[] = {1,2,4,5,3,6};
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        TreeNode root = ctf.createTree(inorder, preorder);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        System.out.println();
        tt.preOrder(root);
        System.out.println();
        FindMaxNumberInAllPath maxsum = new FindMaxNumberInAllPath();
        System.out.println(maxsum.maximumnSumOfPath(root));//385
        
    }
}
