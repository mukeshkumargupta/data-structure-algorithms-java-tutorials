package com.interview.tree;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class BoundaryTraversal_v1 {
	//Map<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
	//hashMap.
	
	public void traversal(Node head) {
		
	}

    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        /*head = bt.addNode(100, head);
        head = bt.addNode(90, head);
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(25, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(-7, head);*/
        //Other test cases taken from geeksforgeeks website
        //By looking this existing code, output is like clock wise starting from first right child
        //But in geeks for geeksforgeeks, it is starting anti clock wise including root.
        head = bt.addNode(20, head);
        head = bt.addNode(8, head);
        head = bt.addNode(4, head);
        head = bt.addNode(12, head);
        head = bt.addNode(10, head);
        head = bt.addNode(14, head);
        head = bt.addNode(22, head);
        head = bt.addNode(25, head);
        BoundaryTraversal_v1 bd = new BoundaryTraversal_v1();
        bd.traversal(head);
    }
}
