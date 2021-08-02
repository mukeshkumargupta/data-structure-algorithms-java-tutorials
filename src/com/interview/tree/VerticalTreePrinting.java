package com.interview.tree;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;

/**
 * http://www.geeksforgeeks.org/print-binary-tree-vertical-order-set-2/
 */
public class VerticalTreePrinting {

    public void printVertical(TreeNode root){
        Map<Integer,List<TreeNode>> map = new TreeMap<Integer,List<TreeNode>>();
        populateMap(root,map,0);
        printLevel(map);
    }
    
    private void printLevel(Map<Integer,List<TreeNode>> map){
        for(Integer key : map.keySet()){
            List<TreeNode> listTreeNodes = map.get(key);
            for(TreeNode n : listTreeNodes){
                System.out.print(n.val + " ");
            }
            System.out.println();
        }
    }
    
    private void populateMap(TreeNode root, Map<Integer,List<TreeNode>> map,int level){
        if(root == null){
            return;
        }
        List<TreeNode> listTreeNodes = null;
        if(map.containsKey(level)){
            listTreeNodes = map.get(level);
        }else{
            listTreeNodes = new ArrayList<TreeNode>();
            map.put(level, listTreeNodes);
        }
        listTreeNodes.add(root);
        populateMap(root.left,map,level-1);
        populateMap(root.right,map,level+1);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode head = null;
        head = bt.addTreeNode(3, head);
        head = bt.addTreeNode(-6, head);
        head = bt.addTreeNode(-7, head);
        head = bt.addTreeNode(2, head);
        head = bt.addTreeNode(9, head);
        head = bt.addTreeNode(6, head);
        head = bt.addTreeNode(11, head);
        head = bt.addTreeNode(13, head);
        head = bt.addTreeNode(12, head);
        VerticalTreePrinting vtp = new VerticalTreePrinting();
        vtp.printVertical(head);
    }
}
