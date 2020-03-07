package com.interview.tree;

//Reference: https://practice.geeksforgeeks.org/problems/count-leaves-in-binary-tree/1
//Category: Must Do
//Note: Print All Leaves
public class CountLeaves {
	void countLeavesUtil(Node root, int[] result) {
		// Your code
		if (root == null) {
			return;
		}
		// Check leave node
		if (root.left == null && root.right == null) {
			result[0] = result[0] + 1;
		}
		countLeavesUtil(root.left, result);
		countLeavesUtil(root.right, result);
	}

	int countLeaves(Node node) {
		// Your code
		int[] result = new int[1];
		countLeavesUtil(node, result);
		return result[0];
	}
}
