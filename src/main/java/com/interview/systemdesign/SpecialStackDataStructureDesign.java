package com.interview.systemdesign;

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 * Reference: 1 https://www.youtube.com/watch?v=gd9xEAnxXzc
 * Reference: 2 Order of 1 time and Space https://www.youtube.com/watch?v=QMlDCR9xyd8&t=156s
 * Reference: https://www.geeksforgeeks.org/design-and-implement-special-stack-val-structure/?ref=rp
 * https://leetcode.com/problems/min-stack/
 * Category: Easy
 * Company: Google, Amazon, Facebook
 */

public class SpecialStackvalStructureDesign {
    class TreeNode {
        int value;
        int minValue;
        TreeNode next;
        TreeNode( int value, int minValue) {
            this.value = value;
            this.minValue = minValue;
            this.next = null;
            //System.out.println("val: " + value + " minVal: " + minValue);
        }
    }
    private TreeNode root;


    

    /** initialize your val structure here. */
    public SpecialStackvalStructureDesign() {
        root = null;

    }
    
    public void push(int val) {
        if (root == null) {
           root = new TreeNode(val , val);
            
        } else {
            int minValue = root.minValue < val ? root.minValue : val;
            TreeNode newTreeNode = new TreeNode(val , minValue);
            newTreeNode.next = root;
            root = newTreeNode;
            
        }

    }
    
    public void pop() {
        //Remove element from linklist
        if (root != null) {
            root = root.next;  
        }
    }
    
    public int top() {
        return root.value;
        
    }
    
    public int getMin() {
        return root.minValue;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
