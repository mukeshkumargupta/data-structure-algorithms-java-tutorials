package com.interview.tree;
//Reference: https://practice.geeksforgeeks.org/problems/determine-if-two-trees-are-identical/1

//Category: Must Do
/*This is a function problem.You only need to complete the function given below*/
/* A Binary Tree node
class Node
{
    int data;
    Node left, right;
    Node(int item)
    {
        data = item;
        left = right = null;
    }
}
*/

public class IdenticalTree {
	/*
	 * Should return true if trees with roots as root1 and root2 are identical else
	 * false
	 */
	boolean isIdentical(Node root1, Node root2) {
		// Your code here
		if (root1 == null && root2 == null) {
			return true;
		}
		// If any of them is null return false
		if (root1 == null || root2 == null) {
			return false;
		}
		if ((root1.data == root2.data) && isIdentical(root1.left, root2.left)
				&& isIdentical(root1.right, root2.right)) {
			return true;
		}
		return false;
	}
}
