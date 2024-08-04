package com.interview.tree;

/**
 * http://www.careercup.com/question?id=5344154741637120
 */
public class SinkNegativeToBottom {

    public void sinkZero(TreeNode root) {
        if (root == null) {
            return;
        }

        sinkZero(root.left);
        sinkZero(root.right);

        if (root.val < 0) {
            pushDown(root);
        }
    }

    private void pushDown(TreeNode root) {
        if(root == null){
            return;
        }
        // find a child with non zero TreeNode value
        if (root.left == null && root.right == null) {
            // already leaf TreeNode. nothing to do. just return
            return;
        }

        //if root left is not null and root left val is not 0 pick it to swap
        if (root.left != null && root.left.val >= 0) {
            int temp = root.val;
            root.val = root.left.val;
            root.left.val = temp;
            pushDown(root.left);
        }
        else if(root.right != null && root.right.val >= 0){
            int temp = root.val;
            root.val = root.right.val;
            root.right.val = temp;
            pushDown(root.right);
        }
    }
    
    public static void main(String args[]){
        int preorder[] = {-1,1,6,-2,11,3,2,-3,31,22,17};
        int inorder[]  = {-2,6,11,1,3,-1,31,-3,22,2,17};
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        TreeNode root = ctf.createTree(inorder, preorder);
        SinkNegativeToBottom szb = new SinkNegativeToBottom();
        szb.sinkZero(root);
        BinaryTreeLevelOrderTraversal lot = new BinaryTreeLevelOrderTraversal();
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        lot.levelOrder(root);
    }
}
