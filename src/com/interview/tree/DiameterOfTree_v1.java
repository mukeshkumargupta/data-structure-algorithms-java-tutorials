package com.interview.tree;

public class DiameterOfTree_v1 {
	private int height(Node root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = height(root.left);
		int rightHeight = height(root.right);
		return (Math.max(leftHeight, rightHeight) + 1);

	}

	private int diameter(Node root) {

		if (root == null) {
			return 0;
		}

		int dial = diameter(root.left);
		int diar = diameter(root.right);
		return Math.max(Math.max(dial, diar), height(root));
	}

	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();
		Node head = null;
		head = bt.addNode(10, head);
		head = bt.addNode(15, head);
		head = bt.addNode(5, head);
		head = bt.addNode(7, head);
		head = bt.addNode(19, head);
		head = bt.addNode(20, head);
		head = bt.addNode(-1, head);
		head = bt.addNode(21, head);
		head = bt.addNode(11, head);
		head = bt.addNode(12, head);
		head = bt.addNode(13, head);
		head = bt.addNode(14, head);
		DiameterOfTree_v1 dt = new DiameterOfTree_v1();
		System.out.println(dt.diameter(head));
	}

}
