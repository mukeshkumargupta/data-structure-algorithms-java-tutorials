package com.interview.tree;

/**
 * Date 07/07/2017 
 * @author Mukesh Kumar Gupta
 * 
 * Youtube link - https://youtu.be/bmaeYtlO2OE
 * Youtube link - https://youtu.be/_SiwrPXG9-g
 * Youtube link - https://youtu.be/NA8B84DZYSA
 * Category: Easy, Must Do
 */
class TreeNodeRef{
    TreeNode TreeNode;
}

enum Color{
    RED,
    BLACK
}

class TreeNode{
    TreeNode left;
    TreeNode right;
    TreeNode next;
    int val;
    int lis;
    int height;
    int size;
    Color color;
    
    public static TreeNode newTreeNode(int val){
        TreeNode n = new TreeNode();
        n.left = null;
        n.right = null;
        n.val = val;
        n.lis = -1;
        n.height = 1;
        n.size = 1;
        n.color = Color.RED;
        return n;
    }
    
     TreeNode(int val) {
         this.val = val;
    }
}

public class BinaryTree {
    public TreeNode addTreeNode(int val, TreeNode head){
        TreeNode tempHead = head;
        TreeNode n = TreeNode.newTreeNode(val);
        if(head == null){
            head = n;
            return head;
        }
        TreeNode prev = null;
        //Find accurate place where you need to insert new TreeNode
        //Always return root TreeNode.
        while(head != null){
            prev = head;
            if(head.val < val){
                head = head.right;
            }else{
                head = head.left;
            }
        }
        if(prev.val < val){
            prev.right = n;
        }else{
            prev.left = n;
        }
        return tempHead;
    }
    
    class IntegerRef{
        int height;
    }
    
    public int height(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftHeight  = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode head = null;
        head = bt.addTreeNode(10, head);
        head = bt.addTreeNode(15, head);
        head = bt.addTreeNode(5, head);
        head = bt.addTreeNode(7, head);
        head = bt.addTreeNode(19, head);
        head = bt.addTreeNode(20, head);
        head = bt.addTreeNode(-1, head);
        head = bt.addTreeNode(21, head);
        System.out.println(bt.height(head));
        
    }
}
