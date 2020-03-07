package com.interview.tree;
//Reference: https://practice.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-bst/1
//Category: Must Do
//Not working
public class BSTLCA {
	Node LCAUtil(Node node, int n1, int n2) {
		// Your code here
		if (node == null) {
			return null;
		}
		// If any of data is same as root data then return that node
		if (node.data == n1 || node.data == n2) {
			return node;
		}

		// Try to find cases either in left part or right part
		if (n1 > node.data && n2 > node.data) {
			// Search in right
			LCAUtil(node.right, n1, n2);
		} else if (n1 < node.data && n2 < node.data) {
			LCAUtil(node.left, n1, n2);
		}

		if (node.data > n1 && node.data < n2) {
			return node;
		} else {
			return null;
		}
	}

	Node LCA(Node node, int n1, int n2) {
		if (n1 > n2) {
			return LCAUtil(node, n2, n1);
		} else {
			return LCAUtil(node, n1, n2);
		}
	}
}
